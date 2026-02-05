ALTER TABLE issuing_authorities ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL;
ALTER TABLE banks ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL;
ALTER TABLE branches ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL;
ALTER TABLE individuals ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL;
ALTER TABLE organizations ADD COLUMN created_at timestamptz DEFAULT now() NOT NULL;