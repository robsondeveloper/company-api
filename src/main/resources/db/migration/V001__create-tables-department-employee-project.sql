CREATE TABLE department (
	id UUID PRIMARY KEY,
	name CHARACTER VARYING(128) NOT NULL,
	number integer NOT NULL,
	UNIQUE (number)
);

CREATE TABLE employee (
	id UUID PRIMARY KEY,
	name CHARACTER VARYING(128) NOT NULL,
	birth DATE NOT NULL,
	salary NUMERIC(8, 2) NOT NULL
);

CREATE TABLE project (
	id UUID PRIMARY KEY,
	name CHARACTER VARYING(128) NOT NULL,
	code CHARACTER VARYING(8) NOT NULL,
	UNIQUE (code)
);