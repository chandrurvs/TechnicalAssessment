package com.vehicleservices.spring.controller;

/**
 * @author Chandrasekaran
 * 
 * @version 1.0
 * 
 *
 */
public class RestURIConstants {
	
	public static final String CREATE_VEHICLE = "/addVehicle";
	public static final String GET_ALL_VEHICLES = "/retrievevehicles";
	public static final String GET_VEHICLE = "/findById/{id}";
	public static final String DELETE_VEHICLE = "/delete";
	public static final String DELETE_VEHICLE_BY_ID = "/delete/{id}";
	public static final String UPDATE_VEHICLE = "/update/{id}";
	public static final String REST_CONTEXT = "/rest";
	public static final String RECORD_NOT_FOUND = "No Records Found";
	public static final String INVALID_VEHICLE_TYPE = "Invalid Vechicle Type : ";
	public static final String INVALID_VEHICLE_ID = "Invalid Vechicle Id : ";
	public static final String DELETION_SUCCESS = " Deleted Successfully!!!";
}
