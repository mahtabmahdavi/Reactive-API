CREATE TABLE students(
    id BIGSERIAL NOT NULL PRIMARY KEY ,
    first_name VARCHAR(50) ,
    last_name VARCHAR(50) ,
    average DOUBLE PRECISION ,
    age INTEGER
)