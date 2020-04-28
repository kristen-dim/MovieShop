package com.swiftacad.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Shop;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long>{

	List<Shop> findByName(String name);

}