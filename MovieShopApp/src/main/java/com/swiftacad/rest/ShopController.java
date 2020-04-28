package com.swiftacad.rest;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swiftacad.entity.Movie;
import com.swiftacad.entity.PromotionalMaterial;
import com.swiftacad.entity.Shop;
import com.swiftacad.repository.MovieRepository;
import com.swiftacad.repository.PromotionalMaterialRepository;
import com.swiftacad.repository.ShopRepository;
import com.swiftacad.resources.ShopResource;

@Controller
public class ShopController {
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private PromotionalMaterialRepository promotionalMaterialRepository;
	
	
	@RequestMapping(value = "shop", method = RequestMethod.POST)
	public ResponseEntity<Shop> createShop(@RequestBody ShopResource shopResource) {
		Shop shop = createShopFromResource(shopResource);
		shopRepository.save(shop);
		
		for(Movie m : shop.getMovies()) {
			m.setShop(shop);
		movieRepository.save(m);
		}
		
		for(PromotionalMaterial pm : shop.getPromotionalMaterials()) {
			pm.setShop(shop);
		promotionalMaterialRepository.save(pm);
		}
		
		
		return new ResponseEntity<Shop>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "shop", method = RequestMethod.PUT)
	public ResponseEntity<Shop> updateShop(@RequestBody ShopResource shopResource) {
		Shop shop = createShopFromResource(shopResource);
		shopRepository.save(shop);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "shop/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<ShopResource> findById(@PathVariable("id") Long id) {
		Optional<Shop> optional = shopRepository.findById(id);
		
		return optional.map(shop -> new ResponseEntity<>(createResourceFromShop(shop), HttpStatus.OK))
             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}

	@RequestMapping(value = "promotionalMaterial/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<ShopResource>> findByName(@PathVariable("name") String name){
		List<Shop> shops = shopRepository.findByName(name);
		
		if (shops.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(shops.stream()
              .map(this::createResourceFromShop)
              .collect(toList()), HttpStatus.OK);
}
		@RequestMapping(value="shop/id/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id ) {
	promotionalMaterialRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	
 
	@RequestMapping(value="shop/name/{name}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteByName(@PathVariable("name") String name ) {
		promotionalMaterialRepository.deleteByName(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private Shop createShopFromResource(ShopResource shopResource) {
		Shop shop = new Shop();
        
        shop.setName(shopResource.getName());
        shop.setId(shopResource.getId());

        return shop;
    }

    private ShopResource createResourceFromShop(Shop shop) {
    	ShopResource shopResource = new ShopResource();
    	shopResource.setId(shop.getId());
    	shopResource.setName(shop.getName());
        

        return shopResource;
    }

	

}

