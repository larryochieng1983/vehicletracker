/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import java.util.List;

import com.wizglobal.vehicletracker.domain.IncomingSms;

/**
 * @author Otieno Lawrence
 * 
 */
public abstract class IncomingMessageObserver {

	protected List<IncomingSms> incomingSms;

	public abstract void saveMessage();

}
