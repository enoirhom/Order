create table item (
    item_id uuid primary key,
    name varchar(25) not null,
    description text not null,
    stock_quantity int not null,
    price numeric not null
);
