create schema IF NOT EXISTS elearning;

create table user
(
    id       bigint auto_increment primary key,
    created  datetime(6)  null,
    updated  datetime(6)  null,
    email    varchar(255) not null,
    name     varchar(255) not null,
    password varchar(255) not null,
    phone    varchar(255) null,
    role     varchar(25)  not null,
    surname  varchar(255) not null,
    constraint UK_user_email unique (email)
);

create table course
(
    id         bigint auto_increment primary key,
    created    datetime(6)  null,
    updated    datetime(6)  null,
    end        date         null,
    name       varchar(255) not null,
    start      date         null,
    teacher_id bigint       null,
    constraint FK_course_user
        foreign key (teacher_id) references user (id)
);


create table lesson
(
    id          bigint auto_increment primary key,
    created     datetime(6)  null,
    updated     datetime(6)  null,
    date        date         null,
    description varchar(255) null,
    topic       varchar(255) null,
    course_id   bigint       not null,
    constraint FK_lesson_course
        foreign key (course_id) references elearning.course (id)
);

create table courses_students
(
    course_id  bigint not null,
    student_id bigint not null,
    constraint FK_student_course
        foreign key (student_id) references elearning.user (id),
    constraint FK_course_student
        foreign key (course_id) references elearning.course (id)
);

create table home_work
(
    id          bigint auto_increment primary key,
    created     datetime(6)  null,
    updated     datetime(6)  null,
    description varchar(255) null,
    task        varchar(255) null
);
