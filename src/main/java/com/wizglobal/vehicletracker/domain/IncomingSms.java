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

	@Size(max = 1)
	@Column(name = "ENCODING")
	private String encoding;

	@Column(name = "MESSAGEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageDate;

	@Column(name = "RECEIVEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveDate;

	@Size(max = 255)
	@Column(name = "MESSAGE")	
	private String message;

	@Size(max = 64)
	@Column(name = "ORIGINAL_REF_NO")	
	private String originalRefNumber;

	@Column(name = "ORGINAL_RECEIVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date originalReceiveDate;

	@Size(max = 64)
	@Column(name = "GATEWAY_ID")
	private String gatewayId;

	public IncomingSms() {

	}

	public IncomingSms( String originator, Date messageDate,
			Date receiveDate, String message ) {
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
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding( String encoding ) {
		this.encoding = encoding;
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

	/**
	 * @return the originalRefNumber
	 */
	public String getOriginalRefNumber() {
		return originalRefNumber;
	}

	/**
	 * @param originalRefNumber the originalRefNumber to set
	 */
	public void setOriginalRefNumber( String originalRefNumber ) {
		this.originalRefNumber = originalRefNumber;
	}

	/**
	 * @return the originalReceiveDate
	 */
	public Date getOriginalReceiveDate() {
		return originalReceiveDate;
	}

	/**
	 * @param originalReceiveDate the originalReceiveDate to set
	 */
	public void setOriginalReceiveDate( Date originalReceiveDate ) {
		this.originalReceiveDate = originalReceiveDate;
	}

	/**
	 * @return the gatewayId
	 */
	public String getGatewayId() {
		return gatewayId;
	}

	/**
	 * @param gatewayId the gatewayId to set
	 */
	public void setGatewayId( String gatewayId ) {
		this.gatewayId = gatewayId;
	}

}
