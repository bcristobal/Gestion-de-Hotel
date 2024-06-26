/* DELETE 'hotelsDB' database*/
DROP SCHEMA IF EXISTS hotelsDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'localhost';

/* CREATE 'hotelsDB' DATABASE */
CREATE SCHEMA hotelsDB;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';

GRANT ALL ON hotelsDB.* TO 'spq'@'localhost';