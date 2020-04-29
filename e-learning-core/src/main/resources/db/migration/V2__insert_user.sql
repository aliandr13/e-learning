INSERT INTO user (id, created, updated, email, name, password, phone, role, surname) VALUES (1, '2020-03-22 15:28:34', null, 'admin@mail.com', 'admin', '$2a$10$RVLWALKNm0QKRnVYnuOAgOByLRjzELeU2PXIm817BuoAuWAxmLere', null, 'ADMIN', 'admin');
INSERT INTO user (id, created, updated, email, name, password, phone, role, surname) VALUES (2, '2020-03-31 20:52:21.267863000', null, 'teacher@gmail.com', 'ALIAKSANDR', '$2a$10$FB2hOeIycPBI0BV1cyzPcu5Ogv7equ3hOmUySzscgvlXfcfvIWkmK', '80293458054', 'TEACHER', 'ZENKEVICH');
INSERT INTO user (id, created, updated, email, name, password, phone, role, surname) VALUES (3, '2020-04-01 00:04:37', null, 'student@tut.by', 'Иван', '$2a$10$RVLWALKNm0QKRnVYnuOAgOByLRjzELeU2PXIm817BuoAuWAxmLere', '', 'STUDENT', 'Иванов');
INSERT INTO user (id, created, updated, email, name, password, phone, role, surname) VALUES (4, '2020-04-01 00:06:17', null, 'student@yandex.ru', 'Петр', '$2a$10$RVLWALKNm0QKRnVYnuOAgOByLRjzELeU2PXIm817BuoAuWAxmLere', null, 'STUDENT', 'Петров');

INSERT INTO course (id, created, updated, end, name, start, teacher_id) VALUES (1, '2020-03-31 20:52:38.666271000', null, '2020-03-30', 'JD2-19-64', '2020-02-29', 2);

INSERT INTO lesson (id, created, updated, date, description, topic, course_id) VALUES (1, '2020-04-01 00:07:09', '2020-04-01 00:09:16', '2020-04-02', 'Maven основы', 'Maven 1', 1);
INSERT INTO lesson (id, created, updated, date, description, topic, course_id) VALUES (3, '2020-04-01 00:08:00', null, '2020-04-07', 'Maven продолжение', 'Maven 2', 1);

INSERT INTO courses_students (course_id, student_id) VALUES (1, 3);
INSERT INTO courses_students (course_id, student_id) VALUES (1, 4);