package com.lapsa.insurance.domain;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import tech.lapsa.java.commons.function.MyFunctions;
import tech.lapsa.java.commons.function.MyObjects;

@MappedSuperclass
public abstract class SidedScannedDocument extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "FRONTSIDE_IMAGE_ID")
    // @MaxImageFileSize(measure = FileSizeMeasure.MB, value = 10)
    private Image frontside;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "BACKSIDE_IMAGE_ID")
    // @MaxImageFileSize(measure = FileSizeMeasure.MB, value = 10)
    private Image backside;

    @Override
    public void unlazy() {
	super.unlazy();
	Arrays.asList(getFrontside(), getBackside()) //
		.stream() //
		.filter(MyObjects::nonNull) //
		.map(Image::getContent)
		.forEach(MyFunctions.voidConsumer());
    }

    // GENERATED

    public Image getFrontside() {
	return frontside;
    }

    public void setFrontside(final Image frontside) {
	this.frontside = frontside;
    }

    public Image getBackside() {
	return backside;
    }

    public void setBackside(final Image backside) {
	this.backside = backside;
    }
}
