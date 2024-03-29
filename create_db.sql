CREATE DATABASE ar_dkis;

\connect ar_dkis

CREATE TABLE IF NOT EXISTS bicycle
(
    id           INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    model        VARCHAR,
    brand        VARCHAR,
    condition    VARCHAR,
    speeds_count INTEGER,
    price        NUMERIC
);

CREATE TABLE IF NOT EXISTS users
(
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username varchar,
    u_password varchar,
    u_role varchar
);