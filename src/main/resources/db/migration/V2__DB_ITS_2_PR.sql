CREATE TABLE company
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(128) NOT NULL UNIQUE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE professional
(
    id         BIGSERIAL PRIMARY KEY,
    company_id BIGINT       NOT NULL,
    name       VARCHAR(128) NOT NULL,
    role       VARCHAR(32)  NOT NULL,
    specialty  VARCHAR(64),
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_professional_company
        FOREIGN KEY (company_id)
            REFERENCES company (id),

    CONSTRAINT chk_professional_role
        CHECK (role IN ('Assistant', 'Technician'))
);

CREATE INDEX idx_professional_company_id
    ON professional (company_id);

CREATE INDEX idx_professional_role
    ON professional (role);

INSERT INTO company (name)
VALUES ('OLN Internet') ON CONFLICT (name) DO NOTHING;