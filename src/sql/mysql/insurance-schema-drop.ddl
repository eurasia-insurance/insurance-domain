ALTER TABLE USER_LOGIN DROP FOREIGN KEY FK_USER_LOGIN_USER_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_CREATED_BY_USER_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_PICKED_BY_USER_ID
ALTER TABLE REQUEST DROP FOREIGN KEY FK_REQUEST_COMPLETED_BY_USER_ID
ALTER TABLE INSURANCE_REQUEST DROP FOREIGN KEY FK_INSURANCE_REQUEST_ID
ALTER TABLE CALLBACK_REQUEST DROP FOREIGN KEY FK_CALLBACK_REQUEST_ID
ALTER TABLE VEHICLE DROP FOREIGN KEY FK_VEHICLE_VEHICLE_CERTIFICATE_ID
ALTER TABLE DRIVER DROP FOREIGN KEY FK_DRIVER_DRIVER_LICENSE_ID
ALTER TABLE DRIVER DROP FOREIGN KEY FK_DRIVER_IDENTITY_CARD_ID
ALTER TABLE POLICY_REQUEST DROP FOREIGN KEY FK_POLICY_REQUEST_POLICY_ID
ALTER TABLE POLICY_REQUEST DROP FOREIGN KEY FK_POLICY_REQUEST_ID
ALTER TABLE POLICY_VEHICLE DROP FOREIGN KEY FK_POLICY_VEHICLE_POLICY_ID
ALTER TABLE POLICY_VEHICLE DROP FOREIGN KEY FK_POLICY_VEHICLE_ID
ALTER TABLE POLICY_DRIVER DROP FOREIGN KEY FK_POLICY_DRIVER_POLICY_ID
ALTER TABLE POLICY_DRIVER DROP FOREIGN KEY FK_POLICY_DRIVER_ID
ALTER TABLE CASCO DROP FOREIGN KEY FK_CASCO_CASCO_VEHICLE_ID
ALTER TABLE CASCO_DRIVER DROP FOREIGN KEY FK_CASCO_DRIVER_ID
ALTER TABLE CASCO_DRIVER DROP FOREIGN KEY FK_CASCO_DRIVER_CASCO_ID
ALTER TABLE CASCO_REQUEST DROP FOREIGN KEY FK_CASCO_REQUEST_CASCO_ID
ALTER TABLE CASCO_REQUEST DROP FOREIGN KEY FK_CASCO_REQUEST_ID
ALTER TABLE CASCO_VEHICLE DROP FOREIGN KEY FK_CASCO_VEHICLE_ID
ALTER TABLE USER_2_GROUP DROP FOREIGN KEY FK_USER_2_GROUP_USER_ID
ALTER TABLE USER_2_GROUP DROP FOREIGN KEY FK_USER_2_GROUP_USER_GROUP_ID
DROP TABLE IDENTITY_CARD
DROP TABLE VEHICLE_CERTIFICATE
DROP TABLE USER
DROP TABLE USER_LOGIN
DROP TABLE USER_GROUP
DROP INDEX IX_REQUEST_CREATED ON REQUEST
DROP INDEX IX_REQUEST_ARCHIVED ON REQUEST
DROP TABLE REQUEST
DROP TABLE DRIVER_LICENSE
DROP TABLE INSURANCE_REQUEST
DROP TABLE CALLBACK_REQUEST
DROP TABLE VEHICLE
DROP TABLE DRIVER
DROP TABLE POLICY_REQUEST
DROP TABLE POLICY_VEHICLE
DROP TABLE POLICY_DRIVER
DROP TABLE POLICY
DROP TABLE CASCO
DROP TABLE CASCO_DRIVER
DROP TABLE CASCO_REQUEST
DROP TABLE CASCO_VEHICLE
DROP TABLE INSURANCE_VER_13
DROP TABLE USER_2_GROUP
