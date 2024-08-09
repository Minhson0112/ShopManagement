package com.sounshop.ShopManagement.controller.api;

import com.sounshop.ShopManagement.dto.SalesInfoDTO;
import com.sounshop.ShopManagement.entity.SalesInfo;
import com.sounshop.ShopManagement.service.SalesInfoService;
import com.sounshop.ShopManagement.service.ProductInfoService;
import com.sounshop.ShopManagement.dto.ProductStorageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/salesInfo")
public class SalesInfoController {

    @Autowired
    private SalesInfoService salesInfoService;

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping
    public ResponseEntity<List<SalesInfoDTO>> getAllSalesInfo() {
        System.out.println("check /api/salesInfo get");
        List<SalesInfoDTO> salesInfoList = salesInfoService.getAllSalesInfo();
        return ResponseEntity.ok(salesInfoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalesInfoDTO>> searchSalesInfo(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate day,
            @RequestParam(required = false) LocalDate dayBefore,
            @RequestParam(required = false) LocalDate dayAfter) {
        
        System.out.println("check /api/salesInfo/search get");
        System.out.println("check title: " + title);
        System.out.println("check day: " + day);
        System.out.println("check dayBefore: " + dayBefore);
        System.out.println("check dayAfter: " + dayAfter);

        List<SalesInfoDTO> salesInfoList = salesInfoService.searchSalesInfo(title, day, dayBefore, dayAfter);
        return ResponseEntity.ok(salesInfoList);
    }

    @PostMapping
    public ResponseEntity<SalesInfo> addSalesInfo(@RequestBody Map<String, Object> payload) {
        String productName = (String) payload.get("productName");
        String clientName = (String) payload.get("clientName");
        String address = (String) payload.get("address");
        Integer tradingQuantity = (Integer) payload.get("tradingQuantity");

        if (productName == null || clientName == null || address == null || tradingQuantity == null) {
            return ResponseEntity.badRequest().body(null);
        }

        ProductStorageDTO productStorageDTO = productInfoService.findProductStorageByProductName(productName)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        int nowQuantity = productStorageDTO.getQuantity();
        if (tradingQuantity > nowQuantity) {
            return ResponseEntity.badRequest().body(null);
        }
        int tradingPrice = tradingQuantity * productStorageDTO.getSellPrice();
        int profit = tradingPrice - (tradingQuantity * productStorageDTO.getEntryPrice());

        SalesInfo salesInfo = new SalesInfo();
        salesInfo.setProductId(productStorageDTO.getProductId());
        salesInfo.setClientName(clientName);
        salesInfo.setAddress(address);
        salesInfo.setTradingQuantity(tradingQuantity);
        salesInfo.setTradingDate(LocalDate.now());
        salesInfo.setPrice(tradingPrice);
        salesInfo.setProfit(profit);

        SalesInfo savedSalesInfo = salesInfoService.saveSalesInfo(salesInfo);
        return ResponseEntity.ok(savedSalesInfo);
    }
}
