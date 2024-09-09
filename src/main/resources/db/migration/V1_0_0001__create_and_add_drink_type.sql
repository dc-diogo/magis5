create table if not exists drink_type
(
    id           bigint not null AUTO_INCREMENT,
    name         varchar(255) not null,
    volume       int4         not null,
    primary key (id)
);

-- Insert two drink types
INSERT INTO drink_type (name, volume) VALUES ('Alcoholic', 500);
INSERT INTO drink_type (name, volume) VALUES ('Non-Alcoholic', 400);