/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wizglobal.vehicletracker.domain.GprsSetting;

/**
 * @author Otieno Lawrence
 * 
 */
public class GprsSettingService implements DataAccessService<GprsSetting>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public GprsSetting create( GprsSetting t ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GprsSetting find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems( GprsSetting[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GprsSetting update( GprsSetting item ) {
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
	public List<GprsSetting> findByNativeQuery( String sql ) {
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

	public GprsSetting findGprsSettingByServiceProviderName( String serviceProviderName ) {
		// TODO Auto-generated method stub
		return null;
	}

}
