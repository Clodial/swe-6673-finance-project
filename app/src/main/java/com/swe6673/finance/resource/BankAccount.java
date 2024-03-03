package com.swe6673.finance.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class BankAccount {
	
	@Id
	private String accountNumber;
	private String routingNumber;
	private double assetHoldings;
	private AccountType type;
	private AccountStatus status;
	
}
