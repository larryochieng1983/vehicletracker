/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import com.wizglobal.vehicletracker.domain.IncomingSms;

/**
 * @author Otieno Lawrence
 * 
 */
public abstract class IncomingMessageObserver {

	protected IncomingSms incomingSms;

	public abstract void saveMessage();

}
