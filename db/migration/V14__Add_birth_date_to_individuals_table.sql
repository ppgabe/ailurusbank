ALTER TABLE individuals ADD COLUMN birth_date DATE;

ALTER TABLE individuals ALTER COLUMN birth_date SET NOT NULL;