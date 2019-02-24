package com.vehicleservices.spring.exception;

/**
 * @author Chandrasekaran 
 * 
 * @version 1.0
 *
 */
public class ErrorResponse {

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int errorCode;
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
