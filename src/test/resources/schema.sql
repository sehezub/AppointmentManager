DROP TABLE IF EXISTS Medical_Appointment;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;

CREATE TABLE Medical_Appointment (
    id BIGINT IDENTITY PRIMARY KEY,
    appointment_date TIMESTAMP,
    details VARCHAR(64),
    patient_id BIGINT,
    doctor_id BIGINT,
    duration BIGINT
);
CREATE TABLE patient (
    id BIGINT IDENTITY PRIMARY KEY,
    name VARCHAR(64)
);
CREATE TABLE doctor (
    id BIGINT IDENTITY PRIMARY KEY,
    name VARCHAR(64)
);

INSERT INTO Medical_Appointment VALUES(1, TIMESTAMP('2023-08-23', '12:00:00'), 'Turno con el dentista', 1, 2, 3600);
INSERT INTO Medical_Appointment VALUES(2, TIMESTAMP('2023-08-24', '8:00:00'), 'Turno con el dermatologo', 2, 1, 3600);

INSERT INTO Patient VALUES(1, 'Javier');
INSERT INTO Patient VALUES(2, 'Daniela');
INSERT INTO Patient VALUES(3, 'Tomas');

INSERT INTO Doctor VALUES(1, 'Jhon');
INSERT INTO Doctor VALUES(2, 'Joseph');
INSERT INTO Doctor VALUES(3, 'Jotaro');