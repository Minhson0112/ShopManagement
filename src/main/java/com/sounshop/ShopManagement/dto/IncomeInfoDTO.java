package com.sounshop.ShopManagement.dto;

public class IncomeInfoDTO {
    private int revenue;
    private int profit;
    private int sales;
    private int totalBuy;
    private int buysOfMonth;
    

    public IncomeInfoDTO(){}
    
    public IncomeInfoDTO(int revenue, int profit, int sales, int totalBuy, int buysOfMonth){
        this.revenue = revenue;
        this.profit = profit;
        this.sales = sales;
        this.totalBuy = totalBuy;
        this.buysOfMonth = buysOfMonth;
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
    public void setTotalBuy(int totalBuy){
        this.totalBuy = totalBuy;
    }
    public int getTotalBuy(){
        return totalBuy;
    }
    public void setBuysOfMonth(int buysOfMonth){
        this.buysOfMonth = buysOfMonth;
    }
    public int getBuysOfMonth(){
        return buysOfMonth;
    }
}
