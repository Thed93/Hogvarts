ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age > 0);

ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name);

ALTER  TABLE student ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty ADD CONSTRAINT name_color UNIQUE (name, color);

ALTER  TABLE student ALTER COLUMN age SET DEFAULT 20;
