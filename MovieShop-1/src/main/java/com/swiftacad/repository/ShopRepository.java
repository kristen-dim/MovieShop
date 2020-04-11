package com.swiftacad.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Shop;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long>{

}