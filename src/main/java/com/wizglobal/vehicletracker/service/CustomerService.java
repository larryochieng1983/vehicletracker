/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.util.Dba;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

/**
 * @author Otieno Lawrence
 * 
 */
public class CustomerService implements DataAccessService<Customer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dba dba;

	public CustomerService( Dba dba ) {
		this.dba = dba;
	}

	@Override
	public Customer create( Customer t ) {
		EntityManager em = dba.getActiveEm();
		em.persist( t );
		return t;
	}

	@Override
	public Customer find( Object id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete( Object id ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteItems( Customer[] items ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer update( Customer item ) {
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
	public List<Customer> findByNativeQuery( String sql ) {
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
