package com.wizglobal.vehicletracker.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kenny
 */
public abstract class BaseFacesController {
    
    /**
     * Method to annotate with @PostConstruct
     *
     */
    public abstract void init();

    /**
     *
     * @return Faces context
     */
    public FacesContext getFacesContext() {
	return FacesContext.getCurrentInstance();
    }
    
    /**
     *
     * @return Currect HttpServletRequest.
     */
    public HttpServletRequest getServletRequest(){
	return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }
    
    /**
     *
     * @return Servlet response associated with the current request.
     */
    public HttpServletResponse getreServletResponse(){
	return  (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }
}
