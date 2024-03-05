package com.cg.in.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.in.entities.AC;
import com.cg.in.entities.Account;
import com.cg.in.entities.Transaction;
import com.cg.in.exception.AccountException;
import com.cg.in.externalservice.CustomerService;
import com.cg.in.externalservice.TransactionService;
import com.cg.in.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	private final AccountRepository accountRepo;
	
	private final RestTemplate restTemplate;
	
	private CustomerService cust;
	
	@Autowired
	private TransactionService transactionservice;
	
	// private final String transactionServiceBaseUrl = "http://transaction-service/";
	
	 @Autowired
	 public AccountServiceImpl(RestTemplate restTemplate,AccountRepository accountRepo,CustomerService cust) 
	 {
	        this.restTemplate = restTemplate;
	        this.accountRepo=accountRepo;
	        this.cust = cust;
	 }
	
	

	@Override
	public void deposit(int accountNum, double amount) throws AccountException {
		Account acc=getAccountById(accountNum).getAccount();
		if(acc==null)
		{
			throw new AccountException("The account with account number : "+accountNum+" did not exist");
		}
		else
		{
			double bal=acc.getBalance()+amount;
			acc.setBalance(bal);
			Account account=accountRepo.save(acc);
			
			transactionservice.addTransaction(new Transaction(amount,"Deposit",LocalDate.now(),account.getAccountNum()));
		}
	}

	@Override
	public void withdraw(int accountNum, double Wamount)throws AccountException {
		Account acc=getAccountById(accountNum).getAccount();
		if(acc==null)
		{
			throw new AccountException("The account with account number : "+accountNum+" did not exist");
		}
		else if(acc.getBalance()<Wamount)
		{
			throw new AccountException("Insufficient balance");
		}
		else
		{
			double bal=acc.getBalance()-Wamount;
			acc.setBalance(bal);
			accountRepo.save(acc);
			transactionservice.addTransaction(new Transaction(Wamount,"Withdraw",LocalDate.now(),accountNum));
		}
		
	}

	@Override
	public void transfer(int faccountNum, int taccountNum, double tbalance) throws AccountException {
		
		Account facc=getAccountById(faccountNum).getAccount();
		Account tacc=getAccountById(taccountNum).getAccount();
		if(facc==null )
		{
			throw new AccountException("The account with account number : "+faccountNum+" did not exist");
		}
		else if(tacc==null)
		{
			throw new AccountException("The account with account number : "+taccountNum+" did not exist");
		}
		else if(facc.getBalance()<tbalance)
		{
			throw new AccountException("Insufficient balance");
		}
		else
		{
			double fbal=facc.getBalance()-tbalance;
			facc.setBalance(fbal);
			accountRepo.save(facc);
			transactionservice.addTransaction(new Transaction(tbalance,"Transfer from account: "+faccountNum+" to account: "+taccountNum,LocalDate.now(),faccountNum));
			
			
			double tbal=tacc.getBalance()+tbalance;
			tacc.setBalance(tbal);
			accountRepo.save(tacc);
			transactionservice.addTransaction(new Transaction(tbalance,"Transfer to account: "+taccountNum+" from account: "+faccountNum,LocalDate.now(),taccountNum));
		}
	}

	@Override
	public AC getAccountById(int accountNum) {
		
		AC ac=new AC();
		
		 ac.setAccount(accountRepo.findById(accountNum).get());
		 ac.setCustomer(cust.getCustomerById(ac.getAccount().getCustomerId()));
		return ac;
	
	}

	@Override
	public void deleteAccountById(int accountNum) {
		accountRepo.deleteById(accountNum);
		
	}

	@Override
	public Account createAccount(Account account) {
		
		 return accountRepo.save(account);
	}

	@Override
	public void updateAccount(Account updatedAccount) {
	
		accountRepo.save(updatedAccount);
		
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepo.findAll();
		
		
	}

}
