package com.belajar.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "member")
public class Member extends Persistence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	// >>> percobaan menggunakan IDENTITY
	@Column
	private int id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "contact", nullable = false)
	private String contact;
	
	@Column(name = "expires", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expires;

	// >>> constructor using SUPER-CLASS
	public Member() {
		super();
	}

	// >>> constructor using field, yang super() dicoba dibiarkan dahulu, apakah bisa menggantikan SUPER-CLASS?
	public Member(String firstName, String surname, String address, String contact, Date expires) {
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.contact = contact;
		this.expires = expires;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}
}
