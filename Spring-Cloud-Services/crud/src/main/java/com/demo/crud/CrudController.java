package com.demo.crud;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudController {

    @PostMapping(value = "/api/save/product")
    public String saveProduct(@RequestBody Product prod){
        return "";
    }
}
