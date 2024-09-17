
CREATE TABLE people(
    id SERIAL PRIMARY KEY, 
    types VARCHAR,
    middle_age INT
);

CREATE TABLE actions(
    id SERIAL PRIMARY KEY,
    object_name VARCHAR,
    subject_name VARCHAR,
    FOREIGN KEY (object_name) REFERENCES people(id),
    FOREIGN KEY (subject_name) REFERENCES item(namee),
    action_type VARCHAR
);

CREATE TABLE item (
    id SERIAL PRIMARY KEY,
    namee VARCHAR,
    FOREIGN KEY (namee) REFERENCES actions(subject_name),
    origin VARCHAR
);

CREATE TABLE loc(
    id SERIAL PRIMARY KEY, 
    namee VARCHAR,
    age INT
);

CREATE TABLE navigation(
    id SERIAL PRIMARY KEY,
    FOREIGN KEY (group_id) REFERENCES people(id),
    FOREIGN KEY (items_id) REFERENCES item(id),
    FOREIGN KEY (loc_id) REFERENCES loc(id)
);

