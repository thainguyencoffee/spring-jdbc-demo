create table user_application (
    id uuid default gen_random_uuid() not null ,
    username varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    city varchar(255) not null,
    street varchar(255) not null,
    email varchar(255) not null
);