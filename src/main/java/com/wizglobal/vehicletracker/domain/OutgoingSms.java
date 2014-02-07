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
@Table(name = "OUTGOINGSMS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OutgoingSms extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4369855708056490720L;

	@Enumerated
	@Column(name = "TYPE")
	private Message.MessageTypes type;

	@Column(name = "RECIPIENT")
	private String recipient;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "WAP_URL")
	private String wapUrl;

	@Column(name = "WAP_EXPIRY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date wapExpiryDate;

	@Column(name = "WAP_SIGNAL")
	private String wapSignal;

	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "ORIGINATOR")
	private String originator;

	@Size(max=1)
	@Column(name = "ENCODING")
	private String encoding;

	@Column(name = "STATUS_REPORT")
	private int statusReport;

	@Column(name = "FLASH_SMS")
	private int flash_sms;

	@Column(name = "SRC_PORT")
	private int srcPort;

	@Column(name = "DST_PORT")
	private int dstPort;

	@Column(name = "SENT_DATE")
	private Date sentDate;

	@Size(max=1)
	@Column(name = "REF_NO")
	private String refNumber;

	@Column(name = "PRIORITY")
	private int priority;

	@Size(max=1)
	@Column(name = "STATUS")
	private String status;

	@Column(name = "ERRORS")
	private int errors;

	@Column(name = "GATEWAY_ID")
	private String gatewayId;

	public OutgoingSms() {

	}

	public OutgoingSms( Message.MessageTypes type, String recipient, String text, Date sentDate ) {
		this.type = type;
		this.recipient = recipient;
		this.text = text;
		this.sentDate = sentDate;
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
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient( String recipient ) {
		this.recipient = recipient;
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
	 * @return the wapUrl
	 */
	public String getWapUrl() {
		return wapUrl;
	}

	/**
	 * @param wapUrl the wapUrl to set
	 */
	public void setWapUrl( String wapUrl ) {
		this.wapUrl = wapUrl;
	}

	/**
	 * @return the wapExpiryDate
	 */
	public Date getWapExpiryDate() {
		return wapExpiryDate;
	}

	/**
	 * @param wapExpiryDate the wapExpiryDate to set
	 */
	public void setWapExpiryDate( Date wapExpiryDate ) {
		this.wapExpiryDate = wapExpiryDate;
	}

	/**
	 * @return the wapSignal
	 */
	public String getWapSignal() {
		return wapSignal;
	}

	/**
	 * @param wapSignal the wapSignal to set
	 */
	public void setWapSignal( String wapSignal ) {
		this.wapSignal = wapSignal;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate( Date createDate ) {
		this.createDate = createDate;
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
	 * @return the statusReport
	 */
	public int getStatusReport() {
		return statusReport;
	}

	/**
	 * @param statusReport the statusReport to set
	 */
	public void setStatusReport( int statusReport ) {
		this.statusReport = statusReport;
	}

	/**
	 * @return the flash_sms
	 */
	public int getFlash_sms() {
		return flash_sms;
	}

	/**
	 * @param flash_sms the flash_sms to set
	 */
	public void setFlash_sms( int flash_sms ) {
		this.flash_sms = flash_sms;
	}

	/**
	 * @return the srcPort
	 */
	public int getSrcPort() {
		return srcPort;
	}

	/**
	 * @param srcPort the srcPort to set
	 */
	public void setSrcPort( int srcPort ) {
		this.srcPort = srcPort;
	}

	/**
	 * @return the dstPort
	 */
	public int getDstPort() {
		return dstPort;
	}

	/**
	 * @param dstPort the dstPort to set
	 */
	public void setDstPort( int dstPort ) {
		this.dstPort = dstPort;
	}

	/**
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * @param sentDate the sentDate to set
	 */
	public void setSentDate( Date sentDate ) {
		this.sentDate = sentDate;
	}

	/**
	 * @return the refNumber
	 */
	public String getRefNumber() {
		return refNumber;
	}

	/**
	 * @param refNumber the refNumber to set
	 */
	public void setRefNumber( String refNumber ) {
		this.refNumber = refNumber;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority( int priority ) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus( String status ) {
		this.status = status;
	}

	/**
	 * @return the errors
	 */
	public int getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors( int errors ) {
		this.errors = errors;
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
