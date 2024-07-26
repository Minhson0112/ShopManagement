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
    @GetMapping
    public ResponseEntity<List<ProductInfo>> getAllProductInfo() {
        List<ProductInfo> productInfoList = productInfoService.getAllProductInfo();
        return ResponseEntity.ok(productInfoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductInfo>> getSearchResults(@RequestParam(required = false) String title, @RequestParam(required = false) String category) {
        List<ProductInfo> products = productInfoService.findProducts(title, category);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductInfo> updateProductInfo(@PathVariable Integer productId, @RequestBody Map<String, Object> payload) {
        String productName = (String) payload.get("productName");
        String category = (String) payload.get("category");
        Integer entryPrice = Integer.parseInt(payload.get("entryPrice").toString());
        Integer sellPrice = Integer.parseInt(payload.get("sellPrice").toString());

        System.out.println("check /api/productInfo/{productId} method: Put");

        ProductInfo updatedProduct = productInfoService.updateProduct(productId, productName, category, entryPrice, sellPrice);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
public ResponseEntity<Void> deleteProductInfo(@PathVariable Integer productId) {
    System.out.println("check /api/productInfo/ method: delete");
    try {
        productInfoService.deleteProduct(productId);
        return ResponseEntity.ok().build(); // Trả về mã trạng thái 200 OK mà không có nội dung
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build(); // Trả về mã trạng thái 400 Bad Request mà không có nội dung
    }
}
    
}
