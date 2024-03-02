package com.swe6673.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;
import com.swe6673.finance.service.FinanceAppService;

@RestController
public class FinanceAppController {

	@Autowired
	FinanceAppService financeApp;
	
	@PostMapping("/createaccount")
	public ResponseEntity<String> accountCreation(
			@RequestBody AccountCreationRequest request) {
		return ResponseEntity.ok(
				financeApp.createAccount(request));
	}
	
	@PostMapping("/transferassets")
	public ResponseEntity<BankAccount> accountTransferAssets(@RequestBody TransferAssetsRequest request) {
		return ResponseEntity.ok(financeApp.accountTransfer(request));
	}
	
	@GetMapping("/getdetails/{accountNumber}")
	public ResponseEntity<BankAccount> accountGetDetails(
			@PathVariable("accountNumber") String accountNumber) {
		return ResponseEntity.ok(financeApp.getAccountDetails(accountNumber));
	}
	
	@PostMapping("/closeaccount")
	public ResponseEntity<Boolean> accountClose(
			@RequestBody CloseAccountRequest closeAcctRqst) {
		return ResponseEntity.ok(financeApp.closeAccount(closeAcctRqst));
	}
	
	@PostMapping("/addfunds")
	public ResponseEntity<BankAccount> accountDepositFunds(
			@RequestBody FundTransferDOA fundDeposit) {
		return ResponseEntity.ok(financeApp.addFunds(fundDeposit));
	}
	
	@PostMapping("/withdrawfunds")
	public ResponseEntity<BankAccount> accountWithdrawFunds(
			@RequestBody FundTransferDOA fundWithdraw) {
		return ResponseEntity.ok(financeApp.withdrawFunds(fundWithdraw));
	}
	
}
