package com.onlineGasBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.onlineGasBooking.customer.dto.CustomerDto;
import com.onlineGasBooking.customer.model.Customer;
import com.onlineGasBooking.customer.repository.CustomerRepository;
import com.onlineGasBooking.customer.service.CustomerService;
import com.onlineGasBooking.customer.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@Mock
    private ModelMapper modelMapper;
	
	@InjectMocks
    private CustomerServiceImpl customerService; 
	
	@Mock
    private CustomerRepository custRepo;
	
	
	
    @Test
    public void testAddCustomer() {
        // Create a CustomerDto to be added
        CustomerDto newCustomer = new CustomerDto(1L, "John Doe","john@123","Bangalore sector 2",9901976541L,"john@gmail.com",1L);

        // Mocking Repository behavior
        Customer nCustomer = new Customer();
        Customer savedCustomer = new Customer();
        
        when(modelMapper.map(newCustomer, Customer.class)).thenReturn(nCustomer);
        when(custRepo.save(nCustomer)).thenReturn(savedCustomer);
        when(modelMapper.map(savedCustomer, CustomerDto.class)).thenReturn(newCustomer);

        // Testing the service method
        CustomerDto addedCustomerDto = customerService.addCustomer(newCustomer);

        // Assertions
        assertNotNull(addedCustomerDto);
        assertEquals("John Doe", addedCustomerDto.getUserName());
    }



	    @Test
	    public void testGetAllCustomers() {
	        // Mocking Customer objects
	        Customer customer1 = new Customer(1L, "John Doe","john@123","Bangalore sector 2",9901976541L,"john@gmail.com",1L);
	        Customer customer2 = new Customer(2L, "Dev","dev@123","Bangalore phase 1",8801976541L,"dev@gmail.com",1L);

	        List<Customer> mockCustomerList = new ArrayList<>();
	        mockCustomerList.add(customer1);
	        mockCustomerList.add(customer2);

	        // Mocking Repository behavior
	        when(custRepo.findAll()).thenReturn(mockCustomerList);

	        // Testing the service method
	        List<CustomerDto> customerDtoList = customerService.getAllCustomers();

	        // Assertions
	        assertEquals(2, customerDtoList.size());
	    }
	}

	
	
