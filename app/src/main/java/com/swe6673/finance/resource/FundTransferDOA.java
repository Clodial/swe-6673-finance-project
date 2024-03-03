package com.swe6673.finance.resource;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundTransferDOA {

	private double amount;
	@Id
	private String accountNumber;
	private String routingNumber;
	
}
