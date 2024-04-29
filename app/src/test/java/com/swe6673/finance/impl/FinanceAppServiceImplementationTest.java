package com.swe6673.finance.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import com.swe6673.finance.repository.BankRepository;
import com.swe6673.finance.resource.AccountCreationRequest;
import com.swe6673.finance.resource.AccountStatus;
import com.swe6673.finance.resource.AccountType;
import com.swe6673.finance.resource.BankAccount;
import com.swe6673.finance.resource.CloseAccountRequest;
import com.swe6673.finance.resource.FundTransferDOA;
import com.swe6673.finance.resource.TransferAssetsRequest;

public class FinanceAppServiceImplementationTest {

	@InjectMocks
	public FinanceAppServiceImplementation financeService;
	
	@Mock
	public BankRepository bankRepo;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	public void testFailBadMoneyBankAccountCreate() throws Exception {
		AccountCreationRequest badAccount = new AccountCreationRequest(AccountType.CHECKING, -1.0);
		when(financeService.generateAccountNumber()).thenReturn("1234");
		when(financeService.generateRoutingNumber()).thenReturn("1234");
		BankAccount account = 
				new BankAccount("1234", "1234", badAccount.getAmount(), badAccount.getType(), AccountStatus.OPEN);
		when(bankRepo.save(account)).thenThrow(Exception.class);
		try {
			financeService.createBankAccount(badAccount);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Bank Account cannot start with a negative balance");
		}
	}
	
	@Test
	public void testFailInvalidBankAccountCreate() throws Exception {
		AccountCreationRequest badAccount = new AccountCreationRequest(null, 1.0);
		when(financeService.generateAccountNumber()).thenReturn("1234");
		when(financeService.generateRoutingNumber()).thenReturn("1234");
		BankAccount account = 
				new BankAccount("1234", "1234", badAccount.getAmount(), badAccount.getType(), AccountStatus.OPEN);
		when(bankRepo.save(account)).thenThrow(Exception.class);
		try {
			financeService.createBankAccount(badAccount);
			fail(); // if the function above doesn't hit an error, fail the test, the test fails
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Bank Account Type is not provided");
		}
	}
	
	@Test
	public void testSuccessSavingsAccountCreate() throws Exception {
		AccountCreationRequest goodAccount = new AccountCreationRequest(AccountType.SAVINGS, 0.0);
		when(financeService.generateAccountNumber()).thenReturn("1234");
		when(financeService.generateRoutingNumber()).thenReturn("1234");
		BankAccount account = 
				new BankAccount("1234", "1234", goodAccount.getAmount(), goodAccount.getType(), AccountStatus.OPEN);
		Mockito.when(bankRepo.save(account)).thenReturn(account);
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
		when(financeService.generateAccountNumber()).thenReturn("1234");
		when(financeService.generateRoutingNumber()).thenReturn("1234");
		BankAccount account = 
				new BankAccount("1234", "1234", goodAccount.getAmount(), goodAccount.getType(), AccountStatus.OPEN);
		Mockito.when(bankRepo.save(account)).thenReturn(account);
		try {
			String financeMessage = financeService.createBankAccount(goodAccount);
			assertEquals("Checking Account Created", financeMessage);
		} catch (Exception e){
			fail();
		}
	}
	
	@Test
	public void testFailNullBankTransfer() throws Exception {
		TransferAssetsRequest badRequest = new TransferAssetsRequest(null, null, "1234", "1234", 0.0);
		BankAccount incomingAccount = 
				new BankAccount(
						badRequest.getFromAccountNumber(), 
						badRequest.getFromRoutingNumber(), 
						badRequest.getAmount(), 
						AccountType.CHECKING, 
						AccountStatus.OPEN);
		when(bankRepo.findById(badRequest.getFromAccountNumber())).thenThrow(Exception.class);
		when(bankRepo.save(incomingAccount)).thenThrow(Exception.class);
		try {
			financeService.bankAccountTransfer(badRequest);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Missing Account/Routing number information");
		}
	}
	
	@Test
	public void testFailNullOutgoingBankTransfer() throws Exception {
		TransferAssetsRequest badRequest = new TransferAssetsRequest("1234", "1234", null, null, 0.0);
		BankAccount outgoingAccount = 
				new BankAccount(
						badRequest.getToAccountNumber(), 
						badRequest.getToRoutingNumber(), 
						badRequest.getAmount(), 
						AccountType.CHECKING, 
						AccountStatus.OPEN);
		when(bankRepo.findById(badRequest.getToAccountNumber())).thenThrow(Exception.class);
		when(bankRepo.save(outgoingAccount)).thenThrow(Exception.class);
		try {
			financeService.bankAccountTransfer(badRequest);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Missing Account/Routing number information");
		}
	}
	
