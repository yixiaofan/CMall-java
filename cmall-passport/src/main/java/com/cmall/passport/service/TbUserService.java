package com.cmall.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmall.commons.pojo.CmallResult;
import com.cmall.pojo.TbUser;

public interface TbUserService {
    /*
     * 登录
     */
    CmallResult login(TbUser user,HttpServletRequest request,HttpServletResponse response);
    /*
     * 根据token查询用户信息
     */
    CmallResult getUserInfoByToken(String token);
    /*
     * 退出
     */
    CmallResult logout(String token,HttpServletRequest request,HttpServletResponse response);
    /*
     * 修改用户信息
     */
    CmallResult updUserInfo(String token,String username,String phone,String email);
    /*
     * 注册
     */
    CmallResult insUserInfo(TbUser user);
}
