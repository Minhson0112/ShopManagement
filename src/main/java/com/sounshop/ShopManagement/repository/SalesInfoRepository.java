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

    String SELECT_DTO = "SELECT new com.sounshop.ShopManagement.dto.SalesInfoDTO(s.tradingId, p.productName, s.clientName, s.address, s.tradingQuantity, s.price, s.tradingDate) ";
    String FROM_JOIN = "FROM SalesInfo s JOIN ProductInfo p ON s.productId = p.productId ";
    String WHERE_NOT_DELETED = "WHERE p.isDelete = false";

    @Query(SELECT_DTO + FROM_JOIN + WHERE_NOT_DELETED)
    List<SalesInfoDTO> findAllSalesInfo();

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title% AND s.tradingDate = :day AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDate(@Param("title") String title, @Param("day") LocalDate day);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title% AND s.tradingDate BETWEEN :dayBefore AND :dayAfter AND p.isDelete = false")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDateBetween(@Param("title") String title, @Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE s.tradingDate BETWEEN :dayBefore AND :dayAfter AND p.isDelete = false")
    List<SalesInfoDTO> findByTradingDateBetween(@Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE s.tradingDate = :day")
    List<SalesInfoDTO> findByTradingDate(@Param("day") LocalDate day);
}
