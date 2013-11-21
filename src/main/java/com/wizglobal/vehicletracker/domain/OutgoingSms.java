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

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "SMSSERVER_OUT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OutgoingSms extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4369855708056490720L;
	@Column(name = "TYPE")
	private char type;
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
	private char wapSignal;
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name = "ORIGINATOR")
	private String originator;
	@Column(name = "ENCODING")
	private char encoding;
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
	@Column(name = "REF_NO")
	private String refNumber;
	@Column(name = "PRIORITY")
	private int priority;
	@Column(name = "STATUS")
	private char status;
	@Column(name = "ERRORS")
	private int errors;
	@Column(name = "GATEWAY_ID")
	private String gatewayId;

	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient
	 *            the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the wapUrl
	 */
	public String getWapUrl() {
		return wapUrl;
	}

	/**
	 * @param wapUrl
	 *            the wapUrl to set
	 */
	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}

	/**
	 * @return the wapExpiryDate
	 */
	public Date getWapExpiryDate() {
		return wapExpiryDate;
	}

	/**
	 * @param wapExpiryDate
	 *            the wapExpiryDate to set
	 */
	public void setWapExpiryDate(Date wapExpiryDate) {
		this.wapExpiryDate = wapExpiryDate;
	}

	/**
	 * @return the wapSignal
	 */
	public char getWapSignal() {
		return wapSignal;
	}

	/**
	 * @param wapSignal
	 *            the wapSignal to set
	 */
	public void setWapSignal(char wapSignal) {
		this.wapSignal = wapSignal;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the originator
	 */
	public String getOriginator() {
		return originator;
	}

	/**
	 * @param originator
	 *            the originator to set
	 */
	public void setOriginator(String originator) {
		this.originator = originator;
	}

	/**
	 * @return the encoding
	 */
	public char getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding
	 *            the encoding to set
	 */
	public void setEncoding(char encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the statusReport
	 */
	public int getStatusReport() {
		return statusReport;
	}

	/**
	 * @param statusReport
	 *            the statusReport to set
	 */
	public void setStatusReport(int statusReport) {
		this.statusReport = statusReport;
	}

	/**
	 * @return the flash_sms
	 */
	public int getFlash_sms() {
		return flash_sms;
	}

	/**
	 * @param flash_sms
	 *            the flash_sms to set
	 */
	public void setFlash_sms(int flash_sms) {
		this.flash_sms = flash_sms;
	}

	/**
	 * @return the srcPort
	 */
	public int getSrcPort() {
		return srcPort;
	}

	/**
	 * @param srcPort
	 *            the srcPort to set
	 */
	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	/**
	 * @return the dstPort
	 */
	public int getDstPort() {
		return dstPort;
	}

	/**
	 * @param dstPort
	 *            the dstPort to set
	 */
	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	/**
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * @param sentDate
	 *            the sentDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	/**
	 * @return the refNumber
	 */
	public String getRefNumber() {
		return refNumber;
	}

	/**
	 * @param refNumber
	 *            the refNumber to set
	 */
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * @return the errors
	 */
	public int getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(int errors) {
		this.errors = errors;
	}

	/**
	 * @return the gatewayId
	 */
	public String getGatewayId() {
		return gatewayId;
	}

	/**
	 * @param gatewayId
	 *            the gatewayId to set
	 */
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

}
