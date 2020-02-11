create table user_role
(
    id   int auto_increment primary key,
    role varchar(50) not null unique
) engine = InnoDB;

create table user
(
    id          bigint auto_increment primary key,
    email       varchar(255) not null unique,
    name        varchar(255) not null,
    surname     varchar(255) not null,
    middle_name varchar(255) null,
    phone       varchar(50)  null,
    role_id     int          not null,
    constraint FK_user_role
        foreign key (role_id) references user_role (id)
) engine = InnoDB;

create table user_auth
(
    id       bigint auto_increment primary key,
    login    varchar(255) not null unique,
    password varchar(255) not null,
    salt     varchar(255) not null,
    user_id  bigint       not null unique,
    constraint FK_user_user_auth
        foreign key (user_id) references user (id) on DELETE CASCADE
) engine = InnoDB;

create table course
(
    id   int auto_increment primary key,
    name varchar(255) not null unique
) engine = InnoDB;

create table group_t
(
    id          bigint auto_increment primary key,
    name        varchar(255) not null unique,
    status      varchar(50)  not null,
    start_date  date         null,
    finish_date date         null,
    course_id   int          not null,
    constraint FK_group_t_course
        foreign key (course_id) references course (id)
) engine = InnoDB;

create table user_group_link
(
    group_id bigint not null,
    user_id  bigint not null,
    constraint FK_user_group_link_user
        foreign key (user_id) references user (id),
    constraint FK_user_group_link_group
        foreign key (group_id) references group_t (id)
);

##### values
INSERT INTO user_role (role)
values ('ADMIN'),
       ('TEACHER'),
       ('STUDENT');

INSERT INTO user (name, middle_name, surname, phone, email, role_id)
VALUES ('tom', 'tommy', 'tom_last', '3752911122233', 'admin@admin.com', 1);
INSERT INTO user (name, middle_name, surname, phone, email, role_id)
VALUES ('tom', 'tommy', 'tom_last', '3752911122233', 'teacher@mail.ru', 2);
INSERT INTO user (name, middle_name, surname, phone, email, role_id)
VALUES ('jerry', 'jerry', 'jerry_last', '+375-29-444-55-66', 'student@rambler.ru', 3);

INSERT INTO user_auth (login, password, salt, user_id)
VALUES ('admin@admin.com', '5c5212a214c634002e7f970fa293746fb7d49c7cfc7087edecd4c32402c06007',
        'SEZhwD0GPt9W099OZkx2y9kZzJc=', 1),
       ('teacher@mail.ru', '7205baa2f9ff6455de144716f620285eff0bc89b4d0607eb3346272b4dc7ac2c',
        '4kSwPVILWcrRre7xItQSUCBzBcM=', 2),
       ('student@rambler.ru', '4f8cc2bce35fa155e06ac1d48af1e8ecc614f7392a1868f39dd117d6cdf71762',
        'fWOYaqYJw1j1AgNK9nFBfO0esrU=', 3);

insert into course (name)
values ('Java core'),
       ('Java EE'),
       ('Computer science'),
       ('IOS'),
       ('Android');