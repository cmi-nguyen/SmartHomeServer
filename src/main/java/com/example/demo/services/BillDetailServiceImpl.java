package com.example.demo.services;

import com.example.demo.entities.BillDetail;
import com.example.demo.repositories.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService{
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Override
    public BillDetail saveBillDetail(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    @Override
    public List<BillDetail> fetchBillDetailList() {
        return billDetailRepository.findAll();
    }
}
