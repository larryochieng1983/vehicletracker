/**
 * 
 */
package com.wizglobal.vehicletracker.model;

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
@Table(name = "SMSSERVER_CALLS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VoiceCall extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4146155995341572322L;
	@Column(name = "CALL_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date call_date;
	@Column(name = "GATEWAY_ID")
	private String gatewayId;
	@Column(name = "CALLER_ID")
	private String callerId;

	/**
	 * @return the call_date
	 */
	public Date getCall_date() {
		return call_date;
	}

	/**
	 * @param call_date
	 *            the call_date to set
	 */
	public void setCall_date(Date call_date) {
		this.call_date = call_date;
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

	/**
	 * @return the callerId
	 */
	public String getCallerId() {
		return callerId;
	}

	/**
	 * @param callerId
	 *            the callerId to set
	 */
	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

}
