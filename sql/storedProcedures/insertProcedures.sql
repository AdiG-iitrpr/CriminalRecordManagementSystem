-- insertion of criminal (update courtHearing)
CREATE OR REPLACE FUNCTION add_criminal(
    IN criminal_id VARCHAR(5),
    IN first_name VARCHAR(30),
    IN last_name VARCHAR(30),
    IN gender gen,
    IN age INT,
    IN criminal_address VARCHAR(100),
    IN district VARCHAR(30),
    IN case_id INT,
    IN suspect_id INT 
)
RETURNS VOID AS $$
    BEGIN
        INSERT INTO Criminal 
        VALUES(criminal_id,suspect_id,first_name,last_name,gender,age,criminal_address,district);
        
        INSERT INTO courtHearing 
        VALUES(case_id,suspect_id,CURRENT_DATE, 'GUILTY');
        
        UPDATE Cases
        SET verdict = 'PROVENGUILTY'
        WHERE case_id = case_id;
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