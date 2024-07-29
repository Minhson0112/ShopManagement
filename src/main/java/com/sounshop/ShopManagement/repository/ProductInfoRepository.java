package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.entity.ProductInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

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

    @Query(value = "SELECT productId, productName, category, entryPrice, sellPrice, addDate, isDelete FROM productInfo WHERE isDelete = false", nativeQuery = true)
    List<ProductInfo> findAllProductInfo();

    @Query(value = "SELECT * FROM productInfo WHERE productName LIKE CONCAT('%',:productName,'%') AND category = :category AND isDelete = false", nativeQuery = true)
    List<ProductInfo> findByProductNameContainingAndCategory(@Param("productName") String productName, @Param("category") String category);

    @Query(value = "SELECT * FROM productInfo WHERE productName LIKE CONCAT('%',:productName,'%') AND isDelete = false", nativeQuery = true)
    List<ProductInfo> findByProductNameContaining(@Param("productName") String productName);

    @Query(value = "SELECT * FROM productInfo WHERE category = :category AND isDelete = false", nativeQuery = true)
    List<ProductInfo> findByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM productInfo WHERE productName = :productName AND isDelete = false", nativeQuery = true)
    ProductInfo findByProductName(@Param("productName") String productName);
}
