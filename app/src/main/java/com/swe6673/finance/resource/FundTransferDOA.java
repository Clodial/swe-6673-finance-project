package com.swe6673.finance.resource;

import lombok.Data;

@Data
public class FundTransferDOA {

	private double amount;
	private String accountNumber;
	private String routingNumber;
	
}
