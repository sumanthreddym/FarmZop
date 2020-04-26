package com.farmzop.application.OrdersDisplayAdapter;

/**
 * Created by Gaurav on 12-01-2016.
 */
public class OrderDetailsNormal implements OrderDetails{

    private static final int TYPE=0;//0 for normal

   private int orderId;
   private String orderDate;
   private int amount;
   private String paymentMode;
   private boolean packed;
   private  boolean delivering;
   private boolean delivered;

    public OrderDetailsNormal(int orderId, String orderDate, int amount, String paymentMode, boolean packed, boolean delivering, boolean delivered) {
        this.delivered = delivered;
        this.amount = amount;
        this.delivering = delivering;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.packed = packed;
        this.paymentMode = paymentMode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public boolean isPacked() {
        return packed;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public boolean isDelivering() {
        return delivering;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public int getAmount() {
        return amount;
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
