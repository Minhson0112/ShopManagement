package com.sounshop.ShopManagement.controller.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.LocalDate;

import com.sounshop.ShopManagement.service.ProductInfoService;
import com.sounshop.ShopManagement.entity.ProductInfo;


@RestController
@RequestMapping(value = "/api/productInfo")
public class productController {
    @Autowired
    private ProductInfoService productInfoService;

   @PostMapping
    public ResponseEntity<ProductInfo> addProductInfo(@RequestBody Map<String, Object> payload) {
        String productName = (String) payload.get("productName");
        String category = (String) payload.get("category");
        Integer entryPrice = Integer.parseInt(payload.get("entryPrice").toString());
        Integer sellPrice = Integer.parseInt(payload.get("sellPrice").toString());
        LocalDate addDate = LocalDate.parse((String) payload.get("addDate"));
        Boolean isDelete = Boolean.parseBoolean(payload.get("isDelete").toString());

        System.out.println("check /api/productInfo method: Post");

        ProductInfo productInfo = new ProductInfo(productName, category, entryPrice, sellPrice, addDate, isDelete);
        ProductInfo savedProduct = productInfoService.saveProduct(productInfo);
        return ResponseEntity.ok(savedProduct);
        
    }
    
}
