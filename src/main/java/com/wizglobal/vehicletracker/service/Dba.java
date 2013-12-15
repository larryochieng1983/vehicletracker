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
	private EntityManagerFactory emf = null;
	protected static final Logger logger = LoggerFactory.getLogger( Dba.class );

	private EntityManager outer;
	private EntityManager entityManager;

	/**
	 * Called only once to instantiate the persistence context.
	 */
	private Dba() {
	    logger.info("Initializing Persistence context");
	    try {
		emf = Persistence.createEntityManagerFactory("vehicleTrackerPU");
		entityManager = emf.createEntityManager();
		logger.info("Persistence Context initialized");
	    } catch (Exception e) {
		logger.error("Failed to initialize persistence context. Application will not work as expected", e);
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

//	// thread safe way to initialize the entity manager factory.
//	private void initialize() {
//		if( initialized ) {
//			return;
//		}
//		synchronized( lock ) {
//			if( initialized ) {
//				return;
//			}
//			initialized = true;
//			try {				
//				emf = Persistence.createEntityManagerFactory( "vehicleTrackerPU" );
//			} catch( Throwable t ) {
//				t.printStackTrace();
//				return;
//			}
//		}
//	}

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
	
	/**
	 * Note that transactions should be managed
	*
	* @return Entity manager
	*/
	public EntityManager getEntityManager(){
	    return entityManager;
	}
	
	/**
	 *
	 * @return A singleton isntance of 
	 */
	public static Dba getInstance() {
	    return DbaHolder.DBA_INSTANCE;
	}
	
	private static class DbaHolder{
	    private static final Dba DBA_INSTANCE = new Dba();
	}
}
