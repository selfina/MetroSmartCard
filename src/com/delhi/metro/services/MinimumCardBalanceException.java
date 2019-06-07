package com.delhi.metro.services;

public class MinimumCardBalanceException extends Exception {
  
	private String message;
	
	
	
	 public MinimumCardBalanceException(String message) {
	        //super(cause);
	        this.message = message;
	    }
	 
	    public String getMessage() {
	        return message;
	    }
	 
	
}
