package com.example.demo.services;

import com.example.demo.entities.Bill;

import java.util.List;

public interface BillService {
    // Save op
    Bill saveBill(Bill bill);

    // Read op
    List<Bill> fetchBillList();
    // Update op
    Bill updateBill(Bill bill, int billID);

    // Delete op
    void deleteBillByID(int billID);
}
