package com.cmall.cart.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.TbItemChild;

public interface CartService {
    /*
     * 加入购物车
     */
    void addCart(long id,int num,HttpServletRequest request);
    /*
     * 显示购物车
     */
    List<TbItemChild> showCart(HttpServletRequest request);
    /*
     * 根据id修改数量
     */
    CmallResult update(long id,int num,HttpServletRequest request);
    /*
     * 删除购物车商品
     */
    CmallResult delete(long id,HttpServletRequest req);
}
