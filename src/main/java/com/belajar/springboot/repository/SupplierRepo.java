package com.belajar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.springboot.model.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer>{
	public Supplier findByNameIgnoreCase(String name);
}
