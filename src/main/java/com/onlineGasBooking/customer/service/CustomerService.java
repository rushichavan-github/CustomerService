package com.onlineGasBooking.customer.service;


import java.util.List;


import com.onlineGasBooking.customer.dto.ApiResponse;
import com.onlineGasBooking.customer.dto.CustomerDto;
import com.onlineGasBooking.customer.dto.CylinderDto;
import com.onlineGasBooking.customer.exceptions.CustomerNotPresentException;


public interface CustomerService {
	public CustomerDto addCustomer(CustomerDto customerDto);

	public List<CustomerDto> getAllCustomers();

	public CustomerDto getCustomerById(Long id) throws CustomerNotPresentException;

	public CustomerDto updateCustomer(long id, CustomerDto updateCustomerDto) throws CustomerNotPresentException;

	public String deleteCustomer(long id) throws CustomerNotPresentException;
	
	public List<ApiResponse> getCusCylinderById() throws CustomerNotPresentException;

//	public List<CylinderDto> getAllCylinders();

//	public List<Customer> validateCustomer(String username, String password);
}
