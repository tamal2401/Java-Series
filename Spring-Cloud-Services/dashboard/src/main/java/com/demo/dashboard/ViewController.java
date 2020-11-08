package com.demo.dashboard;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViewController {

    @Autowired
    CrudServiceFeignClient crudServiceFeignClient;

    @PostMapping(value = "/api/product/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProduct(@RequestBody Product prod) {
        Product savedElement = crudServiceFeignClient.saveProduct(prod);
        String res = null!=savedElement?"Data saved successfully":"";
        return res;
    }

    @PostMapping(value = "/api/product/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody Product prod) {
        crudServiceFeignClient.updateProduct(prod);
    }

    @DeleteMapping(value = "/api/product/delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        crudServiceFeignClient.removeProduct(id);
    }

    @GetMapping(value = "/api/product/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProduct() {
        return crudServiceFeignClient.allProducts();
    }
}
