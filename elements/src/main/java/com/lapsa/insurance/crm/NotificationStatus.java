package com.lapsa.insurance.crm;

import com.lapsa.insurance.elements.ElementsBundleBase;

public enum NotificationStatus implements ElementsBundleBase {
    PENDING, // Завершено
    DONE, // В очереди
    //
    ;

    @Override
    public String canonicalName() {
	return String.format("%1$s.%2$s", this.getClass().getName(), name());
    }
}
