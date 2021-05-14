package com.course.kafka.broker.message;

public class PromotionMessage {

    private String promotionCode;

    public PromotionMessage(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionMessage() {
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
