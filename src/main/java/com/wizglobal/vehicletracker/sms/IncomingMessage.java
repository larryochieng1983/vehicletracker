/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.util.ArrayList;
import java.util.List;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import com.wizglobal.vehicletracker.scheduler.IncomingMessageObserver;

/**
 * @author Otieno Lawrence
 * 
 */
public class IncomingMessage {

	private List<IncomingSms> incomingMessages = new ArrayList<IncomingSms>();

	private List<IncomingMessageObserver> observers = new ArrayList<IncomingMessageObserver>();

	/**
	 * @return the incomingMessages
	 */
	public List<IncomingSms> getIncomingMessages() {
		return this.incomingMessages;
	}

	/**
	 * @param incomingMessages the incomingMessages to set
	 */
	public void setIncomingMessages( List<IncomingSms> incomingMessages ) {
		this.incomingMessages = incomingMessages;
		notifyAllObservers();
	}

	public void attach( IncomingMessageObserver observer ) {
		this.observers.add( observer );
	}

	public void notifyAllObservers() {
		System.out.println( "Observers......>>>>>>" + this.observers.size() );
		for( IncomingMessageObserver observer : this.observers ) {
			System.out.println( "Notifying Observers One by One......>>>>>>" );
			observer.saveMessage(getIncomingMessages());
		}
	}
}
