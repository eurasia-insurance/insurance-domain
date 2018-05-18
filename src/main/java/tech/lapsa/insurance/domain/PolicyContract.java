package tech.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.Column;

public class PolicyContract extends Contract {

    private static final long serialVersionUID = 1L;

    // constructor

    protected PolicyContract() {
    }

    // POLICY_ID s:int Идентификатор полиса (обязательно)

    // externalId

    @Basic
    @Column(name = "EXTERNAL_ID")
    protected Integer externalId;

    public Integer getExternalId() {
	return externalId;
    }

    // POLICY_NUMBER s:string Номер полиса (обязательно)
    
    // number

    @Override
    public String getNumber() {
	return super.getNumber();
    }

    // 
    
    // DATE_BEG s:string Дата начала действия полиса (обязательно)
    // DATE_END s:string Дата окончания действия полиса (обязательно)
    // PREMIUM s:double Страховая премия (обязательно)
    // CALCULATED_PREMIUM s:double Страховая премия рассчитанная системой
    // SYSTEM_DELIMITER_ID s:int Идентификатор страховой компании
    // CLIENT_ID s:int Идентификатор страхователя (обязательно)
    // RECORD_CHANGED_AT s:string Дата\время изменения полиса
    // RECORD_CHANGED_AT_DATETIME s:string Дата\время изменения полиса
    // CREATED_BY_USER_ID s:int Идентификатор пользователя, создавшего полис
    // INPUT_DATE s:string Дата\время ввода полиса в систему
    // CHANGED_BY_USER_ID s:int Идентификатор пользователя, изменившего полис
    // POLICY_DATE s:string Дата полиса
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

}
