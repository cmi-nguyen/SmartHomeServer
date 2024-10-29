package com.example.demo.services;

import com.example.demo.entities.Brand;

import java.util.List;

public interface BrandService {
    // Add op
    Brand saveBrand(Brand brand);

    // Read op
    List<Brand> fetchBrandList();

    // Update op
    Brand updateBrand(Brand brand, int brandID);

    // Delete op

    void deleteBrandByID(int brandID);
}
