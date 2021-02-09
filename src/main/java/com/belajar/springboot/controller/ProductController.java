package com.belajar.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belajar.springboot.model.Product;
import com.belajar.springboot.model.Supplier;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.payload.ProductPayload;
import com.belajar.springboot.repository.ProductRepo;
import com.belajar.springboot.repository.SupplierRepo;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	@Autowired
	ProductRepo productRepo;
	@Autowired
	SupplierRepo supplierRepo;
	
	@GetMapping(path = "", produces = "application/json")
	public String home() {
		return "Hallo, welcome to /product path";
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Product product = productRepo.findById(id).orElse(null);
		
		try {
			if(product == null) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(
						"ID is null", 
						"ID yang dimasukkan tidak tersedia"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Product> product = productRepo.findAll();
		
//		List<ProductPayload> response = new ArrayList<>();
//		for(Product product : products) {
//			response.add(new ProductPayload(
//					product.getName(),
//					product.getPrice(),
//					product.getQuantity(),
//					product.getRe_order_level()
//		}
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
	}
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createProduct(@RequestBody ProductPayload payload) {
		Supplier supplier = supplierRepo.findByNameIgnoreCase(payload.getSupplierName());
		try {
			Product newProduct = new Product(payload.getName(), payload.getPrice(), payload.getQuantity(), payload.getRe_order_level(), supplier);
			productRepo.save(newProduct);
		}catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ProductPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductPayload payload) {
		
		Product existProduct = productRepo.findById(id).orElse(null);
		if(existProduct == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Product not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existProduct.setName(payload.getName());
		existProduct.setPrice(payload.getPrice());
		existProduct.setQuantity(payload.getQuantity());
		existProduct.setRe_order_level(payload.getRe_order_level());
		
		productRepo.save(existProduct);
		return new ResponseEntity<Product>(existProduct, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
		Product product = productRepo.findById(id).orElse(null);

		if(product == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		productRepo.deleteById(id);
		return new ResponseEntity<String>("Member has been deleted", HttpStatus.OK);
	}
}
