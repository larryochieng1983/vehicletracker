/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wizglobal.vehicletracker.domain.SimCard;

/**
 * @author Otieno Lawrence
 * 
 */
public class SimCardService implements DataAccessService<SimCard>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public SimCard create( SimCard t ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimCard find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems( SimCard[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SimCard update( SimCard item ) {
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
	public List<SimCard> findByNativeQuery( String sql ) {
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
