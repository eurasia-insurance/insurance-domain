ALTER TABLE POS_CONTACT_PHONE DROP FOREIGN KEY FK_POS_CONTACT_PHONE_POS_ID
ALTER TABLE DRIVER DROP FOREIGN KEY FK_DRIVER_DRIVER_LICENSE_ID
ALTER TABLE DRIVER DROP FOREIGN KEY FK_DRIVER_IDENTITY_CARD_ID
ALTER TABLE POLICY_DRIVER DROP FOREIGN KEY FK_POLICY_DRIVER_POLICY_ID
ALTER TABLE POLICY_DRIVER DROP FOREIGN KEY FK_POLICY_DRIVER_ID
ALTER TABLE DRIVER_LICENSE DROP FOREIGN KEY FK_DRIVER_LICENSE_BACKSIDE_IMAGE_ID
ALTER TABLE DRIVER_LICENSE DROP FOREIGN KEY FK_DRIVER_LICENSE_FRONTSIDE_IMAGE_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_CREATED_BY_USER_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_CLOSED_BY_USER_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_ACCEPTED_BY_USER_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_COMPLETED_BY_USER_ID
ALTER TABLE INSURANCE_REQUEST DROP FOREIGN KEY FK_INSURANCE_REQUEST_OBTAINING_PICKUPPOS_ID
ALTER TABLE INSURANCE_REQUEST DROP FOREIGN KEY FK_INSURANCE_REQUEST_ID
ALTER TABLE POLICY_REQUEST DROP FOREIGN KEY FK_POLICY_REQUEST_POLICY_ID
ALTER TABLE POLICY_REQUEST DROP FOREIGN KEY FK_POLICY_REQUEST_ID
ALTER TABLE VEHICLE DROP FOREIGN KEY FK_VEHICLE_VEHICLE_CERTIFICATE_ID
ALTER TABLE CASCO_VEHICLE DROP FOREIGN KEY FK_CASCO_VEHICLE_VIEW1_IMAGE_ID
ALTER TABLE CASCO_VEHICLE DROP FOREIGN KEY FK_CASCO_VEHICLE_VIEW3_IMAGE_ID
ALTER TABLE CASCO_VEHICLE DROP FOREIGN KEY FK_CASCO_VEHICLE_ID
ALTER TABLE CASCO_VEHICLE DROP FOREIGN KEY FK_CASCO_VEHICLE_VIEW2_IMAGE_ID
ALTER TABLE CASCO_VEHICLE DROP FOREIGN KEY FK_CASCO_VEHICLE_VIEW4_IMAGE_ID
ALTER TABLE IDENTITY_CARD DROP FOREIGN KEY FK_IDENTITY_CARD_BACKSIDE_IMAGE_ID
ALTER TABLE IDENTITY_CARD DROP FOREIGN KEY FK_IDENTITY_CARD_FRONTSIDE_IMAGE_ID
ALTER TABLE CASCO_DRIVER DROP FOREIGN KEY FK_CASCO_DRIVER_ID
ALTER TABLE CASCO_DRIVER DROP FOREIGN KEY FK_CASCO_DRIVER_CASCO_ID
ALTER TABLE CASCO DROP FOREIGN KEY FK_CASCO_CASCO_VEHICLE_ID
ALTER TABLE CALLBACK_REQUEST DROP FOREIGN KEY FK_CALLBACK_REQUEST_ID
ALTER TABLE VEHICLE_CERTIFICATE DROP FOREIGN KEY FK_VEHICLE_CERTIFICATE_BACKSIDE_IMAGE_ID
ALTER TABLE VEHICLE_CERTIFICATE DROP FOREIGN KEY FK_VEHICLE_CERTIFICATE_FRONTSIDE_IMAGE_ID
ALTER TABLE CASCO_REQUEST DROP FOREIGN KEY FK_CASCO_REQUEST_CASCO_ID
ALTER TABLE CASCO_REQUEST DROP FOREIGN KEY FK_CASCO_REQUEST_ID
ALTER TABLE POS_CONTACT_EMAIL DROP FOREIGN KEY FK_POS_CONTACT_EMAIL_POS_ID
ALTER TABLE POLICY_VEHICLE DROP FOREIGN KEY FK_POLICY_VEHICLE_POLICY_ID
ALTER TABLE POLICY_VEHICLE DROP FOREIGN KEY FK_POLICY_VEHICLE_ID
ALTER TABLE USER_LOGIN DROP FOREIGN KEY FK_USER_LOGIN_USER_ID
ALTER TABLE ADDRESS_STREET_LOCALIZATION DROP FOREIGN KEY FK_ADDRESS_STREET_LOCALIZATION_ENTITY_ID
ALTER TABLE ADDRESS_STREET_LOCALIZATION DROP KEY UNQ_ADDRESS_STREET_LOCALIZATION_0
ALTER TABLE POS_NAME_LOCALIZATION DROP FOREIGN KEY FK_POS_NAME_LOCALIZATION_ENTITY_ID
ALTER TABLE POS_NAME_LOCALIZATION DROP KEY UNQ_POS_NAME_LOCALIZATION_0
DROP TABLE IMAGE
DROP TABLE POS_CONTACT_PHONE
DROP TABLE DRIVER
DROP TABLE POLICY_DRIVER
DROP TABLE DRIVER_LICENSE
DROP INDEX REQUEST_IDX01 ON REQUEST
DROP INDEX REQUEST_IDX02 ON REQUEST
DROP INDEX REQUEST_IDX03 ON REQUEST
DROP TABLE REQUEST
DROP TABLE INSURANCE_REQUEST
DROP TABLE POLICY_REQUEST
DROP TABLE VEHICLE
DROP TABLE CASCO_VEHICLE
DROP TABLE USER
DROP TABLE IDENTITY_CARD
DROP TABLE CASCO_DRIVER
DROP TABLE CASCO
DROP TABLE POLICY
DROP TABLE CALLBACK_REQUEST
DROP TABLE VEHICLE_CERTIFICATE
DROP TABLE CASCO_REQUEST
DROP TABLE VER_1_11
DROP TABLE POS_CONTACT_EMAIL
DROP TABLE POLICY_VEHICLE
DROP TABLE USER_LOGIN
DROP INDEX POS_IDX01 ON POS
DROP INDEX POS_IDX02 ON POS
DROP INDEX POS_IDX03 ON POS
DROP INDEX POS_IDX04 ON POS
DROP TABLE POS
DROP TABLE ADDRESS_STREET_LOCALIZATION
DROP TABLE POS_NAME_LOCALIZATION
