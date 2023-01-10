INSERT INTO zone (name)
VALUES ('FirstZone'),
       ('SecondZone'),
       ('ThirdZone');

INSERT INTO animal_type (name, required_food_per_day)
VALUES ('Elephant', 20),
       ('Lion', 11),
       ('Bunny', 4);

INSERT INTO animal (animal_type_id, name, zone_id)
VALUES (1, 'John', 1),
       (1, 'Harry', 1),
       (2, 'Ronald', 2),
       (2, 'Neo', 2),
       (2, 'Jimmy', 2),
       (3, 'Billy', 2),
       (3, 'Nancy', 2),
       (3, 'Nancy', 3);