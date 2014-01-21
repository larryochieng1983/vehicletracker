/**
 *
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.util.QueryParam;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VehicleService extends DataAccessService<Vehicle> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger( VehicleService.class );

	public VehicleService() {
		super( Vehicle.class );
	}

	/**
	 * Lazy Load vehicles.
	 * 
	 * @param start beginning
	 * @param pageSize page size
	 * @param params params
	 * @return results
	 */
	public List<Vehicle> loadVehicles( int start, int pageSize, List<QueryParam> params ) {
		logger.info( "Loading vehicles: " + start + ", to " + pageSize );
		Query query = em.createQuery( "from Vehicle" );
		query.setFirstResult( start );
		query.setMaxResults( pageSize );

		if( params != null && !params.isEmpty() ) {
			for( QueryParam queryParam : params ) {
				query.setParameter( queryParam.getName(), queryParam.getValue() );
			}
		}
		return query.getResultList();
	}

	/**
	 * Access Vehicle table column names.
	 * 
	 */
	public static enum VehicleSortColumns {

		ID("id"), REGISTRATION("registrationNumber"), CUSTOMER("customer"), MODEL("model"), COLOR(
				"color"), VEHICLE_TYPE("vehicleType"), VEHICLE_MANUFACTURER("manufacturer"),
		GPS_DEVICE_ID("gpsDevice");
		private final String columnName;

		private VehicleSortColumns( String columnName ) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}
	}
}
