create table book
(
    id            bigint auto_increment primary key,
    name          varchar(80)                                                     not null,
    author        varchar(60)                                                     not null,
    publisher     varchar(50)                                                     not null,
    isbn          varchar(80)                                                     not null,
    rental_status enum ('AVAILABLE', 'NOT_POSSIBLE', 'ORDER_SOON', 'DO_NOT_HOLD') not null DEFAULT 'DO_NOT_HOLD'
);

create index isbn_idx
    on book (isbn);

