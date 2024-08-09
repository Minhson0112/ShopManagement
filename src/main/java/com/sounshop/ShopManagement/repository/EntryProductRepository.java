package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.dto.EntryProductDTO;
import com.sounshop.ShopManagement.entity.EntryProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntryProductRepository extends CrudRepository<EntryProduct, Integer> {

    @Query("SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) " +
           "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId WHERE p.isDelete = false")
    List<EntryProductDTO> findAllEntryProducts();

    @Query("SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) " +
           "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId WHERE p.productName LIKE %:title% AND ep.entryDate = :day AND p.isDelete = false")
    List<EntryProductDTO> findByProductNameContainingAndEntryDate(@Param("title") String title, @Param("day") LocalDate day);

    @Query("SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) " +
           "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId WHERE p.productName LIKE %:title% AND ep.entryDate BETWEEN :dayBefore AND :dayAfter AND p.isDelete = false")
    List<EntryProductDTO> findByProductNameContainingAndEntryDateBetween(@Param("title") String title, @Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query("SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) " +
           "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId WHERE p.productName LIKE %:title% AND p.isDelete = false")
    List<EntryProductDTO> findByProductNameContaining(@Param("title") String title);

    @Query("SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) " +
       "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId WHERE ep.entryDate = :day AND p.isDelete = false")
    List<EntryProductDTO> findByEntryDate(@Param("day") LocalDate day);

    @Query("SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) " +
       "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId " +
       "WHERE ep.entryDate BETWEEN :dayBefore AND :dayAfter AND p.isDelete = false")
    List<EntryProductDTO> findByEntryDateBetween(@Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

}
