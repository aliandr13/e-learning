
insert into role_t (role_name)
values ('ADMIN'),
       ('TEACHER'),
       ('STUDENT');

INSERT INTO user_t (login, password, salt, role_id)
VALUES ('admin@admin.com', '5c5212a214c634002e7f970fa293746fb7d49c7cfc7087edecd4c32402c06007',
        'SEZhwD0GPt9W099OZkx2y9kZzJc=',
        1),
       ('teacher@mail.ru', '7205baa2f9ff6455de144716f620285eff0bc89b4d0607eb3346272b4dc7ac2c',
        '4kSwPVILWcrRre7xItQSUCBzBcM=', 2),
       ('student@rambler.ru', '4f8cc2bce35fa155e06ac1d48af1e8ecc614f7392a1868f39dd117d6cdf71762',
        'fWOYaqYJw1j1AgNK9nFBfO0esrU=', 3);

INSERT INTO course_t(name)
VALUES ('Java'),
       ('Python'),
       ('IOS');

INSERT INTO teacher_t (first_name, middle_name, last_name, email, phone, user_id)
VALUES ('tom', 'tommy', 'tom_last', 'tom@mail.com', '3752911122233', 2);
INSERT INTO student_t (first_name, middle_name, last_name, email, phone, user_id)
VALUES ('jerry', 'jerry', 'jerry_last', 'jerry@mail.com', '+375-29-444-55-66', 3);
