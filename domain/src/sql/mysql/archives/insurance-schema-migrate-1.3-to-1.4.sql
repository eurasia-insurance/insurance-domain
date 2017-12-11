-- MIGRATION SCRIPT FROM 1.3 TO 1.4

-- Update PAYMENT

UPDATE INSURANCE_REQUEST
	SET PAYMENT_METHOD = 'UNDEFINED'
	WHERE PAYMENT_METHOD IS NULL;

UPDATE INSURANCE_REQUEST
	SET PAYMENT_STATUS = 'UNDEFINED' 
	WHERE PAYMENT_METHOD = 'UNDEFINED';

-- Update OBTAINING

UPDATE INSURANCE_REQUEST
	SET OBTAINING_METHOD = 'UNDEFINED'
	WHERE OBTAINING_METHOD IS NULL;

UPDATE INSURANCE_REQUEST
	SET OBTAINING_STATUS = 'UNDEFINED' 
	WHERE OBTAINING_METHOD = 'UNDEFINED';



/*
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), CLOSED DATETIME, CLOSING_RESULT VARCHAR(255), CREATED DATETIME,  * add *                      STATUS VARCHAR(255),  * add *                           * add *                         RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), PRIMARY KEY (ID));
 * 2
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), CLOSED DATETIME,  * remove *                  CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), PRIMARY KEY (ID));
 */

-- add column PROGRESS_STATUS
ALTER TABLE INSURANCE_REQUEST ADD COLUMN PROGRESS_STATUS VARCHAR(255) AFTER CREATED;
UPDATE INSURANCE_REQUEST SET PROGRESS_STATUS = 'NEW';
UPDATE INSURANCE_REQUEST SET PROGRESS_STATUS = 'FINISHED' WHERE STATUS = 'CLOSED';

-- add column TRANSACTION_PROBLEM
ALTER TABLE INSURANCE_REQUEST ADD COLUMN TRANSACTION_PROBLEM VARCHAR(255) AFTER STATUS;
UPDATE INSURANCE_REQUEST SET TRANSACTION_PROBLEM = NULL 
	WHERE PROGRESS_STATUS = 'FINISHED'
	AND CLOSING_RESULT = 'COMPLETED';
UPDATE INSURANCE_REQUEST SET TRANSACTION_PROBLEM = 'TEST'
	WHERE PROGRESS_STATUS = 'FINISHED'
	AND CLOSING_RESULT = 'TEST';
UPDATE INSURANCE_REQUEST SET TRANSACTION_PROBLEM = 'CANCELED_BY_CONSUMER'
	WHERE PROGRESS_STATUS = 'FINISHED'
	AND CLOSING_RESULT = 'CANCELED';

-- add column TRANSACTION_STATUS
ALTER TABLE INSURANCE_REQUEST ADD COLUMN TRANSACTION_STATUS VARCHAR(255) AFTER TRANSACTION_PROBLEM;
UPDATE INSURANCE_REQUEST SET TRANSACTION_STATUS = 'COMPLETED' 
	WHERE PROGRESS_STATUS = 'FINISHED'
	AND TRANSACTION_PROBLEM IS NULL;
UPDATE INSURANCE_REQUEST SET TRANSACTION_STATUS = 'NOT_COMPLETED'
	WHERE PROGRESS_STATUS = 'FINISHED'
	AND TRANSACTION_PROBLEM IS NOT NULL;

-- remove column CLOSING_RESULT
ALTER TABLE INSURANCE_REQUEST DROP COLUMN CLOSING_RESULT;

/*
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ACCEPTED DATETIME, CLOSED DATETIME, CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), PRIMARY KEY (ID));
 * 2
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31),                    CLOSED DATETIME, CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), PRIMARY KEY (ID));
 */

-- add column ACCEPTED
ALTER TABLE INSURANCE_REQUEST ADD COLUMN ACCEPTED DATETIME AFTER DTYPE;

