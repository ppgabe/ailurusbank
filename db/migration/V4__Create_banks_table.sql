CREATE TABLE currencies
(
    code     CHAR(3) PRIMARY KEY,
    num_code CHAR(3)            NOT NULL,

    name     VARCHAR(100)       NOT NULL,

    decimals SMALLINT DEFAULT 2 NOT NULL,
    symbol   VARCHAR(5)         NOT NULL,

    CONSTRAINT uq_currencies_num_code UNIQUE (num_code)
);

INSERT INTO currencies (code, num_code, name, decimals, symbol)
VALUES ('PHP', '608', 'Philippine Peso', 2, '₱'),
       ('USD', '840', 'US Dollar', 2, '$'),
       ('SGD', '702', 'Singapore Dollar', 2, 'S$'),
       ('AUD', '036', 'Australian Dollar', 2, 'A$'),
       ('CNY', '156', 'Yuan Renminbi', 2, '¥'),
       ('GBP', '826', 'Pound Sterling', 2, '£'),
       ('JPY', '392', 'Yen', 0, '¥');

CREATE TABLE countries
(
    -- ISO 3166-1 alpha-2
    code          CHAR(2) PRIMARY KEY,

    -- ISO 3166-1 alpha-3
    iso3          CHAR(3)      NOT NULL,

    name          VARCHAR(100) NOT NULL,
    currency_code CHAR(3)      NOT NULL,

    CONSTRAINT uq_countries_iso3 UNIQUE (iso3),
    CONSTRAINT uq_countries_name UNIQUE (name),
    CONSTRAINT fk_countries_currency_code FOREIGN KEY (currency_code) REFERENCES currencies (code)
);

INSERT INTO countries (code, iso3, name, currency_code)
VALUES ('PH', 'PHL', 'Philippines', 'PHP'),
       ('US', 'USA', 'United States of America', 'USD'),
       ('SG', 'SGP', 'Singapore', 'SGD'),
       ('AU', 'AUS', 'Australia', 'AUD'),
       ('CN', 'CHN', 'China', 'CNY'),
       ('GB', 'GBR', 'United Kingdom of Great Britain and Northern Ireland', 'GBP'),
       ('JP', 'JPN', 'Japan', 'JPY');

CREATE TABLE banks
(
    id               UUID DEFAULT uuidv7() PRIMARY KEY,

    institution_code VARCHAR(4)   NOT NULL,
    country_code     CHAR(2)      NOT NULL,
    name             VARCHAR(255) NOT NULL,

    CONSTRAINT uq_banks_institution_code UNIQUE (institution_code),
    CONSTRAINT uq_banks_name_per_country UNIQUE (name, country_code),
    CONSTRAINT fk_banks_country_code FOREIGN KEY (country_code) REFERENCES countries (code)
);