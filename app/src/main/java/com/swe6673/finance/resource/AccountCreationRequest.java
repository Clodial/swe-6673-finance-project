package com.swe6673.finance.resource;

import lombok.Data;

@Data
public class AccountCreationRequest {

	private AccountType type;
	private float amount;
	
}
