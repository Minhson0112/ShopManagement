package com.sounshop.ShopManagement.service;

import com.sounshop.ShopManagement.dto.SalesInfoDTO;
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
        if (day != null && title == null && dayBefore == null && dayAfter == null) {
            return salesInfoRepository.findByTradingDate(day);
        } else if (day != null) {
            return salesInfoRepository.findByProductNameContainingAndTradingDate(title, day);
        } else if (dayBefore != null && dayAfter != null) {
            return salesInfoRepository.findByProductNameContainingAndTradingDateBetween(title, dayBefore, dayAfter);
        } else if (dayBefore != null) {
            return salesInfoRepository.findByProductNameContainingAndTradingDateAfter(title, dayBefore);
        } else if (dayAfter != null) {
            return salesInfoRepository.findByProductNameContainingAndTradingDateBefore(title, dayAfter);
        } else {
            return salesInfoRepository.findByProductNameContaining(title);
        }
    }
}
