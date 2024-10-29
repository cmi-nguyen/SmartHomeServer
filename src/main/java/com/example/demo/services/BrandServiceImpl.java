package com.example.demo.services;

import com.example.demo.entities.Brand;
import com.example.demo.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> fetchBrandList() {
        return brandRepository.findAll();
    }

    @Override
    public Brand updateBrand(Brand brand, int brandID) {
        Brand brandDB = brandRepository.findById(brandID).get();
        if (Objects.nonNull(brand.getBrandID())){
            brandDB.setBrandID(brand.getBrandID());
        }
        if (Objects.nonNull(brand.getBrandName())&&!"".equalsIgnoreCase(brand.getBrandName())){
            brandDB.setBrandName(brand.getBrandName());
        }
        if (Objects.nonNull(brand.getStatus())){
            brandDB.setStatus(brand.getStatus());
        }
        return brandRepository.save(brandDB);
    }

    @Override
    public void deleteBrandByID(int brandID) {
        brandRepository.deleteById(brandID);
    }
}
