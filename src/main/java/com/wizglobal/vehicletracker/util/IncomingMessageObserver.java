/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
public class IncomingMessageObserver implements MessageListObserver<IncomingSms> {

	@Inject
	private IncomingSmsService incomingSmsService;

	@Inject
	private VehiclePositionService positionService;

	@Inject
	private VehicleService vehicleService;

	private IncomingMessage incomingMessage;

	@PostConstruct
	public void init() throws Exception {
		System.out.println( "<<<<<<<<<" + incomingSmsService );
	}

	/**
	 * @param incomingMessage the incomingMessage to set
	 */
	public void setIncomingMessage( IncomingMessage incomingMessage ) {
		this.incomingMessage = incomingMessage;
		this.incomingMessage.attach( this );
	}

	/**
	 * @return the incomingMessage
	 */
	public IncomingMessage getIncomingMessage() {
		return incomingMessage;
	}

	@Override
	public void saveMessage( List<IncomingSms> messages ) {
		System.out.println( "Saving Called......>>>>>>" + incomingSmsService );
		List<VehiclePosition> positions = new ArrayList<VehiclePosition>();
		incomingSmsService.create( messages );
		// Extract the google map info
		for( IncomingSms incomingSms : messages ) {
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
