package com.wizglobal.vehicletracker.exception;

/**
 * Thrown to wrapp all exceptions related to database access.
 *
 * @author kenny 
 */
public class DataAccessException extends Exception {

    public DataAccessException(String message, Throwable cause) {
	super(message, cause);
    }

    public DataAccessException(String message) {
	super(message);
    }
    
}
