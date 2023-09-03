package com.onlineGasBooking.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineGasBooking.customer.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Optional<Customer> findByuserName(String userName);
}
