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
import com.wizglobal.vehicletracker.web.GatewayServiceMonitor;
import java.io.IOException;
import javax.inject.Inject;
import org.smslib.SMSLibException;
import org.smslib.Service;

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
                initializaGateway();
                System.out.println("sikufika hapa");
	    } catch (Exception e) {
		logger.error("Failed to initialize persistence context. Application will not work as expected", e);
	    }
	}
        
        protected final void initializaGateway() {
            try {
                GatewayService.getGatewayService().startService();
            } catch (SMSLibException | IOException | InterruptedException e) {
                logger.error("Gateway Service failed to initialize successfully. " + e.getMessage(), e);
            } catch (Exception exception) {
                logger.error("Internal error occurred while initializing the gateway service. " + exception.getMessage(), exception );
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
