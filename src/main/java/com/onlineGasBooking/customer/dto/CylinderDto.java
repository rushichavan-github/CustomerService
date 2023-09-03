package com.onlineGasBooking.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CylinderDto {
	private Long cylinderId;
    @Size(min = 2, max = 20)
	private String type;
	@NotEmpty
	private float weight;
	@NotEmpty
	private float price;
}
