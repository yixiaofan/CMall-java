package com.cmall.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cmall.order.pojo.MyOrderParam;
import com.cmall.order.service.TbOrderService;
import com.cmall.commons.pojo.CmallResult;
import com.cmall.commons.pojo.EasyUIDataGrid;
import com.cmall.commons.pojo.MyOrderItem;
import com.cmall.commons.pojo.MyOrderItemItem;
import com.cmall.commons.pojo.TbItemChild;
import com.cmall.commons.utils.CookieUtils;
import com.cmall.commons.utils.HttpClientUtil;
import com.cmall.commons.utils.IDUtils;
import com.cmall.commons.utils.JsonUtils;
import com.cmall.dubbo.service.TbItemDubboService;
import com.cmall.dubbo.service.TbOrderDubboService;
import com.cmall.pojo.TbItem;
import com.cmall.pojo.TbOrder;
import com.cmall.pojo.TbOrderItem;
import com.cmall.pojo.TbOrderShipping;
import com.cmall.redis.dao.JedisDao;

@Service
public class TbOrderServiceImpl implements TbOrderService {
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${cart.key}")
    private String cartKey;
    @Value("${passport.url}")
    private String passportUrl;
    @Value("${redis.itemNum.key}")
    private String itemNumKey;
    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;
    @Reference
    private TbOrderDubboService tbOrderDubboServiceImpl;
    @Override
    public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	String result=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(result, CmallResult.class);
	String key=cartKey+((LinkedHashMap)er.getData()).get("username");
	String json=jedisDaoImpl.get(key);
	List<TbItemChild> list=JsonUtils.jsonToList(json, TbItemChild.class);
	List<TbItemChild> listNew=new ArrayList<>();
	for(TbItemChild child:list){
	    for(Long id:ids){
		if((long)child.getId()==(long)id){
		    //判断购买量是否大于等于库存
		    TbItem tbItem=tbItemDubboServiceImpl.selById(id);
		    if(tbItem.getNum()>=child.getNum()){
			child.setEnough(true);
		    }else{
			child.setEnough(false);
		    }
		    listNew.add(child);
		}
	    } 
	}
	return listNew;
    }
    @Override
    public CmallResult create(MyOrderParam param, HttpServletRequest request) {
	//订单表数据
	TbOrder order=new TbOrder();
	order.setPayment(param.getPayment());
	order.setPaymentType(param.getPaymentType());
	long id=IDUtils.genItemId();
	order.setOrderId(id+"");
	Date date=new Date();
	order.setCreateTime(date);
	order.setUpdateTime(date);
	
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	String result=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(result, CmallResult.class);
	Map user=((LinkedHashMap)er.getData());
	order.setUserId(Long.parseLong(user.get("id").toString()));
	order.setBuyerNick(user.get("username").toString());
	order.setBuyerRate(0);
	//订单-商品表
	for(TbOrderItem item:param.getOrderItems()){
	    String numKey=itemNumKey+item.getItemId();
	    item.setId(IDUtils.genItemId()+"");
	    item.setOrderId(id+"");
	    jedisDaoImpl.decr(numKey, (long)item.getNum());
	}
	//收货人信息
	TbOrderShipping shipping=param.getOrderShipping();
	shipping.setOrderId(id+"");
	shipping.setCreated(date);
	shipping.setUpdated(date);
	
	CmallResult erResult=new CmallResult();
	erResult.setStatus(200);
	erResult.setData(order.getOrderId());
	try {
	    int index=tbOrderDubboServiceImpl.insOrder(order, param.getOrderItems(), shipping);
	    System.out.println(index);
	    if(index>0){
		erResult.setStatus(200);
		erResult.setData(order.getOrderId());
		//删除购买的商品
		String json=jedisDaoImpl.get(cartKey+user.get("username"));
		List<TbItemChild> listCart=JsonUtils.jsonToList(json, TbItemChild.class);
		List<TbItemChild> listNew=new ArrayList<>();
		for(TbItemChild child:listCart){
		    for(TbOrderItem item:param.getOrderItems()){
			if(child.getId().longValue()==Long.parseLong(item.getItemId())){
			    listNew.add(child);
			}
		    }
		}
		for(TbItemChild mynew:listNew){
		    listCart.remove(mynew);
		}
		jedisDaoImpl.set(cartKey+user.get("username"), JsonUtils.objectToJson(listCart));
		//删除
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return erResult;
    }
    @Override
    public EasyUIDataGrid showOrder(int page, int rows, HttpServletRequest request) {
	String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
	String result=HttpClientUtil.doPost(passportUrl+token);
	CmallResult er=JsonUtils.jsonToPojo(result, CmallResult.class);
	Number id=(Number) ((LinkedHashMap)er.getData()).get("id");
	EasyUIDataGrid data = tbOrderDubboServiceImpl.showOrder(page, rows, id.longValue());
	//根据订单号查询到订单商品
	List<MyOrderItem> myOrderItems=new ArrayList<MyOrderItem>();
	List<TbOrder> orders=(List<TbOrder>) data.getRows();
	for(TbOrder order : orders){
	    MyOrderItem myOrderItem=new MyOrderItem();
	    List<MyOrderItemItem> myOrderItemItems=new ArrayList<MyOrderItemItem>();
	    Long totalFee=(long) 0;
	    for(TbOrderItem orderItem:showOrderItem(order.getOrderId())){
		MyOrderItemItem myOrderItemItem=new MyOrderItemItem();
		myOrderItemItem.setId(orderItem.getId());
		myOrderItemItem.setItemId(orderItem.getItemId());
		myOrderItemItem.setNum(orderItem.getNum());
		myOrderItemItem.setPicPath(orderItem.getPicPath());
		myOrderItemItem.setPrice(orderItem.getPrice());
		myOrderItemItem.setTitle(orderItem.getTitle());
		myOrderItemItems.add(myOrderItemItem);
		totalFee+=orderItem.getPrice()*orderItem.getNum();
	    }
	    myOrderItem.setOrderItems(myOrderItemItems);
	    myOrderItem.setTotalFee(totalFee);
	    myOrderItem.setUpdateTime(order.getUpdateTime());
	    myOrderItem.setOrderId(order.getOrderId());
	    myOrderItem.setBuyerRate(order.getBuyerRate());
	    myOrderItems.add(myOrderItem);
	}
	data.setRows(myOrderItems);
	return data;
    }
    @Override
    public List<TbOrderItem> showOrderItem(String orderid) {
	return tbOrderDubboServiceImpl.showOrderItem(orderid);
    }
    
}
