package com.sounshop.ShopManagement.service;

import com.sounshop.ShopManagement.dto.ProductStorageDTO;
import com.sounshop.ShopManagement.entity.ProductInfo;
import com.sounshop.ShopManagement.repository.ProductInfoRepository;
import com.sounshop.ShopManagement.entity.Storage;
import com.sounshop.ShopManagement.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private StorageRepository storageRepository;

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

   
    public List<ProductInfo> getAllProductInfo() {
        return (List<ProductInfo>) productInfoRepository.findAllProductInfo();
    }

    public ProductInfo findProductByName(String name) {
        return productInfoRepository.findByProductName(name);
    }

   
    public List<ProductInfo> findProducts(String title, String category) {
        if (title != null && category != null) {
            return productInfoRepository.findByProductNameContainingAndCategory(title, category);
        } else if (title != null) {
            return productInfoRepository.findByProductNameContaining(title);
        } else if (category != null) {
            return productInfoRepository.findByCategory(category);
        } else {
            return productInfoRepository.findAllProductInfo();
        }
    }

    public ProductInfo updateProduct(Integer productId, String productName, String category, Integer entryPrice, Integer sellPrice) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
        productInfo.setProductName(productName);
        productInfo.setCategory(category);
        productInfo.setEntryPrice(entryPrice);
        productInfo.setSellPrice(sellPrice);
        return productInfoRepository.save(productInfo);
    }

    public void deleteProduct(Integer productId) {
        Storage storage = storageRepository.findByProductId(productId);
        if (storage != null && storage.getQuantity() == 0) {
            ProductInfo productInfo = productInfoRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
            productInfo.setIsDelete(true);
            productInfoRepository.save(productInfo);
        } else {
            throw new IllegalArgumentException("Cannot delete product with non-zero quantity in warehouse");
        }
    }

    public Optional<ProductStorageDTO> findProductStorageByProductName(String productName) {
        return productInfoRepository.findProductStorageByProductName(productName);
    }
}
