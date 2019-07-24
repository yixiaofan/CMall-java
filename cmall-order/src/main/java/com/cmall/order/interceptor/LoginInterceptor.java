package com.cmall.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.utils.CookieUtils;
import com.cmall.commons.utils.HttpClientUtil;
import com.cmall.commons.utils.JsonUtils;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception {
	// TODO Auto-generated method stub
	HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
	// TODO Auto-generated method stub
	HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	if(token!=null&&!token.equals("")){
	    String json=HttpClientUtil.doPost("http://47.100.242.105:8084/user/token/"+token);
	    CmallResult er=JsonUtils.jsonToPojo(json, CmallResult.class);
	    if(er.getStatus()==200){
		return true;
	    }
	}
	return false;
    }

}
