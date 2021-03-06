CREATE TABLE IDENTITY_CARD (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, ISSUING_AUTHORITY VARCHAR(255), NUMBER VARCHAR(255), TYPE VARCHAR(255), UPDATED DATETIME NOT NULL, PRIMARY KEY (ID))
CREATE TABLE VEHICLE_CERTIFICATE (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, VEHICLE_CERTITFICATE_NUMBER VARCHAR(255), VEHICLE_REGISTRATION_NUMBER VARCHAR(255), UPDATED DATETIME NOT NULL, PRIMARY KEY (ID))
CREATE TABLE USER (ID INTEGER AUTO_INCREMENT NOT NULL, EMAIL VARCHAR(255), HIDDEN TINYINT(1) default 0, NAME VARCHAR(255), UPDATED DATETIME NOT NULL, PRIMARY KEY (ID))
CREATE TABLE USER_LOGIN (ID INTEGER AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) UNIQUE, UPDATED DATETIME NOT NULL, USER_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE USER_GROUP (ID INTEGER AUTO_INCREMENT NOT NULL, NAME VARCHAR(255), UPDATED DATETIME NOT NULL, PRIMARY KEY (ID))
CREATE TABLE REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), CLOSED DATETIME, COMPLETED DATETIME, CREATED DATETIME, NOTE LONGTEXT, PICKED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), UPDATED DATETIME NOT NULL, REQUESTER_INET_ADDR VARCHAR(255), REQUESTER_INET_HOST VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_ID_NUMBER VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), UTM_CAMPAIGN VARCHAR(255), UTM_CONTENT VARCHAR(255), UTM_MEDIUM VARCHAR(255), UTM_SOURCE VARCHAR(255), UTM_TERM VARCHAR(255), CLOSED_BY_USER_ID INTEGER, COMPLETED_BY_USER_ID INTEGER, CREATED_BY_USER_ID INTEGER, PICKED_BY_USER_ID INTEGER, PRIMARY KEY (ID))
CREATE INDEX REQUEST_IDX01 ON REQUEST (STATUS)
CREATE INDEX REQUEST_IDX02 ON REQUEST (CREATED)
CREATE INDEX REQUEST_IDX03 ON REQUEST (CLOSED)
CREATE TABLE DRIVER_LICENSE (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, NUMBER VARCHAR(255), UPDATED DATETIME NOT NULL, PRIMARY KEY (ID))
CREATE TABLE INSURANCE_REQUEST (ID INTEGER NOT NULL, AGREEMENT_NUMBER VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), PAYMENT_AMOUNT DOUBLE, PAYMENT_CARD VARCHAR(255), PAYMENT_CARD_BANK VARCHAR(255), PAYMENT_CURRENCY VARCHAR(255), PAYMENT_INSTANT DATETIME, INVOICE_AMOUNT DOUBLE, INVOICE_CURRENCY VARCHAR(255), INVOICE_LANGUAGE INTEGER, INVOICE_NUMBER VARCHAR(255), INVOICE_PAYEE_EMAIL VARCHAR(255), INVOICE_PAYEE_NAME VARCHAR(255), INVOICE_PAYEE_PHONE VARCHAR(255), INVOICE_PAYEE_TAXPAYER_NUMBER VARCHAR(255), INVOICE_PRODUCT_NAME VARCHAR(255), INVOICE_QUANTITY INTEGER, PAYMENT_METHOD_NAME VARCHAR(255), PAYER_NAME VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE CALLBACK_REQUEST (ID INTEGER NOT NULL, COMMENTS VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE VEHICLE (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), REGISTRATION_AREA VARCHAR(255), REGISTRATION_CITY VARCHAR(255), COLOR VARCHAR(255), MANUFACTURER_NAME VARCHAR(255), MODEL_NAME VARCHAR(255), UPDATED DATETIME NOT NULL, VIN_CODE VARCHAR(255), VEHICLE_YOM INTEGER, VEHICLE_CERTIFICATE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE DRIVER (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ID_NUMBER VARCHAR(255), UPDATED DATETIME NOT NULL, DRIVER_LICENSE_ID INTEGER, IDENTITY_CARD_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY_REQUEST (ID INTEGER NOT NULL, POLICY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY_VEHICLE (ID INTEGER NOT NULL, FORCED_MAJOR_CITY TINYINT(1) default 0, TEMPORARYENTRY TINYINT(1) default 0, VEHICLE_AGE_CLASS VARCHAR(255), VEHICLE_CLASS VARCHAR(255), POLICY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY_DRIVER (ID INTEGER NOT NULL, AGE_CLASS VARCHAR(255), EXPIRIENCE_CLASS VARCHAR(255), IS_HAS_ANY_PRIVILEGE TINYINT(1) default 0, INSURANCE_CLASS VARCHAR(255), POLICY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, DATE_OF_TERMINATION DATE, POLICY_NUMBER VARCHAR(255), TERMINATION_REASON VARCHAR(255), UPDATED DATETIME NOT NULL, ACTUAL_AMOUNT DOUBLE, ACTUAL_CURRENCY VARCHAR(255), CALCULATED_AMOUNT DOUBLE, CALCULATED_CURRENCY VARCHAR(255), INSURANT_EMAIL VARCHAR(255), INSURANT_ID_NUMBER VARCHAR(255), INSURANT_PHONE_NUMBER VARCHAR(255), INSURANT_COMPANY_NAME VARCHAR(255), INSURANT_PERSON_DOB DATE, INSURANT_PERSON_GENDER VARCHAR(255), INSURANT_PERSON_NAME VARCHAR(255), INSURANT_PERSON_PATRONYMIC VARCHAR(255), INSURANT_PERSON_SURENAME VARCHAR(255), PERIOD_FROM DATE, PERIOD_TO DATE, PRIMARY KEY (ID))
CREATE TABLE CASCO (ID INTEGER AUTO_INCREMENT NOT NULL, CONTRACT_ENDS_AFTER_FIRST_CASE TINYINT(1) default 0, COVER_NON_ROAD_ACCIDENTS TINYINT(1) default 0, COVER_ROAD_ACCIDENTS TINYINT(1) default 0, DEDUCTIBLE_FULL_RATE VARCHAR(255), DEDUCTIBLE_PARTIAL_RATE VARCHAR(255), DEDUCTIBLE_PARTIAL_REQUIRED TINYINT(1) default 0, DRIVER_AND_PASSENGER_COUNT INTEGER, DRIVER_AND_PASSENGER_COVERAGE TINYINT(1) default 0, EVACUATOR_REQUIRED TINYINT(1) default 0, HELP_WITH_POLICY_REQUIRED TINYINT(1) default 0, NO_GUILT_NO_DEDUCTIBLE_REQUIRED TINYINT(1) default 0, NO_POLICE_CALL_REQUIRED TINYINT(1) default 0, REPLACEMENT_CAR_REQUIRED TINYINT(1) default 0, SPECIAL_SERVICES_STATION_REQUIRED TINYINT(1) default 0, THIRD_PARTY_LIABILITY_COVERAGE TINYINT(1) default 0, UPDATED DATETIME NOT NULL, ACTUAL_AMOUNT DOUBLE, ACTUAL_CURRENCY VARCHAR(255), CALCULATED_AMOUNT DOUBLE, CALCULATED_CURRENCY VARCHAR(255), PERIOD_FROM DATE, PERIOD_TO DATE, CASCO_VEHICLE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CASCO_DRIVER (ID INTEGER NOT NULL, CASCO_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CASCO_REQUEST (ID INTEGER NOT NULL, CASCO_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CASCO_VEHICLE (ID INTEGER NOT NULL, VEHICLE_CAR_AGE_CLASS VARCHAR(255), VEHICLE_COST DOUBLE, PRIMARY KEY (ID))
CREATE TABLE INSURANCE_VER_10 (DUMMY INTEGER NOT NULL, PRIMARY KEY (DUMMY))
CREATE TABLE USER_2_GROUP (USER_ID INTEGER NOT NULL, USER_GROUP_ID INTEGER NOT NULL, PRIMARY KEY (USER_ID, USER_GROUP_ID))
ALTER TABLE USER_LOGIN ADD CONSTRAINT FK_USER_LOGIN_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_CREATED_BY_USER_ID FOREIGN KEY (CREATED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_PICKED_BY_USER_ID FOREIGN KEY (PICKED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_CLOSED_BY_USER_ID FOREIGN KEY (CLOSED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_COMPLETED_BY_USER_ID FOREIGN KEY (COMPLETED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE INSURANCE_REQUEST ADD CONSTRAINT FK_INSURANCE_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE CALLBACK_REQUEST ADD CONSTRAINT FK_CALLBACK_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE VEHICLE ADD CONSTRAINT FK_VEHICLE_VEHICLE_CERTIFICATE_ID FOREIGN KEY (VEHICLE_CERTIFICATE_ID) REFERENCES VEHICLE_CERTIFICATE (ID)
ALTER TABLE DRIVER ADD CONSTRAINT FK_DRIVER_DRIVER_LICENSE_ID FOREIGN KEY (DRIVER_LICENSE_ID) REFERENCES DRIVER_LICENSE (ID)
ALTER TABLE DRIVER ADD CONSTRAINT FK_DRIVER_IDENTITY_CARD_ID FOREIGN KEY (IDENTITY_CARD_ID) REFERENCES IDENTITY_CARD (ID)
ALTER TABLE POLICY_REQUEST ADD CONSTRAINT FK_POLICY_REQUEST_POLICY_ID FOREIGN KEY (POLICY_ID) REFERENCES POLICY (ID)
ALTER TABLE POLICY_REQUEST ADD CONSTRAINT FK_POLICY_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE POLICY_VEHICLE ADD CONSTRAINT FK_POLICY_VEHICLE_POLICY_ID FOREIGN KEY (POLICY_ID) REFERENCES POLICY (ID)
ALTER TABLE POLICY_VEHICLE ADD CONSTRAINT FK_POLICY_VEHICLE_ID FOREIGN KEY (ID) REFERENCES VEHICLE (ID)
ALTER TABLE POLICY_DRIVER ADD CONSTRAINT FK_POLICY_DRIVER_POLICY_ID FOREIGN KEY (POLICY_ID) REFERENCES POLICY (ID)
ALTER TABLE POLICY_DRIVER ADD CONSTRAINT FK_POLICY_DRIVER_ID FOREIGN KEY (ID) REFERENCES DRIVER (ID)
ALTER TABLE CASCO ADD CONSTRAINT FK_CASCO_CASCO_VEHICLE_ID FOREIGN KEY (CASCO_VEHICLE_ID) REFERENCES VEHICLE (ID)
ALTER TABLE CASCO_DRIVER ADD CONSTRAINT FK_CASCO_DRIVER_ID FOREIGN KEY (ID) REFERENCES DRIVER (ID)
ALTER TABLE CASCO_DRIVER ADD CONSTRAINT FK_CASCO_DRIVER_CASCO_ID FOREIGN KEY (CASCO_ID) REFERENCES CASCO (ID)
ALTER TABLE CASCO_REQUEST ADD CONSTRAINT FK_CASCO_REQUEST_CASCO_ID FOREIGN KEY (CASCO_ID) REFERENCES CASCO (ID)
ALTER TABLE CASCO_REQUEST ADD CONSTRAINT FK_CASCO_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE CASCO_VEHICLE ADD CONSTRAINT FK_CASCO_VEHICLE_ID FOREIGN KEY (ID) REFERENCES VEHICLE (ID)
ALTER TABLE USER_2_GROUP ADD CONSTRAINT FK_USER_2_GROUP_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (ID)
ALTER TABLE USER_2_GROUP ADD CONSTRAINT FK_USER_2_GROUP_USER_GROUP_ID FOREIGN KEY (USER_GROUP_ID) REFERENCES USER_GROUP (ID)
