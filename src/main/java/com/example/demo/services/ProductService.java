package com.example.demo.services;

import com.example.demo.entities.Product;

import java.util.List;

public interface ProductService {
    // Save operation
    Product saveProduct(Product product);

    // Read operation

    List<Product> fetchProductList();

    // Update operation
    Product updateProduct(Product product, int productID);

    // Delete operation
    void deleteProductByID(int productID);
}
