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
@Table(name = "OUTGOINGSMS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OutgoingSms extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4369855708056490720L;

	@Column(name = "RECIPIENT")
	private String recipient;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "ORIGINATOR")
	private String originator;

	@Column(name = "SENT_DATE")
	private Date sentDate;

	public OutgoingSms() {

	}

	public OutgoingSms( String recipient, String text, Date sentDate ) {
		this.recipient = recipient;
		this.text = text;
		this.sentDate = sentDate;
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

}
