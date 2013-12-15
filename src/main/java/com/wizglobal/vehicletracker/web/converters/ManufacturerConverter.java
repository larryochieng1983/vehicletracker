package com.wizglobal.vehicletracker.web.converters;

import com.wizglobal.vehicletracker.domain.VehicleManufacturer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * Converting Manufacturer to string and object.
 *
 * @author kenny
 */
@FacesConverter("vehicleManufacturerConverter")
public class ManufacturerConverter extends SelectItemsConverter {
  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object manufacturerObject) {
	VehicleManufacturer manufacturer = (VehicleManufacturer) manufacturerObject;
	return manufacturer == null ? null : String.valueOf(manufacturer.getId());
    }
    
}
