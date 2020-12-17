package com.demo.crud;

import java.util.List;

public interface ProductDao {

    public Product saveData(Product product);

    List<Product> getAll();
}
