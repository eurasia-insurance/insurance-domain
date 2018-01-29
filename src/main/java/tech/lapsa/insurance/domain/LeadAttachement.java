package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class LeadAttachement extends CreationParameteredIntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // constructor

    protected LeadAttachement() {
    }

    // lead

    @ManyToOne(fetch = EAGER, cascade = { DETACH, MERGE, PERSIST, REFRESH }, optional = false)
    @JoinColumn(name = "LEAD_ID")
    protected Lead lead;

    public Lead getLead() {
	return lead;
    }

}
