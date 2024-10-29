package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save operation
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    // Read operation
    @Override
    public List<Product> fetchProductList() {
        return productRepository.findAll();
    }

    // Update operation
    @Override
    public Product updateProduct(Product product, int productID) {
        Product proDB = productRepository.findById(productID).get();
        if (Objects.nonNull(product.getProductID())){
            proDB.setProductID(product.getProductID());
        }
        if (Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())){
            proDB.setProductName(product.getProductName());
        }
        if (Objects.nonNull(product.getAmount())){
            proDB.setAmount(product.getAmount());
        }
        if (Objects.nonNull(product.getCategoryID())){
            proDB.setCategoryID(product.getCategoryID());
        }
        if (Objects.nonNull(product.getPrice())){
            proDB.setPrice(product.getPrice());
        }
        return saveProduct(proDB);
    }

    // Delete operation
    @Override
    public void deleteProductByID(int productID) {
        productRepository.deleteById(productID);
    }
}
