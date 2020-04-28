package com.swiftacad.rest;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.swiftacad.entity.PromotionalMaterial;
import com.swiftacad.entity.Shop;
import com.swiftacad.repository.PromotionalMaterialRepository;
import com.swiftacad.repository.ShopRepository;
import com.swiftacad.resources.PromotionalMaterialResource;

@Controller
public class PromotionalMaterialController {
	@Autowired
	private PromotionalMaterialRepository promotionalMaterialRepository;
	@Autowired
    private ShopRepository shopRepository;

	@RequestMapping(value="promotionalMaterial", method = RequestMethod.POST)
	public ResponseEntity<PromotionalMaterial> addPromotionalMaterial(@RequestBody PromotionalMaterialResource promotionalMaterialResource){
		PromotionalMaterial promotionalMaterial = createPromotionalMaterialFromResource(promotionalMaterialResource);
		promotionalMaterialRepository.save(promotionalMaterial);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="promotionalMaterial", method = RequestMethod.PUT)
	public ResponseEntity<PromotionalMaterial> updatePromotionalMaterial(@RequestBody PromotionalMaterialResource promotionalMaterialResource) {
		PromotionalMaterial promotionalMaterial = createPromotionalMaterialFromResource(promotionalMaterialResource);
		promotionalMaterialRepository.save(promotionalMaterial);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "promotionalMaterial/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<PromotionalMaterialResource> findById(@PathVariable("id") Long id) {
		Optional<PromotionalMaterial> optional = promotionalMaterialRepository.findById(id);
		
		return optional.map(promotionalMaterial -> new ResponseEntity<>(createResourceFromPromotionalMaterial(promotionalMaterial), HttpStatus.OK))
             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}

	@RequestMapping(value = "promotionalMaterial/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<PromotionalMaterialResource>> findByName(@PathVariable("name") String name){
		List<PromotionalMaterial> promotionalMaterials = promotionalMaterialRepository.findByName(name);
		
		if (promotionalMaterials.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(promotionalMaterials.stream()
              .map(this::createResourceFromPromotionalMaterial)
              .collect(toList()), HttpStatus.OK);
}
	
	@RequestMapping(value = "promotionalMaterial/price/{price}", method = RequestMethod.GET)
	public ResponseEntity<List<PromotionalMaterialResource>> findByPrice(@PathVariable("price") BigDecimal price){
		List<PromotionalMaterial> promotionalMaterials = promotionalMaterialRepository.findByPrice(price);
		
		if (promotionalMaterials.isEmpty()) {
	          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	      return new ResponseEntity<>(promotionalMaterials.stream()
	              .map(this::createResourceFromPromotionalMaterial)
	              .collect(toList()), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "promotionalMaterials", method = RequestMethod.GET)
	public ResponseEntity<List<PromotionalMaterial>> findAll() {
		 List<PromotionalMaterial> promotionalMaterials = new ArrayList<>();
		promotionalMaterialRepository.findAll().forEach( prMat -> promotionalMaterials.add(prMat));
		
		return new ResponseEntity<>(promotionalMaterials, HttpStatus.OK);
	}
	

	
	@RequestMapping(value="promotionalMaterial/id/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id ) {
	promotionalMaterialRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	
 
	@RequestMapping(value="promotionaMaterial/name/{name}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteByName(@PathVariable("name") String name ) {
		promotionalMaterialRepository.deleteByName(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private PromotionalMaterial createPromotionalMaterialFromResource(PromotionalMaterialResource promotionalMaterialResource) {
        PromotionalMaterial promotionalMaterial = new PromotionalMaterial();
        Shop shop = shopRepository.findById(promotionalMaterialResource.getShopId()).orElseThrow(IllegalArgumentException::new);
        promotionalMaterial.setShop(shop);
        promotionalMaterial.setName(promotionalMaterialResource.getName());
        promotionalMaterial.setPrice(promotionalMaterialResource.getPrice());

        return promotionalMaterial;
    }

    private PromotionalMaterialResource createResourceFromPromotionalMaterial(PromotionalMaterial promotionalMaterial) {
    	PromotionalMaterialResource promotionalMaterialResource = new PromotionalMaterialResource();
    	promotionalMaterialResource.setShopId(promotionalMaterial.getShop().getId());
    	promotionalMaterialResource.setPrice(promotionalMaterial.getPrice());
    	promotionalMaterialResource.setName(promotionalMaterial.getName());
        

        return promotionalMaterialResource;
    }

}
