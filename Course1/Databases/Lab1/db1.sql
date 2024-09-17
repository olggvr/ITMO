DROP TYPE IF EXISTS types CASCADE;
DROP TYPE IF EXISTS action_type CASCADE;
DROP TYPE IF EXISTS origin CASCADE;
DROP TYPE IF EXISTS namee CASCADE;
DROP TYPE IF EXISTS loc_name CASCADE;


DROP TABLE IF EXISTS people CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS loc CASCADE;
DROP TABLE IF EXISTS actions CASCADE;
DROP TABLE IF EXISTS navigation CASCADE;


CREATE TYPE types as ENUM ('первооткрыватели', 'ученые');
CREATE TYPE action_type as ENUM ('отбивать', 'определять возраст');
CREATE TYPE origin as ENUM ('юрский', 'команчский', 'плеоцен');
CREATE TYPE namee as ENUM ('камень', 'стена');
CREATE TYPE loc_name as ENUM ('город', 'поле');

CREATE TABLE IF NOT EXISTS people(
    id SERIAL PRIMARY KEY, 
    types VARCHAR,
    middle_age INT
);

CREATE TABLE IF NOT EXISTS item (
    id SERIAL PRIMARY KEY,
    namee VARCHAR UNIQUE NOT NULL,
    origin VARCHAR
);

CREATE TABLE IF NOT EXISTS loc(
    id SERIAL PRIMARY KEY, 
    loc_name VARCHAR,
    age INT
);

CREATE TABLE IF NOT EXISTS actions(
    id SERIAL PRIMARY KEY,
    object_name INT,
    subject_name VARCHAR NOT NULL,
    FOREIGN KEY (object_name) REFERENCES people(id),
    FOREIGN KEY (subject_name) REFERENCES item(namee),
    action_type VARCHAR
);

CREATE TABLE IF NOT EXISTS navigation(
    id SERIAL PRIMARY KEY,
    group_id INT NOT NULL,
    items_id INT NOT NULL, 
    loc_id INT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES people(id),
    FOREIGN KEY (items_id) REFERENCES item(id),
    FOREIGN KEY (loc_id) REFERENCES loc(id)
);

INSERT INTO people (types, middle_age) VALUES ('первооткрыватели', 30), ('ученые', 43);
INSERT INTO item (namee, origin) VALUES ('камень', 'юрский'), ('стена', 'плеоцен');
INSERT INTO actions (object_name, subject_name, action_type) VALUES (1, 'камень','отбивать'), (2, 'камень', 'определять возраст');
INSERT INTO loc (loc_name, age) VALUES ('город', 500000), ('поле', 1000);

INSERT INTO navigation (group_id, items_id, loc_id) VALUES (1, 2, 2), (2, 1, 1);