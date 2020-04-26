package com.farmzop.application;

/**
 * Created by Gaurav on 30-01-2016.
 */
public class CheckoutItem {

    String couponCode;
    String Address;
    int deliveryDayCode;//1:now, 2:today, 3:tomorrow
    String timeSlot;

    int timeSlotCode;//1:10am to 12pm, 2:12pm to 2pm .......7: 10pm to 12am
    int paymentModeCode;//1:COD, 2:Online

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTimeSlot() {

        return timeSlot;
    }


    public void setAddress(String address) {
        Address = address;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setDeliveryDayCode(int deliveryDayCode) {
        this.deliveryDayCode = deliveryDayCode;
    }

    public void setPaymentModeCode(int paymentModeCode) {
        this.paymentModeCode = paymentModeCode;
    }

    public void setTimeSlotCode(int timeSlotCode) {
        this.timeSlotCode = timeSlotCode;
    }

    public String getAddress() {
        return Address;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public int getDeliveryDayCode() {
        return deliveryDayCode;
    }

    public int getPaymentModeCode() {
        return paymentModeCode;
    }

    public int getTimeSlotCode() {
        return timeSlotCode;
    }


    /**This method is used to give the corresponding Delivery Day based on the payment code
     @param code Delivery Day code Code
     @return mode returns the Delivery Day as a string
     */
    public String getDeliveryDay(int code)
    {
        String day;
        switch(code)
        {
            case 0:day="Now";
                break;

            case 1:day="Today";
                break;

            case 2:day="Tomorrow";
                break;
            default:day="Today";
        }

        return day;
    }

    /**This method is used to give the corresponding Payment mode based on the payment code
    @param code iPayment Code
     @return mode returns the Payment mode as a string
     */
    public String getPaymentMode(int code)
    {
        String mode;
        switch(code)
        {
            case 0:mode="COD";
                break;

            case 1:mode="ONLINE";
                break;
            default:mode="COD";
        }

        return mode;
    }


    /**This method is used to give the corresponding Delivery time based on the payment code
     @param code Delivery Time Slot Code
     @return mode returns the Delivery time slot as a string
     */
    public String getDeliveryTimeSlot(int code)
    {
        String slot;
        switch(code)
        {
            case 0:slot="10:00 AM to 12:00 PM";
                break;

            case 1:slot="12:00 PM to 02:00 PM";
                break;

            case 2:slot="02:00 PM to 12:00 PM";
                break;
            case 3:slot="04:00 PM to 06:00 PM";
                break;
            case 4:slot="06:00 PM to 08:00 PM";
                break;
            case 5:slot="08:00 PM to 10:00 PM";
                break;
            case 6:slot="10:00 pM to 12:00 AM";
                break;

            default:slot="10:00 AM to 12:00 PM";
        }

        return slot;
    }

}
