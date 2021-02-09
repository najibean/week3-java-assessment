package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("price")
	private long price;
	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("re_order_level")
	private int re_order_level;
	@JsonProperty("supplier_name")
	private String supplierName;
	
	public ProductPayload(String name, long price, int quantity, int re_order_level, String supplierName) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.re_order_level = re_order_level;
		this.supplierName = supplierName;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getRe_order_level() {
		return re_order_level;
	}

	public String getSupplierName() {
		return supplierName;
	}
}
