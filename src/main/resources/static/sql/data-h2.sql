INSERT INTO zones (name)
VALUES ('FirstZone'),
       ('SecondZone'),
       ('ThirdZone');

INSERT INTO animal_type (name, required_food_per_day, zone_id)
VALUES ('ELEPHANT', 20, 1),
       ('LION', 11, 2),
       ('BUNNY', 4, 3);

INSERT INTO animal (animal_type_id, name)
VALUES (1, 'John'),
       (1, 'Harry'),
       (2, 'Ronald'),
       (2, 'Neo'),
       (3, 'Billy'),
       (3, 'Nancy');

