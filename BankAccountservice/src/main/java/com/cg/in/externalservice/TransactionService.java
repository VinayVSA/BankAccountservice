package com.cg.in.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.in.entities.Transaction;
@Service
@FeignClient(name = "TRANSACTION-SERVICE")
public interface TransactionService {

	 @PostMapping("/transaction/add")
	 public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) ;
	      
	
}
