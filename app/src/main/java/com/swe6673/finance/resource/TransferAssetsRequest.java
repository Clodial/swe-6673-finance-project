package com.swe6673.finance.resource;

import lombok.Data;

@Data
public class TransferAssetsRequest {

	private String fromAccountNumber;
	private String fromRoutingNumber;
	private String toAccountNumber;
	private String toRoutingNumber;
	private float amount;
	
}
