package tech.lapsa.insurance.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "CALCULATION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Calculation extends LeadAttachement {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Calculation() {
    }

    // amount

    @Embedded
    protected Amount amount;

    public Amount getAmount() {
	return amount;
    }
}
