package com.onlineGasBooking.customer.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	private long customerId;
	
	@Size(min = 3 , max = 20)
	private String userName;
	
	@Size(min = 6 , max = 20)
	private String password;
	
	@Size(min = 10,max = 50)
	private String address;
	
	@Size(min = 10,max = 10)
	private Long mobileNumber;
	
	private String emailId;
	
	private long cylinderId;
}
