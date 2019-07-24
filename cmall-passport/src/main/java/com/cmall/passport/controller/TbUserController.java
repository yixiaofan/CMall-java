package com.cmall.passport.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmall.passport.service.TbUserService;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbUser;

@Controller
public class TbUserController {
    @Resource
    private TbUserService tbUserServiceImpl;
    
    /*
     * 登录
     */
    @RequestMapping("user/login")
    @ResponseBody
    public CmallResult login(TbUser user,HttpServletRequest request,HttpServletResponse response){
	try {
	    user.setUsername(new String(user.getUsername().getBytes("ISO8859-1"), "utf-8"));
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	return tbUserServiceImpl.login(user,request,response);
    }
    
    /*
     * 通过token获取用户信息
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public Object getUserInfo(@PathVariable String token){
	return tbUserServiceImpl.getUserInfoByToken(token);
    }
    
    /*
     * 退出
     */
    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token,HttpServletRequest request,HttpServletResponse response){
	return tbUserServiceImpl.logout(token,request,response);
    }
    
    /*
     * 修改用户信息
     */
    @RequestMapping("user/update/{token}")
    @ResponseBody
    public Object update(@PathVariable String token,String username,String phone,String email){
	return tbUserServiceImpl.updUserInfo(token, username, phone, email);
    }
    
    /*
     * 注册
     */
    @RequestMapping("user/register")
    @ResponseBody
    public Object register(@RequestBody TbUser user){
	return tbUserServiceImpl.insUserInfo(user);
    }
}
