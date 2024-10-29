package com.example.demo.services;

import com.example.demo.entities.Discount;

import java.util.List;

public interface DiscountService {
    // Add op
    public Discount saveDiscount(Discount discount);

    // Read op
    public List<Discount> fetchDiscountList();

    // Update op
    public Discount updateDiscount(Discount discount, int discountID);

    // Delete op
    public void deleteByID(int discountID);
}
