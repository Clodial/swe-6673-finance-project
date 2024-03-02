package com.swe6673.finance.resource;

import lombok.Data;

@Data
public class FundTransferDOA {

	private float amount;
	private String accountNumber;
	private String routingNumber;
	
}