	@Test
	public void testFailInvalidBankTransfer() throws Exception {
		TransferAssetsRequest badRequest = new TransferAssetsRequest("1234", "1234", "1234", "1234", 0.0);
		BankAccount outgoingAccount = 
				new BankAccount(
						badRequest.getToAccountNumber(), 
						badRequest.getToRoutingNumber(), 
						badRequest.getAmount(), 
						AccountType.CHECKING, 
						AccountStatus.OPEN);
		when(bankRepo.save(outgoingAccount)).thenThrow(Exception.class);
		try {
			financeService.bankAccountTransfer(badRequest);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot transfer assets between the same bank account");
		}
	}
	
	@Test
	public void testFailInvalidMoneyBankTransfer() throws Exception {
		TransferAssetsRequest badRequest = new TransferAssetsRequest("1234", "1234", "1235", "1235", -10.0);
		BankAccount outgoingAccount = 
				new BankAccount(
						badRequest.getToAccountNumber(), 
						badRequest.getToRoutingNumber(), 
						badRequest.getAmount(), 
						AccountType.CHECKING, 
						AccountStatus.OPEN);
		when(bankRepo.save(outgoingAccount)).thenThrow(Exception.class);
		try {
			financeService.bankAccountTransfer(badRequest);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot transfer negative assets between bank accounts");
		}
	}
	
	@Test
	public void testFailInvalidNoMoneyBankTransfer() throws Exception {
		TransferAssetsRequest badRequest = new TransferAssetsRequest("1234", "1234", "1235", "1235", 0.0);
		BankAccount outgoingAccount = 
				new BankAccount(
						badRequest.getToAccountNumber(), 
						badRequest.getToRoutingNumber(), 
						badRequest.getAmount(), 
						AccountType.CHECKING, 
						AccountStatus.OPEN);
		when(bankRepo.save(outgoingAccount)).thenThrow(Exception.class);
		try {
			financeService.bankAccountTransfer(badRequest);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot transfer no assets between bank accounts");
		}
	}
	
	@Test
	public void testSuccessBankTransfer() throws Exception {
		TransferAssetsRequest goodRequest = new TransferAssetsRequest("1234", "1234", "1235", "1235", 10.0);
		new BankAccount("1234", "1234", 0.0, AccountType.CHECKING, AccountStatus.OPEN);
		BankAccount mockOutgoingAccount = 
				new BankAccount("1235", "1235", 10.0, AccountType.CHECKING, AccountStatus.OPEN);
		try {
			BankAccount updatedAccount = financeService.bankAccountTransfer(goodRequest);
			assertEquals(updatedAccount.getAssetHoldings(), mockOutgoingAccount.getAssetHoldings());
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testFailInvalidBankDetails() throws Exception {
		when(bankRepo.findById("1234")).thenThrow(Exception.class);
		try {
			financeService.getBankAccountDetails("1234");
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "account does not exist");
		}
	}
	
	@Test
	public void testFailNullBankDetails() throws Exception {
		when(bankRepo.findById(null)).thenThrow(Exception.class);
		try {
			financeService.getBankAccountDetails(null);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "No account given");
		}
	}
	
