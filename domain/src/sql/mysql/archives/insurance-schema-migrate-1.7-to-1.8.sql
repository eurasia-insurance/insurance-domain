-- MIGRATION SCRIPT FROM 1.7 TO 1.8

/*
 * CHANGES ON
 * eurasia36/eurasia36-crm#71
 */

/*
 * add column NOTE
 */
ALTER TABLE INSURANCE_REQUEST ADD COLUMN NOTE VARCHAR(255) AFTER CREATED;


-- create table REQUEST

CREATE TABLE REQUEST (
  ID INTEGER AUTO_INCREMENT NOT NULL, 
  DTYPE VARCHAR(31), 
  ACCEPTED DATETIME, 
  CLOSED DATETIME,
  COMPLETED DATETIME,
  CREATED DATETIME,
  NOTE VARCHAR(255),
  PROGRESS_STATUS VARCHAR(255),
  STATUS VARCHAR(255),
  UPDATED DATETIME,
  REQUESTER_ALLOW_PROCESS_PERSONAL_DATA TINYINT(1) default 0, 
  REQUESTER_ALLOW_SPAM TINYINT(1) default 0, 
  REQUESTER_EMAIL VARCHAR(255), 
  REQUESTER_ID_NUMBER VARCHAR(255), 
  REQUESTER_NAME VARCHAR(255), 
  REQUESTER_PREFER_LANGUAGE VARCHAR(255), 
  REQUESTER_PHONE_AREA_CODE VARCHAR(255), 
  REQUESTER_PHONE_COUNTRY_CODE VARCHAR(255), 
  REQUESTER_PHONE_NUMBER VARCHAR(255), 
  UTM_CAMPAIGN VARCHAR(255), 
  UTM_CONTENT VARCHAR(255), 
  UTM_MEDIUM VARCHAR(255), 
  UTM_SOURCE VARCHAR(255), 
  UTM_TERM VARCHAR(255), 
  ACCEPTED_BY_USER_ID INTEGER, 
  CLOSED_BY_USER_ID INTEGER, 
  COMPLETED_BY_USER_ID INTEGER, 
  PRIMARY KEY (ID));

ALTER TABLE REQUEST 
   ADD CONSTRAINT FK_REQUEST_CLOSED_BY_USER_ID FOREIGN KEY (CLOSED_BY_USER_ID) REFERENCES USER (ID),
   ADD CONSTRAINT FK_REQUEST_ACCEPTED_BY_USER_ID FOREIGN KEY (ACCEPTED_BY_USER_ID) REFERENCES USER (ID),
   ADD CONSTRAINT FK_REQUEST_COMPLETED_BY_USER_ID FOREIGN KEY (COMPLETED_BY_USER_ID) REFERENCES USER (ID);

CREATE INDEX REQUEST_IDX01 ON REQUEST (STATUS);
CREATE INDEX REQUEST_IDX02 ON REQUEST (CREATED);
CREATE INDEX REQUEST_IDX03 ON REQUEST (CLOSED);

-- migrating data

INSERT INTO REQUEST
    (
	   ID,
	   DTYPE,
	   ACCEPTED,
	   CLOSED,
	   COMPLETED,
	   CREATED,
	   NOTE,
	   PROGRESS_STATUS,
	   STATUS,
	   UPDATED,
	   REQUESTER_ALLOW_PROCESS_PERSONAL_DATA,
	   REQUESTER_ALLOW_SPAM,
	   REQUESTER_EMAIL,
	   REQUESTER_ID_NUMBER,
	   REQUESTER_NAME,
	   REQUESTER_PREFER_LANGUAGE,
	   REQUESTER_PHONE_AREA_CODE,
	   REQUESTER_PHONE_COUNTRY_CODE,
	   REQUESTER_PHONE_NUMBER,
	   UTM_CAMPAIGN,
	   UTM_CONTENT,
	   UTM_MEDIUM,
	   UTM_SOURCE,
	   UTM_TERM,
	   ACCEPTED_BY_USER_ID,
	   CLOSED_BY_USER_ID,
	   COMPLETED_BY_USER_ID
	)
	SELECT
	   ID,
	   DTYPE,
	   ACCEPTED,
	   CLOSED,
	   COMPLETED,
	   CREATED,
	   NOTE,
	   PROGRESS_STATUS,
	   STATUS,
	   UPDATED,
	   REQUESTER_ALLOW_PROCESS_PERSONAL_DATA,
	   REQUESTER_ALLOW_SPAM,
	   REQUESTER_EMAIL,
	   REQUESTER_ID_NUMBER,
	   REQUESTER_NAME,
	   REQUESTER_PREFER_LANGUAGE,
	   REQUESTER_PHONE_AREA_CODE,
	   REQUESTER_PHONE_COUNTRY_CODE,
	   REQUESTER_PHONE_NUMBER,
	   UTM_CAMPAIGN,
	   UTM_CONTENT,
	   UTM_MEDIUM,
	   UTM_SOURCE,
	   UTM_TERM,
	   ACCEPTED_BY_USER_ID,
	   CLOSED_BY_USER_ID,
	   COMPLETED_BY_USER_ID
	FROM INSURANCE_REQUEST; 

-- cleanup

ALTER TABLE INSURANCE_REQUEST 
   DROP FOREIGN KEY FK_INSURANCE_REQUEST_ACCEPTED_BY_USER_ID,
   DROP KEY FK_INSURANCE_REQUEST_ACCEPTED_BY_USER_ID,
   DROP FOREIGN KEY FK_INSURANCE_REQUEST_CLOSED_BY_USER_ID,
   DROP KEY FK_INSURANCE_REQUEST_CLOSED_BY_USER_ID,
   DROP FOREIGN KEY FK_INSURANCE_REQUEST_COMPLETED_BY_USER_ID,
   DROP KEY FK_INSURANCE_REQUEST_COMPLETED_BY_USER_ID,
   DROP COLUMN DTYPE,
   DROP COLUMN ACCEPTED,
   DROP COLUMN CLOSED,
   DROP COLUMN COMPLETED,
   DROP COLUMN CREATED,
   DROP COLUMN NOTE,
   DROP COLUMN PROGRESS_STATUS,
   DROP COLUMN STATUS,
   DROP COLUMN UPDATED,
   DROP COLUMN REQUESTER_ALLOW_PROCESS_PERSONAL_DATA,
   DROP COLUMN REQUESTER_ALLOW_SPAM,
   DROP COLUMN REQUESTER_EMAIL,
   DROP COLUMN REQUESTER_ID_NUMBER,
   DROP COLUMN REQUESTER_NAME,
   DROP COLUMN REQUESTER_PREFER_LANGUAGE,
   DROP COLUMN REQUESTER_PHONE_AREA_CODE,
   DROP COLUMN REQUESTER_PHONE_COUNTRY_CODE,
   DROP COLUMN REQUESTER_PHONE_NUMBER,
   DROP COLUMN UTM_CAMPAIGN,
   DROP COLUMN UTM_CONTENT,
   DROP COLUMN UTM_MEDIUM,
   DROP COLUMN UTM_SOURCE,
   DROP COLUMN UTM_TERM,
   DROP COLUMN ACCEPTED_BY_USER_ID,
   DROP COLUMN CLOSED_BY_USER_ID,
   DROP COLUMN COMPLETED_BY_USER_ID;

-- notifications

DROP TABLE NOTIFICATION;

/*
 * VERSION TABLE
 */
DROP TABLE VER_1_7;
CREATE TABLE VER_1_8 (DUMMY INTEGER NOT NULL, PRIMARY KEY (DUMMY));
