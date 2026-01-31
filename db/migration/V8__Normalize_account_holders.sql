CREATE TYPE client_type AS ENUM ('RETAIL', 'PROFESSIONAL', 'ELIGIBLE_COUNTERPARTY', 'INTERNAL', 'STAFF', 'PROSPECT');

ALTER TABLE account_holders
    RENAME TO clients;

DROP INDEX idx_one_primary_per_type;

ALTER TABLE account_holders_contact
    RENAME TO clients_contact;

ALTER TABLE clients_contact
    DROP COLUMN account_holder_id CASCADE,
    ADD COLUMN client_id  uuid                      NOT NULL,
    ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL,
    ADD CONSTRAINT fk_clients_contact_client_id FOREIGN KEY (client_id) REFERENCES clients (id);

CREATE UNIQUE INDEX idx_one_primary_per_contact_type
    ON clients_contact (client_id, type)
    WHERE type = 'primary';

ALTER TABLE clients
    DROP COLUMN account_id CASCADE,
    DROP COLUMN username CASCADE,
    DROP COLUMN first_name CASCADE,
    DROP COLUMN middle_name CASCADE,
    DROP COLUMN last_name CASCADE,
    DROP COLUMN birth_date CASCADE,
    ADD COLUMN type       client_type               NOT NULL,
    ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL;

CREATE TABLE individuals
(
    id          uuid PRIMARY KEY,
    first_name  varchar(100) NOT NULL,
    middle_name varchar(100),
    last_name   varchar(100) NOT NULL,
    nationality char(3) NOT NULL,

    CONSTRAINT fk_individuals_nationality FOREIGN KEY (nationality) REFERENCES countries(iso3),
    CONSTRAINT fk_individuals_client_id FOREIGN KEY (id) REFERENCES clients (id) ON DELETE CASCADE
);

CREATE TABLE organizations
(
    id                       uuid PRIMARY KEY,
    name                     varchar(100) NOT NULL,
    incorporation_date       date         NOT NULL,
    country_of_incorporation char(2)      NOT NULL,

    CONSTRAINT fk_organizations_country_of_incorporation FOREIGN KEY (country_of_incorporation) REFERENCES countries(code)
);

CREATE TABLE issuing_authorities (
    code varchar(20) PRIMARY KEY,
    name varchar(255) NOT NULL,
    country_code char(2) NOT NULL,
    type varchar(50),

    CONSTRAINT fk_issuing_authorities_country_code FOREIGN KEY (country_code) REFERENCES countries(code)
);

CREATE TABLE organization_identifiers
(
    id uuid DEFAULT uuidv7() PRIMARY KEY,
    organization_id uuid NOT NULL,
    type varchar(50) NOT NULL,
    value varchar(100) NOT NULL,
    issuing_authority varchar(20) NOT NULL,
    country_of_jurisdiction char(2) NOT NULL,
    created_at timestamptz DEFAULT now() NOT NULL,

    CONSTRAINT fk_organization_identifiers_org_id FOREIGN KEY (organization_id) REFERENCES organizations(id),
    CONSTRAINT fk_organization_identifiers_issuing_authority FOREIGN KEY (issuing_authority) REFERENCES issuing_authorities(code),
    CONSTRAINT uq_organization_identifiers_type_value_per_country UNIQUE (type, value, issuing_authority, country_of_jurisdiction),
    CONSTRAINT fk_organization_identifiers_country_of_jurisdiction FOREIGN KEY (country_of_jurisdiction) REFERENCES countries(code)
);

CREATE TABLE account_holders_map
(
    id         uuid        DEFAULT uuidv7() PRIMARY KEY,
    client_id  UUID                      NOT NULL,
    account_id UUID                      NOT NULL,
    role       varchar(20),
    created_at timestamptz DEFAULT now() NOT NULL,

    CONSTRAINT fk_account_holders_map_client_id FOREIGN KEY (client_id) REFERENCES clients (id),
    CONSTRAINT fk_account_holders_map_account_id FOREIGN KEY (account_id) REFERENCES accounts (id)
);