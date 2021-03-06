package com.swiftacad.rest;

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

import com.swiftacad.repository.PromotionalMaterialRepository;

@Controller
public class PromotionalMaterialController {
	@Autowired
	private PromotionalMaterialRepository promotionalMaterialRepository;

	@RequestMapping(value="promotionalMaterial", method = RequestMethod.POST)
	public ResponseEntity<PromotionalMaterial> addPromotionalMaterial(@RequestBody PromotionalMaterial promotionalMaterial){
		System.out.println(promotionalMaterialRepository);
		promotionalMaterialRepository.save(promotionalMaterial);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="promotionalMaterial", method = RequestMethod.PUT)
	public ResponseEntity<PromotionalMaterial> updatePromotionalMaterial(@RequestBody PromotionalMaterial promotionalMaterial) {
		promotionalMaterialRepository.save(promotionalMaterial);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "promotionalMaterial/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<PromotionalMaterial> findById(@PathVariable("id") Long id) {
		Optional<PromotionalMaterial> optional = promotionalMaterialRepository.findById(id);
		
		if( optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "promotionalMaterial/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<PromotionalMaterial>> findByName(@PathVariable("name") String name){
		List<PromotionalMaterial> promotionalMaterials = promotionalMaterialRepository.findByName(name);
		
		if(promotionalMaterials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PromotionalMaterial>>(promotionalMaterials, HttpStatus.OK);
	}
	
	@RequestMapping(value = "promotionalMaterial/price/{price}", method = RequestMethod.GET)
	public ResponseEntity<List<PromotionalMaterial>> findByPrice(@PathVariable("price") BigDecimal price){
		List<PromotionalMaterial> promotionalMaterials = promotionalMaterialRepository.findByPrice(price);
		
		if(promotionalMaterials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<PromotionalMaterial>>(promotionalMaterials, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "promotionalMaterial", method = RequestMethod.GET)
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
	
	

}
