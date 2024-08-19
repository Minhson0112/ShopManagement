package com.sounshop.ShopManagement.dto;

public class ProductSalesDTO {
    private String productName;
    private long totalSales;

    // Constructors
    public ProductSalesDTO() {}

    public ProductSalesDTO(String productName, long totalSales) {
        this.productName = productName;
        this.totalSales = totalSales;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(long totalSales) {
        this.totalSales = totalSales;
    }
}
