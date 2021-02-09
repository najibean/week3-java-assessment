package com.belajar.springboot.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberPayload {
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("address")
	private String address;
	@JsonProperty("contact")
	private String contact;
	@JsonProperty("expires")
	private Date expires;
	
	public MemberPayload(String firstName, String surname, String address, String contact, Date expires) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.contact = contact;
		this.expires = expires;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getContact() {
		return contact;
	}

	public Date getExpires() {
		return expires;
	}
}
