package com.swe6673.finance.service;

import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;

public interface FinanceAppService {

	public String createAccount(float amount, AccountType type);
	public BankAccount getAccountDetails(String accountNumber);
	public boolean closeAccount(String accountNumber);
	public BankAccount addFunds(float amount, String accountNumber, String routingNumber);
	public BankAccount withdrawFunds(float amount, String accountNumber, String routingNumber);
	
}
