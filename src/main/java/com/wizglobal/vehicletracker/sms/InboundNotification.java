/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.domain.VehiclePosition;
import com.wizglobal.vehicletracker.map.GmapUrlHelper;
import com.wizglobal.vehicletracker.service.IncomingSmsService;
import com.wizglobal.vehicletracker.service.VehiclePositionService;
import com.wizglobal.vehicletracker.service.VehicleService;

/**
 * @author Otieno Lawrence
 * 
 */
@Named(value = "inboundNotification")
@ApplicationScoped
public class InboundNotification implements IInboundMessageNotification, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger( InboundNotification.class );

	@Inject
	private IncomingSmsService incomingSmsService;
	@Inject
	private VehicleService vehicleService;
	@Inject
	private VehiclePositionService vehiclePositionService;
	

	public void process( AGateway gateway, MessageTypes msgType, InboundMessage msg ) {
		if( msgType == MessageTypes.INBOUND ) {
			log.info( ">>> New Inbound message " + msg.getMessageId() + "detected from Gateway: "
					+ gateway.getGatewayId() );
			IncomingSms incomingSms = new IncomingSms();
			incomingSms.setOriginator( msg.getOriginator() );
			incomingSms.setMessageDate( msg.getDate() );
			incomingSms.setReceiveDate( msg.getDate() );
			incomingSms.setMessage( msg.getText() );
			incomingSmsService.create( incomingSms );
			
			//create the vehicle position
			VehiclePosition position = new VehiclePosition();
			Vehicle vehicle = null;
			List<Vehicle> list = vehicleService.findWithNamedQuery( "findVehicleByPhoneNumber",
					parameters );
			if( !list.isEmpty() ) {
				vehicle = list.get( 0 );
			}
			position.setVehicle( vehicle );
			position.setTimeTracked( incomingSms.getMessageDate() );
			position.setGpsPosition( GmapUrlHelper.getGpsPostion( incomingSms.getMessage() ) );
		} else if( msgType == MessageTypes.STATUSREPORT ) {
			log.info( ">>> New Inbound Status Report message detected from Gateway: "
					+ gateway.getGatewayId() );
			log.info( msg );
		}

	}
}
