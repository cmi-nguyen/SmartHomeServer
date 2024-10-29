package com.example.demo.controllers;

import com.example.demo.entities.Bill;
import com.example.demo.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    @Autowired private BillService billService;

    @GetMapping("/bill")
    public List<Bill> fetchBillList(){return billService.fetchBillList();}

    @PostMapping("/bill")
    public Bill saveBill(@RequestBody Bill bill){
        return billService.saveBill(bill);
    }

    @PutMapping("/bill/{id}")
    public Bill updateBill(@RequestBody Bill bill, @PathVariable("id") int billID){
        return billService.updateBill(bill,billID);
    }
    @DeleteMapping("/bill/{id}")
    public String deleteBill(@PathVariable("id") int billID){
        billService.deleteBillByID(billID);
        return "Delete success";
    }
}
