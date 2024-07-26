package com.sounshop.ShopManagement.service;

import com.sounshop.ShopManagement.entity.ProductInfo;
import com.sounshop.ShopManagement.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    public ProductInfo saveProduct(ProductInfo productInfo) {
        productInfoRepository.saveProductInfo(
            productInfo.getProductName(),
            productInfo.getCategory(),
            productInfo.getEntryPrice(),
            productInfo.getSellPrice(),
            productInfo.getAddDate().toString(),
            productInfo.getIsDelete()
        );
        return productInfo;
    }

   
    public Iterable<ProductInfo> getAllProductInfo() {
        return productInfoRepository.findAll();
    }

    public ProductInfo getProductInfoById(Integer productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    public void deleteProductInfo(Integer productId) {
        productInfoRepository.deleteById(productId);
    }
}
