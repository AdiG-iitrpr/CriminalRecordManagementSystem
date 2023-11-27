--contact details of suspect --

CREATE OR REPLACE FUNCTION checkContact()
RETURNS TRIGGER AS $$
    BEGIN
        IF LENGTH(new.contact) <> 10 THEN
            RAISE EXCEPTION 'Invalid "contact" length: "%"', new.contact
            USING HINT = '"contact" length should be exactly equal to 10';
        ELSIF LENGTH(new.contact) = 0 THEN
            RAISE EXCEPTION 'Invalid "contact" length: "%"', new.contact
            USING HINT = '"contact" length should be greater than zero';
        ELSIF (SELECT new.contact ~ '^[0-9]+$') = false THEN
            RAISE EXCEPTION 'Invalid "contact" type: "%"', new.contact
            USING HINT = '"contact" must only contain digits';
        END IF;

        RETURN new;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER checkcontact
BEFORE INSERT OR UPDATE ON Suspect 
FOR EACH ROW EXECUTE PROCEDURE checkContact();



-- check if capacity is full or not in jail 

CREATE OR REPLACE FUNCTION checkAvailability() 
RETURNS TRIGGER AS $$
BEGIN 
    IF NOT EXISTS (
        SELECT 1
        FROM Jail
        WHERE Jail.number_of_cells = Jail.number_of_cells_occupied AND Jail.jail_id = new.jail_id
    ) THEN
        RETURN new;
    ELSE
        RAISE EXCEPTION 'Cannot insert criminal into Jail with jailId (CAPACITY FULL ): "%"',new.jail_id;
    END IF;
    RETURN new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER checkAvailabilityOfCells
BEFORE INSERT OR UPDATE ON jailLog
FOR EACH ROW
EXECUTE FUNCTION checkAvailability();
