/**
 * 
 */
package com.wizglobal.vehicletracker.web;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wizglobal.vehicletracker.domain.User;
import com.wizglobal.vehicletracker.service.DataAccessService;
import com.wizglobal.vehicletracker.util.LazySorter;

/**
 * @author Otieno Lawrence
 * 
 */
public class LazyUserDataModel extends LazyDataModel<User> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Data Source for binding data to the DataTable
	private List<User> datasource;
	// Selected Page size in the DataTable
	private int pageSize;
	// Current row index number
	private int rowIndex;
	// Total row number
	private int rowCount;
	// Data Access Service for create read update delete operations
	private DataAccessService crudService;

	/**
	 * 
	 * @param crudService
	 */
	public LazyUserDataModel( DataAccessService crudService ) {
		this.crudService = crudService;
	}

	/**
	 * Lazy loading user list with sorting ability
	 * 
	 * @param first
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @return List<User>
	 */
	@Override
	public List<User> load( int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters ) {
		datasource = crudService.findWithNamedQuery( "User.findAll", first, first + pageSize );
		// if sort field is not null then we sort the field according to
		// sortfield and sortOrder parameter
		if( sortField != null ) {
			Collections.sort( datasource, new LazySorter( sortField, sortOrder ) );
		}
		setRowCount( crudService.countTotalRecord( "User.findTotal" ) );
		return datasource;
	}

	/**
	 * Checks if the row is available
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isRowAvailable() {
		if( datasource == null )
			return false;
		int index = rowIndex % pageSize;
		return index >= 0 && index < datasource.size();
	}

	/**
	 * Gets the user object's primary key
	 * 
	 * @param user
	 * @return Object
	 */
	@Override
	public Object getRowKey( User user ) {
		return ((Long) user.getId()).toString();
	}

	/**
	 * Returns the user object at the specified position in datasource.
	 * 
	 * @return
	 */
	@Override
	public User getRowData() {
		if( datasource == null )
			return null;
		int index = rowIndex % pageSize;
		if( index > datasource.size() ) {
			return null;
		}
		return datasource.get( index );
	}

	/**
	 * Returns the user object that has the row key.
	 * 
	 * @param rowKey
	 * @return
	 */
	@Override
	public User getRowData( String rowKey ) {
		if( datasource == null )
			return null;
		for( User user : datasource ) {
			if( ((Long) user.getId()).toString().equals( rowKey ) )
				return user;
		}
		return null;
	}

	/*
	 * ===== Getters and Setters of LazyUserDataModel fields
	 */

	/**
	 * 
	 * @param pageSize
	 */
	@Override
	public void setPageSize( int pageSize ) {
		this.pageSize = pageSize;
	}

	/**
	 * Returns page size
	 * 
	 * @return int
	 */
	@Override
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Returns current row index
	 * 
	 * @return int
	 */
	@Override
	public int getRowIndex() {
		return this.rowIndex;
	}

	/**
	 * Sets row index
	 * 
	 * @param rowIndex
	 */
	@Override
	public void setRowIndex( int rowIndex ) {
		this.rowIndex = rowIndex;
	}

	/**
	 * Sets row count
	 * 
	 * @param rowCount
	 */
	@Override
	public void setRowCount( int rowCount ) {
		this.rowCount = rowCount;
	}

	/**
	 * Returns row count
	 * 
	 * @return int
	 */
	@Override
	public int getRowCount() {
		return this.rowCount;
	}

	/**
	 * Sets wrapped data
	 * 
	 * @param list
	 */
	@Override
	public void setWrappedData( Object list ) {
		this.datasource = (List<User>) list;
	}

	/**
	 * Returns wrapped data
	 * 
	 * @return
	 */
	@Override
	public Object getWrappedData() {
		return datasource;
	}
}
