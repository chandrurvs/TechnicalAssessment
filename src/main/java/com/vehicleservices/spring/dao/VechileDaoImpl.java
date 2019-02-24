package com.vehicleservices.spring.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vehicleservices.spring.model.RefVehicleTypes;
import com.vehicleservices.spring.model.Vehicle;
import com.vehicleservices.spring.model.VehicleDetails;

/**
 * @author Chandrasekaran
 * 
 * @version 1.0
 *
 */
@Repository
public class VechileDaoImpl implements VehicleDao {
	private static Logger logger = Logger.getLogger(VechileDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#GetVehicle(int)
	 */
	@Override
	public Vehicle GetVehicle(int id) {
		VehicleDetails vehicleDetails = findByVehicleId(id);
		Vehicle vehicle = null;
		if (null != vehicleDetails)
			vehicle = buildVehicle(vehicleDetails);
		return vehicle;
	}

	
	/**
	 * 
	 * @param vehicleDetails
	 * @return vehicle Object
	 */
	private Vehicle buildVehicle(VehicleDetails vehicleDetails) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleId(vehicleDetails.getVehicleDetailsId());
		vehicle.setVehicleTypeId(vehicleDetails.getRefVehicleTypes().getVehicleTypeId());
		vehicle.setVehicleId(vehicleDetails.getVehicleDetailsId());
		vehicle.setMake(vehicleDetails.getMake());
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setModelYear(vehicleDetails.getModelYear());
		vehicle.setPrice(vehicleDetails.getPrice());
		vehicle.setVehicleName(vehicleDetails.getVehicleName());
		return vehicle;
	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#save(com.vehicleservices.spring.model.Vehicle)
	 */
	@Override
	public Vehicle save(Vehicle vehicle) {
		RefVehicleTypes refVehicleTypes = getRefTypesByTypeId(vehicle.getVehicleTypeId());
		VehicleDetails vehicleDetails = new VehicleDetails();
		vehicleDetails = createVehicleDetails(vehicle, vehicleDetails);
		vehicleDetails.setRefVehicleTypes(refVehicleTypes);
		getSession().saveOrUpdate(vehicleDetails);
		vehicle.setVehicleId(vehicleDetails.getVehicleDetailsId());
		return vehicle;
	}

	/**
	 * @param vehicleTypeId
	 * @return RefVehicleTypes
	 */
	private RefVehicleTypes getRefTypesByTypeId(String vehicleTypeId) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<RefVehicleTypes> cq = cb.createQuery(RefVehicleTypes.class);
		Root<RefVehicleTypes> root = cq.from(RefVehicleTypes.class);
		cq.select(root).where(cb.equal(root.get("vehicleTypeId"), vehicleTypeId));
		Query<RefVehicleTypes> query = sessionFactory.getCurrentSession().createQuery(cq);

		RefVehicleTypes refVehicleTypes = query.getSingleResult();

		return refVehicleTypes;

	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#getVehicleList()
	 */
	@Override
	public List<Vehicle> getVehicleList() {
		@SuppressWarnings("unchecked")
		List<VehicleDetails> details = sessionFactory.getCurrentSession().createQuery(" from VehicleDetails").list();
		logger.info("VechileDaoImpl.getVehicleList()" + details.size());
		return buildVehicleDetailsDTO(details);

	}

	/**
	 * @param vehicleDetailsList
	 * @return
	 */
	private List<Vehicle> buildVehicleDetailsDTO(List<VehicleDetails> vehicleDetailsList) {
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		vehicleDetailsList.forEach(vehicleDetails -> {
			vehicleList.add(buildVehicle(vehicleDetails));
		});
		logger.info("VechileDaoImpl.buildVehicleDetailsDTO()" + vehicleList.size());
		return vehicleList;
	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#deleteVehicleById(int)
	 */
	@Override
	public int deleteVehicleById(int id) {
		return deleteRow(id);

	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#deleteLastVehicle()
	 */
	@Override
	public int deleteLastVehicle() {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<VehicleDetails> root = cq.from(VehicleDetails.class);
		cq.select(cb.max(root.get("vehicleDetailsId")));
		Query<Object> query = getSession().createQuery(cq);
		Object max = query.getSingleResult();
		return deleteRow(Integer.parseInt(max.toString()));
	}

	/**
	 * @param id
	 * @return int
	 */
	private int deleteRow(int id) {
		@SuppressWarnings("unchecked")
		Query<VehicleDetails> query = getSession().createQuery("delete VehicleDetails where vehicleDetailsId = :id");
		int status = query.setParameter("id", id).executeUpdate();
		return status;
	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#saveOrUpdate(com.vehicleservices.spring.model.Vehicle)
	 */
	@Override
	public void saveOrUpdate(Vehicle vehicle) {
		VehicleDetails vehicleDetails = findByVehicleId(vehicle.getVehicleId());
		vehicleDetails = createVehicleDetails(vehicle, vehicleDetails);
		getSession().saveOrUpdate(vehicleDetails);
	}

	/**
	 * @param id
	 * @return VehicleDetails
	 */
	private VehicleDetails findByVehicleId(int id) {
		return getSession().get(VehicleDetails.class, id);
	}

	/* (non-Javadoc)
	 * @see com.vehicleservices.spring.dao.VehicleDao#getRefVehicleTypeById(java.lang.String)
	 */
	@Override
	public RefVehicleTypes getRefVehicleTypeById(String vehicleTypeId) {
		return getRefTypesByTypeId(vehicleTypeId);
	}

	/**
	 * @param vehicle
	 * @param vehicleDetails
	 * @return VehicleDetails
	 */
	private VehicleDetails createVehicleDetails(Vehicle vehicle, VehicleDetails vehicleDetails) {

		vehicleDetails.setMake(vehicle.getMake());
		vehicleDetails.setModel(vehicle.getModel());
		vehicleDetails.setModelYear(vehicle.getModelYear());
		vehicleDetails.setPrice(vehicle.getPrice());
		vehicleDetails.setVehicleName(vehicle.getVehicleName());
		vehicleDetails.setCreatedDate(new Date(System.currentTimeMillis()));
		vehicleDetails.setUpdatedDate(new Date(System.currentTimeMillis()));
		return vehicleDetails;
	}

}
