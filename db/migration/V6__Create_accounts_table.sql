CREATE TYPE account_status AS ENUM ('active', 'frozen', 'closed', 'pending');
CREATE TYPE account_type AS ENUM ('checking', 'savings', 'corporate', 'loan');

CREATE SEQUENCE IF NOT EXISTS account_id_seq START 10000001;
CREATE OR REPLACE FUNCTION generate_account_number(target_branch_id UUID)
    RETURNS VARCHAR(20) AS
$$
DECLARE
    v_institution_code VARCHAR(4);
    v_branch_code      VARCHAR(3);
    v_base_num         TEXT;
    v_checksum         INT;
BEGIN
    SELECT bk.institution_code,
           br.branch_code
    INTO
        v_institution_code,
        v_branch_code
    FROM branches br
             JOIN banks bk ON br.bank_id = bk.id
    WHERE br.id = target_branch_id;

    v_base_num := v_institution_code || v_branch_code ||
                  LPAD(nextval('account_id_seq')::TEXT, 8, '0');

    v_checksum := 98 - (((v_base_num) || '00')::NUMERIC % 97);

    IF v_checksum = 98 THEN
        v_checksum := 1;
    end if;

    RETURN v_base_num || LPAD(v_checksum::TEXT, 2, '0');
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trg_apply_account_number()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.account_number IS NULL THEN
        NEW.account_number := generate_account_number(NEW.branch_id);
    END IF;

    RETURN NEW;
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_iso7064_mod97(account_num TEXT)
    RETURNS BOOLEAN AS
$$
DECLARE
    remainder INT;
BEGIN
    EXECUTE 'SELECT (' || account_num || '::numeric % 97' INTO remainder;

    RETURN remainder = 1;
end;
$$ LANGUAGE plpgsql IMMUTABLE;

CREATE TABLE accounts
(
    id                  UUID           DEFAULT uuidv7() PRIMARY KEY,

    branch_id           UUID                             NOT NULL,

    account_number      VARCHAR(20)                      NOT NULL,

    currency_code       CHAR(3)                          NOT NULL,

    balance_minor_units BIGINT         DEFAULT 0         NOT NULL,

    type                account_type   DEFAULT 'savings' NOT NULL,
    status              account_status DEFAULT 'pending' NOT NULL,
    created_at          timestamptz    DEFAULT now()     NOT NULL,

    CONSTRAINT uq_account_number UNIQUE (account_number),
    CONSTRAINT chk_positive_balance CHECK (balance_minor_units >= 0),
    CONSTRAINT chk_iso7064_verification CHECK (validate_iso7064_mod97(account_number))
);

CREATE TRIGGER ensure_account_number_generation
    BEFORE INSERT
    ON accounts
    FOR EACH ROW
EXECUTE FUNCTION trg_apply_account_number();