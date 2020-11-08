package com.demo.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
public class CrudController {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ProductDao productDao;

    public CrudController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping(value = "/api/save/product")
    public Product saveProduct(@RequestBody Product prod) throws DataSaveException {
        try{
            Product product = productDao.saveData(prod);
            return product;
        }catch(Exception e){
            log.error("Error while saving the data :"+e.getMessage());
            throw new DataSaveException("Error while saving data");
        }
    }
}
