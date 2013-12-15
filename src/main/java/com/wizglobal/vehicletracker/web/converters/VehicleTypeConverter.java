package com.wizglobal.vehicletracker.web.converters;

import com.wizglobal.vehicletracker.domain.VehicleType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * Converting customer to string and object.
 *
 * @author kenny
 */
@FacesConverter("vehicleTypeConverter")
public class VehicleTypeConverter extends SelectItemsConverter {
  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object typeObject) {
	VehicleType vehicleType = (VehicleType) typeObject;
	return vehicleType == null ? null : String.valueOf(vehicleType.getId());
    }
    
}
