package com.wizglobal.vehicletracker.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Logging producer for injectable log4j logger
 * 
 * @author Otieno Lawrence
 */
public class LoggerProducer {

	/**
	 * @param injectionPoint
	 * @return logger
	 */
	@Produces
	public Logger produceLogger( InjectionPoint injectionPoint ) {
		return Logger.getLogger( injectionPoint.getMember().getDeclaringClass().getName() );
	}
}