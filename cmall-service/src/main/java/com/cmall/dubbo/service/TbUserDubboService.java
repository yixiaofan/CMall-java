package com.cmall.dubbo.service;

import com.cmall.pojo.TbUser;

public interface TbUserDubboService {
    /*
     * 根据用户名和密码查询登录
     */
    TbUser selByUser(TbUser user);
    
    /*
     * 根据用户id修改用户信息
     */
    int updUserInfo(TbUser user);
    
    /*
     * 注册
     */
    int insUserInfo(TbUser user);
}
