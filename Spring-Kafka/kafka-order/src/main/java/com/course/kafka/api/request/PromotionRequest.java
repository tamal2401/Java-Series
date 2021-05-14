package com.course.kafka.api.request;

public class PromotionRequest {

    private String promotionCode;

    public PromotionRequest(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionRequest() {
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    @Override
    public String toString() {
        return "PromotionMessage{" +
                "promotionCode='" + promotionCode + '\'' +
                '}';
    }
}
