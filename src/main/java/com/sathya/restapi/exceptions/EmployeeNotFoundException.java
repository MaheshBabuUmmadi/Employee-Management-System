package com.sathya.restapi.exceptions;

import java.util.Optional;

public class EmployeeNotFoundException extends RuntimeException
{
	public EmployeeNotFoundException(String msg)
	{
		super(msg);
	}
	
	
}
