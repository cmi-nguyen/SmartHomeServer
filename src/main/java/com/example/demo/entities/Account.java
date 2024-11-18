package com.example.demo.entities;

import com.example.demo.config.AESEncryption;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;



@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer accountID;


    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getAccountName()  {
       
        return  this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getUserKey() {
        return user_key;
    }

    public void setUserKey(String key) {
        this.user_key = key;
    }
    public String getRsaKey() {
        return rsaKey;
    }

    public void setRsaKey(String rsaKey) {
        this.rsaKey = rsaKey;
    }
    @Column(name="pin")
//    @Convert(converter =  AESEncryption.class)
    private  String pin;
    @Column(name = "account_name")
//    @Convert(converter =  AESEncryption.class)
    private String accountName;
    @Column(name = "password")
//    @Convert(converter =  AESEncryption.class)
    private String password;
    @Column(name = "status")
    private Integer status;
    @Column(name="type")
    private  Integer type;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name="gender")
    private boolean gender;
    @Column(name = "user_key")
    private String user_key;
    @Column(name="rsa_key" ,columnDefinition = "TEXT")
    private String rsaKey;
// Encrypt

}