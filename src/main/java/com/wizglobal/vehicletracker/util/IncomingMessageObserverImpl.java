/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.domain.VehiclePosition;
import com.wizglobal.vehicletracker.service.IncomingSmsService;
import com.wizglobal.vehicletracker.service.VehiclePositionService;
import com.wizglobal.vehicletracker.service.VehicleService;
import com.wizglobal.vehicletracker.sms.IncomingMessage;

/**
 * @author Otieno Lawrence
 * 
 */
public class IncomingMessageObserverImpl extends IncomingMessageObserver {

	@Inject
	private IncomingSmsService incomingSmsService;

	@Inject
	private VehiclePositionService positionService;

	@Inject
	private VehicleService vehicleService;

	private IncomingMessage incomingMessage;
	

	public IncomingMessageObserverImpl( IncomingMessage incomingMessage ) {
		this.incomingMessage = incomingMessage;
		this.incomingMessage.attach( this );
	}

	/**
	 * @param incomingMessage the incomingMessage to set
	 */
	public void setIncomingMessage( IncomingMessage incomingMessage ) {
		this.incomingMessage = incomingMessage;
	}

	@Override
	public void saveMessage() {
		System.out.println( "Saving Called......>>>>>>"+incomingSmsService );
		List<VehiclePosition> positions = new ArrayList<VehiclePosition>();
		incomingSmsService.create( incomingMessage.getIncomingMessages() );
		// Extract the google map info
		for( IncomingSms incomingSms : incomingMessage.getIncomingMessages() ) {
			if( incomingSms.getText().startsWith( "https" ) ) {
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put( "phoneNumber", incomingSms.getOriginator() );
				VehiclePosition position = new VehiclePosition();
				Vehicle vehicle = null;
				List<Vehicle> list = vehicleService.findWithNamedQuery( "findVehicleByPhoneNumber",
						parameters );
				if( !list.isEmpty() ) {
					vehicle = list.get( 0 );
				}
				position.setVehicle( vehicle );
				position.setTimeTracked( incomingSms.getMessageDate() );
				position.setGpsPosition( GmapUrlHelper.getGpsPostion( incomingSms.getText() ) );
				positions.add( position );
			}
			positionService.create( positions );
		}
	}

}
