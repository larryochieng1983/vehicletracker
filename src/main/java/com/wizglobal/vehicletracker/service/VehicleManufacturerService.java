/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleManufacturer;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleManufacturerService implements DataAccessService<VehicleManufacturer>,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public VehicleManufacturer create( VehicleManufacturer t ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleManufacturer find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems( VehicleManufacturer[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehicleManufacturer update( VehicleManufacturer item ) {
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
	public List<VehicleManufacturer> findByNativeQuery( String sql ) {
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

}
