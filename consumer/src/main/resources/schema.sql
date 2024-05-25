create or replace table user(
    id bigint primary key,
    username varchar(256) not null unique,
    created_at date not null default now()
);

create or replace table item(
    id bigint primary key,
    name varchar(256) not null,
    start bigint not null,
    timeout int not null,
    updated_at date not null,
    created_at date not null default now(),
    bidder_id bigint references user(id),
    owner_id bigint references user(id)
);

-- drop table if exists bid;
create or replace table bid(
    id bigint primary key,
    user_id bigint not null references user(id),
    item_id bigint not null references item(id),
    value bigint not null,
    created_at date not null default now()
);