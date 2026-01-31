ALTER TABLE account_holders
    DROP COLUMN created_at;

ALTER TABLE account_holders
    ADD COLUMN account_id UUID UNIQUE NOT NULL;

ALTER TABLE account_holders
    ADD CONSTRAINT uq_account_holders_account_id UNIQUE (account_id);