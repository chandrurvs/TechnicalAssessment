package com.vehicleservices.spring.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Vehicle {

	@NotNull(message = "vehicleTypeId should not be null")
	private String vehicleTypeId;
	@Size(max = 45, message = "make  max lenght is 45")
	private String make;
	@Size(max = 45, message = "model max lenght is 45")
	private String model;
	private Integer vehicleId;
	@Size(max = 4, message = "Model Year max length is 4 and YYYY format")
	private String modelYear;
	@Digits(fraction = 2, integer = 10, message = "Max Integer Part is 10 and Fraction part is 2")
	private Double price;
	private String vehicleName;

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehicle(String vehicleTypeId, String vehicleDesc, String make, String model, String modelYear, Double price,
			String vehicleName) {
		super();
		this.vehicleTypeId = vehicleTypeId;
		// this.vehicleDesc = vehicleDesc;
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
		this.price = price;
		this.vehicleName = vehicleName;
	}

	public String getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(String vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	/*
	 * public String getVehicleDesc() { return vehicleDesc; } public void
	 * setVehicleDesc(String vehicleDesc) { this.vehicleDesc = vehicleDesc; }
	 */

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicleId == null) ? 0 : vehicleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (vehicleId == null) {
			if (other.vehicleId != null)
				return false;
		} else if (!vehicleId.equals(other.vehicleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleTypeId=" + vehicleTypeId + ", make=" + make + ", model=" + model + ", vehicleId="
				+ vehicleId + ", modelYear=" + modelYear + ", price=" + price + ", vehicleName=" + vehicleName + "]";
	}
}
