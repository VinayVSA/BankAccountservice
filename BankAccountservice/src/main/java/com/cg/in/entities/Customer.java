package com.cg.in.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private int customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
}
