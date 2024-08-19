package com.sounshop.ShopManagement.controller.api;
import com.sounshop.ShopManagement.dto.ProductSalesDTO;
import com.sounshop.ShopManagement.dto.RevenuePeriodDTO;
import com.sounshop.ShopManagement.service.IncomeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/income")
public class IncomeDetailsController {

    @Autowired
    private IncomeDetailsService incomeDetailsService;

    @GetMapping("/salesProduct")
    public ResponseEntity<List<ProductSalesDTO>> getProductSales(@RequestParam("month") int month) {
        List<ProductSalesDTO> productSales = incomeDetailsService.findProductSale(month);

        if(!productSales.isEmpty()){
            return ResponseEntity.ok(productSales);
        }
        else{
            return ResponseEntity.noContent().build();
        }
        
    }

    @GetMapping("/revenueByPeriod")
    public ResponseEntity<List<RevenuePeriodDTO>> getRevenueByPeriod(@RequestParam("month") int month) {
        List<RevenuePeriodDTO> revenueData = incomeDetailsService.getRevenueByPeriod(month);
        if (!revenueData.isEmpty()) {
            return ResponseEntity.ok(revenueData);
        } else {
            return ResponseEntity.noContent().build();
        }
    }


          
}
