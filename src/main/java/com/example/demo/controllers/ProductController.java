package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired private ProductService productService;
    // Create op mapping ( "Save" mean save to database)
    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    // Read mapping
    @GetMapping("/products")
    public List<Product> fetchProductList(){
        return productService.fetchProductList();
    }

    // Update operation mapping
    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int productID){
        return productService.updateProduct(product,productID);
    }

    // Delete operation mapping
    @DeleteMapping("/products/{id}")
    public String deleteProductByID(@PathVariable("id") int productID){
        productService.deleteProductByID(productID);
        return "Delete Successful";
    }
}
