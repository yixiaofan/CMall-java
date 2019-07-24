package com.cmall.commons.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MyOrderItem implements Serializable{
    private List<MyOrderItemItem> orderItems;
    private Date updateTime;
    private Long totalFee;
    private String orderId;
    private Integer buyerRate;
    
    public List<MyOrderItemItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<MyOrderItemItem> orderItems) {
        this.orderItems = orderItems;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Long getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Integer getBuyerRate() {
        return buyerRate;
    }
    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }
    
}
