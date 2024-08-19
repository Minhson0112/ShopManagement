package com.sounshop.ShopManagement.dto;

public class RevenuePeriodDTO {
    private String period;
    private int revenue;
    private int profit;

    // Constructors
    public RevenuePeriodDTO() {}

    public RevenuePeriodDTO(String period, int revenue, int profit) {
        this.period = period;
        this.revenue = revenue;
        this.profit = profit;
    }

    // Getters and Setters
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
