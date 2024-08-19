package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.dto.SalesInfoDTO;
import com.sounshop.ShopManagement.dto.ProductSalesDTO;
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

    @Query(SELECT_DTO + FROM_JOIN)
    List<SalesInfoDTO> findAllSalesInfo();

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title% AND s.tradingDate = :day")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDate(@Param("title") String title, @Param("day") LocalDate day);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title% AND s.tradingDate BETWEEN :dayBefore AND :dayAfter")
    List<SalesInfoDTO> findByProductNameContainingAndTradingDateBetween(@Param("title") String title, @Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE s.tradingDate BETWEEN :dayBefore AND :dayAfter")
    List<SalesInfoDTO> findByTradingDateBetween(@Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE s.tradingDate = :day")
    List<SalesInfoDTO> findByTradingDate(@Param("day") LocalDate day);

    @Query("SELECT COUNT(s) FROM SalesInfo s WHERE s.tradingDate = :today")
    int countSalesInfoToday(@Param("today") LocalDate today);

    @Query("SELECT COUNT(s) FROM SalesInfo s WHERE s.tradingDate = :yesterday")
    int countSalesInfoYesterday(@Param("yesterday") LocalDate yesterday);

    @Query("SELECT SUM(s.price) FROM SalesInfo s WHERE s.tradingDate = :today")
    Integer sumPriceToday(@Param("today") LocalDate today);

    @Query("SELECT SUM(s.price) FROM SalesInfo s WHERE s.tradingDate = :yesterday")
    Integer sumPriceYesterday(@Param("yesterday") LocalDate yesterday);

    @Query("SELECT COUNT(s) FROM SalesInfo s WHERE MONTH(s.tradingDate) = :month AND YEAR(s.tradingDate) = :year")
    int orderOfMonth(@Param("month") int month, @Param("year") int year);
    
    @Query("SELECT SUM(s.price) FROM SalesInfo s WHERE MONTH(s.tradingDate) = :month AND YEAR(s.tradingDate) = :year ")
    Integer uriage(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(s.profit) FROM SalesInfo s WHERE MONTH(s.tradingDate) = :month AND YEAR(s.tradingDate) = :year ")
    Integer revenue(@Param("month") int month, @Param("year") int year);

    @Query("SELECT s.clientName FROM SalesInfo s WHERE MONTH(s.tradingDate) = :month AND YEAR(s.tradingDate) = :year GROUP BY s.clientName ORDER BY COUNT(s) DESC")
    List<String> findTopClient(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COUNT(s) FROM SalesInfo s WHERE s.clientName = :clientName AND MONTH(s.tradingDate) = :month AND YEAR(s.tradingDate) = :year")
    int countPurchasesByClient(@Param("clientName") String clientName, @Param("month") int month, @Param("year") int year);

    @Query("SELECT new com.sounshop.ShopManagement.dto.ProductSalesDTO(p.productName, SUM(s.tradingQuantity)) " +
       "FROM SalesInfo s JOIN ProductInfo p ON s.productId = p.productId " +
       "WHERE MONTH(s.tradingDate) = :month AND YEAR(s.tradingDate) = :year " +
       "GROUP BY p.productName")
    List<ProductSalesDTO> findProductSales(@Param("month") int month, @Param("year") int year);


    @Query("SELECT SUM(s.price), SUM(s.profit) " +
       "FROM SalesInfo s " +
       "WHERE s.tradingDate BETWEEN :startDate AND :endDate")
    List<Object[]> findRevenueByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);




}
