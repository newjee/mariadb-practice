package hr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public List<EmployeesVo> findByName(String keyword) {
        List<EmployeesVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1. JDBC Driver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결하기
            String url = "jdbc:mariadb://localhost:3306/employees?charset=utf8";
            conn = DriverManager.getConnection(url, "hr", "hr");

            //3. SQL 준비
            String sql =
                    "SELECT emp_no, first_name, last_name" +
                            " FROM employees" +
                            " WHERE first_name LIKE ?" +
                            " AND last_name LIKE ?";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while (rs.next()) {
                Long empNo = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);

                EmployeesVo vo = new EmployeesVo();
                vo.setEmpNo(empNo);
                vo.setFirstName(firstName);
                vo.setLastName(lastName);

                result.add(vo);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 6. 자원정리
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<EmployeesVo> findBySalary(int minSalary, int maxSalary) {
        List<EmployeesVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1. JDBC Driver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결하기
            String url = "jdbc:mariadb://localhost:3306/employees?charset=utf8";
            conn = DriverManager.getConnection(url, "hr", "hr");

            //3. SQL 준비
            String sql =
                    "SELECT a.first_name, b.salary" +
                            " FROM employees a, salaries b" +
                            " WHERE a.emp_no = b.emp_no" +
                            " AND b.to_date = '9999-01-01'" +
                            " AND b.salary <= ?" +
                            " AND b.salary >= ?" +
                            " ORDER BY b.salary desc, a.first_name asc";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setString(1, String.valueOf(maxSalary));
            pstmt.setString(2, String.valueOf(minSalary));

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while (rs.next()) {
                String firstName = rs.getString(1);
                int salary = rs.getInt(2);

                EmployeesVo vo = new EmployeesVo();
                vo.setFirstName(firstName);
                vo.setSalary(salary);

                result.add(vo);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (
                SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 6. 자원정리
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
