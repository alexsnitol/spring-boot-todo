CREATE TABLE IF NOT EXISTS todo(
    id          varchar(255) PRIMARY KEY,
    description varchar(255) NOT NULL,
    completed   boolean,
    created     timestamp,
    modified    timestamp
);