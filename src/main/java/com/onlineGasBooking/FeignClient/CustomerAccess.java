package com.onlineGasBooking.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlineGasBooking.customer.dto.CylinderDto;

@FeignClient(name = "cylinder-service",url = "http://localhost:8020/cylinderManagement")
public interface CustomerAccess {
		
	@GetMapping("/getCylinderById/{id}")
	public CylinderDto findCylinderById(@PathVariable(name="id") Long id);
	
	
}
