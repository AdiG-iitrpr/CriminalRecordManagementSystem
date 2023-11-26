CREATE TYPE gen AS ENUM ('M','F');

CREATE TYPE case_stat AS ENUM('ACTIVE', 'FILED', 'RUNNING');

CREATE TYPE ver AS ENUM('PENDING', 'PROVENGUILTY', 'INNOCENT', 'REFERRED');

CREATE TABLE IF NOT EXISTS district (
    district VARCHAR(30) PRIMARY KEY,
    officer_in_charge VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Station (
    station_id INT PRIMARY KEY,
    stationAddress VARCHAR(40),
    district VARCHAR(30),
    FOREIGN KEY (district) REFERENCES district(district)
);

CREATE TABLE IF NOT EXISTS Officer (
    officer_id SERIAL PRIMARY KEY,
    firstname VARCHAR(30),
    lastName VARCHAR(30),
    dateofJoining DATE,
    officer_address VARCHAR(100),
    station_id INT,
    FOREIGN KEY (station_id) REFERENCES Station(station_id)
);

CREATE TABLE IF NOT EXISTS Cases (
    case_id SERIAL PRIMARY KEY,
    case_name VARCHAR(30),
    case_status case_stat,
    verdict ver,
    crime_description TEXT,
    OfficerID INT,
    courtName VARCHAR(30),
    FOREIGN KEY (OfficerID) REFERENCES Officer(officer_id)
);

CREATE TABLE IF NOT EXISTS FIR (
    fir_id SERIAL PRIMARY KEY,
    incident_Type VARCHAR(40),
    incident_Time TIME,
    time_lodged TIME,
    date_lodged DATE,
    description_of_case TEXT,
    station_id INT,
    FOREIGN KEY (station_id) REFERENCES Station(station_id)
);

CREATE TABLE IF NOT EXISTS Suspect (
    suspect_id SERIAL PRIMARY KEY,
    fir_id INT,
    suspect_name VARCHAR(30),
    suspect_address VARCHAR(40),
    contact BIGINT(10),
    FOREIGN KEY (fir_id) REFERENCES FIR(fir_id)
);

CREATE TABLE IF NOT EXISTS Criminal (
    criminal_id VARCHAR(5) PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    gender gen,
    criminal_address VARCHAR(100),
    district VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS courtHearing (
    case_id INT,
    criminal_id VARCHAR(5),
    dateofhearing DATE,
    verdict TEXT,
    FOREIGN KEY (case_id) REFERENCES Cases(case_id),
    FOREIGN KEY (criminal_id) REFERENCES Criminal(criminal_id)
);


CREATE TABLE IF NOT EXISTS Jail (
    jail_id SERIAL PRIMARY KEY,
    district VARCHAR(30),
    jail_admin VARCHAR(40),
    jail_address VARCHAR(40),
    number_of_cells INT,
    FOREIGN KEY (district) REFERENCES district(district)
);

CREATE TABLE IF NOT EXISTS jailLog (
    jail_id INT,
    prisoner_id SERIAL,
    criminal_id VARCHAR(5),
    DateOfConviction DATE,
    DateOfRelease DATE,
    FOREIGN KEY (jail_id) REFERENCES Jail(jail_id),
    FOREIGN KEY (criminal_id) REFERENCES Criminal(criminal_id)
);
