package kz.theeurasia.policy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import kz.theeurasia.esbdproxy.domain.enums.osgpovts.InsuredExpirienceClassEnum;

@Named
@ApplicationScoped
public class InsuredExpirienceClassService {

    private ResourceBundle gpovts;

    @Inject
    private LocaleService localeService;

    @PostConstruct
    public void init() {
	gpovts = ResourceBundle.getBundle(Messages.BUNDLE_BASE_NAME, localeService.getLocale());
    }

    public List<InsuredExpirienceClassEnum> getAllItems() {
	List<InsuredExpirienceClassEnum> result = new ArrayList<>();
	for (InsuredExpirienceClassEnum r : InsuredExpirienceClassEnum.values())
	    result.add(r);
	return result;
    }

    public List<SelectItem> getAllItemsSI() {
	return _createSIFromList(getAllItems());
    }

    public List<InsuredExpirienceClassEnum> getSelectableItems() {
	List<InsuredExpirienceClassEnum> result = new ArrayList<>();
	for (InsuredExpirienceClassEnum r : InsuredExpirienceClassEnum.values())
	    result.add(r);
	return result;
    }

    public List<SelectItem> getSelectableItemsSI() {
	return _createSIFromList(getSelectableItems());
    }

    private List<SelectItem> _createSIFromList(List<InsuredExpirienceClassEnum> list) {
	List<SelectItem> result = new ArrayList<>();
	for (InsuredExpirienceClassEnum r : list) {
	    SelectItem si = new SelectItem(r, gpovts.getString(
		    Messages.LABEL_INSURED_EXPIRIENCE_CLASS_PREFIX.getCode() + r.name()));
	    result.add(si);
	}
	return result;
    }
}
