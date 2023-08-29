select version(), current_date, now() from dual;
select sin(pi()/4), 1+5 from dual;
select version();

create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
    
    );
    
-- schema 확인
show tables;
describe pet;
desc pet;

-- table 삭제 : DDL
drop table pet;

show tables;

-- insert : DML
insert into pet values(
	'둘리','세지','dragon','m','1990-10-10',null);
    
select * from pet;

-- update : DML
update pet set name = '또치' where name = '둘리';

select * from pet;

-- delete : DML
delete from pet where name ='또치';

select * from pet;

load data local infile '/Users/nabi/Downloads/pet.txt' into table pet;

