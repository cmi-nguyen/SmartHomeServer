package com.example.demo.controllers;

import com.example.demo.entities.BillDetail;
import com.example.demo.services.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillDetailController {
    @Autowired
    private BillDetailService billDetailService;
    @GetMapping("/billdetail")
    public List<BillDetail> fetchBillDetailList(){ return billDetailService.fetchBillDetailList();}
    @PostMapping("/billdetail")
    public BillDetail saveBillDetail(@RequestBody BillDetail billDetail){
        return billDetailService.saveBillDetail(billDetail);
    }
}
