-- MIGRATION SCRIPT FROM 2.4.1 TO 2.4.2

ALTER TABLE INSURANCE_REQUEST
	ADD COLUMN  PAYMENT_CARD VARCHAR(255) AFTER PAYMENT_AMOUNT;

/*
 * VERSION TABLE
 */
DROP TABLE INSURANCE_VER_2_4_1;
CREATE TABLE INSURANCE_VER_2_4_2 (DUMMY INTEGER NOT NULL, PRIMARY KEY (DUMMY));
