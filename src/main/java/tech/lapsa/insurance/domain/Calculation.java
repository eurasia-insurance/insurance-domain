package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.time.Instant;
import java.util.Currency;
import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CALCULATION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Calculation
	extends IntIdEntitySuperclass
	implements Comparable<Calculation> {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Calculation() {
    }

    // created

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    protected Instant created = Instant.now();

    public Instant getCreated() {
	return created;
    }

    // lead

    @ManyToOne(fetch = EAGER, cascade = { DETACH, MERGE, PERSIST, REFRESH }, optional = false)
    @JoinColumn(name = "LEAD_ID")
    protected Lead lead;

    public Lead getLead() {
	return lead;
    }

    // amount

    @Basic
    @Column(name = "AMOUNT")
    protected Double amount;

    public Double getAmount() {
	return amount;
    }

    // currency

    @Basic
    @Column(name = "CURRENCY")
    protected Currency currency;

    public Currency getCurrency() {
	return currency;
    }

    // other

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int compareTo(final Calculation o) {
	return -created.compareTo(o.created);
    }
}
