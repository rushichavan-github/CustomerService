package com.onlineGasBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        // Given
        CustomerDto inputDto = new CustomerDto();
        inputDto.setCustomerId(23);
        inputDto.setUserName("Rohit");
        inputDto.setPassword("rohit$321");
        inputDto.setAddress("Sector2 Bangalore");
        inputDto.setEmailId("rohit@gamil.com");
        inputDto.setMobileNumber(9901971822L);
        Customer mappedCustomer = new Customer();
        Customer savedCustomer = new Customer();
        
        // Mock behavior for modelMapper
        when(modelMapper.map(inputDto, Customer.class)).thenReturn(mappedCustomer);

        // Mock behavior for custRepo
        when(custRepo.save(mappedCustomer)).thenReturn(savedCustomer);
        
        // Mock behavior for modelMapper to map saved customer
        when(modelMapper.map(savedCustomer, CustomerDto.class)).thenReturn(inputDto);

        // When
        CustomerDto resultDto = customerService.addCustomer(inputDto);

        // Then
        assertEquals(inputDto,resultDto);
        
    }
	
	
}
