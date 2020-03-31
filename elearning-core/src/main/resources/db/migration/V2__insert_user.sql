INSERT INTO user (id, created, updated, email, name, password, phone, role, surname)
VALUES (1, '2020-03-22 15:28:34', null, 'admin@mail.com', 'admin',
        '$2a$10$RVLWALKNm0QKRnVYnuOAgOByLRjzELeU2PXIm817BuoAuWAxmLere', null, 'ADMIN', 'admin'),
       (2, '2020-03-31 20:52:21.267863000', null, 'zenkevich.alexander@gmail.com', 'ALIAKSANDR',
        '$2a$10$FB2hOeIycPBI0BV1cyzPcu5Ogv7equ3hOmUySzscgvlXfcfvIWkmK', '80293458054', 'TEACHER', 'ZENKEVICH');

INSERT INTO elearning.course (id, created, updated, end, name, start, teacher_id)
VALUES (1, '2020-03-31 20:52:38.666271000', null, '2020-03-30', 'JD2-19-64', '2020-02-29', 2);
