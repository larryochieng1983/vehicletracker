package com.wizglobal.vehicletracker.web;

import com.wizglobal.vehicletracker.util.StringUtils;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kenny
 */
public abstract class BasePage {
    
    public static final String AJAX_PARTIAL_REQUEST_PARAM = "javax.faces.partial.ajax";
    
    
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
    
    /**
     *
     * @return Request params from the servlet request.
     */
    protected Map<String, String[]> getRequestParams(){
	return getServletRequest().getParameterMap();
    }
    
    /**
     * Append ?faces-redirect=true
     *
     * @param navigationCase navigation case e.g /home.jsf. ;
     * @return {navigationCase}.concat('?faces-redirect=true') or null if nav outcome is null.
     */
    public String appendFacesRedirectTrue(String navigationCase){
	return  StringUtils.isNonEmptyString(navigationCase) ? navigationCase.concat(FacesRedirect.FACES_REDIRECT_TRUE.getValueAsGETparam()) : null;
    }
    
    /**
     *
     * @return determines if the current request is an Ajax request or not.
     */
    protected boolean isAjaxRequest() {
	return "true".equalsIgnoreCase(getServletRequest().getParameter(AJAX_PARTIAL_REQUEST_PARAM));
    }
    
    /**
     * Faces constants for navigation outcomes.
     *
     */
    public static enum FacesRedirect{
	/**
	 * faces-redirect=true
	 *
	 */
	FACES_REDIRECT_TRUE("faces-redirect=true"),
	
	/**
	 * faces-redirect=true
	 *
	 */
	FACES_REDIRECT_FALSE("faces-redirect=false");
	
	private final String value;
	
	private FacesRedirect(String stringVal) {
	    value = stringVal;
	}

	/**
	 *
	 * @return string value of the enum.
	 */
	public String getValue() {
	    return value;
	}
	
	/**
	 *
	 * @return adds '?' to the {@link #getValue() } above.
	 */
	public String getValueAsGETparam() {
	    return "?".concat(value);
	}
    }
}
