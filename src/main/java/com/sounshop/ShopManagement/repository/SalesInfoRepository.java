package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.dto.SalesInfoDTO;
import com.sounshop.ShopManagement.entity.SalesInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesInfoRepository extends CrudRepository<SalesInfo, Integer> {

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.isDelete = false")
    List<SalesInfoDTO> findAllSalesInfo();

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:title% AND s.tradingDate = :day AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDate(@Param("title") String title, @Param("day") LocalDate day);

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:title% AND s.tradingDate BETWEEN :dayBefore AND :dayAfter AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDateBetween(@Param("title") String title, @Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:title% AND s.tradingDate <= :dayAfter AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDateBefore(@Param("title") String title, @Param("dayAfter") LocalDate dayAfter);

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:title% AND s.tradingDate >= :dayBefore AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDateAfter(@Param("title") String title, @Param("dayBefore") LocalDate dayBefore);

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE p.productName LIKE %:title% AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContaining(@Param("title") String title);

    @Query("SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) " +
           "FROM SalesInfo s " +
           "JOIN ProductInfo p ON s.productId = p.productId " +
           "WHERE s.tradingDate = :day")
    List<SalesInfoDTO> findByTradingDate(@Param("day") LocalDate day);
}
