create table dog (
    id serial primary key,
    name varchar(255) not null,
    owner varchar(255),
    description text
);

