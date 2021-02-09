package com.belajar.springboot.model;

import java.util.Date;

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
@Table(name = "sale")
public class Sale extends Persistence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JoinColumn(name = "product_id", nullable = false)
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Product product;
	@JoinColumn(name = "member_id", nullable = false)
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Member member;
	@Column
	private Date date;
	@Column
	private long price;
	
	public Sale() {
		super();
	}

	public Sale(Product product, Member member, Date date, long price) {
		this.product = product;
		this.member = member;
		this.date = date;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}	
}
