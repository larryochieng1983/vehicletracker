/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizglobal.vehicletracker.domain.BaseEntity;
import com.wizglobal.vehicletracker.util.QueryUtil;

/**
 * @author Otieno Lawrence
 * 
 */
public class Dba {
	private static volatile boolean initialized = false;
	private static Boolean lock = new Boolean( true );
	private static EntityManagerFactory emf = null;

	protected Logger logger = LoggerFactory.getLogger( getClass() );

	private EntityManager outer;

	/**
	 * open dba and also start a transaction
	 */
	public Dba() {
		this( false );
	}

	/**
	 * open dba; if readonly no JPA transaction is actually started, meaning you will have no persistence store. You
	 * can still persist stuff, but the entities won't become managed.
	 */
	public Dba( boolean readOnly ) {

		initialize();
		openEm( readOnly );
	}

	public void openEm( boolean readOnly ) {
		if( outer != null ) {
			return;
		}

		outer = emf.createEntityManager();

		if( readOnly == false ) {
			outer.getTransaction().begin();
		}
	}

	/**
	 * Get the outer transaction; an active transaction must already exist for this to succeed.
	 */
	public EntityManager getActiveEm() {
		if( outer == null ) {
			throw new IllegalStateException( "No transaction was active!" );
		}

		return outer;
	}

	/**
	 * Close the entity manager, properly committing or rolling back a transaction if one is still active.
	 */
	public void closeEm() {
		if( outer == null ) {
			return;
		}

		try {
			if( outer.getTransaction().isActive() ) {

				if( outer.getTransaction().getRollbackOnly() ) {
					outer.getTransaction().rollback();
				} else {
					outer.getTransaction().commit();
				}
			}

		} finally {
			outer.close();
			outer = null;
		}
	}

	/**
	 * Mark the transaction as rollback only, if there is an active transaction to begin with.
	 */
	public void markRollback() {

		if( outer != null ) {
			outer.getTransaction().setRollbackOnly();
		}
	}

	public boolean isRollbackOnly() {
		return outer != null && outer.getTransaction().getRollbackOnly();
	}

	// thread safe way to initialize the entity manager factory.
	private void initialize() {
		if( initialized ) {
			return;
		}
		synchronized( lock ) {
			if( initialized ) {
				return;
			}
			initialized = true;
			try {				
				emf = Persistence.createEntityManagerFactory( "vehicleTrackerPU" );
			} catch( Throwable t ) {
				t.printStackTrace();
				return;
			}
		}
	}

	public QueryUtil query( String q ) {

		if( outer == null ) {
			throw new IllegalStateException( "Creating a query when there is no active transaction!" );
		}

		return new QueryUtil( outer.createQuery( q ) );
	}

	/**
	 * 
	 * 
	 * @param clz
	 * @param id
	 * @return
	 */
	public <T> T getById( Class<T> clz, long id ) {

		if( outer == null ) {
			throw new IllegalStateException( "No transaction was active!" );
		}

		return outer.find( clz, id );
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public <T extends BaseEntity> T save( T obj ) {

		if( outer == null ) {
			throw new IllegalStateException( "Creating a query when there is no active transaction!" );
		}

		if( obj.getId() > 0L ) {

			return outer.merge( obj );

		} else {
			outer.persist( obj );
			return obj;
		}
	}
}
