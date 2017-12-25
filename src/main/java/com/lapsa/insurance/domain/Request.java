package com.lapsa.insurance.domain;

import java.time.Instant;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lapsa.insurance.domain.crm.InetAddrData;
import com.lapsa.insurance.domain.crm.UTMData;
import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.ProgressStatus;
import com.lapsa.insurance.elements.RequestSource;
import com.lapsa.insurance.elements.RequestStatus;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.javax.validation.NotNullValue;

@Entity
@Table(name = "REQUEST", indexes = { //
	@Index(name = "REQUEST_IDX01", columnList = "STATUS"), //
	@Index(name = "REQUEST_IDX02", columnList = "CREATED"), //
	@Index(name = "REQUEST_IDX03", columnList = "CLOSED") //
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Request extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    protected Instant created;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED")
    protected Instant updated;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACCEPTED")
    protected Instant accepted;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETED")
    protected Instant completed;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLOSED")
    protected Instant closed;

    public Request() {
    }

    public Request(final RequestSource source) {
	this.source = source;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "SOURCE")
    @NotNullValue
    protected RequestSource source;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    @NotNullValue
    protected RequestStatus status = RequestStatus.OPEN;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "PROGRESS_STATUS")
    @NotNullValue
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

    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "CREATED_BY_USER_ID")
    protected User createdBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCEPTED_BY_USER_ID")
    protected User acceptedBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPLETED_BY_USER_ID")
    protected User completedBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLOSED_BY_USER_ID")
    protected User closedBy;

    @Basic
    @Column(name = "NOTE")
    protected String note;

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getCreatedBy()).ifPresent(Domain::unlazy);
	MyOptionals.of(getAcceptedBy()).ifPresent(Domain::unlazy);
	MyOptionals.of(getCompletedBy()).ifPresent(Domain::unlazy);
	MyOptionals.of(getClosedBy()).ifPresent(Domain::unlazy);
    }

    // GENERATED

    public Instant getCreated() {
	return created;
    }

    public void setCreated(final Instant created) {
	this.created = created;
    }

    public Instant getUpdated() {
	return updated;
    }

    public void setUpdated(final Instant updated) {
	this.updated = updated;
    }

    public Instant getClosed() {
	return closed;
    }

    public void setClosed(final Instant closed) {
	this.closed = closed;
    }

    public User getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(final User createdBy) {
	this.createdBy = createdBy;
    }

    public Instant getAccepted() {
	return accepted;
    }

    public void setAccepted(final Instant accepted) {
	this.accepted = accepted;
    }

    public Instant getCompleted() {
	return completed;
    }

    public void setCompleted(final Instant completed) {
	this.completed = completed;
    }

    public RequestStatus getStatus() {
	return status;
    }

    public void setStatus(final RequestStatus status) {
	this.status = status;
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

    public User getAcceptedBy() {
	return acceptedBy;
    }

    public void setAcceptedBy(final User acceptedBy) {
	this.acceptedBy = acceptedBy;
    }

    public User getCompletedBy() {
	return completedBy;
    }

    public void setCompletedBy(final User completedBy) {
	this.completedBy = completedBy;
    }

    public User getClosedBy() {
	return closedBy;
    }

    public void setClosedBy(final User closedBy) {
	this.closedBy = closedBy;
    }

    public String getNote() {
	return note;
    }

    public void setNote(final String note) {
	this.note = note;
    }

    public RequestSource getSource() {
	return source;
    }

    public void setSource(final RequestSource source) {
	this.source = source;
    }

    public InetAddrData getInetAddrData() {
	return inetAddrData;
    }

    public void setInetAddrData(final InetAddrData inetAddrData) {
	this.inetAddrData = inetAddrData;
    }
}
