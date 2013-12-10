/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ManagedBean
@ViewScoped
public class Index {
	public Index() {
	}

	@PostConstruct
	public void init() {
		//Some test
		JpaUtil jpaUtil = new JpaUtil();
		try {
			// some stupid code that triggers the database to be created
			AppConfig.getAllUsers( jpaUtil );
		} finally {
			// cleanup the transaction
			jpaUtil.closeEm();
		}
	}

	public String getHello() {
		return "hello";
	}
}
