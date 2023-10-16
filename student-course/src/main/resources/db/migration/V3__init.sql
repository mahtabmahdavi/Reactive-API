CREATE TABLE course_person_mapping (
    id BIGSERIAL PRIMARY KEY ,
    course BIGINT ,
    person BIGINT ,
    FOREIGN KEY (course) REFERENCES courses (id) ,
    FOREIGN KEY (person) REFERENCES people (id)
);