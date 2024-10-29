package com.example.demo.services;

import com.example.demo.entities.BillDetail;

import java.util.List;

public interface BillDetailService {
    // Add op
    BillDetail saveBillDetail(BillDetail billDetail);

    // Read op
    List<BillDetail> fetchBillDetailList();


}
