package com.swe6673.finance.service;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;

public interface FinanceAppService {

	public String createAccount(AccountCreationRequest request);
	public BankAccount accountTransfer(TransferAssetsRequest request);
	public BankAccount getAccountDetails(String accountNumber);
	public boolean closeAccount(CloseAccountRequest closeAcctRqst);
	public BankAccount addFunds(FundTransferDOA fundDeposit);
	public BankAccount withdrawFunds(FundTransferDOA fundWithdrawal);
	
}
