package com.swiftacad.resources;

import java.math.BigDecimal;

public class PromotionalMaterialResource {
	private Long id;
	private BigDecimal price;
	private String name;
	private long shopId;
	
	public PromotionalMaterialResource() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	
	
	
	
	

}
