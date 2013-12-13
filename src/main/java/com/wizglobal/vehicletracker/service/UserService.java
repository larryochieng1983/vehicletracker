/**
 *
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 *
 */
@ApplicationScoped
public class UserService implements DataAccessService<User>,  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public User create( User t ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteItems( User[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User update( User item ) {
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
	public List<User> findByNativeQuery( String sql ) {
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
