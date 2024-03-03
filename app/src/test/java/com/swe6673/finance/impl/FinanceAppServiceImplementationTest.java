package com.swe6673.finance.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountStatus;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;

public class FinanceAppServiceImplementationTest {

	@Autowired
	FinanceAppServiceImplementation financeService;
	
	@Test
	public void testFailBadMoneyBankAccountCreate() throws Exception {
		AccountCreationRequest badAccount = new AccountCreationRequest(AccountType.CHECKING, -1.0);
		try {
			financeService.createBankAccount(badAccount);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Bank Account cannot start with a negative balance");
		}
	}
	
	@Test
	public void testFailInvalidBankAccountCreate() throws Exception {
		AccountCreationRequest badAccount = new AccountCreationRequest(null, -1.0);
		try {
			financeService.createBankAccount(badAccount);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Bank Account Type is not provided");
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
	
	@Test
	public void testFailNullBankTransfer() throws Exception {
		
	}
	
	@Test
	public void testFailInvalidBankTransfer() throws Exception {
		
	}
	
	@Test
	public void testSuccessBankTransfer() throws Exception {
		
	}
	
	@Test
	public void testFailInvalidBankDetails() throws Exception {
		
	}
	
	@Test
	public void testSuccessBankDetails() throws Exception{
		
	}
	
	@Test
	public void testFailInvalidRoutingCloseAccount() throws Exception {
		
	}
	
	@Test
	public void testFailInvalidaccNumberCloseAccount() throws Exception {
		
	}
	
	@Test
	public void testSuccessCloseAccount() throws Exception {
		
	}
	
	@Test
	public void testFailInvalidDepositBank() throws Exception {
		
	}
	
	@Test
	public void testFailNegativeMoneyDepositBank() throws Exception {
		
	}
	
	@Test
	public void testSuccessDepositDepositBank() throws Exception {
		
	}
	
	@Test
	public void testFailInvalidWithdrawBank() throws Exception {
		
	}
	
	@Test
	public void testFailNegativeWithdrawBank() throws Exception {
		
	}
	
	@Test
	public void testSuccessOverdraftWithdrawBank() throws Exception {
		
	}
	
	@Test
	public void testSuccessNormalWithdrawBank() throws Exception {
		
	}
}
