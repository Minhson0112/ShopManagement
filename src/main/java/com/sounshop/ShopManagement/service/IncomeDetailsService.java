package com.sounshop.ShopManagement.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sounshop.ShopManagement.repository.SalesInfoRepository;
import java.util.List;

@Service
public class IncomeDetailsService {

    @Autowired
    private SalesInfoRepository salesInfoRepository;

    public int monthOrders(){
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentyear = today.getYear();
        return salesInfoRepository.orderOfMonth(currentMonth, currentyear);
    }
    public int calculateOrderDifference(){
        LocalDate today = LocalDate.now();
        int lastMonth = today.getMonthValue() - 1;
        int currentYear = today.getYear();
        int lastMonthOrder = salesInfoRepository.orderOfMonth(lastMonth, currentYear);
        int currentMonthOrders = monthOrders();

        return currentMonthOrders - lastMonthOrder;

    }

    public int uriageOfmonth(){
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentyear = today.getYear();
        Integer uriageOfmonth = salesInfoRepository.uriage(currentMonth, currentyear);
        if (uriageOfmonth != null) {
            return uriageOfmonth;
        }
        return 0;
    }

    public int revenueOfMonth(){
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentyear = today.getYear();
        Integer revenueOfMonth = salesInfoRepository.revenue(currentMonth, currentyear);
        if(revenueOfMonth != null){
            return revenueOfMonth;
        }
        return 0;
    }

   
    public String topClient(){
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentyear = today.getYear();
        List<String> clientNameList = salesInfoRepository.findTopClient(currentMonth, currentyear);
        if(!clientNameList.isEmpty()){
            return clientNameList.get(0);
        }
        else{
            return "Chưa có khách hàng";
        }
    }
    
    public int countPurchases(){
        String clientName = topClient();
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentyear = today.getYear();
        return salesInfoRepository.countPurchasesByClient(clientName, currentMonth, currentyear);
    }
}
