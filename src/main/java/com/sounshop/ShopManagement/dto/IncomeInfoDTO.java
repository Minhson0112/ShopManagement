package com.sounshop.ShopManagement.dto;

public class IncomeInfoDTO {
    private int revenue;
    private int profit;
    private int sales;
    

    public IncomeInfoDTO(){}
    
    public IncomeInfoDTO(int revenue, int profit, int sales){
        this.revenue = revenue;
        this.profit = profit;
        this.sales = sales;
    }

    public void setRevenue(int revenue){
        this.revenue = revenue;
    }
    public int getRevenue(){
        return revenue;
    }
    public void setProfit(int profit){
        this.profit = profit;
    }
    public int getProfit(){
        return profit;
    }
    public void setSales(int sales){
        this.sales = sales;
    }
    public int getSales(){
        return sales;
    }
}
