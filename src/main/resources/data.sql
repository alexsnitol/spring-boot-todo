CREATE TABLE TO_DO(
    id  varchar(255) PRIMARY KEY NOT NULL,
    description varchar(255) NOT NULL,
    completed boolean DEFAULT (false),
    created timestamp,
    modified timestamp
);

INSERT INTO TO_DO(id, description, completed, created, modified)
VALUES
    ('1', 'test 1', true, '2022-01-01 00:00:00', '2022-01-01 00:00:00'),
    ('2', 'test 2', false, '2022-01-01 00:00:00', '2022-01-01 00:00:00'),
    ('3', 'test 3', false, '2022-01-01 00:00:00', '2022-01-01 00:00:00')
;