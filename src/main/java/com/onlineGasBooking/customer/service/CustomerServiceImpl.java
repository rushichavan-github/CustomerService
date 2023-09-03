package com.onlineGasBooking.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlineGasBooking.FeignClient.CustomerAccess;
import com.onlineGasBooking.customer.dto.ApiResponse;
import com.onlineGasBooking.customer.dto.CustomerDto;
import com.onlineGasBooking.customer.dto.CylinderDto;
import com.onlineGasBooking.customer.exceptions.CustomerNotPresentException;
import com.onlineGasBooking.customer.model.Customer;
import com.onlineGasBooking.customer.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private CustomerAccess cylinderAccess;
	
	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer customerSave = modelMapper.map(customerDto, Customer.class);
		Customer savedCustomer = custRepo.save(customerSave);
		return modelMapper.map(savedCustomer, CustomerDto.class);
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		List<Customer> cusList = custRepo.findAll();
		return cusList.stream()
		.map(customer -> modelMapper.map(customer, CustomerDto.class))
		.toList();
	}

	@Override
	public CustomerDto getCustomerById(Long id) throws CustomerNotPresentException {
	  Optional<Customer> customer = custRepo.findById(id);
	  if(customer.isPresent()) {
		  Customer customer2 = customer.get();
		  return modelMapper.map(customer2, CustomerDto.class);
	  }else {
		  throw new CustomerNotPresentException();
	  }
	}

	@Override
	public CustomerDto updateCustomer(long id, CustomerDto updateCustomer) throws CustomerNotPresentException {
		Optional<Customer> customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			throw new CustomerNotPresentException();
		}
		Customer cust=customer.get();
		cust.setUserName(updateCustomer.getUserName());
		cust.setPassword(updateCustomer.getPassword());
		cust.setMobileNumber(updateCustomer.getMobileNumber());
		cust.setAddress(updateCustomer.getAddress());
		cust.setEmailId(updateCustomer.getEmailId());
		
		Customer updatedCustomer = custRepo.save(cust);
		return modelMapper.map(updatedCustomer, CustomerDto.class);
		
	}

	@Override
	public String deleteCustomer(long id) throws CustomerNotPresentException {
		
		if(custRepo.existsById(id)) {
			custRepo.deleteById(id);
			return "Customer Details Deleted Successfully";
		}
		throw new CustomerNotPresentException();
	}

	@Override
	public List<ApiResponse> getCusCylinderById() throws CustomerNotPresentException {
		List<Customer> customer = custRepo.findAll();
		List<ApiResponse> allCusCyl = new ArrayList<>();
		for(Customer cus:customer) {
			ApiResponse apiResponse = new ApiResponse();
			CustomerDto customerDto =	modelMapper.map(cus, CustomerDto.class);
			apiResponse.setCustomer(customerDto);
			CylinderDto cylinderDto = 	cylinderAccess.findCylinderById(cus.getCylinderId());
			apiResponse.setCylinder(cylinderDto);
			allCusCyl.add(apiResponse);
		}
		
		return allCusCyl;
	}
	
	
//	@Override
//	public List<Customer> validateCustomer(String username, String password) {
//		return custRepo.findAll().stream()
//		.filter(Customer->Customer.getUserName().equals(username) && Customer.getPassword().equals(password))
//		.toList();
//	}

}
