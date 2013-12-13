/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VoiceCall;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Otieno Lawrence
 * 
 */
public class VoiceCallService implements DataAccessService<VoiceCall> , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public VoiceCall create( VoiceCall t ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoiceCall find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteItems( VoiceCall[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VoiceCall update( VoiceCall item ) {
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
	public List<VoiceCall> findByNativeQuery( String sql ) {
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
