package com.example.demo.controllers;

import com.example.demo.entities.Discount;
import com.example.demo.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {
    @Autowired private DiscountService discountService;

    @GetMapping("/discounts")
    public List<Discount> fetchDiscountList(){ return discountService.fetchDiscountList();}

    @PostMapping("/discounts")
    public Discount saveDiscount(@RequestBody Discount discount){
        return discountService.saveDiscount(discount);
    }

    @PutMapping("/discounts/{id}")
    public Discount updateDiscount(@RequestBody Discount discount, @PathVariable("id") int discountID){
        return discountService.updateDiscount(discount,discountID);
    }

    @DeleteMapping("/discounts/{id}")
    public String deleteDiscount(@PathVariable("id") int discountID){
        discountService.deleteByID(discountID);
        return "Deleted";
    }
}
