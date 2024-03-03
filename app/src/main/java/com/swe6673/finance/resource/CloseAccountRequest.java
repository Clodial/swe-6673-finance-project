package com.swe6673.finance.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloseAccountRequest {

	private String accountNumber;
	
}
