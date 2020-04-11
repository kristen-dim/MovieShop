package com.swiftacad.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.PromotionalMaterial;

@Repository

	public interface PromotionalMaterialRepository extends CrudRepository<PromotionalMaterial, Long>{
		List<PromotionalMaterial> findByName(String name);

		List<PromotionalMaterial> findByPrice(BigDecimal price);

		void deleteByName(String name);
	}
