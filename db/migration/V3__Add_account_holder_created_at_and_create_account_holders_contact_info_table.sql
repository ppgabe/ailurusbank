ALTER TABLE account_holders
    ADD COLUMN IF NOT EXISTS created_at timestamptz NOT NULL DEFAULT now();

CREATE TYPE contact_precedence AS ENUM ('primary', 'alternative', 'emergency');

CREATE TABLE account_holders_contact
(
    id                UUID PRIMARY KEY DEFAULT uuidv7(),

    account_holder_id UUID               NOT NULL REFERENCES account_holders (id) ON DELETE CASCADE,
    precedence        contact_precedence NOT NULL,
    type              VARCHAR(50)        NOT NULL,
    value             VARCHAR(255)       NOT NULL,
    description       TEXT,

    CONSTRAINT uq_account_contact_value UNIQUE (account_holder_id, value)
);

CREATE UNIQUE INDEX idx_one_primary_per_type
    ON account_holders_contact (account_holder_id, type)
    WHERE precedence = 'primary';