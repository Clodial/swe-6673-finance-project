package com.swe6673.finance.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class BankAccount {
	
	private String accountNumber;
	private String routingNumber;
	private double assetHoldings;
	private AccountType type;
	private AccountStatus status;
	
}
