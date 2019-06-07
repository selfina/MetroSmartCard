package com.delhi.metro.services;

public class InsufficientCardBalance extends Exception {
	
	private String message;

    public InsufficientCardBalance(String message) {
	        
	        this.message = message;
	    }
	 
	    public String getMessage() {
	        return message;
	    }
	 

}
