create table if not exists course_t
(
    id   bigint auto_increment primary key,
    name varchar(255) not null
)
    engine = InnoDB;

create table if not exists group_t
(
    id         bigint auto_increment primary key,
    name       varchar(255) not null,
    start_date date         null,
    course_id  bigint       not null
)
    engine = InnoDB;

create index group_course_index
    on group_t (course_id);

create table if not exists role_t
(
    id        bigint auto_increment primary key,
    role_name varchar(50) not null,
    constraint role_role_name_unique_constraint
        unique (role_name)
)
    engine = InnoDB;



create table if not exists student_t
(
    id          bigint auto_increment primary key,
    email       varchar(255) not null,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255) null,
    phone       varchar(255) not null,
    grade       float        null,
    user_id     bigint       not null,
    group_id    bigint       null,
    constraint student_user_id_unique_constraint
        unique (user_id)
)
    engine = InnoDB;

create table if not exists teacher_group
(
    teacher_id bigint not null,
    group_id   bigint not null,
    primary key (teacher_id, group_id)
)
    engine = InnoDB;

create table if not exists teacher_t
(
    id          bigint auto_increment primary key,
    email       varchar(255) not null,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255) null,
    phone       varchar(255) not null,
    work        varchar(255) null,
    user_id     bigint       not null,
    constraint teacher_user_id_unique_constraint
        unique (user_id)
)
    engine = InnoDB;

create table if not exists user_t
(
    id       bigint auto_increment primary key,
    login    varchar(255) not null,
    password varchar(255) not null,
    salt     varchar(255) null,
    role_id  bigint       not null
)
    engine = InnoDB;
