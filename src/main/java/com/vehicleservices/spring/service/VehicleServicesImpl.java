package com.vehicleservices.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicleservices.spring.dao.VehicleDao;
import com.vehicleservices.spring.model.RefVehicleTypes;
import com.vehicleservices.spring.model.Vehicle;

/**
 * @author Chandrasekaran 
 * 
 * @version 1.0
 *
 */
@Service
@Transactional
public class VehicleServicesImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	public Vehicle save(Vehicle vehicle) {
		return vehicleDao.save(vehicle);
	}

	public List<Vehicle> getVehicleList() {
		return vehicleDao.getVehicleList();
	}

	public Vehicle GetVehicle(int id) {
		return vehicleDao.GetVehicle(id);
	}

	public void saveOrUpdate(Vehicle vehicle) {
		vehicleDao.saveOrUpdate(vehicle);
	}

	public int deleteVehicleById(int id) {
		int status = vehicleDao.deleteVehicleById(id);
		return status;
	}

	@Override
	public RefVehicleTypes getRefVehicleTypeById(String vehicleTypeId) {

		return vehicleDao.getRefVehicleTypeById(vehicleTypeId);
	}

	@Override
	public int deleteLastVehicle() {
		return vehicleDao.deleteLastVehicle();
	}

}
