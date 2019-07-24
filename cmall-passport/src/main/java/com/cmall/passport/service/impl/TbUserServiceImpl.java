package com.cmall.passport.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.passport.service.TbUserService;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.utils.CookieUtils;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.dubbo.service.TbUserDubboService;
import com.cmall.pojo.TbUser;
import com.cmall.redis.dao.JedisDao;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Reference
    private TbUserDubboService tbUserDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;
    @Override
    public CmallResult login(TbUser user,HttpServletRequest request,HttpServletResponse response) {
	CmallResult er=new CmallResult();
	TbUser userSelect=tbUserDubboServiceImpl.selByUser(user);
	if(userSelect!=null){
	    er.setStatus(200);
	    //当用户登录成功后把用户信息放入到redis中
	    String key=UUID.randomUUID().toString();
	    jedisDaoImpl.set(key, JsonUtils.objectToJson(userSelect));
	    jedisDaoImpl.expire(key, 60*60*24*7);
	    //产生Cookie
	    //CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60*60*24*7);
	    
	    userSelect.setPassword(key);
	    er.setData(userSelect);
	}else{
	    er.setMsg("用户名或密码错误");
	}
	return er;
    }
    @Override
    public CmallResult getUserInfoByToken(String token) {
	CmallResult er=new CmallResult();
	String json=jedisDaoImpl.get(token);
	if(json!=null&&!json.equals("")){
	    TbUser tbUser=JsonUtils.jsonToPojo(json, TbUser.class);
	    //可以把密码清空
	    tbUser.setPassword(null);
	    er.setStatus(200);
	    er.setMsg("OK");
	    er.setData(tbUser);
	}else{
	    er.setMsg("获取失败");
	}
	return er;
    }
    @Override
    public CmallResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
	jedisDaoImpl.del(token);
	//CookieUtils.deleteCookie(request, response, "TT_TOKEN");
	CmallResult er=new CmallResult();
	er.setStatus(200);
	er.setMsg("OK");
	return er;
    }
    @Override
    public CmallResult updUserInfo(String token,String username,String phone,String email) {
	CmallResult er=new CmallResult();
	String json=jedisDaoImpl.get(token);
	if(json!=null&&!json.equals("")){
	    TbUser tbUser=JsonUtils.jsonToPojo(json, TbUser.class);
	    tbUser.setEmail(email);
	    tbUser.setPhone(phone);
	    tbUser.setUsername(username);
	    Date date=new Date();
	    tbUser.setUpdated(date);
	    tbUserDubboServiceImpl.updUserInfo(tbUser);
	    jedisDaoImpl.set(token, JsonUtils.objectToJson(tbUser));
	    er.setStatus(200);
	    er.setMsg("OK");
	}else{
	    er.setMsg("获取失败");
	}
	return er;
    }
    @Override
    public CmallResult insUserInfo(TbUser user) {
	CmallResult er=new CmallResult();
	Date date=new Date();
	user.setCreated(date);
	user.setUpdated(date);
	int count=tbUserDubboServiceImpl.insUserInfo(user);
	if(count==1){
	    er.setStatus(200);
	    er.setMsg("OK");
	}else{
	    er.setMsg("获取失败");
	}
	return er;
    }

}
