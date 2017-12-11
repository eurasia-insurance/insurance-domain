CREATE TABLE IMAGE (ID BIGINT AUTO_INCREMENT NOT NULL, CONTENT LONGBLOB, FILE_NAME VARCHAR(255), MIME_TYPE VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE POS_CONTACT_PHONE (ID INTEGER AUTO_INCREMENT NOT NULL, PHONE_TYPE VARCHAR(255), PHONE_AREA_CODE VARCHAR(255), PHONE_COUNTRY_CODE VARCHAR(255), PHONE_NUMBER VARCHAR(255), POS_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE DRIVER (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ID_NUMBER VARCHAR(255), DRIVER_LICENSE_ID INTEGER, IDENTITY_CARD_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY_DRIVER (ID INTEGER NOT NULL, AGE_CLASS VARCHAR(255), EXPIRIENCE_CLASS VARCHAR(255), IS_HAS_ANY_PRIVILEGE TINYINT(1) default 0, INSURANCE_CLASS VARCHAR(255), POLICY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE DRIVER_LICENSE (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, NUMBER VARCHAR(255), BACKSIDE_IMAGE_ID BIGINT, FRONTSIDE_IMAGE_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ACCEPTED DATETIME, CLOSED DATETIME, COMPLETED DATETIME, CREATED DATETIME, NOTE VARCHAR(255), PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), UPDATED DATETIME, REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_ID_NUMBER VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), UTM_CAMPAIGN VARCHAR(255), UTM_CONTENT VARCHAR(255), UTM_MEDIUM VARCHAR(255), UTM_SOURCE VARCHAR(255), UTM_TERM VARCHAR(255), ACCEPTED_BY_USER_ID INTEGER, CLOSED_BY_USER_ID INTEGER, COMPLETED_BY_USER_ID INTEGER, CREATED_BY_USER_ID INTEGER, PRIMARY KEY (ID))
CREATE INDEX REQUEST_IDX01 ON REQUEST (STATUS)
CREATE INDEX REQUEST_IDX02 ON REQUEST (CREATED)
CREATE INDEX REQUEST_IDX03 ON REQUEST (CLOSED)
CREATE TABLE INSURANCE_REQUEST (ID INTEGER NOT NULL, TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE POLICY_REQUEST (ID INTEGER NOT NULL, POLICY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE VEHICLE (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), REGISTRATION_AREA VARCHAR(255), REGISTRATION_CITY VARCHAR(255), COLOR VARCHAR(255), MANUFACTURER_NAME VARCHAR(255), MODEL_NAME VARCHAR(255), VIN_CODE VARCHAR(255), VEHICLE_YOM INTEGER, VEHICLE_CERTIFICATE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CASCO_VEHICLE (ID INTEGER NOT NULL, VEHICLE_CAR_AGE_CLASS VARCHAR(255), VEHICLE_COST DOUBLE, VIEW1_IMAGE_ID BIGINT, VIEW2_IMAGE_ID BIGINT, VIEW3_IMAGE_ID BIGINT, VIEW4_IMAGE_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE USER (ID INTEGER AUTO_INCREMENT NOT NULL, EMAIL VARCHAR(255), NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE IDENTITY_CARD (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, ISSUING_AUTHORITY VARCHAR(255), NUMBER VARCHAR(255), TYPE VARCHAR(255), BACKSIDE_IMAGE_ID BIGINT, FRONTSIDE_IMAGE_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE CASCO_DRIVER (ID INTEGER NOT NULL, CASCO_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CASCO (ID INTEGER AUTO_INCREMENT NOT NULL, CONTRACT_ENDS_AFTER_FIRST_CASE TINYINT(1) default 0, COVER_NON_ROAD_ACCIDENTS TINYINT(1) default 0, COVER_ROAD_ACCIDENTS TINYINT(1) default 0, DEDUCTIBLE_FULL_RATE VARCHAR(255), DEDUCTIBLE_PARTIAL_RATE VARCHAR(255), DEDUCTIBLE_PARTIAL_REQUIRED TINYINT(1) default 0, DRIVER_AND_PASSENGER_COUNT INTEGER, DRIVER_AND_PASSENGER_COVERAGE TINYINT(1) default 0, EVACUATOR_REQUIRED TINYINT(1) default 0, HELP_WITH_POLICY_REQUIRED TINYINT(1) default 0, NO_GUILT_NO_DEDUCTIBLE_REQUIRED TINYINT(1) default 0, NO_POLICE_CALL_REQUIRED TINYINT(1) default 0, REPLACEMENT_CAR_REQUIRED TINYINT(1) default 0, SPECIAL_SERVICES_STATION_REQUIRED TINYINT(1) default 0, THIRD_PARTY_LIABILITY_COVERAGE TINYINT(1) default 0, ACTUAL_PERMIUM_COST DOUBLE, CALCULATED_PERMIUM_COST DOUBLE, DISCOUNT_AMOUNT DOUBLE, PREMIUM_CURRENCY VARCHAR(255), CASCO_VEHICLE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY (ID INTEGER AUTO_INCREMENT NOT NULL, ACTUAL_PERMIUM_COST DOUBLE, CALCULATED_PERMIUM_COST DOUBLE, DISCOUNT_AMOUNT DOUBLE, PREMIUM_CURRENCY VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE CALLBACK_REQUEST (ID INTEGER NOT NULL, COMMENTS VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE VEHICLE_CERTIFICATE (ID INTEGER AUTO_INCREMENT NOT NULL, DATE_OF_ISSUE DATE, VEHICLE_CERTITFICATE_NUMBER VARCHAR(255), VEHICLE_REGISTRATION_NUMBER VARCHAR(255), BACKSIDE_IMAGE_ID BIGINT, FRONTSIDE_IMAGE_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE CASCO_REQUEST (ID INTEGER NOT NULL, CASCO_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE VER_1_9 (DUMMY INTEGER NOT NULL, PRIMARY KEY (DUMMY))
CREATE TABLE POS_CONTACT_EMAIL (ID INTEGER AUTO_INCREMENT NOT NULL, EMAIL_ADDRESS VARCHAR(255), POS_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POLICY_VEHICLE (ID INTEGER NOT NULL, FORCED_MAJOR_CITY TINYINT(1) default 0, TEMPORARYENTRY TINYINT(1) default 0, VEHICLE_AGE_CLASS VARCHAR(255), VEHICLE_CLASS VARCHAR(255), POLICY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE USER_LOGIN (ID INTEGER AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) UNIQUE, USER_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE POS (ID INTEGER AUTO_INCREMENT NOT NULL, IS_AVAILABLE TINYINT(1) default 0, IS_OWN_OFFICE TINYINT(1) default 0, IS_DELIVERY_SERVICE_AVAILABLE TINYINT(1) default 0, NAME VARCHAR(255), IS_PICKUP_AVAILABLE TINYINT(1) default 0, ADDRESS_CITY VARCHAR(255), ADDRESS_POSTINDEX VARCHAR(255), ADDRESS_STREET VARCHAR(255), GEO_LATITUDE DOUBLE, GEO_LONGITUDE DOUBLE, PRIMARY KEY (ID))
CREATE INDEX POS_IDX01 ON POS (IS_AVAILABLE)
CREATE INDEX POS_IDX02 ON POS (IS_AVAILABLE, IS_PICKUP_AVAILABLE)
CREATE INDEX POS_IDX03 ON POS (IS_AVAILABLE, IS_DELIVERY_SERVICE_AVAILABLE)
CREATE INDEX POS_IDX04 ON POS (IS_AVAILABLE, IS_PICKUP_AVAILABLE, IS_DELIVERY_SERVICE_AVAILABLE)
CREATE TABLE ADDRESS_STREET_LOCALIZATION (ENTITY_ID INTEGER, VALUE VARCHAR(255), LANGUAGE VARCHAR(255))
CREATE TABLE POS_NAME_LOCALIZATION (ENTITY_ID INTEGER, VALUE VARCHAR(255), LANGUAGE VARCHAR(255))
ALTER TABLE ADDRESS_STREET_LOCALIZATION ADD CONSTRAINT UNQ_ADDRESS_STREET_LOCALIZATION_0 UNIQUE (ENTITY_ID, LANGUAGE)
ALTER TABLE POS_NAME_LOCALIZATION ADD CONSTRAINT UNQ_POS_NAME_LOCALIZATION_0 UNIQUE (ENTITY_ID, LANGUAGE)
ALTER TABLE POS_CONTACT_PHONE ADD CONSTRAINT FK_POS_CONTACT_PHONE_POS_ID FOREIGN KEY (POS_ID) REFERENCES POS (ID)
ALTER TABLE DRIVER ADD CONSTRAINT FK_DRIVER_DRIVER_LICENSE_ID FOREIGN KEY (DRIVER_LICENSE_ID) REFERENCES DRIVER_LICENSE (ID)
ALTER TABLE DRIVER ADD CONSTRAINT FK_DRIVER_IDENTITY_CARD_ID FOREIGN KEY (IDENTITY_CARD_ID) REFERENCES IDENTITY_CARD (ID)
ALTER TABLE POLICY_DRIVER ADD CONSTRAINT FK_POLICY_DRIVER_POLICY_ID FOREIGN KEY (POLICY_ID) REFERENCES POLICY (ID)
ALTER TABLE POLICY_DRIVER ADD CONSTRAINT FK_POLICY_DRIVER_ID FOREIGN KEY (ID) REFERENCES DRIVER (ID)
ALTER TABLE DRIVER_LICENSE ADD CONSTRAINT FK_DRIVER_LICENSE_BACKSIDE_IMAGE_ID FOREIGN KEY (BACKSIDE_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE DRIVER_LICENSE ADD CONSTRAINT FK_DRIVER_LICENSE_FRONTSIDE_IMAGE_ID FOREIGN KEY (FRONTSIDE_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_CREATED_BY_USER_ID FOREIGN KEY (CREATED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_CLOSED_BY_USER_ID FOREIGN KEY (CLOSED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_ACCEPTED_BY_USER_ID FOREIGN KEY (ACCEPTED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE REQUEST ADD CONSTRAINT FK_REQUEST_COMPLETED_BY_USER_ID FOREIGN KEY (COMPLETED_BY_USER_ID) REFERENCES USER (ID)
ALTER TABLE INSURANCE_REQUEST ADD CONSTRAINT FK_INSURANCE_REQUEST_OBTAINING_PICKUPPOS_ID FOREIGN KEY (OBTAINING_PICKUPPOS_ID) REFERENCES POS (ID)
ALTER TABLE INSURANCE_REQUEST ADD CONSTRAINT FK_INSURANCE_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE POLICY_REQUEST ADD CONSTRAINT FK_POLICY_REQUEST_POLICY_ID FOREIGN KEY (POLICY_ID) REFERENCES POLICY (ID)
ALTER TABLE POLICY_REQUEST ADD CONSTRAINT FK_POLICY_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE VEHICLE ADD CONSTRAINT FK_VEHICLE_VEHICLE_CERTIFICATE_ID FOREIGN KEY (VEHICLE_CERTIFICATE_ID) REFERENCES VEHICLE_CERTIFICATE (ID)
ALTER TABLE CASCO_VEHICLE ADD CONSTRAINT FK_CASCO_VEHICLE_VIEW1_IMAGE_ID FOREIGN KEY (VIEW1_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE CASCO_VEHICLE ADD CONSTRAINT FK_CASCO_VEHICLE_VIEW3_IMAGE_ID FOREIGN KEY (VIEW3_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE CASCO_VEHICLE ADD CONSTRAINT FK_CASCO_VEHICLE_ID FOREIGN KEY (ID) REFERENCES VEHICLE (ID)
ALTER TABLE CASCO_VEHICLE ADD CONSTRAINT FK_CASCO_VEHICLE_VIEW2_IMAGE_ID FOREIGN KEY (VIEW2_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE CASCO_VEHICLE ADD CONSTRAINT FK_CASCO_VEHICLE_VIEW4_IMAGE_ID FOREIGN KEY (VIEW4_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE IDENTITY_CARD ADD CONSTRAINT FK_IDENTITY_CARD_BACKSIDE_IMAGE_ID FOREIGN KEY (BACKSIDE_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE IDENTITY_CARD ADD CONSTRAINT FK_IDENTITY_CARD_FRONTSIDE_IMAGE_ID FOREIGN KEY (FRONTSIDE_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE CASCO_DRIVER ADD CONSTRAINT FK_CASCO_DRIVER_ID FOREIGN KEY (ID) REFERENCES DRIVER (ID)
ALTER TABLE CASCO_DRIVER ADD CONSTRAINT FK_CASCO_DRIVER_CASCO_ID FOREIGN KEY (CASCO_ID) REFERENCES CASCO (ID)
ALTER TABLE CASCO ADD CONSTRAINT FK_CASCO_CASCO_VEHICLE_ID FOREIGN KEY (CASCO_VEHICLE_ID) REFERENCES VEHICLE (ID)
ALTER TABLE CALLBACK_REQUEST ADD CONSTRAINT FK_CALLBACK_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE VEHICLE_CERTIFICATE ADD CONSTRAINT FK_VEHICLE_CERTIFICATE_BACKSIDE_IMAGE_ID FOREIGN KEY (BACKSIDE_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE VEHICLE_CERTIFICATE ADD CONSTRAINT FK_VEHICLE_CERTIFICATE_FRONTSIDE_IMAGE_ID FOREIGN KEY (FRONTSIDE_IMAGE_ID) REFERENCES IMAGE (ID)
ALTER TABLE CASCO_REQUEST ADD CONSTRAINT FK_CASCO_REQUEST_CASCO_ID FOREIGN KEY (CASCO_ID) REFERENCES CASCO (ID)
ALTER TABLE CASCO_REQUEST ADD CONSTRAINT FK_CASCO_REQUEST_ID FOREIGN KEY (ID) REFERENCES REQUEST (ID)
ALTER TABLE POS_CONTACT_EMAIL ADD CONSTRAINT FK_POS_CONTACT_EMAIL_POS_ID FOREIGN KEY (POS_ID) REFERENCES POS (ID)
ALTER TABLE POLICY_VEHICLE ADD CONSTRAINT FK_POLICY_VEHICLE_POLICY_ID FOREIGN KEY (POLICY_ID) REFERENCES POLICY (ID)
ALTER TABLE POLICY_VEHICLE ADD CONSTRAINT FK_POLICY_VEHICLE_ID FOREIGN KEY (ID) REFERENCES VEHICLE (ID)
ALTER TABLE USER_LOGIN ADD CONSTRAINT FK_USER_LOGIN_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (ID)
ALTER TABLE ADDRESS_STREET_LOCALIZATION ADD CONSTRAINT FK_ADDRESS_STREET_LOCALIZATION_ENTITY_ID FOREIGN KEY (ENTITY_ID) REFERENCES POS (ID)
ALTER TABLE POS_NAME_LOCALIZATION ADD CONSTRAINT FK_POS_NAME_LOCALIZATION_ENTITY_ID FOREIGN KEY (ENTITY_ID) REFERENCES POS (ID)
