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

import com.belajar.springboot.model.Member;
import com.belajar.springboot.model.Product;
import com.belajar.springboot.model.Sale;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.payload.SalePayload;
import com.belajar.springboot.repository.MemberRepo;
import com.belajar.springboot.repository.ProductRepo;
import com.belajar.springboot.repository.SaleRepo;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {
	@Autowired
	SaleRepo saleRepo;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	MemberRepo memberRepo;
	
	@GetMapping(path = "", produces = "application/json")
	public String home() {
		return "Hallo, welcome to /sale path";
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Sale sale = saleRepo.findById(id).orElse(null);
		
		try {
			if(sale == null) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(
						"ID is null", 
						"ID yang dimasukkan tidak tersedia"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Sale>(sale, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Sale> sale = saleRepo.findAll();
		return new ResponseEntity<List<Sale>>(sale, HttpStatus.OK);
	}
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createSale(@RequestBody SalePayload payload) {
		Product product = productRepo.findById(payload.getProduct_id());
		Member member = memberRepo.findById(payload.getMember_id());
		
		try {
			Sale newSale = new Sale(product, member, payload.getDate(), payload.getPrice());
			saleRepo.save(newSale);
		}catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<SalePayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id, @RequestBody SalePayload payload) {
		
		Sale existSale = saleRepo.findById(id).orElse(null);
		if(existSale == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Sale not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existSale.setDate(payload.getDate());
		existSale.setPrice(payload.getPrice());
		
		saleRepo.save(existSale);
		return new ResponseEntity<Sale>(existSale, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
		Sale existSale = saleRepo.findById(id).orElse(null);

		if(existSale == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		saleRepo.deleteById(id);
		return new ResponseEntity<String>("Sale with id: " + id + " has been deleted", HttpStatus.OK);
	}
}
