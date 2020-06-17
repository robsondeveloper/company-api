CREATE TABLE project_employee (
	project_id UUID NOT NULL,
	employee_id UUID NOT NULL,
	FOREIGN KEY (project_id) REFERENCES project (id),
	FOREIGN KEY (employee_id) REFERENCES employee (id)
);