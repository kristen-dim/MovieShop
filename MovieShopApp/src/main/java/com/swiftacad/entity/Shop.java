package com.swiftacad.entity;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
	public class Shop {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String name;
		
	
		
		@OneToMany(targetEntity=Movie.class, mappedBy="shop")
		@LazyCollection(LazyCollectionOption.FALSE)
		private List<Movie> movies;
		
		@OneToMany(targetEntity=PromotionalMaterial.class, mappedBy="shop")
		@LazyCollection(LazyCollectionOption.FALSE)
		private List<PromotionalMaterial> promotionalMaterials;

		public Shop() {
			super();
			
		}

		public Shop(long id, String name, List<Movie> movies, List<PromotionalMaterial> promotionalMaterials) {
			super();
			this.id = id;
			this.name = name;
			this.movies = movies;
			this.promotionalMaterials = promotionalMaterials;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Movie> getMovies() {
			return movies;
		}

		public void setMovies(List<Movie> movies) {
			this.movies = movies;
		}

		public List<PromotionalMaterial> getPromotionalMaterials() {
			return promotionalMaterials;
		}

		public void setPromotionalMaterials(List<PromotionalMaterial> promotionalMaterials) {
			this.promotionalMaterials = promotionalMaterials;
		}
		
		
		
		
		
		

}