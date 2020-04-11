package com.swiftacad.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swiftacad.entity.Movie;
import com.swiftacad.entity.PromotionalMaterial;
import com.swiftacad.entity.Shop;
import com.swiftacad.repository.MovieRepository;
import com.swiftacad.repository.PromotionalMaterialRepository;
import com.swiftacad.repository.ShopRepository;

@Controller
public class ShopController {
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private PromotionalMaterialRepository promotionalMaterialRepository;
	
	
	@RequestMapping(value = "shop", method = RequestMethod.POST)
	public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
		Shop s = shopRepository.save(shop);
		
		for(Movie m : shop.getMovies()) {
			m.setShop(s);
		movieRepository.save(m);
		}
		
		for(PromotionalMaterial pm : shop.getPromotionalMaterials()) {
			pm.setShop(s);
		promotionalMaterialRepository.save(pm);
		}
		
		
		return new ResponseEntity<Shop>(HttpStatus.OK);
	}
}