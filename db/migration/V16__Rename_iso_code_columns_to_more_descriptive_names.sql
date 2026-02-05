ALTER TABLE countries RENAME COLUMN code TO alpha2;
ALTER TABLE countries RENAME COLUMN iso3 TO alpha3;
ALTER TABLE currencies RENAME COLUMN code to alphabetic_code;
ALTER TABLE currencies RENAME COLUMN num_code TO numeric_code;