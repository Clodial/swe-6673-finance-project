package com.swe6673.finance.service;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;

public interface FinanceAppService {

	public String createBankAccount(AccountCreationRequest request);
	public BankAccount bankAccountTransfer(TransferAssetsRequest request);
	public BankAccount getBankAccountDetails(String accountNumber);
	public boolean closeBankAccount(CloseAccountRequest closeAcctRqst);
	public BankAccount depositBankFunds(FundTransferDOA fundDeposit);
	public BankAccount withdrawBankFunds(FundTransferDOA fundWithdrawal);
	
}
