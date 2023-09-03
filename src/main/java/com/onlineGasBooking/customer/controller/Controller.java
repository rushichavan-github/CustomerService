package com.onlineGasBooking.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.onlineGasBooking.customer.dto.CustomerDto;
import com.onlineGasBooking.customer.dto.ApiResponse;
import com.onlineGasBooking.customer.exceptions.CustomerNotPresentException;
import com.onlineGasBooking.customer.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class Controller {
	
	@Autowired
	private CustomerService cusServ;
	
	
	Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@PostMapping("/add")
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto){
		logger.info("Saving Customer Details");
		CustomerDto savedCustomer= cusServ.addCustomer(customerDto);
		logger.info("Customer Saved Successfully");
		return new ResponseEntity<>(savedCustomer,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/customersList")
	public ResponseEntity<List<CustomerDto>> viewAllCustomer(){
		logger.info("Fetch Customers List");
		List<CustomerDto> allCustomerDtos =  cusServ.getAllCustomers();
		logger.info("Customers List Fetched Successfully");
		return new ResponseEntity<>(allCustomerDtos,HttpStatus.OK);
	}
	
	@GetMapping("getById/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id) throws CustomerNotPresentException {
		logger.info("Getting Customer Details");
		CustomerDto customerDto= cusServ.getCustomerById(id);
		logger.info("Customer Details Fetched");
		return new ResponseEntity<>(customerDto, HttpStatus.OK);
	}
	
	@PutMapping("updateById/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) throws CustomerNotPresentException {	
		logger.info("Updating Customer Details");
		CustomerDto updatedCustomer= cusServ.updateCustomer(id, customerDto);
		logger.info("Customer Details Updated");
		return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("deleteCustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable long id) throws CustomerNotPresentException {
		logger.info("Customer Deleted Successfully");
		return new ResponseEntity<>(cusServ.deleteCustomer(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/getCustomerCylinderById")
	public ResponseEntity<List<ApiResponse>> getCusCylinderById() throws CustomerNotPresentException{
		List<ApiResponse> apiResponse =	 cusServ.getCusCylinderById();
		logger.info("Getting Customer and Cylinder Details");
		return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
	}
	
	
//	@GetMapping("validateByUsernamePassword/{username}/{password}")
//	public ResponseEntity<List<CustomerDto>> validateCustomer
//	(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password) throws CustomerNotPresentException{
//		List<Customer> customer = cusServ.validateCustomer(username,password);
//		if(customer.isEmpty()) {
//			throw new CustomerNotPresentException();
//		}
//		List<CustomerDto> customerDto = customer.stream
//				().map(cust-> modelMapper.map(cust, CustomerDto.class))
//				.toList();
//	
//		return new ResponseEntity<>(customerDto,HttpStatus.ACCEPTED);
//	}
}
