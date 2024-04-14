drop table if exists user;
create table if not exists user(
    id bigint auto_increment primary key,
    username varchar(256) not null unique
);

drop table if exists item;
create table if not exists item(
    id bigint auto_increment primary key,
    name varchar(256) not null,
    start bigint not null,
    timeout int not null,
    updated_at date not null,
    bidder_id bigint references user(id)
);

drop table if exists bid;
create table if not exists bid(
    id bigint auto_increment primary key,
    user_id bigint not null references user(id),
    item_id bigint not null references item(id),
    value bigint not null,
    created_at date not null
);