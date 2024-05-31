package com.example.aadhaar.model;


import jakarta.validation.constraints.NotNull;

public class AadhaarRequest {

	 @NotNull
	    private String aadhaarNumber;

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public AadhaarRequest(String aadhaarNumber) {
		super();
		this.aadhaarNumber = aadhaarNumber;
	}

	public AadhaarRequest() {
		super();
	}
	 
	 
}
