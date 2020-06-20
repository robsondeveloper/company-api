CREATE TABLE product (
    id UUID PRIMARY KEY,
    name CHARACTER VARYING(128) NOT NULL,
    description TEXT,
    price DECIMAL(8, 2) NOT NULL,
    photo CHARACTER VARYING(128),
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);