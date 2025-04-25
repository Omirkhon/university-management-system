create table if not exists users (
    id serial primary key,
    name varchar(50) not null,
    password varchar(100) not null,
    role varchar(10) not null
);

create table if not exists courses (
    id serial primary key,
    title varchar(50) not null,
    description varchar(200) not null
);

create table if not exists enrollments (
    id serial primary key,
    description varchar(100) not null,
    created timestamp without time zone not null,
    user_id int references users (id) not null,
    course_id int references courses (id) not null
);