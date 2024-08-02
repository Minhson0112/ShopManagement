package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.dto.StorageDTO;
import com.sounshop.ShopManagement.entity.Storage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StorageRepository extends CrudRepository<Storage, Integer> {
       
    Storage findByProductId(Integer productId);

    @Query("SELECT new com.sounshop.ShopManagement.dto.StorageDTO(s.productId, p.productName, p.category, s.quantity) " +
           "FROM Storage s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.isDelete = false")
    List<StorageDTO> findAllStorageDTOs();

    @Query("SELECT new com.sounshop.ShopManagement.dto.StorageDTO(s.productId, p.productName, p.category, s.quantity) " +
           "FROM Storage s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:productName% AND p.category = :category AND p.isDelete = false")
    List<StorageDTO> findByProductNameContainingAndCategory(String productName, String category);

    @Query("SELECT new com.sounshop.ShopManagement.dto.StorageDTO(s.productId, p.productName, p.category, s.quantity) " +
           "FROM Storage s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:productName% AND p.isDelete = false")
    List<StorageDTO> findByProductNameContaining(String productName);

    @Query("SELECT new com.sounshop.ShopManagement.dto.StorageDTO(s.productId, p.productName, p.category, s.quantity) " +
           "FROM Storage s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.category = :category AND p.isDelete = false")
    List<StorageDTO> findByCategory(String category);
}
