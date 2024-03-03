package com.swe6673.finance.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.swe6673.finance.service.FinanceAppService;

public class FinanceAppControllerTest {

	@Autowired
	FinanceAppController financeAppController;
	
	@MockBean
	FinanceAppService financeApp;
	
	@Test
	public void testFailConnectionAccountCreation() {
		
	}
	
	@Test
	public void testSuccessConnectionAccountCreation() {
		
	}
	
	@Test
	public void testFailConnectionAcctTransferAssets() {
		
	}
	
	@Test
	public void testSuccessConnectionAcctTransferAssets() {
		
	}
	
	@Test
	public void testFailConnectGetDetails() {
		
	}
	
	@Test
	public void testSuccessConnectGetDetails() {
		
	}
	
	@Test
	public void testFailConnectAccountClose() {
		
	}
	
	@Test
	public void testSuccessConnectAccountClose() {
		
	}
	
	@Test
	public void testFailConnectDeposit() {
		
	}
	
	@Test
	public void testSuccessConnectDeposit() {
		
	}
	
	@Test
	public void testFailConnectWithdraw() {
		
	}
	
	@Test
	public void testSuccessConnectWithdraw() {
		
	}
}
