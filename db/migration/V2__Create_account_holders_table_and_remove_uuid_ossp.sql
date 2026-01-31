DROP EXTENSION IF EXISTS "uuid-ossp";

CREATE TABLE account_holders
(
    id          UUID PRIMARY KEY DEFAULT uuidv7(),
    username    VARCHAR(64) UNIQUE,
    first_name  VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name   VARCHAR      NOT NULL,
    birth_date  DATE         NOT NULL
);