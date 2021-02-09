package com.belajar.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product extends Persistence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private long price;
	@Column(nullable = false)
	private int quantity;
	@Column
	private int re_order_level;
	@JoinColumn(name = "supplier_id", nullable = false)
	@ManyToOne(targetEntity = Supplier.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Supplier supplier;

	public Product() {
		super();
	}

	public Product(String name, long price, int quantity, int re_order_level, Supplier supplier) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.re_order_level = re_order_level;
		this.supplier = supplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRe_order_level() {
		return re_order_level;
	}

	public void setRe_order_level(int re_order_level) {
		this.re_order_level = re_order_level;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
