package kz.theeurasia.esbdproxy.domain.dict.general;

//TODO перенести в domain 
public enum CancelationReasonDict {

    CANCELATION_AND_RENEW(1), // Досрочное прекращение договора и заключение
			      // нового
    CANCELATION(2), // Досрочное расторжение
    MADE_INSURANCE_PAYMENT(3), // Произведена страховая выплата
    PREMIUM_COST_ERROR(4), // Корректировка неверно рассчитанной премии
    POLICY_LOST(5), // Утеря
    ISSUED_DUPLICATE_POLICY(7), // Выпущен дубликат
    OTHER(8), // Другая причина
    HUMAN_FAILURE(6); // Ошибка оператора

    private final long id;

    CancelationReasonDict(long id) {
	this.id = id;
    }

    @Deprecated
    public static CancelationReasonDict forId(long id) {
	for (CancelationReasonDict crti : values())
	    if (crti.getId() == id)
		return crti;
	return null;
    }

    // GENERATED

    @Deprecated
    public long getId() {
	return id;
    }

}
