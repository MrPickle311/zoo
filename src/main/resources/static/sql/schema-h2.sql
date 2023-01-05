CREATE TABLE IF NOT EXISTS zones
(
    id   integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name text    NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS animal_type
(
    id                    integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name                  text    NOT NULL,
    required_food_per_day integer NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS animal
(
    id             integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    animal_type_id integer NOT NULL,
    name           text    NOT NULL,
    zone_id        int     NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS animal
    ADD FOREIGN KEY (zone_id)
        REFERENCES zones (id);

ALTER TABLE IF EXISTS animal
    ADD FOREIGN KEY (animal_type_id)
        REFERENCES animal_type (id);