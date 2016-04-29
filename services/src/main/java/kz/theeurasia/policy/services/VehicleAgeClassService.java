package kz.theeurasia.policy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import kz.theeurasia.esbdproxy.domain.dict.osgpovts.VehicleAgeClassDict;
import kz.theeurasia.esbdproxy.services.osgpovts.VehicleAgeClassServiceDAO;

@Named
@ApplicationScoped
public class VehicleAgeClassService {

    private ResourceBundle msg;

    @Inject
    private LocaleService localeService;

    @PostConstruct
    public void init() {
	msg = ResourceBundle.getBundle(Messages.BUNDLE_BASE_NAME, localeService.getLocale());
    }

    @Inject
    private VehicleAgeClassServiceDAO vehicleAgeClassServiceDAO;

    public List<VehicleAgeClassDict> getAllItems() {
	return vehicleAgeClassServiceDAO.getAll();
    }

    public List<SelectItem> getAllItemsSI() {
	return _createSIFromList(getAllItems());
    }

    public List<VehicleAgeClassDict> getSelectableItems() {
	return vehicleAgeClassServiceDAO.getApplicableForOSGPOVTS();
    }

    public List<SelectItem> getSelectableItemsSI() {
	return _createSIFromList(getSelectableItems());
    }

    private List<SelectItem> _createSIFromList(List<VehicleAgeClassDict> list) {
	List<SelectItem> result = new ArrayList<>();
	for (VehicleAgeClassDict r : list) {
	    SelectItem si = new SelectItem(r,
		    msg.getString(Messages.LABEL_VEHICLE_AGE_CLASS_PREFIX.getCode() + r.name()));
	    result.add(si);
	}
	return result;
    }
}
