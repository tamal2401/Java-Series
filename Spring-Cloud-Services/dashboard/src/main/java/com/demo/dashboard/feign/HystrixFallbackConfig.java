package com.demo.dashboard.feign;

import com.demo.dashboard.domain.AlertProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;

public class HystrixFallbackConfig implements CrudServiceFeignClient {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public AlertProductInfo saveProduct(AlertProductInfo prod) {
        log.warn("FeignClient call to save product failed");
        return null;
    }

    @Override
    public void removeProduct(Integer id) {
        log.warn("FeignClient call to delete product failed");
    }

    @Override
    public void updateProduct(AlertProductInfo prod) {
        log.warn("FeignClient call to update product failed");
    }

    @Override
    public List<AlertProductInfo> allProducts() {
        log.warn("FeignClient call to get all product failed");
        return Collections.emptyList();
    }
}
