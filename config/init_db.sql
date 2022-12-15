CREATE TABLE resume
(
    uuid      CHAR(36) PRIMARY KEY,
    full_name TEXT NOT NULL
);
CREATE TABLE contact
(
    id          SERIAL PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);

INSERT INTO resume (uuid, full_name)
VALUES ('123', 'Full Name');
CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);