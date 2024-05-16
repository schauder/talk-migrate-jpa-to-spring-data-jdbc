create table category
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table customer
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);

create table item
(
    id          bigint not null,
    index       integer not null,
    quantity    integer,
    product_id  bigint,
    shipment_id bigint,
    primary key (id)
);

create table product
(
    id          bigint not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

create table product_categories
(
    products_id   bigint not null,
    categories_id bigint not null
);
create table shipment
(
    id          bigint not null,
    customer_id bigint,
    primary key (id)
);

create sequence category_seq start with 1 increment by 50;
create sequence customer_seq start with 1 increment by 50;
create sequence item_seq start with 1 increment by 50;
create sequence product_seq start with 1 increment by 50;
create sequence shipment_seq start with 1 increment by 50;

alter table if exists item add constraint FK_item_product foreign key (product_id) references product;
alter table if exists item add constraint FK_item_shipment foreign key (shipment_id) references shipment;
alter table if exists product_categories add constraint FK_product_categories_category foreign key (categories_id) references category;
alter table if exists product_categories add constraint FK_product_categories_product foreign key (products_id) references product;
alter table if exists shipment add constraint FK_shipment_customer foreign key (customer_id) references customer;
