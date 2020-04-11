package com.swiftacad.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PromotionalMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	
	private BigDecimal price;
	private String name;

	@ManyToOne
	@JoinColumn(name="shop_id")
	@JsonIgnoreProperties("promotionalMaterials")
	private Shop shop;

	public PromotionalMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromotionalMaterial(Long id, BigDecimal price, String name, Shop shop) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.shop = shop;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	
	

}
