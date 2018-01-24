package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStreams;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "LEAD")
@HashCodePrime(3)
public class Lead extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    @Override
    public String localized(LocalizationVariant variant, Locale locale) {
	// TODO Auto-generated method stub
	return null;
    }

    // constructor

    protected Lead() {
    }

    // created

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    protected Instant created = Instant.now();

    public Instant getCreated() {
	return created;
    }

    // updated

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED")
    protected Instant updated;

    public Instant getUpdated() {
	return updated;
    }

    // accepted

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACCEPTED")
    protected Instant accepted;

    public Instant getAccepted() {
	return accepted;
    }

    // completed

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETED")
    protected Instant completed;

    public Instant getCompleted() {
	return completed;
    }

    // closed

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLOSED")
    protected Instant closed;

    public Instant getClosed() {
	return closed;
    }

    // requesterName

    @Basic
    @Column(name = "REQUESTER_NAME")
    protected String requesterName;

    public String getRequesterName() {
	return requesterName;
    }

    // phone

    @Basic
    @Column(name = "REQUESTER_PHONE")
    protected PhoneNumber requesterPhone;

    public PhoneNumber getRequesterPhone() {
	return requesterPhone;
    }

    // email

    @Basic
    @Column(name = "REQUESTER_EMAIL")
    protected String requesterEmail;

    public String getRequesterEmail() {
	return requesterEmail;
    }

    public Optional<String> optRequesterEmail() {
	return MyOptionals.of(requesterEmail);
    }

    //

    // calculations

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "lead")
    protected List<Calculation> calculations;

    public List<Calculation> getCalculations() {
	return MyStreams.orEmptyOf(calculations)
		.sorted()
		.collect(MyCollectors.unmodifiableList());
    }

    public Optional<Calculation> optFirstCalculation() {
	return MyStreams.orEmptyOf(calculations).sorted().findFirst();
    }

    public boolean isHasCalculation() {
	return optFirstCalculation().isPresent();
    }
}