	@Test
	public void testSuccessBankDetails() throws Exception{
		Optional<BankAccount> account = 
				Optional.of(
						new BankAccount("1234", "1234", 0.0, AccountType.CHECKING, AccountStatus.OPEN)
						);
		when(bankRepo.findById("1234")).thenReturn(account);
		try {
			financeService.getBankAccountDetails("1234");
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testFailInvalidRoutingCloseAccount() throws Exception {
		CloseAccountRequest request = new CloseAccountRequest(null);
		when(bankRepo.findById(request.getAccountNumber())).thenThrow(Exception.class);
		try {
			financeService.closeBankAccount(request);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Account number must be included");
		}
	}
	
	@Test
	public void testFailInvalidNullNumberCloseAccount() throws Exception {
		CloseAccountRequest request = new CloseAccountRequest(null);
		doThrow(IllegalArgumentException.class).when(bankRepo).deleteById(request.getAccountNumber());
		try {
			financeService.closeBankAccount(request);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Account number must be included");
		}
	}
	
	@Test
	public void testFailInvalidaccNumberCloseAccount() throws Exception {
		CloseAccountRequest request = new CloseAccountRequest("1234");
		doThrow(IllegalArgumentException.class).when(bankRepo).deleteById(request.getAccountNumber());
		try {
			financeService.closeBankAccount(request);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Account number must be included");
		}
	}
	
	@Test
	public void testSuccessCloseAccount() throws Exception {
		CloseAccountRequest request = new CloseAccountRequest("1234");
		doNothing().when(bankRepo).deleteById(request.getAccountNumber());
		try {
			financeService.closeBankAccount(request);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Account number must be included");
		}
	}
	
	@Test
	public void testFailInvalidNullAccountDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, null, "1234");
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "No accountnumber provided");
		}
	}
	
	@Test
	public void testFailInvalidNullRoutingDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", null);
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "No routingnumber provided");
		}
	}
	
	@Test
	public void testFailInvalidRoutingDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		Optional<BankAccount> outgoingAccount = 
				Optional.of(new BankAccount(fund.getAccountNumber(), "1235", 20.0, AccountType.CHECKING, AccountStatus.OPEN));
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(outgoingAccount);
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Incorrect Routing number for account");
		}
	}
	
	@Test
	public void testFailInvalidClosedDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		Optional<BankAccount> outgoingAccount = 
				Optional.of(new BankAccount(fund.getAccountNumber(), "1235", 20.0, AccountType.CHECKING, AccountStatus.CLOSED));
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(outgoingAccount);
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "account is closed");
		}
	}
	
	@Test
	public void testFailInvalidAccountDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(null);
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "accountnumber is invalid");
		}
	}
	
	@Test
	public void testFailZeroMoneyDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(0.0, "1234", "1234");
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot deposit no money");
		}
	}
	
	@Test
	public void testFailNegativeMoneyDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(-10.0, "1234", "1234");
		try {
			financeService.depositBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot deposit negative money");
		}
	}
	
	@Test
	public void testSuccessDepositDepositBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		Optional<BankAccount> outgoingAccount = 
				Optional.of(new BankAccount(fund.getAccountNumber(), "1235", 20.0, AccountType.CHECKING, AccountStatus.CLOSED));
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(outgoingAccount);
		try {
			BankAccount incoming = financeService.depositBankFunds(fund);
			assertEquals(outgoingAccount.get().getAccountNumber(), incoming.getAccountNumber());
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testFailInvalidNullAccountWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, null, "1234");
		try {
			financeService.withdrawBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "No accountnumber provided");
		}
	}
	
	@Test
	public void testFailInvalidNullRoutingWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", null);
		try {
			financeService.withdrawBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "No routingnumber provided");
		}
	}
	
	@Test
	public void testFailInvalidWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		Optional<BankAccount> outgoingAccount = 
				Optional.of(new BankAccount(fund.getAccountNumber(), "1235", 20.0, AccountType.CHECKING, AccountStatus.CLOSED));
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(outgoingAccount);
		try {
			financeService.withdrawBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "account is closed");
		}
	}
	
	@Test
	public void testFailNegativeWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(-10.0, "1234", "1234");
		try {
			financeService.withdrawBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot withdraw negative money");
		}
	}
	
	@Test
	public void testFailNoMoneyWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(0.0, "1234", "1234");
		try {
			financeService.withdrawBankFunds(fund);
			fail();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot withdraw no money");
		}
	}
	
	@Test
	public void testSuccessOverdraftWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		Optional<BankAccount> outgoingAccount = 
				Optional.of(new BankAccount(fund.getAccountNumber(), "1235", -10.0, AccountType.CHECKING, AccountStatus.CLOSED));
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(outgoingAccount);
		try {
			BankAccount incoming = financeService.withdrawBankFunds(fund);
			assertEquals(outgoingAccount.get().getAccountNumber(), incoming.getAccountNumber());
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSuccessNormalWithdrawBank() throws Exception {
		FundTransferDOA fund = new FundTransferDOA(10.0, "1234", "1234");
		Optional<BankAccount> outgoingAccount = 
				Optional.of(new BankAccount(fund.getAccountNumber(), "1235", 20.0, AccountType.CHECKING, AccountStatus.CLOSED));
		when(bankRepo.findById(fund.getAccountNumber())).thenReturn(outgoingAccount);
		try {
			BankAccount incoming = financeService.withdrawBankFunds(fund);
			assertEquals(outgoingAccount.get().getAccountNumber(), incoming.getAccountNumber());
		} catch(Exception e) {
			fail();
		}
	}
}
