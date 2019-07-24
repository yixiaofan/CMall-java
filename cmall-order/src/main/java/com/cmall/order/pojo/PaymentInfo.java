package com.cmall.order.pojo;

public class PaymentInfo {
    private String title;
    private Double total;
    private String orderId;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    @Override
    public String toString() {
	return "PaymentInfo [title=" + title + ", total=" + total + ", orderId=" + orderId + "]";
    }
}
