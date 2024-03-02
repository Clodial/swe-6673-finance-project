package com.swe6673.finance.impl;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;
import com.swe6673.finance.service.FinanceAppService;

public class FinanceAppServiceImplementation implements FinanceAppService {

	@Override
	public String createBankAccount(AccountCreationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getBankAccountDetails(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean closeBankAccount(CloseAccountRequest closeAcctRqst) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount depositBankFunds(FundTransferDOA fundDeposit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount withdrawBankFunds(FundTransferDOA fundWithdraw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount bankAccountTransfer(TransferAssetsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
