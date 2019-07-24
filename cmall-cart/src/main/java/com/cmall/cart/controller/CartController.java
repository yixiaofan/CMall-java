package com.cmall.cart.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmall.cart.service.CartService;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.TbItemChild;

@Controller
public class CartController {
    @Resource
    private CartService cartServiceImpl;
    /*
     * 添加购物车
     */
    @RequestMapping("cart/add/{id}.html")
    @ResponseBody
    public String addCart(@PathVariable long id,int num,HttpServletRequest request){
	cartServiceImpl.addCart(id, num, request);
	return "ok";
    }
    
    /*
     * 显示购物车
     */
    @RequestMapping("cart/cart")
    @ResponseBody
    public List<TbItemChild> showCart(HttpServletRequest request){
	return cartServiceImpl.showCart(request);
    }
    /*
     * 修改商品数量
     */
    @RequestMapping("cart/update/num/{id}/{num}")
    @ResponseBody
    public CmallResult udpdate(@PathVariable long id,@PathVariable int num,HttpServletRequest request){
	return cartServiceImpl.update(id, num, request);
    }
    /*
     * 删除购物车商品
     */
    @RequestMapping("cart/delete/{id}")
    @ResponseBody
    public CmallResult delete(@PathVariable long id,HttpServletRequest req){
	return cartServiceImpl.delete(id, req);
    }
}
