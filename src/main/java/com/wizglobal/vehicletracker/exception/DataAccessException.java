
package com.wizglobal.vehicletracker.exception;

/**
 * Wrapps all data access exceptions in the data access layer.
 *
 * @author kenny
 */
public class DataAccessException extends Exception {

    /**
     * Creates a new instance of
     * <code>DataAccessException</code> without detail message.
     */
    public DataAccessException() {
    }

    /**
     * Constructs an instance of
     * <code>DataAccessException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DataAccessException(String msg) {
	super(msg);
    }

    public DataAccessException(String message, Throwable cause) {
	super(message, cause);
    }
    
}
