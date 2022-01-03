create table customer (
    customer_id uuid primary key,
    firstname varchar(25) not null,
    lastname varchar(25) not null,
    email varchar(25) not null,
    street varchar(25) not null,
    house_number varchar(10) not null,
    city varchar(25) not null,
    zip_code integer not null,
    phone_number varchar(15) not null
);
