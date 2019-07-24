package com.cmall.cart.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.cart.service.CartService;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.TbItemChild;
import com.cmall.commons.utils.CookieUtils;
import com.cmall.commons.utils.HttpClientUtil;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.dubbo.service.TbItemDubboService;
import com.cmall.pojo.TbItem;
import com.cmall.redis.dao.JedisDao;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private JedisDao jedisDaoImpl;
    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;
    @Value("${passport.url}")
    private String passportUrl;
    @Value("${cart.key}")
    private String cartKey;
    @Override
    public void addCart(long id, int num,HttpServletRequest request) {
	//集合中存放所有购物车商品
	List<TbItemChild> list=new ArrayList<>();
	//redis中的key
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	String jsonUser=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(jsonUser, CmallResult.class);
	String key=cartKey+((LinkedHashMap)er.getData()).get("username");
	
	//如果redis存在key
	if(jedisDaoImpl.exists(key)){
	    String json=jedisDaoImpl.get(key);
	    if(json!=null&&!json.equals("")){
		list=JsonUtils.jsonToList(json, TbItemChild.class);
		for(TbItemChild tbItemChild:list){
		    if((long)tbItemChild.getId()==id){
			//购物车中存在该商品
			tbItemChild.setNum(tbItemChild.getNum()+num);
			jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
			return ;
		    }
		}		
	    }
	}
	TbItem item=tbItemDubboServiceImpl.selById(id);
	TbItemChild child=new TbItemChild();
	child.setId(item.getId());
	child.setTitle(item.getTitle());
	child.setImages(item.getImage()==null||item.getImage().equals("")?new String[1]:item.getImage().split(","));
	child.setNum(num);
	child.setPrice(item.getPrice());
	list.add(child);
	jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
    }
    @Override
    public List<TbItemChild> showCart(HttpServletRequest request) {
	//redis中的key
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	String jsonUser=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(jsonUser, CmallResult.class);
	String key=cartKey+((LinkedHashMap)er.getData()).get("username");
	List<TbItemChild> result=null;
	if(jedisDaoImpl.exists(key)){
	    String json=jedisDaoImpl.get(key);
	    result=JsonUtils.jsonToList(json, TbItemChild.class);
	}
	return result;
    }
    @Override
    public CmallResult update(long id, int num,HttpServletRequest request) {
	//redis中的key
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	String jsonUser=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(jsonUser, CmallResult.class);
	String key=cartKey+((LinkedHashMap)er.getData()).get("username");
	
	String json=jedisDaoImpl.get(key);
	List<TbItemChild> list=JsonUtils.jsonToList(json, TbItemChild.class);
	for(TbItemChild child:list){
	    if((long)child.getId()==id){
		child.setNum(num);
	    }
	}
	String ok=jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
	CmallResult cmallResult=new CmallResult();
	if(ok.equals("OK")){
	    cmallResult.setStatus(200);
	}
	return cmallResult;
    }
    @Override
    public CmallResult delete(long id, HttpServletRequest req) {
	//redis中的key
	String token=CookieUtils.getCookieValue(req, "TT_TOKEN");
	String jsonUser=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(jsonUser, CmallResult.class);
	String key=cartKey+((LinkedHashMap)er.getData()).get("username");
	
	String json=jedisDaoImpl.get(key);
	List<TbItemChild> list=JsonUtils.jsonToList(json, TbItemChild.class);
	TbItemChild tbItemChild=null;
	for(TbItemChild child:list){
	    if((long)child.getId()==id){
		tbItemChild=child;
	    }
	}
	list.remove(tbItemChild);
	String ok=jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
	CmallResult cmallResult=new CmallResult();
	if(ok.equals("OK")){
	    cmallResult.setStatus(200);
	}
	return cmallResult;
    }

}
