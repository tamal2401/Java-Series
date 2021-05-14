package com.course.kafka.api.response;

public class OrderResponse {
    private String orderNum;

    public OrderResponse(String orderNum) {
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderNum='" + orderNum + '\'' +
                '}';
    }
}
