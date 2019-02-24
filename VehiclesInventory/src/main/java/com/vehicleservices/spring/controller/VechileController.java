package com.vehicleservices.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vehicleservices.spring.exception.VehicleTypeNotFoundException;
import com.vehicleservices.spring.helper.ResponseHelper;
import com.vehicleservices.spring.model.Vehicle;
import com.vehicleservices.spring.service.VehicleService;
import com.vehicleservices.spring.validation.VehicleServiceValidation;

/**
 * @author Chandrasekaran
 * 
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value = RestURIConstants.REST_CONTEXT)
public class VechileController {

	@Autowired
	private VehicleService vechileService;

	@Autowired
	private VehicleServiceValidation vehicleServiceValidation;

	@Autowired
	private ResponseHelper responseHelper;
	//Method used to retrieve the All the vehicles 
	@RequestMapping(value = RestURIConstants.GET_ALL_VEHICLES, method = RequestMethod.GET)
	public @ResponseBody List<Vehicle> getVehicleDetails() {
		List<Vehicle> vechileList = vechileService.getVehicleList();
		return vechileList;
	}

	//Post Method used to Add Vehicle details
	@RequestMapping(value = RestURIConstants.CREATE_VEHICLE, method = RequestMethod.POST)
	public ResponseEntity<Object> createVehicle(@Valid @RequestBody Vehicle vehicle, BindingResult result)
			throws Exception {

		vehicleServiceValidation.validate(vehicle, result);
		if (result.hasErrors())
			return responseHelper.sendErrorResponse(result.getAllErrors().get(0).getDefaultMessage());
		try {
			vechileService.getRefVehicleTypeById(vehicle.getVehicleTypeId());
		} catch (Exception ex) {
			throw new VehicleTypeNotFoundException(RestURIConstants.INVALID_VEHICLE_TYPE + vehicle.getVehicleTypeId());
		}
		vechileService.save(vehicle);
		return responseHelper.sendCreatedResponse(vehicle);
	}

	// GET Method use to retrieve Vehicle details by ID
	@RequestMapping(value = RestURIConstants.GET_VEHICLE, method = RequestMethod.GET)
	public ResponseEntity<Object> getVehicleId(@PathVariable("id") int vehicleId) {

		Vehicle vehicle = vechileService.GetVehicle(vehicleId);
		if (null == vehicle)
			return responseHelper.sendErrorResponse(RestURIConstants.INVALID_VEHICLE_ID + vehicleId);
		return responseHelper.sendCreatedResponse(vehicle);
	}

	//Delete method used to Delete the Vehicle detail - Recently added Record
	@RequestMapping(value = RestURIConstants.DELETE_VEHICLE, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteVehicle() {
		int status = vechileService.deleteLastVehicle();
		if (status < 1)
			return responseHelper.sendErrorResponse(RestURIConstants.RECORD_NOT_FOUND);
		return responseHelper.sendDeletedResponse(RestURIConstants.DELETION_SUCCESS);
	}
	//Delete method used to Delete the Vehicle detail - by Id
	@RequestMapping(value = RestURIConstants.DELETE_VEHICLE_BY_ID, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteVehicleById(@PathVariable("id") int vehicleId) {
		int status = vechileService.deleteVehicleById(vehicleId);
		if (status < 1)
			return responseHelper.sendErrorResponse(RestURIConstants.RECORD_NOT_FOUND);

		return responseHelper.sendDeletedResponse(vehicleId + RestURIConstants.DELETION_SUCCESS);
	}

	//Put Method used for update the vehicle details
	@RequestMapping(value = RestURIConstants.UPDATE_VEHICLE, method = RequestMethod.PUT)
	public ResponseEntity<Object> updateVehicleDetails(@PathVariable("id") int vehicleId, @RequestBody Vehicle vehicle)
			throws Exception {
		if (null == vechileService.GetVehicle(vehicleId))
			return responseHelper.sendErrorResponse(RestURIConstants.RECORD_NOT_FOUND);

		try {
			vechileService.getRefVehicleTypeById(vehicle.getVehicleTypeId());
		} catch (Exception ex) {
			throw new VehicleTypeNotFoundException(RestURIConstants.INVALID_VEHICLE_TYPE + vehicle.getVehicleTypeId());
		}
		vehicle.setVehicleId(vehicleId);
		vechileService.saveOrUpdate(vehicle);
		return responseHelper.sendupdatedResponse(vehicle);
	}

}
