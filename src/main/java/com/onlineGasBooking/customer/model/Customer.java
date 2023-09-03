package com.onlineGasBooking.customer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String userName;
	
	private String password;

	private String address;

	private Long mobileNumber;

	private String emailId;
	
	private long cylinderId;
	
//	@OneToOne
//	@JoinColumn(name = "cylinder_id")
//	private Cylinder cylinder;

//	@OneToMany
//  @JoinColumn(name = "gasBooking_id")
//	private GasBooking gasBooking;
}
