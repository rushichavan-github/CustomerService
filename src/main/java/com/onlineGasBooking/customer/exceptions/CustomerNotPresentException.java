package com.onlineGasBooking.customer.exceptions;

@SuppressWarnings("serial")
public class CustomerNotPresentException extends Exception{
		
	public CustomerNotPresentException(String msg) {
		super(msg);
	}
	public CustomerNotPresentException() {
		super();
	}
}
