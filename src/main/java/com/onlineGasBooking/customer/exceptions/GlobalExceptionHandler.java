package com.onlineGasBooking.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = CustomerNotPresentException.class)
	public ResponseEntity<String> customerNotPresentException(){
		return new ResponseEntity<>("No Customer Found!",HttpStatus.NOT_FOUND);
	}
}
