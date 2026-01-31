ALTER TABLE banks
    ADD COLUMN location_code CHAR(2) NOT NULL;
ALTER TABLE banks
    ADD CONSTRAINT uq_banks_bic8 UNIQUE (institution_code, country_code, location_code);

CREATE TABLE branches
(
    id             UUID       DEFAULT uuidv7() PRIMARY KEY,
    bank_id        UUID                     NOT NULL,

    branch_code    VARCHAR(3) DEFAULT 'XXX' NOT NULL,

    name           VARCHAR(255)             NOT NULL,

    street_address VARCHAR(255),
    city           VARCHAR(100),

    CONSTRAINT uq_branches_code_per_bank UNIQUE (bank_id, branch_code)
);