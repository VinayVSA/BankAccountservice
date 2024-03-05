package com.cg.in.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.in.entities.Customer;

@Service
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerService {
	
	@GetMapping("/customer/admin/{id}")
	public Customer getCustomerById(@PathVariable int id);
	
	@GetMapping("/customer/admin/createcustomer")
    public String showCreateCustomerPage(Model model);
	
	
	@PostMapping("/customer/admin/createcustomer")
    public String createCustomer(@ModelAttribute("customer") Customer customer);
	
	@GetMapping("/customer/admin")
	public List<Customer> getAllCustomerDetails();
	
}
