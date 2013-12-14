/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleColor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleColorService implements DataAccessService<VehicleColor>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dba dba;

	public VehicleColorService(Dba dba) {
		this.dba = dba;
	}

	public List<VehicleColor> findWithNamedQuery(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleColor create(VehicleColor t) {
		EntityManager em = dba.getActiveEm();
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}

	@Override
	public VehicleColor find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems(VehicleColor[] items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehicleColor update(VehicleColor item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery(String namedQueryName, Map parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery(String queryName, int resultLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleColor> findByNativeQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countTotalRecord(String namedQueryName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findWithNamedQuery(String namedQueryName, Map parameters,
			int resultLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery(String namedQueryName, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}
}
