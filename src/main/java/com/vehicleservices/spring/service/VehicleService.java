package com.vehicleservices.spring.service;

import java.util.List;

import com.vehicleservices.spring.model.RefVehicleTypes;
import com.vehicleservices.spring.model.Vehicle;

/**
 * @author Chandrasekaran 
 * 
 * @version 1.0
 *
 */
public interface VehicleService {

	Vehicle save(Vehicle vehicle);

	void saveOrUpdate(Vehicle vehicle);

	List<Vehicle> getVehicleList();

	Vehicle GetVehicle(int id);

	int deleteVehicleById(int id);

	int deleteLastVehicle();

	RefVehicleTypes getRefVehicleTypeById(String vehicleTypeId);

}
