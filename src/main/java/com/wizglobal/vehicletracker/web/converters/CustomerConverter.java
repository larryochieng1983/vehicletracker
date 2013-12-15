package com.wizglobal.vehicletracker.web.converters;

import com.wizglobal.vehicletracker.domain.Customer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 * Converting customer to string and object.
 *
 * @author kenny
 */
@FacesConverter("customerConverter")
public class CustomerConverter extends SelectItemsConverter {
  
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object customerObject) {
	Customer customer = (Customer) customerObject;
	return customer == null ? null : String.valueOf(customer.getId());
    }
    
}
