package kz.theeurasia.esbdproxy.domain.dict.osgpovts;

//TODO перенести в domain 
public enum InsuranceClassTypeDict {

    CLASS_0("0"),
    CLASS_1("1"),
    CLASS_2("2"),
    CLASS_3("3"),
    CLASS_4("4"),
    CLASS_5("5"),
    CLASS_6("6"),
    CLASS_7("7"),
    CLASS_8("8"),
    CLASS_9("9"),
    CLASS_10("10"),
    CLASS_11("11"),
    CLASS_12("12"),
    CLASS_13("13"),
    CLASS_M("М");

    private final String code;

    InsuranceClassTypeDict(String code) {
	this.code = code;
    }

    @Deprecated
    public static InsuranceClassTypeDict forCode(String code) {
	for (InsuranceClassTypeDict ict : values()) {
	    if (ict.getCode().equals(code))
		return ict;
	}
	return null;
    }

    // GENERATED

    @Deprecated
    public String getCode() {
	return code;
    }

}
