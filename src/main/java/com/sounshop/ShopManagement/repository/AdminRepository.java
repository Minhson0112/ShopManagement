package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

    @Query(value = "SELECT * FROM admins WHERE userId = ?1", nativeQuery = true)
    Optional<Admin> findByUserId(String userId);
}
