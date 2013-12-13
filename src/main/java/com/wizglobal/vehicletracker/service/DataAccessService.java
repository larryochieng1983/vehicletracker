package com.wizglobal.vehicletracker.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Otieno Lawrence
 */

public interface DataAccessService<T> {

	/**
	 * Stores an instance of the entity class in the database
	 * 
	 * @param T Object
	 * @return
	 */
	public T create( T t );

	/**
	 * Retrieves an entity instance that was previously persisted to the database
	 * 
	 * @param T Object
	 * @param id
	 * @return
	 */
	public T find( Object id );

	/**
	 * Removes the record that is associated with the entity instance
	 * 
	 * @param type
	 * @param id
	 */
	public void delete( Object id );

	/**
	 * Removes the number of entries from a table
	 * 
	 * @param <T>
	 * @param items
	 * @return
	 */
	public boolean deleteItems( T[] items );

	/**
	 * Updates the entity instance
	 * 
	 * @param <T>
	 * @param t
	 * @return the object that is updated
	 */
	public T update( T item );

	/**
	 * Returns the number of records that meet the criteria
	 * 
	 * @param namedQueryName
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName );

	/**
	 * Returns the number of records that meet the criteria
	 * 
	 * @param namedQueryName
	 * @param parameters
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName, Map parameters );

	/**
	 * Returns the number of records with result limit
	 * 
	 * @param queryName
	 * @param resultLimit
	 * @return List
	 */
	public List findWithNamedQuery( String queryName, int resultLimit );

	/**
	 * Returns the number of records that meet the criteria
	 * 
	 * @param <T>
	 * @param sql
	 * @param type
	 * @return List
	 */
	public List<T> findByNativeQuery( String sql );

	/**
	 * Returns the number of total records
	 * 
	 * @param namedQueryName
	 * @return int
	 */
	public int countTotalRecord( String namedQueryName );

	/**
	 * Returns the number of records that meet the criteria with parameter map and result limit
	 * 
	 * @param namedQueryName
	 * @param parameters
	 * @param resultLimit
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName, Map parameters, int resultLimit );

	/**
	 * Returns the number of records that will be used with lazy loading / pagination
	 * 
	 * @param namedQueryName
	 * @param start
	 * @param end
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName, int start, int end );
}