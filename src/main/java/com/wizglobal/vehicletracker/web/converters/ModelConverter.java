package com.wizglobal.vehicletracker.web.converters;

import com.wizglobal.vehicletracker.domain.VehicleModel;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * Converting Model to string and object.
 *
 * @author kenny
 */
@FacesConverter("vehicleModelConverter")
public class ModelConverter extends SelectItemsConverter {
  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object modelObject) {
	VehicleModel model = (VehicleModel) modelObject;
	return model == null ? null : String.valueOf(model.getId());
    }
    
}
