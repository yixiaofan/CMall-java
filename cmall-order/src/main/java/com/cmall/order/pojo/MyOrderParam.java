package com.cmall.order.pojo;

import java.util.List;

import com.cmall.pojo.TbOrderItem;
import com.cmall.pojo.TbOrderShipping;

public class MyOrderParam {
    private int paymentType;
    private String payment;
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;
    
    public int getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }
    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
    @Override
    public String toString() {
	return "MyOrderParam [paymentType=" + paymentType + ", payment=" + payment + ", orderItems=" + orderItems
		+ ", orderShipping=" + orderShipping + "]";
    }
    
}
