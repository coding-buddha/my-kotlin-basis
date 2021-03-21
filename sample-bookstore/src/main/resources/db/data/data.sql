
## book store
INSERT INTO bookstore.book_store (name, address) VALUES ('이문당서점', '경남 통영시 문화동');
INSERT INTO bookstore.book_store (name, address) VALUES ('가나서점', '경남 통영시 북신동');

## book
INSERT INTO bookstore.book
    (name, author, isbn, publisher, book_store_id)
VALUES ('더 해빙', '이서윤', '43298', '수오서재', 1);

INSERT INTO bookstore.book
    (name, author, isbn, publisher, book_store_id)
VALUES ('돈의 속성', '김승호', '32423', '스노우폭스북스', 1);

INSERT INTO bookstore.book
    (name, author, isbn, publisher, book_store_id)
VALUES ('아몬드', '손원평', '5462', '창비', 1);

INSERT INTO bookstore.book
    (name, author, isbn, publisher, book_store_id)
VALUES ('하버드 상위 1퍼센트의 비밀', '정주영', '1232', '한국경제신문', 2);

INSERT INTO bookstore.book
    (name, author, isbn, publisher, book_store_id)
VALUES ('지적 대화를 위한 넓고 얕은 지식', '채사장', '1234', '웨일북', 2);