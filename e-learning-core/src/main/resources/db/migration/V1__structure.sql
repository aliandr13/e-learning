create schema IF NOT EXISTS elearning;

create table user
(
    id       bigint auto_increment primary key,
    email    varchar(255) not null,
    name     varchar(255) not null,
    surname  varchar(255) not null,
    role     varchar(25)  not null,
    password varchar(255) not null,
    phone    varchar(255) null,
    created  datetime(6)  null,
    updated  datetime(6)  null,
    constraint UK_user_email unique (email)
);

create table course
(
    id         bigint auto_increment primary key,
    end        date         null,
    name       varchar(255) not null,
    start      date         null,
    teacher_id bigint       null,
    created    datetime(6)  null,
    updated    datetime(6)  null,
    constraint FK_course_user
        foreign key (teacher_id) references user (id) on DELETE SET NULL
);

create table lesson
(
    id          bigint auto_increment primary key,
    date        date         null,
    topic       varchar(255) null,
    task        varchar(255) null,
    description varchar(255) null,
    course_id   bigint       not null,
    created     datetime(6)  null,
    updated     datetime(6)  null,
    constraint FK_lesson_course
        foreign key (course_id) references course (id) ON DELETE CASCADE,
    constraint date_unique unique (date, course_id)
);

create table courses_students
(
    course_id  bigint not null,
    student_id bigint not null,
    constraint FK_student_course
        foreign key (student_id) references user (id) ON DELETE CASCADE,
    constraint FK_course_student
        foreign key (course_id) references course (id) ON DELETE CASCADE
);

create table student_work
(
    id         bigint auto_increment primary key,
    absent     bit         null,
    mark       int(2)      null,
    lesson_id  bigint      not null,
    student_id bigint      not null,
    created    datetime(6) null,
    updated    datetime(6) null,
    constraint FK_lesson_student_work
        foreign key (lesson_id) references lesson (id) ON DELETE CASCADE,
    constraint FK_student_student_work
        foreign key (student_id) references user (id) ON DELETE CASCADE
);

