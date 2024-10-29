package com.example.demo.services;

import com.example.demo.entities.Discount;
import com.example.demo.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DiscountServiceImpl implements DiscountService{
    @Autowired private DiscountRepository discountRepository;
    @Override
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> fetchDiscountList() {
        return discountRepository.findAll();
    }

    @Override
    public Discount updateDiscount(Discount discount, int discountID) {
        Discount disDB = discountRepository.findById(discountID).get();
        if (Objects.nonNull(discount.getDiscountID())){
            disDB.setDiscountID(discount.getDiscountID());
        }
        if (Objects.nonNull(discount.getDiscountValue())){
            disDB.setDiscountValue(discount.getDiscountValue());
        }
        if (Objects.nonNull(discount.getStatus())){
            disDB.setStatus(discount.getStatus());
        }
        if (Objects.nonNull(discount.getQuantity())){
            disDB.setQuantity(discount.getQuantity());
        }
        if (Objects.nonNull(discount.getDateEnd())&&!"".equalsIgnoreCase(discount.getDateEnd())){
            disDB.setDateEnd(discount.getDateEnd());
        }
        if (Objects.nonNull(discount.getDateStart())&&!"".equalsIgnoreCase(discount.getDateStart())){
            disDB.setDateStart(discount.getDateStart());
        }
        return discountRepository.save(disDB);
    }

    @Override
    public void deleteByID(int discountID) {
        discountRepository.deleteById(discountID);
    }
}
