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
import com.wizglobal.vehicletracker.sms.ReadMessage;

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

	private ReadMessage readMessage;

	public IncomingMessageObserverImpl( ReadMessage readMessage ) {
		this.readMessage = readMessage;
		this.readMessage.attach( this );
	}

	@Override
	public void saveMessage() {
		List<VehiclePosition> positions = new ArrayList<VehiclePosition>();
		incomingSmsService.create( readMessage.getIncomingMessages() );
		// Extract the google map info
		for( IncomingSms incomingSms : readMessage.getIncomingMessages() ) {
			if( incomingSms.getText().startsWith( "" ) ) {
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
