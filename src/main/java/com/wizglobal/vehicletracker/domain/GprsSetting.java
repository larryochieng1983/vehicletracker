/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "GPRS_SETTING")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GprsSetting extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "SERVICE_PROVIDER_NAME")
	@Size(max = 40)
	@NotNull
	private String serviceProviderName;

	@Column(name = "ACCESS_POINT_NAME")
	@Size(max = 40)
	@NotNull
	private String accessPointName;

	@Column(name = "USER_NAME")
	@Size(max = 20)
	@NotNull
	private String userName;

	@Column(name = "ACCESS_PASSWORD")
	@Size(max = 20)
	@NotNull
	private String password;

	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName( String serviceProviderName ) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return the accessPointName
	 */
	public String getAccessPointName() {
		return accessPointName;
	}

	/**
	 * @param accessPointName the accessPointName to set
	 */
	public void setAccessPointName( String accessPointName ) {
		this.accessPointName = accessPointName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName( String userName ) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

}
