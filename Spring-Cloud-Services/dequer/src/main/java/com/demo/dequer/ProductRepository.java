package com.demo.dequer;

public interface ProductRepository {

    void saveProduct(AlertProductInfo prod);

    void updateProduct(AlertProductInfo prod);

    void deleteProduct(int prodId);

    void findProductById(int prodId);
}
