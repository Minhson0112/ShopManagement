package com.sounshop.ShopManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    // Constructors, getters, and setters
    public Admin() {}

    public Admin(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
