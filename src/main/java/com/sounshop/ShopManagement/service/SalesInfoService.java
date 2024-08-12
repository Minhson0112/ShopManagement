package com.sounshop.ShopManagement.service;

import com.sounshop.ShopManagement.dto.SalesInfoDTO;
import com.sounshop.ShopManagement.entity.SalesInfo;
import com.sounshop.ShopManagement.repository.SalesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesInfoService {
    @Autowired
    private SalesInfoRepository salesInfoRepository;

    public List<SalesInfoDTO> getAllSalesInfo() {
        return salesInfoRepository.findAllSalesInfo();
    }

    public List<SalesInfoDTO> searchSalesInfo(String title, LocalDate day, LocalDate dayBefore, LocalDate dayAfter) {
        if (title != null && day != null) {
            return salesInfoRepository.findByProductNameContainingAndTradingDate(title, day);
        }
        if (title != null) {
            return salesInfoRepository.findByProductNameContainingAndTradingDateBetween(title, dayBefore, dayAfter);
        }
        if (day != null) {
            return salesInfoRepository.findByTradingDate(day);
        }
        return salesInfoRepository.findByTradingDateBetween(dayBefore,dayAfter);
    }

    public SalesInfo saveSalesInfo(SalesInfo salesInfo) {
        return salesInfoRepository.save(salesInfo);
    }

    public int calculateOrderDifference(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        int todayOrders = salesInfoRepository.countSalesInfoToday(today);
        int yesterdayOrders = salesInfoRepository.countSalesInfoYesterday(yesterday);

        return todayOrders - yesterdayOrders;

    }

    public int numberOfOrderToday(){
        LocalDate today = LocalDate.now();
        return salesInfoRepository.countSalesInfoToday(today);
    }

    public int calculatePriceDifference(){
        LocalDate today = LocalDate.now();
        Integer  priceToday = salesInfoRepository.sumPriceToday(today);
        Integer  priceYesterday = salesInfoRepository.sumPriceYesterday(today.minusDays(1));

        int todayPrice = (priceToday != null) ? priceToday : 0;
        int yesterdayPrice = (priceYesterday != null) ? priceYesterday : 0;
    
        return todayPrice - yesterdayPrice;
    }

    public int priceToday(){
        LocalDate today = LocalDate.now();
        Integer  priceToday = salesInfoRepository.sumPriceToday(today);
        int todayPrice = (priceToday != null) ? priceToday : 0;

        return todayPrice;
    }
}
