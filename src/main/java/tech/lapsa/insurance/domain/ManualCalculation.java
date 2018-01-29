package tech.lapsa.insurance.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@DiscriminatorValue("MANUAL_CALCULATION")
@HashCodePrime(17)
public class ManualCalculation extends Calculation {

    private static final long serialVersionUID = 1L;

    // constructor

    protected ManualCalculation() {
    }
}
