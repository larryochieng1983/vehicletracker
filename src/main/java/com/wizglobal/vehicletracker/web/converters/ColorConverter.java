package com.wizglobal.vehicletracker.web.converters;

import com.wizglobal.vehicletracker.domain.VehicleColor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * Converting Color to string and object.
 *
 * @author kenny
 */
@FacesConverter("vehicleColorConverter")
public class ColorConverter extends SelectItemsConverter {
  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object colorObject) {
	VehicleColor color = (VehicleColor) colorObject;
	return color == null ? null : String.valueOf(color.getId());
    }
    
}
