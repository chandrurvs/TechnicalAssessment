package com.vehicleservices.spring.exception;

/**
 * @author Chandrasekaran 
 * 
 * @version 1.0
 *
 */
public class VehicleServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String exceptionMsg;

	public VehicleServiceException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return this.exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
