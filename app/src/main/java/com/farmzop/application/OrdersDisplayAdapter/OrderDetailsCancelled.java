package com.farmzop.application.OrdersDisplayAdapter;

/**
 * Created by Gaurav on 11-03-2016.
 */
public class OrderDetailsCancelled implements OrderDetails {

    private static final int TYPE=1;//1 for cancel

    private int orderId;
    private String orderDate;
    private int amount;
    private String paymentMode;

    public OrderDetailsCancelled(int orderId, String orderDate,int amount,String paymentMode) {
        this.amount = amount;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.paymentMode = paymentMode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public int getId() {
        return orderId;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
