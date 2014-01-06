/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.wizglobal.vehicletracker.domain.GpsPosition;

/**
 * @author Otieno Lawrence
 * 
 */
public class GmapUrlHelper {

	private static Logger logger = Logger.getLogger( GmapUrlHelper.class );

	private static URL url;

	public static GpsPosition getGpsPostion( String gMapUrl ) {
		try {
			url = new URL( gMapUrl );
		} catch( MalformedURLException e ) {
			logger.error( e );
			throw new IllegalArgumentException( "Bad Google Map URL" );
		}
		GpsPosition gpsPosition = new GpsPosition();
		String query = url.getQuery();
		String[] parts = query.split( "&" );
		for( String part : parts ) {
			if( part.startsWith( "q" ) ) {
				String[] coords = part.split( "," );
				gpsPosition.setLatitude( coords[0].substring( 2 ) );
				gpsPosition.setLongitude( coords[1].substring( 0 ) );
			}
			if( part.startsWith( "z" ) ) {
				gpsPosition.setZoom( Integer.parseInt( part.substring( 2 ) ) );
			}
		}
		return gpsPosition;
	}
}
