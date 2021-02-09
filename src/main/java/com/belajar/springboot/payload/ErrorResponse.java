package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
	@JsonProperty("error_message")
	private String error_message;
	@JsonProperty("message")
	private String message;
	
	public ErrorResponse(String error_message, String message) {
		super();
		this.error_message = error_message;
		this.message = message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
