-- 테이블간 조인(JOIN) SQL 문제입니다.
-- select *
-- from employees;

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select b.emp_no, b.first_name, a.salary
  from salaries a, employees b
 where a.emp_no = b.emp_no
   and a.to_date = '9999-01-01';
 
-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select b.emp_no, concat(b.first_name, ' ', b.last_name) as full_name, a.title
  from titles a, employees b
 where a.emp_no = b.emp_no
 order by full_name;


-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..

select b.emp_no, concat(b.first_name, ' ', b.last_name) as full_name, c. dept_name
  from dept_emp a, employees b, departments c
 where a.emp_no = b.emp_no
 and a.dept_no = c.dept_no
 order by full_name;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.

select b.emp_no, concat(b.first_name, ' ', b.last_name) as full_name, d.salary,e.title, c.dept_name
  from dept_emp a, employees b, departments c , salaries d, titles e
 where a.emp_no = b.emp_no
 and a.dept_no = c.dept_no
 and a.emp_no = d.emp_no
 and e.emp_no = a.emp_no
 order by full_name;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.
select b.emp_no, concat(b.first_name, ' ', b.last_name) as full_name
  from titles a, employees b
 where a.emp_no = b.emp_no  
   and a.title = 'Technique Leader'
   and a.to_date != '9999-01-01';
   

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.

select concat(a.first_name, ' ', a.last_name) '이름'
		,(select dept_name from departments c where c.dept_no = b.dept_no) '부서명'
        ,(select title from titles d where d.emp_no = a.emp_no and d.to_date = '9999-01-01') '직책'
  from employees a, dept_emp b
 where a.emp_no = b.emp_no
   and a.last_name like 'S%';
   
 
-- 문제7. 이름, 급여 출력, 
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select  c.first_name, b.salary
  from titles a , salaries b, employees c
 where a.emp_no = b.emp_no
   and c.emp_no = b.emp_no
   and b.salary >= 40000
   and a.to_date = '9999-01-01'
   and a.title = 'Engineer'
order by b.salary desc ;

-- 이상한 문제
-- 문제8. 
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오

-- select  *
--   from titles a , salaries b
--  where a.emp_no = b.emp_no
--    and b.salary >= 50000
-- order by b.salary ;


-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.

select a.dept_no, avg(b.salary)
  from dept_emp a , salaries b
 where a.emp_no = b.emp_no
   and a.to_date = '9999-01-01'
   and b.to_date = '9999-01-01'
   
group by a.dept_no
order by avg(b.salary) desc;

-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.

select a.title, avg(b.salary)
  from titles a , salaries b
 where a.emp_no = b.emp_no
   and a.to_date = '9999-01-01'
   and b.to_date = '9999-01-01'
   
group by a.title
order by avg(b.salary) desc;

