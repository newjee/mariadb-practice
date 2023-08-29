1. dba(root) 작업
   - mysql -u root -p
2. db 생성
   - create database webdb;
   - show databases;
3. 사용자 생성
   - create user 'webdb'@'localhost' identified by 'webdb';
4. 권한
   - grant all privilege on webdb.* to 'webdb'@'localhost';
    - flush privileges;
5. check
   - mysql -u webdb -D webdb -p;
- 