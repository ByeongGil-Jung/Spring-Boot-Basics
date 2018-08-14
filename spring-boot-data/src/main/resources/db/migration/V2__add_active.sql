-- Flyway 관리 후, schema 의 변경이 생겼을 때 이렇게 새로운 version 의 sql 파일을 만든다.
-- ( 절대 ! 기존 sql 파일을 수정해선 안된다 ! )
ALTER TABLE account ADD COLUMN active BOOLEAN;