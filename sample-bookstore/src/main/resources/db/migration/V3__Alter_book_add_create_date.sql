-- mysql v5.6.5 이상인 경우에는 CURRENT_TIMESTAMP 를 사용하여 값을 세팅해준다.
ALTER TABLE book ADD COLUMN created_at DATETIME not null DEFAULT CURRENT_TIMESTAMP AFTER rental_status;