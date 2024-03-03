package com.swe6673.finance.resource;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BankAccount {
	@Id
	String accountNumber;

	String routingNumber;
	float assetHoldings;
	AccountType type;
	AccountStatus status;
	
}
