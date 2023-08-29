-- 문제1)
select concat(first_name, ' ', last_name) as full_name from employees
where emp_no = 10944;

-- 2)
select concat(first_name, ' ', last_name) as "이름", gender as "성별", hire_date as "입사일"
from employees
order by  hire_date asc;

-- 3)
-- select
-- (select count(emp_no) from employees where gender = 'f') as female_count,
-- (select count(emp_no) from employees where gender = 'm') as male_count;
-- 남
select count(emp_no) from employees where gender = 'm';
-- 여*
select count(emp_no) from employees where gender = 'f';


-- 4)
select count(emp_no)
from salaries
where to_date = '9999-01-01';

-- 5)
select count(distinct dept_no)
from dept_emp;

-- 6)
select count(emp_no)
from dept_manager;

-- 7)
select dept_name
from departments
order by length(dept_name) desc;

-- 8)
select count(distinct(emp_no))
from salaries
where salary > 120000;

-- 9)
select distinct title
from titles
order by length(title) desc;
 
 -- 10)
select count(emp_no)
from titles
where title = 'Engineer';

-- 11)
select title, from_date, to_date
from titles
where emp_no = 13250
order by from_date asc;