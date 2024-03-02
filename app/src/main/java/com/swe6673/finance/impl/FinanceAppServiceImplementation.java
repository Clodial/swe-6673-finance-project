package com.swe6673.finance.impl;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;
import com.swe6673.finance.service.FinanceAppService;

public class FinanceAppServiceImplementation implements FinanceAppService {

	@Override
	public String createAccount(AccountCreationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccountDetails(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean closeAccount(CloseAccountRequest closeAcctRqst) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount addFunds(FundTransferDOA fundDeposit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount withdrawFunds(FundTransferDOA fundWithdraw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount accountTransfer(TransferAssetsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
