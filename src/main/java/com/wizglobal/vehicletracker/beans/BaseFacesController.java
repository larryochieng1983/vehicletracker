package com.wizglobal.vehicletracker.beans;

import javax.faces.application.FacesMessage;
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
     * Add an info faces message to the current faces context.
     *
     * @param messageSummary Short part of message
     * @param MessageDetail optional, detailed message
     */
    public void addInfoMessage(String messageSummary, String MessageDetail){
	getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, messageSummary, MessageDetail));
    }
    
    /**
     * Add warning faces message to the current faces context.
     *
     * @param messageSummary Short part of message
     * @param MessageDetail optional, detailed message
     */
    public void addWarningMessage(String messageSummary, String MessageDetail){
	getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, messageSummary, MessageDetail));
    }
    
    /**
     * Add warning faces message to the current faces context.
     *
     * @param messageSummary Short part of message
     * @param MessageDetail optional, detailed message
     */
    public void addErrorgMessage(String messageSummary, String MessageDetail){
	getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, messageSummary, MessageDetail));
    }

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
