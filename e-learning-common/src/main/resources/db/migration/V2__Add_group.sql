CREATE TABLE course
(
    id   int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL
);

INSERT INTO course(name)
VALUES ('Java'),
       ('Python'),
       ('IOS');

CREATE TABLE `group`
(
    id        BIGINT primary key AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    course_id int          NOT NULL,
    startDate timestamp,
    CONSTRAINT course_fk FOREIGN KEY (course_id)
        REFERENCES course (id) ON DELETE RESTRICT
);

CREATE TABLE user_info
(
    id          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name  VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    phone       VARCHAR(50),
    group_id    BIGINT,
    user_id     BIGINT       NOT NULL,
    created     timestamp    not null default now(),
    CONSTRAINT user_fk FOREIGN KEY (user_id)
        REFERENCES `user` (id) ON DELETE cascade,
    CONSTRAINT group_fk FOREIGN KEY (group_id)
        REFERENCES `group` (id) ON DELETE RESTRICT

);

INSERT INTO user_info (first_name, middle_name, last_name, email, phone, user_id)
VALUES ('tom', 'tommy', 'tom_last', 'tom@mail.com', '3752911122233', 2),
       ('jerry', 'jerry', 'jerry_last', 'jerry@mail.com', '+375-29-444-55-66', 3)
