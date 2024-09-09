CREATE TABLE sector (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        available_volume INT NOT NULL,
                        drink_type_id BIGINT,
                        last_update TIMESTAMP NOT NULL,
                        CONSTRAINT fk_drink_type FOREIGN KEY (drink_type_id) REFERENCES drink_type(id)
);
INSERT INTO sector (name, available_volume, drink_type_id, last_update)
VALUES
    ('Sector A', 0, NULL, '2023-10-01 10:00:00'),
    ('Sector B', 0, NULL, '2023-10-02 11:00:00'),
    ('Sector C', 0, NULL, '2023-10-03 12:00:00'),
    ('Sector D', 0, NULL, '2023-10-04 10:30:00'),
    ('Sector E', 0, NULL, '2023-10-05 09:45:00');