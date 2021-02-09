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

import com.belajar.springboot.model.Supplier;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.payload.SupplierPayload;
import com.belajar.springboot.repository.SupplierRepo;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {
	@Autowired
	SupplierRepo supplierRepo;
	
	@GetMapping(path = "", produces = "application/json")
	public String home() {
		return "Hallo, welcome to /supplier path";
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Supplier supplier = supplierRepo.findById(id).orElse(null);
		
		try {
			if(supplier == null) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(
						"ID is null", 
						"ID yang dimasukkan tidak tersedia"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Supplier> supplier = supplierRepo.findAll();
		return new ResponseEntity<List<Supplier>>(supplier, HttpStatus.OK);
	}
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createSupplier(@RequestBody SupplierPayload payload) {
		try {
			Supplier newSupplier = new Supplier(payload.getName(), payload.getAddress(), payload.getContact());
			supplierRepo.save(newSupplier);
		}catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<SupplierPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateSupplier(@PathVariable("id") Integer id, @RequestBody SupplierPayload payload) {
		
		Supplier existSupplier = supplierRepo.findById(id).orElse(null);
		if(existSupplier == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Product not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existSupplier.setName(payload.getName());
		existSupplier.setAddress(payload.getAddress());
		existSupplier.setContact(payload.getContact());
		
		supplierRepo.save(existSupplier);
		return new ResponseEntity<Supplier>(existSupplier, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable("id") Integer id) {
		Supplier existSupplier = supplierRepo.findById(id).orElse(null);

		if(existSupplier == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Product not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		supplierRepo.deleteById(id);
		return new ResponseEntity<String>("Member has been deleted", HttpStatus.OK);
	}
}
