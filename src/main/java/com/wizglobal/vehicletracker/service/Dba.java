/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizglobal.vehicletracker.sms.GatewayService;

/**
 * @author Otieno Lawrence
 * 
 */
public class Dba {
	private EntityManagerFactory emf = null;
	protected static final Logger logger = LoggerFactory.getLogger( Dba.class );

	
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
		GatewayService.startService();
	    } catch (Exception e) {
		logger.error("Failed to initialize persistence context. Application will not work as expected", e);
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
