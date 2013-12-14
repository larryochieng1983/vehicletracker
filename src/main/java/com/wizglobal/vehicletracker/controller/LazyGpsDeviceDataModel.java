/**
 * 
 */
package com.wizglobal.vehicletracker.controller;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.service.DataAccessService;

/**
 * @author Otieno Lawrence
 * 
 */
public class LazyGpsDeviceDataModel extends LazyDataModel<GpsDevice> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GpsDevice> datasource;
	private int pageSize;
	private int rowIndex;
	private int rowCount;
	private DataAccessService crudService;

	public LazyGpsDeviceDataModel( DataAccessService crudService ) {
		this.crudService = crudService;
	}

}
