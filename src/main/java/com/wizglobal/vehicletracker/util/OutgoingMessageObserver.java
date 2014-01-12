/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import com.wizglobal.vehicletracker.domain.OutgoingSms;

/**
 * @author Otieno Lawrence
 * 
 */
public abstract class OutgoingMessageObserver {

	protected OutgoingSms outgoingSms;

	public abstract void saveMessage();
}
