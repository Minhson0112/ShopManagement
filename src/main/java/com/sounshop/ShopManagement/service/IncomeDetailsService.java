package com.sounshop.ShopManagement.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sounshop.ShopManagement.dto.ProductSalesDTO;
import com.sounshop.ShopManagement.dto.RevenuePeriodDTO;
import com.sounshop.ShopManagement.repository.SalesInfoRepository;
import java.util.List;
import java.util.ArrayList;
import java.time.YearMonth;

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

    public List<ProductSalesDTO> findProductSale(int month){
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        return salesInfoRepository.findProductSales(month,currentYear);
    }

    public List<RevenuePeriodDTO> getRevenueByPeriod(int month) {
        int year = YearMonth.now().getYear(); // Lấy năm hiện tại
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDay = yearMonth.atDay(1);
        LocalDate tenthDay = yearMonth.atDay(10);
        LocalDate twentiethDay = yearMonth.atDay(20);
        LocalDate lastDay = yearMonth.atEndOfMonth();
    
        // Truy vấn cho từng khoảng thời gian
        List<Object[]> period1Data = salesInfoRepository.findRevenueByDateRange(firstDay, tenthDay);
        List<Object[]> period2Data = salesInfoRepository.findRevenueByDateRange(tenthDay.plusDays(1), twentiethDay);
        List<Object[]> period3Data = salesInfoRepository.findRevenueByDateRange(twentiethDay.plusDays(1), lastDay);
    
        // Tạo danh sách kết quả
        List<RevenuePeriodDTO> result = new ArrayList<>();
        
        // Tạo đối tượng DTO cho mỗi khoảng thời gian
        result.add(new RevenuePeriodDTO("1",0,0));
        if (period1Data.get(0) != null) {
            int revenue1 = (period1Data.get(0)[0] != null) ? ((Number) period1Data.get(0)[0]).intValue() : 0;
            int profit1 = (period1Data.get(0)[1] != null) ? ((Number) period1Data.get(0)[1]).intValue() : 0;
            result.add(new RevenuePeriodDTO("10", revenue1, profit1));
        }
        
        if (period2Data.get(0) != null) {
            int revenue2 = (period2Data.get(0)[0] != null) ? ((Number) period2Data.get(0)[0]).intValue() : 0;
            int profit2 = (period2Data.get(0)[1] != null) ? ((Number) period2Data.get(0)[1]).intValue() : 0;
            result.add(new RevenuePeriodDTO("20", revenue2, profit2));
        }
        
        if (period3Data.get(0) != null) {
            int revenue3 = (period3Data.get(0)[0] != null) ? ((Number) period3Data.get(0)[0]).intValue() : 0;
            int profit3 = (period3Data.get(0)[1] != null) ? ((Number) period3Data.get(0)[1]).intValue() : 0;
            result.add(new RevenuePeriodDTO("" + lastDay.getDayOfMonth(), revenue3, profit3));
        }
    
        return result;
    }
    
}
