CREATE TABLE customer (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(64) NOT NULL,
contact VARCHAR(32) NOT NULL
);

CREATE TABLE locality (
id BIGSERIAL PRIMARY KEY,
city VARCHAR(64) NOT NULL,
address VARCHAR(128) NOT NULL,
neighborhood VARCHAR(64) NOT NULL,
reference_point VARCHAR(256)
);

CREATE TYPE request_status AS ENUM ('Open','Running','Finished');

CREATE TABLE request (
id BIGSERIAL PRIMARY KEY,
protocol VARCHAR(32) NOT NULL UNIQUE,
customer_id BIGINT NOT NULL,
locality_id BIGINT NOT NULL,
description TEXT NOT NULL,
status request_status NOT NULL DEFAULT 'Open',
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT FK_request_customer
FOREIGN KEY (customer_id) REFERENCES customer(id),

CONSTRAINT FK_request_locality
FOREIGN KEY (locality_id) REFERENCES locality(id)
);