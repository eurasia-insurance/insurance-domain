package com.lapsa.insurance.domain;

import java.time.Instant;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lapsa.insurance.domain.crm.InetAddrData;
import com.lapsa.insurance.domain.crm.UTMData;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.ProgressStatus;

import tech.lapsa.java.commons.function.MyOptionals;

@Entity
@Table(name = "REQUEST", indexes = { //
	@Index(name = "IX_REQUEST_CREATED", columnList = "CREATED"), //
	@Index(name = "IX_REQUEST_ARCHIVED", columnList = "ARCHIVED")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Request extends EntitySuperclass {

    private static final long serialVersionUID = 1L;

    public Request() {
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    protected Instant created;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PICKED")
    protected Instant picked;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETED")
    protected Instant completed;

    @Basic
    @Column(name = "ARCHIVED")
    protected boolean archived = false;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "PROGRESS_STATUS")
    protected ProgressStatus progressStatus = ProgressStatus.NEW;

    @Embedded
    protected RequesterData requester = new RequesterData();

    @Embedded
    protected UTMData utmData = new UTMData();

    @Embedded
    @AttributeOverrides({ //
	    @AttributeOverride(name = "inetAddress", column = @Column(name = "REQUESTER_INET_ADDR")), //
	    @AttributeOverride(name = "inetHost", column = @Column(name = "REQUESTER_INET_HOST")), //
    })
    protected InetAddrData inetAddrData = new InetAddrData();

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "CREATED_BY_USER_ID")
    protected User createdBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "PICKED_BY_USER_ID")
    protected User pickedBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "COMPLETED_BY_USER_ID")
    protected User completedBy;

    @Basic
    @Lob
    @Column(name = "NOTE")
    protected String note;

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getCreatedBy()).ifPresent(Domain::unlazy);
	MyOptionals.of(getPickedBy()).ifPresent(Domain::unlazy);
	MyOptionals.of(getCompletedBy()).ifPresent(Domain::unlazy);
    }

    // GENERATED

    public Instant getCreated() {
	return created;
    }

    public void setCreated(final Instant created) {
	this.created = created;
    }

    public boolean getArchived() {
	return archived;
    }

    public void setArchived(final boolean archived) {
	this.archived = archived;
    }

    public User getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(final User createdBy) {
	this.createdBy = createdBy;
    }

    public Instant getPicked() {
	return picked;
    }

    public void setPicked(final Instant picked) {
	this.picked = picked;
    }

    public Instant getCompleted() {
	return completed;
    }

    public void setCompleted(final Instant completed) {
	this.completed = completed;
    }

    public ProgressStatus getProgressStatus() {
	return progressStatus;
    }

    public void setProgressStatus(final ProgressStatus progressStatus) {
	this.progressStatus = progressStatus;
    }

    public RequesterData getRequester() {
	return requester;
    }

    public void setRequester(final RequesterData requester) {
	this.requester = requester;
    }

    public UTMData getUtmData() {
	return utmData;
    }

    public void setUtmData(final UTMData utmData) {
	this.utmData = utmData;
    }

    public User getPickedBy() {
	return pickedBy;
    }

    public void setPickedBy(final User pickedBy) {
	this.pickedBy = pickedBy;
    }

    public User getCompletedBy() {
	return completedBy;
    }

    public void setCompletedBy(final User completedBy) {
	this.completedBy = completedBy;
    }

    public String getNote() {
	return note;
    }

    public void setNote(final String note) {
	this.note = note;
    }

    public InetAddrData getInetAddrData() {
	return inetAddrData;
    }

    public void setInetAddrData(final InetAddrData inetAddrData) {
	this.inetAddrData = inetAddrData;
    }
}
