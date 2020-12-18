package com.demo.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

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
