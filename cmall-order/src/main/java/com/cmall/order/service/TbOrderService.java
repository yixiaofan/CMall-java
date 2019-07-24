package com.cmall.order.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cmall.order.pojo.MyOrderParam;
import com.cmall.pojo.TbOrderItem;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.pojo.TbItemChild;

public interface TbOrderService {
    /*
     * 确认订单信息包含的商品
     */
    List<TbItemChild> showOrderCart(List<Long> ids,HttpServletRequest request);
    /*
     * 创建订单
     */
    CmallResult create(MyOrderParam param, HttpServletRequest request);
    /*
     * 分页显示用户订单列表
     */
    EasyUIDataGrid showOrder(int page, int rows, HttpServletRequest request);
    /*
     * 根据订单号查询订单商品
     */
    List<TbOrderItem> showOrderItem(String orderid);
}
