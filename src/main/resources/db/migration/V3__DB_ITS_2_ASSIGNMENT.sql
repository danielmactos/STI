ALTER TABLE request
    ADD COLUMN assistant_id BIGINT,
    ADD COLUMN technician_id BIGINT,
    ADD COLUMN triage_note TEXT,
    ADD COLUMN assigned_at TIMESTAMP,
    ADD COLUMN finished_at TIMESTAMP;

ALTER TABLE request
    ADD CONSTRAINT fk_request_assistant
        FOREIGN KEY (assistant_id)
            REFERENCES professional (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT;

ALTER TABLE request
    ADD CONSTRAINT fk_request_technician
        FOREIGN KEY (technician_id)
            REFERENCES professional (id)
            ON UPDATE RESTRICT
            ON DELETE RESTRICT;

CREATE INDEX idx_request_assistant_id
    ON request (assistant_id);

CREATE INDEX idx_request_technician_id
    ON request (technician_id);

CREATE INDEX idx_request_assigned_at
    ON request (assigned_at);

CREATE INDEX idx_request_finished_at
    ON request (finished_at);