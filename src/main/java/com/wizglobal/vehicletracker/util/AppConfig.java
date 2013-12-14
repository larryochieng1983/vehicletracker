/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizglobal.vehicletracker.domain.User;
import com.wizglobal.vehicletracker.service.Dba;

/**
 * @author Otieno Lawrence
 * 
 */
public class AppConfig {
	private static volatile boolean initialized = false;
	private static Boolean lock = new Boolean( true );

	protected static Logger logger = LoggerFactory.getLogger( AppConfig.class );

	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers( Dba jpaUtil ) {
		load();
		return jpaUtil.getActiveEm().createQuery( "select u from App_User u order by u.userName" )
				.getResultList();
	}

	private static void load() {

		if( initialized ) {
			return;
		}

		synchronized( lock ) {
			if( initialized ) {
				return;
			}

			initialized = true;

			Dba jpaUtil = new Dba();
			try {
				EntityManager em = jpaUtil.getActiveEm();

				// insert all test data here
				//em.persist( new User() );

				// more init here

				logger.info( "AppConfig initialized succesfully." );

			} catch( Throwable t ) {
				t.printStackTrace();
				logger.error( "Failed to setup persistence unit!", t );
			} finally {
				jpaUtil.closeEm();
			}
		}
	}
}
