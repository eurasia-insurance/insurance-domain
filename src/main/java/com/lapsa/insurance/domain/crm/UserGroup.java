package com.lapsa.insurance.domain.crm;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Stream;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.lapsa.insurance.domain.BaseEntity;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "USER_GROUP")
@HashCodePrime(197)
public class UserGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_2_GROUP", joinColumns = @JoinColumn(name = "USER_GROUP_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> members = new ArrayList<>();

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(USER_GROUP.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(name) //
		.ifPresent(sj::add);

	MyOptionals.of(members)
		.map(List::stream) //
		.map(Stream::count)
		.flatMap(MyOptionals::of)
		.map(String::valueOf)
		.map(USER_GRPOUP_MEMBER.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId())
		.toString();
    }

    // GENERATED

    public List<User> getMembers() {
	return members;
    }

    protected void setMembers(final List<User> members) {
	this.members = members;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }
}