UPDATE INSURANCE_REQUEST
	SET ACCEPTED = UPDATED
	WHERE PROGRESS_STATUS <> 'NEW';


/*
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ACCEPTED DATETIME, CLOSED DATETIME, * add *             CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), PRIMARY KEY (ID));
 * 2
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ACCEPTED DATETIME, CLOSED DATETIME, COMPLETED DATETIME, CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), PRIMARY KEY (ID));
 */

-- add column COMPLETED
ALTER TABLE INSURANCE_REQUEST ADD COLUMN COMPLETED DATETIME AFTER CLOSED;

UPDATE INSURANCE_REQUEST
	SET COMPLETED = CLOSED
	WHERE PROGRESS_STATUS = 'FINISHED';

/*
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ACCEPTED DATETIME, CLOSED DATETIME, COMPLETED DATETIME, CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), * add *                    * add *                   * add *                  * add *                  * add *                PRIMARY KEY (ID));
 * 2
 * CREATE TABLE INSURANCE_REQUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DTYPE VARCHAR(31), ACCEPTED DATETIME, CLOSED DATETIME, COMPLETED DATETIME, CREATED DATETIME, PROGRESS_STATUS VARCHAR(255), STATUS VARCHAR(255), TRANSACTION_PROBLEM VARCHAR(255), TRANSACTION_STATUS VARCHAR(255), RTYPE VARCHAR(255), UPDATED DATETIME, OBTAINING_DELIVERY_ADDRESS VARCHAR(255), OBTAINING_DELIVERY_CITY VARCHAR(255), OBTAINING_DELIVERY_DATE DATE, OBTAINING_DELIVERY_TIME VARCHAR(255), OBTAINING_METHOD VARCHAR(255), OBTAINING_PICKUP_CITY VARCHAR(255), OBTAINING_STATUS VARCHAR(255), OBTAINING_PICKUPPOS_ID INTEGER, PAYMENT_METHOD VARCHAR(255), PAYMENT_REFERENCE VARCHAR(255), PAYMENT_STATUS VARCHAR(255), REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, REQUESTER_ALLOW_SPAM TINYINT(1) default 0, REQUESTER_EMAIL VARCHAR(255), REQUESTER_NAME VARCHAR(255), REQUESTER_PREFER_LANGUAGE VARCHAR(255), REQUESTER_PHONE_AREA_CODE VARCHAR(255), REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), REQUESTER_PHONE_NUMBER VARCHAR(255), UTM_CAMPAIGN VARCHAR(255), UTM_CONTENT VARCHAR(255), UTM_MEDIUM VARCHAR(255), UTM_SOURCE VARCHAR(255), UTM_TERM VARCHAR(255), PRIMARY KEY (ID));
 */

-- add column UTM_CAMPAIGN
ALTER TABLE INSURANCE_REQUEST ADD UTM_CAMPAIGN VARCHAR(255) AFTER REQUESTER_PHONE_NUMBER;

-- add column UTM_CONTENT
ALTER TABLE INSURANCE_REQUEST ADD UTM_CONTENT VARCHAR(255) AFTER UTM_CAMPAIGN;

-- add column UTM_MEDIUM
ALTER TABLE INSURANCE_REQUEST ADD UTM_MEDIUM VARCHAR(255) AFTER UTM_CONTENT;

-- add column UTM_SOURCE
ALTER TABLE INSURANCE_REQUEST ADD UTM_SOURCE VARCHAR(255) AFTER UTM_MEDIUM;

-- add column UTM_TERM
ALTER TABLE INSURANCE_REQUEST ADD UTM_TERM VARCHAR(255) AFTER UTM_SOURCE;

/*
 * VERSION TABLE
 */
DROP TABLE VER_1_3;
CREATE TABLE VER_1_4 (DUMMY INTEGER NOT NULL, PRIMARY KEY (DUMMY));
