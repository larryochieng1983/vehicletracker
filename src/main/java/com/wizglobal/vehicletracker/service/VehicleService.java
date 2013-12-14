/**
 *
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.util.QueryParam;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleService implements DataAccessService<Vehicle>, Serializable {

	private Dba dba;

	public VehicleService( Dba dba ) {
		this.dba = dba;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Vehicle create( Vehicle t ) {
		EntityManager em = dba.getActiveEm();
		em.persist( t );
		return t;
	}

	@Override
	public Vehicle find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems( Vehicle[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vehicle update( Vehicle item ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery( String namedQueryName ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery( String namedQueryName, Map parameters ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery( String queryName, int resultLimit ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findByNativeQuery( String sql ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countTotalRecord( String namedQueryName ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findWithNamedQuery( String namedQueryName, Map parameters, int resultLimit ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findWithNamedQuery( String namedQueryName, int start, int end ) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Vehicle> loadVehicles( int first, int pageSize, List<QueryParam> filterQueryParams ) {
		// TODO Auto-generated method stub
		return null;
	}

}
