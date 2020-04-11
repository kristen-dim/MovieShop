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
	
	public class Movie {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@Column
		private String name;
		private int duration;
		private String producerName;
		private String genre;
		private BigDecimal price;

		@ManyToOne
		@JoinColumn(name="shop_id")
		@JsonIgnoreProperties("movies")
		private Shop shop;
		
		public Movie() {
			
		}

		public Movie(Long id, String name, int duration, String producerName, String genre, BigDecimal price,
				Shop shop) {
			super();
			this.id = id;
			this.name = name;
			this.duration = duration;
			this.producerName = producerName;
			this.genre = genre;
			this.price = price;
			this.shop = shop;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getProducerName() {
			return producerName;
		}

		public void setProducerName(String producerName) {
			this.producerName = producerName;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Shop getShop() {
			return shop;
		}

		public void setShop(Shop shop) {
			this.shop = shop;
		}


	
		
}
