-- 서브쿼리(SUBQUERY) SQL 문제입니다.
use employees;
-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(emp_no)
from salaries
where salary > (select avg(salary)
				from salaries );
 --  where salary> avg(salary)

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

select a.emp_no, b.first_name, a.salary
  from (select max(salary) as salary , b.dept_no as dept_no, a.emp_no as emp_no
		  from salaries a, dept_emp b
          where a.emp_no = b.emp_no
            and a.to_date = '9999-01-01'
			and b.to_date = '9999-01-01'
          group by dept_no) a , employees b
 where a.emp_no = b.emp_no
 order by salary desc;

-- 문제3. 
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select b.emp_no, b.first_name, a.salary
  from salaries a,employees b, dept_emp c							
 where a.emp_no = b.emp_no
   and b.emp_no = c.emp_no
   and a.to_date = '9999-01-01'
   and c.to_date = '9999-01-01'
   and a.salary > (select avg(salary) as salary from salaries, dept_emp 
							where salaries.emp_no = dept_emp.emp_no 
                              and salaries.to_date = '9999-01-01'
                              and dept_emp.to_date = '9999-01-01'
                              and dept_emp.dept_no = c.dept_no
                            group by dept_emp.dept_no) ;


-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select  a.emp_no, a.first_name, 
		(select first_name from employees where c.emp_no = employees.emp_no ) '매니저 이름',
		(select dept_name from departments where b.dept_no = departments.dept_no ) '부서 이름' -- , (select -- (select first_name from a, dept_manager b where a.emp_no in b.  
 from employees a, dept_emp b, dept_manager c
 
 where a.emp_no = b.emp_no
   and b.dept_no = c.dept_no
   and b.to_date = '9999-01-01'
   and c.to_date = '9999-01-01';


-- 문제5. ***
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select a.emp_no, a.first_name, b.title, c.salary
  from  employees a,  titles b, salaries c, dept_emp d, 
		(SELECT b.dept_no as dept_no, AVG(a.salary) AS avg_salary
												FROM salaries a, dept_emp b
												WHERE  a.emp_no = b.emp_no
												   and b.to_date = '9999-01-01' AND a.to_date = '9999-01-01'
												GROUP BY b.dept_no
                                                order by avg_salary desc
                                                limit 1) e
		
		where a.emp_no = b.emp_no
        and b.emp_no = c.emp_no
        and d.emp_no = a.emp_no
        and d.dept_no = e.dept_no
		and b.to_date = '9999-01-01'
		and c.to_date = '9999-01-01'
        and d.to_date ='9999-01-01'
        ;
-- order by 연봉 desc;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 

select a.dept, max(a.salary)
  from (select avg(a.salary) as salary, b.dept_no as dept_no, c.dept_name as dept
		  from salaries a, dept_emp b, departments c
		where a.emp_no = b.emp_no
          and b.dept_no = c.dept_no
          and a.to_date = '9999-01-01'
          and b.to_date = '9999-01-01'

        group by b.dept_no) a ;

-- 문제7.
-- 평균 연봉이 가장 높은 직책?

select a.title, max(a.salary)
  from (select avg(a.salary) as salary, b.title as title
		  from salaries a, titles b
		where a.emp_no = b.emp_no
          and a.to_date = '9999-01-01'
          and b.to_date = '9999-01-01'
        group by title) a ;
        


-- 문제8. ***
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

select (select dept_name from departments where departments.dept_no = c.dept_no) as '부서이름', 
		b.first_name as '사원 이름' , a.salary ' 연봉', 
        (select first_name from employees where employees.emp_no = d.emp_no) as '매니저 이름',
		(select salary from salaries where salaries.emp_no = d.emp_no and salaries.to_date = '9999-01-01') as '매니저 연봉'
   from salaries a, employees b, dept_emp c, dept_manager d
	
 where a.emp_no = b.emp_no
   and b.emp_no = c.emp_no
   and c.dept_no = d.dept_no
   and a.to_date = '9999-01-01'
   and c.to_date = '9999-01-01'
	and d.to_date = '9999-01-01'

   and a.salary > (select salaries.salary from salaries, dept_manager e
	where salaries.emp_no = e.emp_no 
	  and salaries.to_date = '9999-01-01'
	  and e.to_date = '9999-01-01'
      and e.dept_no = c.dept_no
	  );
