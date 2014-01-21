/**
 * 
 */
package com.wizglobal.vehicletracker.scheduler;


/**
 * @author Otieno Lawrence
 * 
 */
public interface MessageObserver<T> {

	void saveMessage(T messages);

}
