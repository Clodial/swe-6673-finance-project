package com.swe6673.finance.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.swe6673.finance.config.AppConfig;
import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountStatus;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;
import com.swe6673.finance.service.FinanceAppService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppConfig.class)
public class FinanceAppControllerTest {

	private static final String API_URLPATH = "/api/bank";
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	FinanceAppService financeApp;
	
	@Test
	public void testFailConnectionAccountCreation() throws Exception {
		AccountCreationRequest request = new AccountCreationRequest(AccountType.CHECKING, -10.0);
		when(financeApp.createBankAccount(request)).thenThrow(Exception.class);
		this.mockMvc.perform(post(API_URLPATH + "/createaccount", request)).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testSuccessConnectionAccountCreation() throws Exception {
		AccountCreationRequest request = new AccountCreationRequest(AccountType.CHECKING, 10.0);
		BankAccount incomingAcct = new BankAccount("1234", "1234", 10.0, AccountType.CHECKING, AccountStatus.OPEN);
		when(financeApp.createBankAccount(request)).thenReturn(incomingAcct.getAccountNumber());
		this.mockMvc.perform(post(API_URLPATH + "/createaccount", request)).andExpect(status().isOk());
	}
	
	@Test
	public void testFailConnectionAcctTransferAssets() throws Exception {
		TransferAssetsRequest request = new TransferAssetsRequest(null, null, "1234", "1234", 0.0);
		when(financeApp.bankAccountTransfer(request)).thenThrow(Exception.class);
		this.mockMvc.perform(post(API_URLPATH + "/transferassets")).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testSuccessConnectionAcctTransferAssets() throws Exception {
		TransferAssetsRequest request = new TransferAssetsRequest("1233", "1233", "1234", "1234", 10.0);
		BankAccount incomingAcct = new BankAccount("1234", "1234", 10.0, AccountType.CHECKING, AccountStatus.OPEN);
		when(financeApp.bankAccountTransfer(request)).thenReturn(incomingAcct);
		this.mockMvc.perform(post(API_URLPATH + "/transferassets")).andExpect(status().isOk());
	}
	
	@Test
	public void testFailConnectGetDetails() throws Exception {
		String accountNumber = null;
		when(financeApp.getBankAccountDetails(accountNumber)).thenThrow(Exception.class);
		this.mockMvc.perform(get(API_URLPATH + "/getdetails/" + accountNumber)).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testSuccessConnectGetDetails() throws Exception {
		String accountNumber = "1234";
		BankAccount incomingAcct = new BankAccount("1234", "1234", 10.0, AccountType.CHECKING, AccountStatus.OPEN);
		when(financeApp.getBankAccountDetails(accountNumber)).thenReturn(incomingAcct);
		this.mockMvc.perform(get(API_URLPATH + "/getdetails/" + accountNumber)).andExpect(status().isOk());
	}
	
	@Test
	public void testFailConnectAccountClose() throws Exception {
		CloseAccountRequest request = new CloseAccountRequest(null);
		when(financeApp.closeBankAccount(request)).thenThrow(Exception.class);
		this.mockMvc.perform(post(API_URLPATH + "/closeaccount", request)).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testSuccessConnectAccountClose() throws Exception {
		CloseAccountRequest request = new CloseAccountRequest("1234");
		when(financeApp.closeBankAccount(request)).thenReturn(true);
		this.mockMvc.perform(post(API_URLPATH + "/closeaccount", request)).andExpect(status().isOk());
	}
	
	@Test
	public void testFailConnectDeposit() throws Exception {
		FundTransferDOA funds = new FundTransferDOA(10.0, null, null);
		when(financeApp.depositBankFunds(funds)).thenThrow(Exception.class);
		this.mockMvc.perform(post(API_URLPATH + "/addfunds", funds)).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testSuccessConnectDeposit() throws Exception {
		FundTransferDOA funds = new FundTransferDOA(10.0, "1234", "1234");
		BankAccount incomingAcct = 
				new BankAccount("1234", "1234", 10.0, AccountType.CHECKING, AccountStatus.OPEN);
		when(financeApp.depositBankFunds(funds)).thenReturn(incomingAcct);
		this.mockMvc.perform(post(API_URLPATH + "/addfunds", funds)).andExpect(status().isOk());
	}
	
	@Test
	public void testFailConnectWithdraw() throws Exception {
		FundTransferDOA funds = new FundTransferDOA(10.0, null, null);
		when(financeApp.withdrawBankFunds(funds)).thenThrow(Exception.class);
		this.mockMvc.perform(post(API_URLPATH + "/withdrawfunds", funds)).andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testSuccessConnectWithdraw() throws Exception {
		FundTransferDOA funds = new FundTransferDOA(10.0, "1234", "1234");
		BankAccount incomingAcct = 
				new BankAccount("1234", "1234", 10.0, AccountType.CHECKING, AccountStatus.OPEN);
		when(financeApp.withdrawBankFunds(funds)).thenReturn(incomingAcct);
		this.mockMvc.perform(post(API_URLPATH + "/withdrawfunds", funds)).andExpect(status().isOk());
	}
}
