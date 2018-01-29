package tech.lapsa.insurance.domain;

import java.util.Currency;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Amount extends Domain {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Amount() {
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
}
