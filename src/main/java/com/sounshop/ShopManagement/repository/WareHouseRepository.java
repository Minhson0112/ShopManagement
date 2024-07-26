package com.sounshop.ShopManagement.repository;

import com.sounshop.ShopManagement.entity.WareHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends CrudRepository<WareHouse, Integer> {
    WareHouse findByProductId(Integer productId);
}
