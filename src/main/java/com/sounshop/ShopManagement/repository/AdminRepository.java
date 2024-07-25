package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {
}