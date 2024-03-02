package com.swe6673.finance.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountStatus;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;

public class FinanceAppServiceImplementationTest {

	FinanceAppServiceImplementation financeService;
	
	@Test
	public void testFailBadMoneyBankAccountCreate() throws Exception {
		AccountCreationRequest badAccount = new AccountCreationRequest(AccountType.CHECKING, -1.0);
		try {
			financeService.createBankAccount(badAccount);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Unable to create account");
		}
	}
	
	@Test
	public void testFailInvalidBankAccountCreate() throws Exception {
		AccountCreationRequest badAccount = new AccountCreationRequest(null, -1.0);
		try {
			financeService.createBankAccount(badAccount);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Unable to create account");
		}
	}
	
	@Test
	public void testSuccessSavingsAccountCreate() throws Exception {
		AccountCreationRequest goodAccount = new AccountCreationRequest(AccountType.SAVINGS, 0.0);
		try {
			String financeMessage = financeService.createBankAccount(goodAccount);
			assertEquals("Savings Account Created", financeMessage);
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void testSuccessCheckingAccountCreate() throws Exception {
		AccountCreationRequest goodAccount = new AccountCreationRequest(AccountType.CHECKING, 0.0);
		try {
			String financeMessage = financeService.createBankAccount(goodAccount);
			assertEquals("Checking Account Created", financeMessage);
		} catch (Exception e){
			fail();
		}
	}
	
	public void test
	
}
