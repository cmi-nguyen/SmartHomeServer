package com.example.demo.services;

import com.example.demo.entities.Bill;
import com.example.demo.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BillIServiceImpl implements BillService{
    @Autowired
    private BillRepository billRepository;
    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> fetchBillList() {
        return billRepository.findAll();
    }

    @Override
    public Bill updateBill(Bill bill, int billID) {
        Bill billDB = billRepository.findById(billID).get();
        if (Objects.nonNull(bill.getBillID())){
            billDB.setBillID(bill.getBillID());
        }
        if (Objects.nonNull(bill.getCustomerID())){
            billDB.setCustomerID(bill.getCustomerID());
        }
        if (Objects.nonNull(bill.getStaffID())){
            billDB.setStaffID(bill.getStaffID());
        }
        if (Objects.nonNull(bill.getDiscountID())){
            billDB.setDiscountID(bill.getDiscountID());
        }
        if (Objects.nonNull(bill.getDate())&&!"".equalsIgnoreCase(bill.getDate())){
            billDB.setDate(bill.getDate());
        }
        if (Objects.nonNull(bill.getStatus())){
            billDB.setStatus(bill.getStatus());
        }
        return billRepository.save(billDB);
    }

    @Override
    public void deleteBillByID(int billID) {
        billRepository.deleteById(billID);
    }
}
