package com.swe6673.finance.resource;

import lombok.Data;

@Data
public class BankAccount {
	
	private String accountNumber;
	private String routingNumber;
	private float assetHoldings;
	private AccountType type;
	private AccountStatus status;
	
}
