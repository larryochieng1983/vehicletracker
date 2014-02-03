/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.smslib.Message;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "SMSSERVER_IN")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class IncomingSms extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243910353101669800L;

	@Column(name = "PROCESSOR")
	private int process;

	@Column(name = "ORIGINATOR")
	@Size(max = 16)
	private String originator;

	@Enumerated
	@Column(name = "TYPE")
	private Message.MessageTypes type;

	@Size(max = 1)
	@Column(name = "ENCODING")
	private String encoding;

	@Column(name = "MESSAGEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date messageDate;

	@Column(name = "RECEIVEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveDate;

	@Column(name = "TEXT")
	@Size(max = 1024)
	private String text;

	@Column(name = "ORIGINAL_REF_NO")
	@Size(max = 64)
	private String originalRefNumber;

	@Column(name = "ORGINAL_RECEIVE_DATE")
	private Date originalReceiveDate;

	@Size(max = 64)
	@Column(name = "GATEWAY_ID")
	private String gatewayId;

	public IncomingSms() {

	}

	public IncomingSms( Message.MessageTypes type, String originator, Date messageDate,
			Date receiveDate, String text ) {
		this.type = type;
		this.originator = originator;
		this.messageDate = messageDate;
		this.receiveDate = receiveDate;
		this.text = text;
	}

	/**
	 * @return the process
	 */
	public int getProcess() {
		return process;
	}

	/**
	 * @param process the process to set
	 */
	public void setProcess( int process ) {
		this.process = process;
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
	 * @return the type
	 */
	public Message.MessageTypes getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType( Message.MessageTypes type ) {
		this.type = type;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText( String text ) {
		this.text = text;
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
