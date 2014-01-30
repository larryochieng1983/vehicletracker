package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.exception.DataAccessException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * Implementation of the generic Data Access Service All CRUD (create, read, update, delete) basic data access
 * operations for any persistent object are performed in this class.
 * 
 * @author Otieno Lawrence
 */

public abstract class DataAccessService<T> {
	private static final Logger LOGGER = Logger.getLogger( DataAccessService.class );
	protected EntityManager em;

	public DataAccessService() {
		em = Dba.getInstance().getEntityManager();
	}

	private Class<T> type;

	/**
	 * Default constructor
	 * 
	 * @param type entity class
	 */
	public DataAccessService( Class<T> type ) {
		this();
		this.type = type;
	}

	/**
	 * Stores an instance of the entity class in the database
	 * 
	 * @param T Object
	 * @return
	 */
	public synchronized T create( final T t ) {
		try {
			em.getTransaction().begin();
			this.em.persist( t );
			this.em.flush();
			this.em.refresh( t );
			em.getTransaction().commit();
		} catch( Exception e ) {
			LOGGER.error("Failed to persist entity. " + t + ", \n" + e.getMessage() +  ", transaction will be rolled back" );
		} finally {
			try {
				if( em.getTransaction().isActive() ) {
					em.getTransaction().rollback();
				}
			} catch( Exception exception ) {
				LOGGER.error( "Fatal error in closing transaction.", exception );
			}                    
                }

		return t;
	}

	public synchronized List<T> create( final List<T> list ) {
		try {
			em.getTransaction().begin();
			for( T t : list ) {
				this.em.persist( t );
				this.em.flush();
				this.em.refresh( t );
			}
			em.getTransaction().commit();
		} catch( Exception e ) {
			try {
				LOGGER.warn( "Failed to persist entities, transaction will be rolled back" );
				if( em.getTransaction().isActive() ) {
					em.getTransaction().rollback();
				}
			} catch( Exception exception ) {
				LOGGER.error( "Fatal error in closing transaction.", exception );
			}
		}
		return list;
	}

	/**
	 * Retrieves an entity instance that was previously persisted to the database
	 * 
	 * @param T Object
	 * @param id
	 * @return
	 */
	public T find( Object id ) {
		return this.em.find( this.type, id );
	}

	/**
	 * Removes the record that is associated with the entity instance
	 * 
	 * @param type
	 * @param id
	 */
	public synchronized boolean delete( final Object id ) {
		try {
			this.em.getTransaction().begin();
			Object ref = this.em.getReference( this.type, id );
			this.em.remove( ref );
			this.em.getTransaction().commit();
		} catch( Exception e ) {
			try {
				LOGGER.warn( "Failed to remove entity with Id: " + id
						+ ", transaction will be rolled back" );
				if( em.getTransaction().isActive() ) {
					em.getTransaction().rollback();
				}
			} catch( Exception exception ) {
				LOGGER.error( "Fatal error in closing transaction.", exception );
			}
			return false;
		}
		return true;
	}

	/**
	 * Removes the number of entries from a table
	 * 
	 * @param <T>
	 * @param items
	 * @return true for success, false otherwise.
	 */
	public synchronized boolean deleteItems( final T[] items ) {
		try {
			em.getTransaction().begin();
			for( T item : items ) {
				em.remove( em.merge( item ) );
			}
			em.flush();
			em.getTransaction().commit();
		} catch( Exception e ) {
			try {
				LOGGER.warn( "Failed to remove entities with " + Arrays.asList( items )
						+ ", transaction will be rolled back" );
				if( em.getTransaction().isActive() ) {
					em.getTransaction().rollback();
				}
			} catch( Exception exception ) {
				LOGGER.error( "Fatal error in closing transaction.", exception );
			}
			return false;
		}
		return true;
	}

	/**
	 * Updates the entity instance
	 * 
	 * @param <T>
	 * @param t
	 * @return the object that is updated
	 */
	public synchronized T update( final T item ) throws DataAccessException {
		try {
			em.getTransaction().begin();
			T mergeResult;
			mergeResult = this.em.merge( item );
			em.getTransaction().commit();
			return mergeResult;
		} catch( Exception e ) {
			try {
				LOGGER.warn( "Failed to update entity " + item + ", transaction will be rolled back" );
				if( em.getTransaction().isActive() ) {
					em.getTransaction().rollback();
				}
			} catch( Exception exception ) {
				LOGGER.error( "Fatal error in closing transaction.", exception );
			}
			throw new DataAccessException( "Update aborted. " + e.getMessage(), e );
		}
	}

	/**
	 * Returns the number of records that meet the criteria
	 * 
	 * @param namedQueryName
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName ) {
		return this.em.createNamedQuery( namedQueryName ).getResultList();
	}

	/**
	 * Returns the number of records that meet the criteria
	 * 
	 * @param namedQueryName
	 * @param parameters
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName, Map parameters ) {
		return findWithNamedQuery( namedQueryName, parameters, 0 );
	}

	/**
	 * Returns the number of records with result limit
	 * 
	 * @param queryName
	 * @param resultLimit
	 * @return List
	 */
	public List findWithNamedQuery( String queryName, int resultLimit ) {
		return this.em.createNamedQuery( queryName ).setMaxResults( resultLimit ).getResultList();
	}

	/**
	 * Returns the number of records that meet the criteria
	 * 
	 * @param <T>
	 * @param sql
	 * @param type
	 * @return List
	 */
	public List<T> findByNativeQuery( String sql ) {
		return this.em.createNativeQuery( sql, type ).getResultList();
	}

	/**
	 * Returns the number of total records
	 * 
	 * @param namedQueryName
	 * @return int
	 */
	public int countTotalRecord( String namedQueryName ) {
		Query query = em.createNamedQuery( namedQueryName );
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	/**
	 * Returns the number of records that meet the criteria with parameter map and result limit
	 * 
	 * @param namedQueryName
	 * @param parameters
	 * @param resultLimit
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName, Map parameters, int resultLimit ) {
		Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = this.em.createNamedQuery( namedQueryName );
		if( resultLimit > 0 ) {
			query.setMaxResults( resultLimit );
		}
		for( Map.Entry<String, Object> entry : rawParameters ) {
			query.setParameter( entry.getKey(), entry.getValue() );
		}
		return query.getResultList();
	}

	/**
	 * Returns the number of records that will be used with lazy loading / pagination
	 * 
	 * @param namedQueryName
	 * @param start
	 * @param end
	 * @return List
	 */
	public List findWithNamedQuery( String namedQueryName, int start, int end ) {
		Query query = this.em.createNamedQuery( namedQueryName );
		query.setMaxResults( end - start );
		query.setFirstResult( start );
		return query.getResultList();
	}

}
