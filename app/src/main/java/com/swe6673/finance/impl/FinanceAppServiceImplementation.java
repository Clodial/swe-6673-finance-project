package com.swe6673.finance.impl;

import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.service.FinanceAppService;

public class FinanceAppServiceImplementation implements FinanceAppService {

	@Override
	public String createAccount(float amount, AccountType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccountDetails(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean closeAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount addFunds(float amount, String accountNumber, String routingNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount withdrawFunds(float amount, String accountNumber, String routingNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
