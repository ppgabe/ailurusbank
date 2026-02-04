ALTER TABLE organization_identifiers RENAME TO client_identifiers;

ALTER TABLE client_identifiers DROP CONSTRAINT fk_organization_identifiers_org_id;

ALTER TABLE client_identifiers
    ADD COLUMN client_id uuid,
    ADD CONSTRAINT fk_client_identifiers_client_id FOREIGN KEY (client_id) REFERENCES clients(id),
    ALTER COLUMN client_id SET NOT NULL;