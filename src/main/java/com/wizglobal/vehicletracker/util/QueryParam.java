package com.wizglobal.vehicletracker.util;

/**
 * Key Value pair of param name and value.
 *
 * @author kenny
 */
public class QueryParam {
    
    private String name;
    private Object value;

    /**
     *
     * @param name Name of param.
     * @param value value of param.
     */
    public QueryParam(String name, Object value) {
	this.name = name;
	this.value = value;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Object getValue() {
	return value;
    }

    public void setValue(Object value) {
	this.value = value;
    }
    
    
}
