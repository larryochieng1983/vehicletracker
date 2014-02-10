/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "INCOMINGSMS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class IncomingSms extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 16)
	@Column(name = "ORIGINATOR")
	private String originator;

	@Column(name = "MESSAGEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageDate;

	@Column(name = "RECEIVEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveDate;

	@Size(max = 255)
	@Column(name = "MESSAGE")
	private String message;

	public IncomingSms() {

	}

	public IncomingSms( String originator, Date messageDate, Date receiveDate, String message ) {
		this.originator = originator;
		this.messageDate = messageDate;
		this.receiveDate = receiveDate;
		this.message = message;
	}

	/**
	 * @return the originator
	 */
	public String getOriginator() {
		return originator;
	}

	/**
	 * @param originator the originator to set
	 */
	public void setOriginator( String originator ) {
		this.originator = originator;
	}

	/**
	 * @return the messageDate
	 */
	public Date getMessageDate() {
		return messageDate;
	}

	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate( Date messageDate ) {
		this.messageDate = messageDate;
	}

	/**
	 * @return the receiveDate
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}

	/**
	 * @param receiveDate the receiveDate to set
	 */
	public void setReceiveDate( Date receiveDate ) {
		this.receiveDate = receiveDate;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage( String message ) {
		this.message = message;
	}

}
