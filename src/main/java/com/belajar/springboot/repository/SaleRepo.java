package com.belajar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.springboot.model.Sale;

public interface SaleRepo extends JpaRepository<Sale, Integer>{

}
