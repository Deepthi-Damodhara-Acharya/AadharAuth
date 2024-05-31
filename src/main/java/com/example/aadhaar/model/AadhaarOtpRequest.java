package com.example.aadhaar.model;

import jakarta.validation.constraints.NotNull;

public class AadhaarOtpRequest {
	
	  @NotNull
	    private String aadhaarNumber;

	    @NotNull
	    private String otp;

		public String getAadhaarNumber() {
			return aadhaarNumber;
		}

		public void setAadhaarNumber(String aadhaarNumber) {
			this.aadhaarNumber = aadhaarNumber;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public AadhaarOtpRequest() {
			super();
		}

		public AadhaarOtpRequest(String aadhaarNumber, String otp) {
			super();
			this.aadhaarNumber = aadhaarNumber;
			this.otp = otp;
		}
	    
}
