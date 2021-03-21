create table bookstore.book_store
(
    id            bigint auto_increment primary key,
    name          varchar(80)                                                     not null,
    address       varchar(255)                                                    not null
);

create table bookstore.book
(
    id            bigint auto_increment primary key,
    name          varchar(80)                                                     not null,
    author        varchar(60)                                                     not null,
    publisher     varchar(50)                                                     not null,
    isbn          varchar(80)                                                     not null,
    rental_status enum ('AVAILABLE', 'NOT_POSSIBLE', 'ORDER_SOON', 'DO_NOT_HOLD') not null DEFAULT 'DO_NOT_HOLD',
    book_store_id bigint                                                          not null
);

create index isbn_idx
    on book (isbn);

alter table bookstore.book
    add foreign key
        (book_store_id) references book_store(id);
