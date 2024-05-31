package com.example.aadhaar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import com.example.aadhaar.model.ServiceResponse;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Value("${uidai.api.url}")
    private String uidaiApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ServiceResponse<String> sendOtp(String aadhaarNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"aadhaarNumber\": \"" + aadhaarNumber + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        String url = String.format("%s/%s/%s/otp", uidaiApiUrl, aadhaarNumber.charAt(0), aadhaarNumber.charAt(1));

        logger.debug("Sending OTP to URL: {}", url);
        logger.debug("Request Body: {}", requestBody);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("OTP sent successfully to Aadhaar number: {}", aadhaarNumber);
                return new ServiceResponse<>(true, "OTP sent successfully.", response.getBody());
            } else {
                logger.error("Failed to send OTP to Aadhaar number: {}. Status: {}", aadhaarNumber, response.getStatusCode());
                return new ServiceResponse<>(false, "Failed to send OTP to UIDAI.", null);
            }
        } catch (RestClientException e) {
            logger.error("Exception occurred while sending OTP to Aadhaar number: {}", aadhaarNumber, e);
            return new ServiceResponse<>(false, "Exception occurred while sending OTP.", null);
        }
    }

    public ServiceResponse<Boolean> verifyOtp(String aadhaarNumber, String otp) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"aadhaarNumber\": \"" + aadhaarNumber + "\", \"otp\": \"" + otp + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        String url = String.format("%s/%s/%s/verify-otp", uidaiApiUrl, aadhaarNumber.charAt(0), aadhaarNumber.charAt(1));

        logger.debug("Verifying OTP at URL: {}", url);
        logger.debug("Request Body: {}", requestBody);

        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, entity, Boolean.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                logger.info("OTP verified successfully for Aadhaar number: {}", aadhaarNumber);
                return new ServiceResponse<>(true, "OTP verified successfully.", response.getBody());
            } else {
                logger.error("Failed to verify OTP for Aadhaar number: {}. Status: {}", aadhaarNumber, response.getStatusCode());
                return new ServiceResponse<>(false, "Failed to verify OTP with UIDAI.", null);
            }
        } catch (RestClientException e) {
            logger.error("Exception occurred while verifying OTP for Aadhaar number: {}", aadhaarNumber, e);
            return new ServiceResponse<>(false, "Exception occurred while verifying OTP.", null);
        }
    }
}
