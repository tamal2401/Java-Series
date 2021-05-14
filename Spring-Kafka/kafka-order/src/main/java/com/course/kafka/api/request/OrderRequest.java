package com.course.kafka.api.request;

import java.util.List;

public class OrderRequest {

    private String location;

    private String creditCardNumber;

    private List<OrderItemrequest> items;

    public OrderRequest() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public List<OrderItemrequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemrequest> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "location='" + location + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", items=" + items +
                '}';
    }
}
