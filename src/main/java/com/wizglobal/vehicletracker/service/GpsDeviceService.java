/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wizglobal.vehicletracker.domain.GpsDevice;

/**
 * @author Otieno Lawrence
 * 
 */
public class GpsDeviceService implements DataAccessService<GpsDevice>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public GpsDevice create( GpsDevice t ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GpsDevice find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems( GpsDevice[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GpsDevice update( GpsDevice item ) {
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
	public List<GpsDevice> findByNativeQuery( String sql ) {
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
