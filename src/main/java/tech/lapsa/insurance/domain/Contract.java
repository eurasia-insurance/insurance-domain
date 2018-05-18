package tech.lapsa.insurance.domain;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CONTRACT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISCR_TYPE")
public abstract class Contract extends LeadAttachement {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Contract() {
    }

    // number

    @Basic
    @Column(name = "NUMBER")
    protected String number;

    /**
     * Номер договора
     * 
     * <h3>Структура <b>Policy</b> ЕСБД</h3>
     * 
     * <pre>
     * POLICY_NUMBER s:string Номер полиса (обязательно)
     * </pre>
     * 
     * @return дата
     */
    public String getNumber() {
	return number;
    }

    // date

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    protected LocalDate date;

    /**
     * Дата заключечния договора
     * 
     * <h3>Структура <b>Policy</b> ЕСБД</h3>
     * 
     * <pre>
     * POLICY_DATE s:string Дата полиса
     * </pre>
     * 
     * @return дата
     */
    public LocalDate getDate() {
	return date;
    }

    // validFrom

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_FROM")
    private LocalDate validFrom;

    /**
     * Дата начала действия договора
     * 
     * <h3>Структура <b>Policy</b> ЕСБД</h3>
     * 
     * <pre>
     * DATE_BEG s:string Дата начала действия полиса (обязательно)
     * </pre>
     * 
     * @return дата
     */
    public LocalDate getValidFrom() {
	return validFrom;
    }

    // validTo

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_TO")
    private LocalDate validTo;

    /**
     * Дата окончания действия договора
     * 
     * <h3>Структура <b>Policy</b> ЕСБД</h3>
     * 
     * <pre>
     * DATE_END s:string Дата окончания действия полиса (обязательно)
     * </pre>
     * 
     * @return дата
     */
    public LocalDate getValidTo() {
	return validTo;
    }

    // party

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "idNumber", column = @Column(name = "PARTY_IDNUMBER")),
	    @AttributeOverride(name = "name", column = @Column(name = "PARTY_NAME")),
	    @AttributeOverride(name = "firstName", column = @Column(name = "PARTY_FIRST_NAME")),
	    @AttributeOverride(name = "surename", column = @Column(name = "PARTY_SURENAME")),
	    @AttributeOverride(name = "patronymic", column = @Column(name = "PARTY_PATRONYMIC")),
	    @AttributeOverride(name = "dayOfBirth", column = @Column(name = "PARTY_DOB")),
    })
    protected PersonalInformation party;

    public PersonalInformation getParty() {
	return party;
    }

    // POLICY_ID s:int Идентификатор полиса (обязательно)
    // CALCULATED_PREMIUM s:double Страховая премия рассчитанная системой
    // SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
    // CLIENT_ID s:int Идентификатор страхователя (обязательно)
    // RECORD_CHANGED_AT s:string Дата\время изменения полиса
    // RECORD_CHANGED_AT_DATETIME s:string Дата\время изменения полиса
    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего полис
    // INPUT_DATE s:string Дата\время ввода полиса в систему
    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего полис
    // RESCINDING_DATE s:string Дата расторжения полиса
    // REWRITE_BOOL s:int Признак переоформления
    // BRANCH_ID s:int Филиал (обязательно)
    // REWRITE_POLICY_ID s:int Ссылка на переоформляемый полис
    // RESCINDING_REASON_ID s:int Идентификатор причины расторжения
    // DESCRIPTION s:string Комментарии к полису
    // Drivers tns:ArrayOfDriver Водители (обязательно)
    // PoliciesTF tns:ArrayOfPolicies_TF Транспортные средства полиса
    // (обязательно)
    // GLOBAL_ID s:string Уникальный глобальный идентификатор полиса
    // ScheduledPayments tns:ArrayOfSCHEDULED_PAYMENT Плановые платежи по полису
    // PAYMENT_ORDER_TYPE_ID s:int Порядок оплаты (Идентификатор)
    // PAYMENT_ORDER_TYPE s:string Порядок оплаты
    // PAYMENT_DATE s:string Дата оплаты
    // MIDDLEMAN_ID s:int Посредник (Идентификатор)
    // MIDDLEMAN_CONTRACT_NUMBER s:string Номер договора посредника
    // CLIENT_FORM_ID s:int Форма клиента (справочник CLIENT_FORMS)

    // amount

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "amount", column = @Column(name = "CONTRACT_AMOUNT")),
	    @AttributeOverride(name = "currency", column = @Column(name = "CONTRACT_CURRENCY")),
    })
    protected Amount contractAmount;

    /**
     * Сумма договора
     * 
     * 
     * <h3>Структура <b>Policy</b> ЕСБД</h3>
     * 
     * <pre>
     * PREMIUM s:double Страховая премия (обязательно)
     * </pre>
     * 
     * @return структура содержащая сумму и валюту договора
     */
    public Amount getContractAmount() {
	return contractAmount;
    }
}
