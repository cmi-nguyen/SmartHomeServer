package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "logentries")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer logID;
    @Column(name="user")
//    @Convert(converter =  AESEncryption.class)
    private  String user;
    @Column(name = "action")
//    @Convert(converter =  AESEncryption.class)
    private String action;
    @Column(name = "date")
//    @Convert(converter =  AESEncryption.class)
    private String date;

}
