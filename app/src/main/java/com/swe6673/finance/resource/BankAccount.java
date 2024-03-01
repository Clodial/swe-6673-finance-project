package com.swe6673.finance.resource;

import lombok.Data;

@Data
public class BankAccount {
	
	String accountNumber;
	String routingNumber;
	float assetHoldings;
	AccountType type;
	AccountStatus status;
	
}
