package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.entity.ProductInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;


@Repository
public interface ProductInfoRepository extends CrudRepository<ProductInfo, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productInfo (productName, category, entryPrice, sellPrice, addDate, isDelete) VALUES (:productName, :category, :entryPrice, :sellPrice, :addDate, :isDelete)", nativeQuery = true)
    void saveProductInfo(
        @Param("productName") String productName,
        @Param("category") String category,
        @Param("entryPrice") Integer entryPrice,
        @Param("sellPrice") Integer sellPrice,
        @Param("addDate") String addDate,
        @Param("isDelete") Boolean isDelete
    );
}
