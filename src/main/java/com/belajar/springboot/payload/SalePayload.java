package com.belajar.springboot.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SalePayload {
	@JsonProperty("product_id")
	private int product_id;
	@JsonProperty("member_id")
	private int member_id;
	@JsonProperty("date")
	private Date date;
	@JsonProperty("price")
	private long price;
	
	public SalePayload(int product_id, int member_id, Date date, long price) {
		super();
		this.product_id = product_id;
		this.member_id = member_id;
		this.date = date;
		this.price = price;
	}

	public int getProduct_id() {
		return product_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public Date getDate() {
		return date;
	}

	public long getPrice() {
		return price;
	}
}
