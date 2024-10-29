package com.example.demo.controllers;

import com.example.demo.entities.Brand;
import com.example.demo.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    @Autowired private BrandService brandService;

    @GetMapping("/brands")
    public List<Brand> fetchBrandList(){return brandService.fetchBrandList();}

    @PostMapping("/brands")
    public Brand saveBrand(@RequestBody Brand brand){
        return brandService.saveBrand(brand);
    }

    @PutMapping("/brands/{id}")
    public Brand updateBrand(@RequestBody Brand brand, @PathVariable int brandID){
        return brandService.updateBrand(brand,brandID);
    }

    @DeleteMapping("/brands/{id}")
    public String deleteBrand(@PathVariable int brandID){
        brandService.deleteBrandByID(brandID);
        return "Deleted ";
    }
}
