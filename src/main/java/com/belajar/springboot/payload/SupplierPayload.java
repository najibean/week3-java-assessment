package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("contact")
	private String contact;
	
	public SupplierPayload(String name, String address, String contact) {
		super();
		this.name = name;
		this.address = address;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getContact() {
		return contact;
	}
}
