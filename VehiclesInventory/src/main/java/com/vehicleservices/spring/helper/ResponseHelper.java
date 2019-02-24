package com.vehicleservices.spring.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vehicleservices.spring.exception.ErrorResponse;
/**
 * @author Chandrasekaran 
 * 
 * @version 1.0
 *
 */
public class ResponseHelper {

	@Autowired
	private ErrorResponse errorResponse;

	/**
	 * @param errorMessage
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> sendErrorResponse(String errorMessage) {
		errorResponse.setErrorMessage(errorMessage);
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @param errorMessage
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> sendupdatedResponse(Object obj) {
		return new ResponseEntity<Object>(obj, HttpStatus.ACCEPTED);
	}

	/**
	 * @param errorMessage
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> sendCreatedResponse(Object obj) {
		return new ResponseEntity<Object>(obj, HttpStatus.CREATED);
	}
	/**
	 * @param errorMessage
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> sendDeletedResponse(String message) {
		return new ResponseEntity<Object>(message, HttpStatus.ACCEPTED);
	}
}
