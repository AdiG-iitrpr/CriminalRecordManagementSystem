--insertion of criminal (update courtHearing)

-- update Case and update courtHearing on basis of suspect_id and case_id 

CREATE OR REPLACE FUNCTION add_criminal (
    --addRecords
    caseid INT,
    suspectid INT 
)
RETURNS VOID AS $$
    BEGIN
        -- complete the query
        INSERT INTO Criminal VALUES();
        INSERT INTO courtHearing VALUES(case_id,suspect_id,CURRRENTDATE, 'PROVENGUILTY');
        
        UPDATE Cases
        SET verdict = 'PROVENGUILTY'
        WHERE case_id = caseid;
    END;
$$ LANGUAGE plpgsql;

-- insertion of JailLog (update number of occupied cells)

CREATE OR REPLACE FUNCTION add_jailLog(
    --add info about criminal being added
    
)

RETURNS VOID AS $$
    BEGIN
        INSERT INTO jailLog VALUES();
        UPDATE Jail
        SET number_of_cells_occupied = number_of_cells_occupied + 1
        WHERE jail_id = jailID;
    END;

$$ LANGUAGE plpgsql;