package com.demo.dashboard.feign;

import com.demo.dashboard.domain.AlertProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "crud-service", configuration = CustomFeignConfiguration.class, fallback = HystrixFallbackConfig.class)
public interface CrudServiceFeignClient {

    @PostMapping(value = "/api/save/product")
    public AlertProductInfo saveProduct(@RequestBody AlertProductInfo prod);

    @DeleteMapping(value = "/api/product/delete/{id}")
    public void removeProduct(@PathVariable Integer id);

    @PostMapping(value = "/api/product/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody AlertProductInfo prod);

    @GetMapping(value = "/api/product/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlertProductInfo> allProducts();
}
