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

    String SELECT_DTO = "SELECT new com.sounshop.ShopManagement.dto.EntryProductDTO(ep.id, p.productName, p.category, ep.quantity, ep.price, ep.entryDate) ";
    String FROM_JOIN = "FROM EntryProduct ep JOIN ProductInfo p ON ep.productId = p.productId ";
    String WHERE_NOT_DELETED = "WHERE p.isDelete = false";

    @Query(SELECT_DTO + FROM_JOIN)
    List<EntryProductDTO> findAllEntryProducts();

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title% AND ep.entryDate = :day")
    List<EntryProductDTO> findByProductNameContainingAndEntryDate(@Param("title") String title, @Param("day") LocalDate day);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title% AND ep.entryDate BETWEEN :dayBefore AND :dayAfter")
    List<EntryProductDTO> findByProductNameContainingAndEntryDateBetween(@Param("title") String title, @Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE p.productName LIKE %:title%")
    List<EntryProductDTO> findByProductNameContaining(@Param("title") String title);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE ep.entryDate = :day")
    List<EntryProductDTO> findByEntryDate(@Param("day") LocalDate day);

    @Query(SELECT_DTO + FROM_JOIN + "WHERE ep.entryDate BETWEEN :dayBefore AND :dayAfter")
    List<EntryProductDTO> findByEntryDateBetween(@Param("dayBefore") LocalDate dayBefore, @Param("dayAfter") LocalDate dayAfter);

    @Query("SELECT SUM(s.price) FROM EntryProduct s WHERE MONTH(s.entryDate) = :month AND YEAR(s.entryDate) = :year")
    Integer totalBuy(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COUNT(s) FROM EntryProduct s WHERE MONTH(s.entryDate) = :month AND YEAR(s.entryDate) = :year")
    int buysOfMonth(@Param("month") int month, @Param("year") int year);
}
