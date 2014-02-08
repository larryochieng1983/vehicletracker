package com.wizglobal.vehicletracker.web;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.service.DataAccessService;
import com.wizglobal.vehicletracker.util.LazySorter;
import com.wizglobal.vehicletracker.util.StringUtils;
import java.util.Collections;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 * @author Otieno Lawrence
 *
 */
public class LazyGpsDeviceDataModel extends LazyDataModel<GpsDevice> implements Serializable {

    private static final long serialVersionUID = 1L;
    private DataAccessService crudService;

    public LazyGpsDeviceDataModel(DataAccessService crudService) {
        this.crudService = crudService;
    }

    @Override
    public Object getRowKey(GpsDevice gpsDevice) {
        return gpsDevice.getId();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    @Override
    public List<GpsDevice> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<GpsDevice> results = crudService.findWithNamedQuery("GpsDevice.findAll", first, first + pageSize);
        if (StringUtils.isNonEmptyString(sortField)) {
            Collections.sort(results, new LazySorter<>(sortField, sortOrder));
        }
        setRowCount(results.size());
        setPageSize(pageSize);
        return results;
    }
}
