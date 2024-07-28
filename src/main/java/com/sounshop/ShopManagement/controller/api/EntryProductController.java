package com.sounshop.ShopManagement.controller.api;

import com.sounshop.ShopManagement.dto.EntryProductDTO;
import com.sounshop.ShopManagement.entity.EntryProduct;
import com.sounshop.ShopManagement.entity.ProductInfo;
import com.sounshop.ShopManagement.service.EntryProductService;
import com.sounshop.ShopManagement.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/entryProduct")
public class EntryProductController {

    @Autowired
    private EntryProductService entryProductService;

    @Autowired
    private ProductInfoService productInfoService;

   

    @GetMapping
    public ResponseEntity<List<EntryProductDTO>> getAllEntryProducts() {
        System.out.println("check get  /api/entryProduct");
        List<EntryProductDTO> entryProductList = entryProductService.getAllEntryProducts();
        return ResponseEntity.ok(entryProductList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EntryProductDTO>> searchEntryProducts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate day,
            @RequestParam(required = false) LocalDate dayBefore,
            @RequestParam(required = false) LocalDate dayAfter) {
        System.out.println("check get  /api/entryProduct/search");
        List<EntryProductDTO> entryProductList = entryProductService.searchEntryProducts(title, day, dayBefore, dayAfter);
        return ResponseEntity.ok(entryProductList);
    }

    
    @PostMapping
    public ResponseEntity<EntryProduct> addEntryProduct(@RequestBody Map<String, Object> payload) {
        System.out.println("check post  /api/entryProduct");
        String productName =  (String) payload.get("productName");
        Integer quantity = Integer.parseInt(payload.get("quantity").toString());
        // lấy entity product info để tính toán tiền nhập và tên, loại...
        ProductInfo productInfo = productInfoService.findProductByName(productName);

        int productId = productInfo.getProductId();
        int entryPrice = productInfo.getEntryPrice();
        entryPrice *= quantity;
        LocalDate today = LocalDate.now();



        EntryProduct entryProduct = new EntryProduct();
        entryProduct.setPrice(entryPrice);
        entryProduct.setQuantity(quantity);
        entryProduct.setProductId(productId);
        entryProduct.setEntryDate(today);
        

        EntryProduct savedEntryProduct = entryProductService.saveEntryProduct(entryProduct);
        return ResponseEntity.ok(savedEntryProduct);
    }
}
