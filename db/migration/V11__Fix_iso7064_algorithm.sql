CREATE OR REPLACE FUNCTION iso7064_alpha_to_numeric(input_text TEXT)
RETURNS TEXT AS $$
DECLARE
    result TEXT := '';
    i INT;
    c TEXT;
    ascii_val INT;
BEGIN
    -- Loop through each character
    FOR i IN 1..length(input_text) LOOP
        c := upper(substr(input_text, i, 1));

        -- If it's a digit, keep it.
        IF c ~ '[0-9]' THEN
            result := result || c;
        -- If it's a letter (A-Z), convert to 10-35
        ELSIF c ~ '[A-Z]' THEN
            ascii_val := ascii(c) - 55; -- 'A' is ASCII 65. 65 - 55 = 10.
            result := result || ascii_val::TEXT;
        ELSE
            RAISE EXCEPTION 'Invalid characters in account ID. Cannot generate ISO 7064 checksum.';
        END IF;
    END LOOP;

    RETURN result;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


CREATE OR REPLACE FUNCTION generate_account_number(target_branch_id UUID)
    RETURNS VARCHAR(20) AS
$$
DECLARE
    v_institution_code VARCHAR(4);
    v_branch_code      VARCHAR(3);
    v_base_num         TEXT;
    v_numeric_str      TEXT;
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

    v_numeric_str := iso7064_alpha_to_numeric(v_base_num);

    v_checksum := 98 - ((v_numeric_str || '00')::NUMERIC % 97);

    IF v_checksum = 98 THEN
        v_checksum := 1;
    end if;

    RETURN v_base_num || LPAD(v_checksum::TEXT, 2, '0');
end;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION validate_iso7064_mod97(account_num TEXT)
    RETURNS BOOLEAN AS
$$
DECLARE
    v_numeric_str TEXT;
    remainder INT;
BEGIN
    v_numeric_str := iso7064_alpha_to_numeric(account_num);

    remainder := v_numeric_str::NUMERIC % 97;

    RETURN remainder = 1;
end;
$$ LANGUAGE plpgsql IMMUTABLE;