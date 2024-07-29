package com.sounshop.ShopManagement.controller.api;

import com.sounshop.ShopManagement.dto.SalesInfoDTO;
import com.sounshop.ShopManagement.service.SalesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/salesInfo")
public class SalesInfoController {

    @Autowired
    private SalesInfoService salesInfoService;

    @GetMapping
    public ResponseEntity<List<SalesInfoDTO>> getAllSalesInfo() {
        List<SalesInfoDTO> salesInfoList = salesInfoService.getAllSalesInfo();
        return ResponseEntity.ok(salesInfoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalesInfoDTO>> searchSalesInfo(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate day,
            @RequestParam(required = false) LocalDate dayBefore,
            @RequestParam(required = false) LocalDate dayAfter) {
        List<SalesInfoDTO> salesInfoList = salesInfoService.searchSalesInfo(title, day, dayBefore, dayAfter);
        return ResponseEntity.ok(salesInfoList);
    }
}
