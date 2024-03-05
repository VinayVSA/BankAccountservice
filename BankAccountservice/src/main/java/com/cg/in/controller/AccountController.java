package com.cg.in.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cg.in.entities.AC;
import com.cg.in.entities.Account;
import com.cg.in.exception.AccountException;
import com.cg.in.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/admin/createaccount")
    public ModelAndView showCreateAccountPage(Model model) {
		 model.addAttribute("account", new Account());
        return new ModelAndView("createaccount");
    }
	
	@PostMapping
	public String createaccount(@ModelAttribute("account") Account account)
	{
		  accountService.createAccount(account);
		  return "redirect:/admin/createaccount";
	}

	
	
    @GetMapping("/admin/deleteaccount")
    public ModelAndView showDeleteAccountPage() {
        return new ModelAndView("deleteaccount");
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id) {
        accountService.deleteAccountById(id);
        return "redirect:/account/admin/deleteaccount?success";
    }
    
	
    
   /* @GetMapping("/admin/getallaccounts")
    public ModelAndView showGetAllAccountsPage() {
        return new ModelAndView("getallaccounts");
    }
*/
    
    
    
    @GetMapping("/admin/getaccountbyid")
    public ModelAndView showGetAccountByIdPage() {
        return new ModelAndView("getaccountbyid");
    }
    

    @GetMapping("/admin/getaccountbyId")
    public ModelAndView showGetAccountByIdPage(@RequestParam("accountNum") int accountNum) {
        // Assuming accountService.getAccountByAccountNum() retrieves the account based on the accountNum
        AC account = accountService.getAccountById(accountNum);
        ModelAndView modelAndView = new ModelAndView("getaccountbyid");
        modelAndView.addObject("account", account);
        return modelAndView;
    }



    
    
    @GetMapping("/admin/getallaccounts")
    public ModelAndView showGetAllAccountsPage(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return new ModelAndView("getallaccounts");
    }
    
	@GetMapping("/admin")
	public List<Account> getAllAccounts()
	{
		return accountService.getAllAccounts();
	}
	
	
	/*
	@GetMapping("/{id}")
	public AC getAccountById(@PathVariable int id)
	{
		return accountService.getAccountById(id);
	}
	*/
	 @GetMapping("/customer/deposit")
	    public ModelAndView showDepositForm() {
	        return new ModelAndView("deposit");
	    }
	
	
	@PostMapping("/customer/deposit")
	public  ModelAndView deposit(@RequestParam("accountId") int accountId, @RequestParam("amount") double amount) throws AccountException
	{
		
		ModelAndView modelAndView = new ModelAndView("deposit");
	    try {
	        accountService.deposit(accountId, amount);
	        modelAndView.addObject("message", "Deposited " + amount + " into account with ID " + accountId + " successfully");
	    } catch (AccountException e) {
	        modelAndView.addObject("error", "Deposit Unsuccessful!");
	    }
	    return modelAndView;
		
	}
	
	@GetMapping("/customer/withdraw")
    public ModelAndView showWithdrawForm() {
        return new ModelAndView("withdraw");
    }
	
	@PostMapping("customer/withdraw")
	public ModelAndView withdraw(@RequestParam("accountId") int accountId, @RequestParam("amount") double amount) throws AccountException
	{
		ModelAndView model= new ModelAndView("withdraw");
		try {
		 accountService.withdraw(accountId, amount);
		 model.addObject("message","Withdrawn " + amount + "  from account with ID " + accountId + " successfully");
		}
		catch(AccountException e)
		{
			model.addObject("message","Withdrawn unsuccessful");
		}
		 
			return model;
	}
	
	
	@GetMapping("customer/transfer")
    public ModelAndView showTransferForm() {
        return new ModelAndView("transfer");
    }
	
	
	
	@PostMapping("customer/transfer")
	public ModelAndView transfer(@RequestParam("fid") int fid,@RequestParam("tid") int tid,@RequestParam("amount") double amount) throws AccountException
	{
		ModelAndView model= new ModelAndView("transfer");
		try {
	     accountService.transfer(fid, tid, amount);
		 model.addObject("message", "Transfered " + amount + "  from account with ID " + fid +" to " +tid+" successfully");
		}
		catch(AccountException e)
		{
			model.addObject("message","transfer unsuccessful");
		}
		 
			
		return model;
		
	}
	

}
