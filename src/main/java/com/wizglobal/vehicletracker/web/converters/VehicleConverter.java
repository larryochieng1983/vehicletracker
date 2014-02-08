package com.wizglobal.vehicletracker.web.converters;

import com.wizglobal.vehicletracker.domain.Vehicle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * Converting customer to string and object.
 *
 * @author kenny
 */
@FacesConverter("vehicleConverter")
public class VehicleConverter extends SelectItemsConverter {
  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object vehicleObject) {
	Vehicle vehicle = (Vehicle) vehicleObject;
	return vehicle == null ? null : String.valueOf(vehicle.getId());
    }
    
}
