create table user_role
(
    id   int auto_increment primary key,
    role varchar(50) not null unique
) engine = InnoDB;

create table user
(
    id       bigint auto_increment primary key,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    salt     varchar(255) not null,
    role_id  int          not null,
    constraint FK_user_role
        foreign key (role_id) references user_role (id)
) engine = InnoDB;

create table user_info
(
    id          bigint auto_increment primary key,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255) null,
    phone       varchar(50)  null,
    user_id     bigint       not null unique,
    constraint FK_user_info_user
        foreign key (user_id) references user (id) on DELETE CASCADE
) engine = InnoDB;

##### values
INSERT INTO user_role (role)
values ('ADMIN'),
       ('TEACHER'),
       ('STUDENT');

INSERT INTO user (email, password, salt, role_id)
VALUES ('admin@admin.com', '5c5212a214c634002e7f970fa293746fb7d49c7cfc7087edecd4c32402c06007',
        'SEZhwD0GPt9W099OZkx2y9kZzJc=', 1),
       ('teacher@mail.ru', '7205baa2f9ff6455de144716f620285eff0bc89b4d0607eb3346272b4dc7ac2c',
        '4kSwPVILWcrRre7xItQSUCBzBcM=', 2),
       ('student@rambler.ru', '4f8cc2bce35fa155e06ac1d48af1e8ecc614f7392a1868f39dd117d6cdf71762',
        'fWOYaqYJw1j1AgNK9nFBfO0esrU=', 3);


INSERT INTO user_info (first_name, middle_name, last_name, phone, user_id)
VALUES ('tom', 'tommy', 'tom_last', '3752911122233', 1);
INSERT INTO user_info (first_name, middle_name, last_name, phone, user_id)
VALUES ('tom', 'tommy', 'tom_last', '3752911122233', 2);
INSERT INTO user_info (first_name, middle_name, last_name, phone, user_id)
VALUES ('jerry', 'jerry', 'jerry_last', '+375-29-444-55-66', 3);



