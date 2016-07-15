package kz.theeurasia.esbdproxy.domain.entities.general;

import com.lapsa.insurance.elements.SubjectType;

/**
 * Класс для представления субъекта - юридического лица (компания)
 * 
 * @author vadim.isaev
 *
 */
public class SubjectCompanyEntity extends SubjectEntity {

    // Juridical_Person_Name s:string Наименование (для юр. лица)
    private String companyName;

    // MAIN_CHIEF s:string Первый руководитель
    private String headName;

    // MAIN_ACCOUNTANT s:string Главный бухгалтер
    private String accountantName;

    // ACTIVITY_KIND_ID s:int Вид деятельности (справочник ACTIVITY_KINDS)
    private long companyActivityKindId;
    private CompanyActivityKindEntity companyActivityKind;

    @Override
    public int hashCode() {
	return this.getClass().hashCode() * new Long(getId()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
	return obj != null && this.getClass().isInstance(obj) && getId() == this.getClass().cast(obj).getId();
    }

    @Override
    public SubjectType getSubjectType() {
	return SubjectType.COMPANY;
    }

    // GENERATED

    public String getCompanyName() {
	return companyName;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    public String getHeadName() {
	return headName;
    }

    public void setHeadName(String headName) {
	this.headName = headName;
    }

    public String getAccountantName() {
	return accountantName;
    }

    public void setAccountantName(String accountantName) {
	this.accountantName = accountantName;
    }

    public long getCompanyActivityKindId() {
	return companyActivityKindId;
    }

    public void setCompanyActivityKindId(long companyActivityKindId) {
	this.companyActivityKindId = companyActivityKindId;
    }

    public CompanyActivityKindEntity getCompanyActivityKind() {
	return companyActivityKind;
    }

    public void setCompanyActivityKind(CompanyActivityKindEntity companyActivityKind) {
	this.companyActivityKind = companyActivityKind;
    }
}
