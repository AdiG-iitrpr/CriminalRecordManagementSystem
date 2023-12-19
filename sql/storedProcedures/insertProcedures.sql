-- insertion of criminal (update courtHearing)
CREATE OR REPLACE FUNCTION add_criminal(
    IN criminal_id VARCHAR(5),
    IN first_name VARCHAR(30),
    IN last_name VARCHAR(30),
    IN gender VARCHAR(1),
    IN age INT,
    IN criminal_address VARCHAR(100),
    IN district VARCHAR(30),
    IN caseid INT,
    IN suspect_id INT 
)
RETURNS VOID AS $$
    -- #variable_conflict use_column
    BEGIN
        INSERT INTO Criminal 
        VALUES(criminal_id,suspect_id,first_name,last_name,gender,age,criminal_address,district);
        
        INSERT INTO courtHearing 
        VALUES(caseid,suspect_id,CURRENT_DATE, 'Guilty');
        
        UPDATE Cases
        SET verdict = 'PROVENGUILTY'
        WHERE Cases.case_id = caseid;
    END;
$$ LANGUAGE plpgsql;

-- insertion of JailLog (update number of occupied cells)

CREATE OR REPLACE FUNCTION add_jailLog(
    jailid INT,
    criminal_id VARCHAR(5)
)

RETURNS VOID AS $$
    BEGIN
        INSERT INTO jailLog(jail_id, criminal_id) VALUES(jailid,criminal_id);
        UPDATE Jail
        SET number_of_cells_occupied = number_of_cells_occupied + 1
        WHERE Jail.jail_id = jailid;
    END;

$$ LANGUAGE plpgsql;