-- limit 0,4

select first_name, gender, hire_date
from employees
where hire_date <'1990-01-01'
and gender = 'F' ;


-- 3) in 연산자
select emp_no, dept_no
from dept_emp
where dept_no in ('d005','d009');

select first_name, hire_date
from employees
where hire_date like '1989-%%-%%';


select first_name, hire_date
from employees
where hire_date between '1989-01-01' and '1989-12-31';

-- order by

select emp_no, salary, from_date, to_date
from salaries
where to_date like '2001%'
	or from_date like '2001%'
order by salary desc;

select emp_no, salary 
from salaries
order by emp_no asc , salary desc;


-- upper
select upper('d'), ucase('d') from dual;

select substring("heel",3,2) from dual;

select *
from employees
where substring(hire_date, 1, 4) = '1989';

select lpad('1234', 10 ,'-') from dual;

select rpad(salary, 10, ' ') 
from salaries;

select concat('---', ltrim(' hhheelo.  '),'---')
from dual;


select abs(-1) 
;
select floor(3.14)
;
select mod(10,3) ;
select round(1.498) , round(1.498,1);

select *
from employees;
-- 근무기간
select first_name, hire_date,date_format(hire_date, "%y-%m"),
		abs(period_diff(date_format(hire_date, "%y%m"), date_format(now(), '%y%m'))) as month
from employees;

-- 근속년수 5년 휴가 
select first_name, hire_date,
		date_add(hire_date, interval 5 year)
from employees;


select avg(salary)
from salaries
where emp_no = 10060;

select emp_no ,avg(salary), sum(salary)
  from salaries
group by emp_no
  having emp_no  = 10060;
  
  
  select a.emp_no
  from employees a , titles b
  
  
  where a.emp_no = b.emp_no
  and b.to_date = '9999-01-01'