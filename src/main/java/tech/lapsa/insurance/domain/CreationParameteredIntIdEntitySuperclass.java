package tech.lapsa.insurance.domain;

import java.time.Instant;
import java.util.Comparator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class CreationParameteredIntIdEntitySuperclass extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // constructor

    protected CreationParameteredIntIdEntitySuperclass() {
    }

    // created

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    protected Instant createdOn = Instant.now();

    public Instant getCreatedOn() {
	return createdOn;
    }

    protected static final Comparator<CreationParameteredIntIdEntitySuperclass> CREATION_REVERSE_ORDER_COMPARATOR = (a,
	    b) -> {
	try {
	    return -a.createdOn.compareTo(b.createdOn);
	} catch (NullPointerException e) {
	    try {
		return -a.id.compareTo(b.id);
	    } catch (NullPointerException e2) {
		return 0;
	    }
	}
    };

}
