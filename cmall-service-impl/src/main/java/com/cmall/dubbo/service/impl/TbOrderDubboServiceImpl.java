package com.cmall.dubbo.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.cmall.mapper.TbOrderItemMapper;
import com.cmall.mapper.TbOrderMapper;
import com.cmall.mapper.TbOrderShippingMapper;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.dubbo.service.TbOrderDubboService;
import com.cmall.pojo.TbOrder;
import com.cmall.pojo.TbOrderExample;
import com.cmall.pojo.TbOrderItem;
import com.cmall.pojo.TbOrderItemExample;
import com.cmall.pojo.TbOrderShipping;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TbOrderDubboServiceImpl implements TbOrderDubboService {
    @Resource
    private TbOrderMapper tbOrderMapper;
    @Resource
    private TbOrderItemMapper tbOrderItemMapper;
    @Resource
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Override
    public int insOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping shipping) throws Exception {
	int index=tbOrderMapper.insertSelective(order);
	for(TbOrderItem tbOrderItem:list){
	    index+=tbOrderItemMapper.insertSelective(tbOrderItem);
	}
	index+=tbOrderShippingMapper.insertSelective(shipping);
	if(index==2+list.size()){
	    return 1;
	}else{
	    throw new Exception("创建订单失败");
	}
    }
    @Override
    public EasyUIDataGrid showOrder(int page, int rows, Long id) {
	PageHelper.startPage(page,rows);
	//根据用户id查询到订单
	TbOrderExample example=new TbOrderExample();
	example.createCriteria().andUserIdEqualTo(id);
	example.setOrderByClause("update_time desc");
	List<TbOrder> orders=tbOrderMapper.selectByExample(example);
	//分页代码
	PageInfo<TbOrder> pi=new PageInfo<>(orders);
	//放入实体类
	EasyUIDataGrid datagrid=new EasyUIDataGrid();
	datagrid.setRows(pi.getList());
	datagrid.setTotal(pi.getTotal());
	return datagrid;
    }
    @Override
    public List<TbOrderItem> showOrderItem(String orderid) {
	TbOrderItemExample example=new TbOrderItemExample();
	example.createCriteria().andOrderIdEqualTo(orderid);
	return tbOrderItemMapper.selectByExample(example);
    }
    @Override
    public int updOrderRate(String orderId) {
	TbOrder order=new TbOrder();
	order.setBuyerRate(1);
	TbOrderExample example=new TbOrderExample();
	example.createCriteria().andOrderIdEqualTo(orderId);
	return tbOrderMapper.updateByExampleSelective(order, example);
    }
    
}
