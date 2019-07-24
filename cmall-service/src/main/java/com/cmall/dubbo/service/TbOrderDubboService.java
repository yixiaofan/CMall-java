package com.cmall.dubbo.service;

import java.util.List;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.pojo.TbOrder;
import com.cmall.pojo.TbOrderItem;
import com.cmall.pojo.TbOrderShipping;

public interface TbOrderDubboService {
    /*
     * 创建订单
     */
    int insOrder(TbOrder order,List<TbOrderItem> list,TbOrderShipping shipping) throws Exception;
    
    /*
     * 订单分页查询
     */
    EasyUIDataGrid showOrder(int page,int rows,Long id);
    
    /*
     * 订单商品查询
     */
    List<TbOrderItem> showOrderItem(String orderid);
    
    /*
     * 更新订单评论
     */
    int updOrderRate(String orderId);
}
