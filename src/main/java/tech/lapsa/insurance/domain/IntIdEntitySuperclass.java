package tech.lapsa.insurance.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyOptionals;

@MappedSuperclass
public abstract class IntIdEntitySuperclass extends Domain {

    private static final long serialVersionUID = 1L;

    // constructor

    protected IntIdEntitySuperclass() {
    }

    protected IntIdEntitySuperclass(final Integer id) throws IllegalArgumentException {
	this.id = MyNumbers.requireNonZero(id, "id");
    }

    // id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
	return id;
    }

    protected String appendEntityId() {
	return appendEntityId(id);
    }

    protected static String appendEntityId(final Object id) {
	return " [ID=" + MyOptionals.of(id).map(Object::toString).orElse("NONE") + "]";
    }
}
