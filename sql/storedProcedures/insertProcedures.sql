--insertion of criminal (update courtHearing)


CREATE OR REPLACE FUNCTION add_criminal (
    criminalId VARCHAR(5),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    gender gen,
    criminal_address VARCHAR(100),
    district CHAR(30),
    caseid INT,
    suspectid INT 
)
RETURNS VOID AS $$
    BEGIN
        INSERT INTO Criminal 
        VALUES(criminal_id,first_name,last_name,gender,criminal_address,district);
        
        INSERT INTO courtHearing 
        VALUES(case_id,suspect_id,CURRENT_DATE, 'PROVENGUILTY');
        
        UPDATE Cases
        SET verdict = 'PROVENGUILTY'
        WHERE case_id = caseid;
    END;
$$ LANGUAGE plpgsql;

-- insertion of JailLog (update number of occupied cells)

CREATE OR REPLACE FUNCTION add_jailLog(
    jail_id INT,
    criminal_id VARCHAR(5)
)

RETURNS VOID AS $$
    BEGIN
        INSERT INTO jailLog VALUES(jail_id,criminal_id);
        UPDATE Jail
        SET number_of_cells_occupied = number_of_cells_occupied + 1
        WHERE jail_id = jailID;
    END;

$$ LANGUAGE plpgsql;