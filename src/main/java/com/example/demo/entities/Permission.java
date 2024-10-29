package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "permission")
public class Permission {
    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer permissionID;

    @Column(name = "permission_name")
    private String permissionName;

}
