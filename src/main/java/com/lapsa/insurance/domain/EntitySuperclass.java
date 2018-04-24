package com.lapsa.insurance.domain;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;

@MappedSuperclass
public abstract class EntitySuperclass extends Domain {

    private static final long serialVersionUID = 1L;

    protected EntitySuperclass() {
    }

    protected EntitySuperclass(final Integer id) {
	this.id = MyNumbers.requireNonZero(id, "id");
    }

    // id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
	return id;
    }

    // autoUpdated

    @Version
    @Column(name = "OPTLOCK")
    private Timestamp version;

    public Instant getUpdatedVersion() {
	return MyObjects.nullOrGet(version, Timestamp::toInstant);
    }

    protected String appendEntityId() {
	return appendEntityId(id);
    }

    protected static String appendEntityId(final Object id) {
	return " [ID=" + MyOptionals.of(id).map(Object::toString).orElse("NONE") + "]";
    }
}
