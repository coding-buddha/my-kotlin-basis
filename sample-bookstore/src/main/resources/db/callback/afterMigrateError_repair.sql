-- flyway migrate error 발생 시, 롤백되지 않도록 실패된 항목을 자동으로 삭제하도록 한다.
-- 해당 파일의 경로는 application-default.yml 에서 flyway locations 항목에 추가된다.
DELETE FROM flyway_schema_history WHERE success=false;