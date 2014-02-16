
package com.wizglobal.vehicletracker.web;

import com.wizglobal.vehicletracker.sms.GatewayService;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.smslib.Service;

/**
 *
 * @author kenny
 */
@Named(value = "gatewayServiceMonitor")
@SessionScoped
public class GatewayServiceMonitor implements Serializable {
    private String startUpMessage;
    
    /**
     * Creates a new instance of GatewayServiceMonitor
     */
    public GatewayServiceMonitor() {
    }

    public void setStartUpMessage(String startUpMessage) {
        this.startUpMessage = startUpMessage;
    }

    public String getStartUpMessage() {
        return startUpMessage;
    }
    
    public String getGatewayStatus(){
        Service.ServiceStatus.
        return GatewayService.getGatewayService().getService().getServiceStatus().toString();
    }
    
}
