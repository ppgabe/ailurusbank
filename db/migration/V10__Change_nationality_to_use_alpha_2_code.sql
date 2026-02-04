ALTER TABLE individuals
    ADD COLUMN nationality_alpha_2 char(2);

UPDATE individuals
    SET nationality_alpha_2 = c.code
    FROM countries c
    WHERE nationality = c.iso3;

ALTER TABLE individuals
    DROP COLUMN nationality CASCADE;

ALTER TABLE individuals
    ALTER COLUMN nationality_alpha_2 SET NOT NULL;

ALTER TABLE individuals
    RENAME COLUMN nationality_alpha_2 TO nationality;

ALTER TABLE individuals
    ADD CONSTRAINT fk_individuals_nationality FOREIGN KEY (nationality) REFERENCES countries(code);
