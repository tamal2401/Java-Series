package com.demo.dashboard;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViewController {

    @PostMapping(value = "/api/product/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody Product prod) {

    }

    @PostMapping(value = "/api/product/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody Product prod) {

    }

    @DeleteMapping(value = "/api/product/delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {

    }

    @GetMapping(value = "/api/product/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProduct() {
        return null;
    }
}
