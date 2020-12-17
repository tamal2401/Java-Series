package com.demo.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

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

    @DeleteMapping(value = "/api/product/delete/{id}")
    public void removeProduct(@PathVariable Integer id) {

    }

    @PostMapping(value = "/api/product/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProduct(@RequestBody Product prod) {

    }

    @GetMapping(value = "/api/product/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> allProducts() {
        return productDao.getAll();
    }
}
