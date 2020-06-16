ALTER TABLE employee ADD COLUMN department_id UUID;
ALTER TABLE employee ADD CONSTRAINT department_id_fk FOREIGN KEY (department_id) REFERENCES department (id);