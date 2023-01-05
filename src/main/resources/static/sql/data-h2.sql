INSERT INTO zones (name)
VALUES ('FirstZone'),
       ('SecondZone'),
       ('ThirdZone');

INSERT INTO animal_type (name, required_food_per_day)
VALUES ('ELEPHANT', 20),
       ('LION', 11),
       ('BUNNY', 4);

INSERT INTO animal (animal_type_id, name, zone_id)
VALUES (1, 'John', 1),
       (1, 'Harry', 1),
       (2, 'Ronald', 2),
       (2, 'Neo', 2),
       (3, 'Billy', 3),
       (3, 'Nancy', 3);

