package com.belajar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.springboot.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	public Product findById(int id);
}
