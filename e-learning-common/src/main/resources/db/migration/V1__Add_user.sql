CREATE TABLE user_role
(
    id        int PRIMARY KEY,
    role_name varchar(20) NOT NULL
);

INSERT INTO user_role(id, role_name)
VALUES (1, 'ADMIN'),
       (2, 'TEACHER'),
       (3, 'STUDENT');

CREATE TABLE user
(
    id        BIGINT AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(150) NOT NULL,
    salt      varchar(50)  NOT NULL,
    role_id   int          NOT NULL DEFAULT 3,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES user_role (id)
        ON DELETE CASCADE
);

ALTER TABLE user
    AUTO_INCREMENT = 100;

INSERT INTO user (id, user_name, password, salt, role_id)
VALUES (1, 'admin', '1043ee247aa02eb9db8752488c441faab1987f38a9cc7273ec0af580df390f3c', 'OUJsaKYYAtqtAwtlnsGeLz7XPRA=',
        1),
       (2, 'teacher', '0321b8f47714898d51a7687a95f962e7a0f4cc1d4a58ba9c0e1130f4dd84076a',
        'AWMUzF47hUp+P3cUyV+Kc03llys=', 2),
       (3, 'student', '0321b8f47714898d51a7687a95f962e7a0f4cc1d4a58ba9c0e1130f4dd84076a',
        'AWMUzF47hUp+P3cUyV+Kc03llys=', 3);

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
