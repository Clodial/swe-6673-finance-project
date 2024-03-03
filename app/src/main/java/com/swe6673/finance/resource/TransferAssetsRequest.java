package com.swe6673.finance.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferAssetsRequest {

	private String fromAccountNumber;
	private String fromRoutingNumber;
	private String toAccountNumber;
	private String toRoutingNumber;
	private double amount;
	
}
