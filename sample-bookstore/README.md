# sample-bookstore

## kotlin x jpa
* 디폴트 생성자를 만들어주기 위해선 plugins 이 필요하다.

## [flyway](https://flywaydb.org/documentation/)
* `org.flywaydb.flyway` plugins 추가
    * `db` 와 통신할 수 있도록 flyway `task` 작성    
* `resourcs/db/migration` 디렉토리 내에 sql 파일을 넣어야 한다.
    * `V1__{file-name}.sql` 형태로 파일을 만듦

### gradle command : flywayMigrate
`./gradlew :sample-bookstore:flywayMigrate -i` 실행
```
> Task :sample-bookstore:flywayMigrate
Caching disabled for task ':sample-bookstore:flywayMigrate' because:
  Build cache is disabled
Task ':sample-bookstore:flywayMigrate' is not up-to-date because:
  Task has not declared any outputs despite executing actions.
Resolving global dependency management for project 'sample-bookstore'
Excluding [com.google.protobuf:protobuf-java]
Excluding []
Flyway Community Edition 7.6.0 by Redgate
Database: jdbc:mysql://localhost:13309/bookstore (MySQL 8.0)
Successfully validated 2 migrations (execution time 00:00.017s)
Creating Schema History table `bookstore`.`flyway_schema_history` ...
Current version of schema `bookstore`: << Empty Schema >>
Migrating schema `bookstore` to version "1 - Create book table"
Migrating schema `bookstore` to version "2 - Add book"
Successfully applied 2 migrations to schema `bookstore` (execution time 00:00.165s)
:sample-bookstore:flywayMigrate (Thread[Execution worker for ':' Thread 2,5,main]) completed. Took 1.801 secs.
```
### gradle task : flywayMigrate
* https://flywaydb.org/documentation/usage/gradle/migrate
* 스키마를 최신버전으로 마이그레이션 수행한다.
* flyway 는 스키마 히스토리 테이블이 존재하지 않으면 새롭게 생성한다.

### gradle task : flywayClean (프로덕션 확경에서 절대 금지)
* https://flywaydb.org/documentation/usage/gradle/clean
* 모든 오브젝트를 드랍시킨다. (테이블, 뷰, 프로시저, 트리거 등등)

### gradle task : flywayValidate
* https://flywaydb.org/documentation/usage/gradle/validate
* 적용된 마이그레이션에 대해서 유효성을 검사하며, 스키마 생성에 방해하는 요소들에 대한 변경을 감지한다.
  * 마이그레이션 이름/유형/체크섬의 차이가 발견됨
  * 로컬에서 해결되지 않은 버전이 적용됨
* 만약 `valiate` 수행 시에, 문제가 발생되면 아래와 같은 문구를 만남
```
Validate failed: Migrations have failed validation
Migration checksum mismatch for migration version 3
-> Applied to database : -418923588
-> Resolved locally    : -1502844751. Either revert the changes to the migration, or run repair to update the schema history.
```

### gradle task : flywayRepair
* https://flywaydb.org/documentation/usage/gradle/repair
* flyway schema history table 을 `repair` 한다.
  * DDL 트랜잭션이 지원되지 않는 데이터베이스에서 실패한 마이그레이션을 제거
  * 적용된 체크섬을 사용 가능한 마이그레이션 체크섬으로 재정렬
  
### gradle task : info
* https://flywaydb.org/documentation/usage/gradle/info
* flyway_schema_history 의 내용을 출력한다.

