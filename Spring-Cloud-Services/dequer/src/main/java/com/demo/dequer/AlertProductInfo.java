package com.demo.dequer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertProductInfo {
    private Integer productId;
    private String productName;
    private String promotionDesc;
    private Timestamp postTime;

    @Override
    public String toString() {
        return "AlertProductInfo{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", promotionDesc='" + promotionDesc + '\'' +
                ", postTime=" + postTime +
                '}';
    }
}
