package driver;

import java.sql.*;

public class MyDriverTest {

    public static void main(String[] args) {

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1. jdbc MyDriver Class 로딩
            Class.forName("driver.MyDriver");



//            2. 연결
            String url = "jdbc:mydb://localhost:3306/mydb";
            connection = DriverManager.getConnection(url, "hr", "hr");

            System.out.println("연결 성공! - " + connection);

//            //3. statement
//            stmt = connection.createStatement();
//
//            //4. sql 실행
//            String sql =
//                    "select emp_no, first_name, last_name" +
//                            "  from employees" +
//                            " where first_name like '%" + keyword + "%'" +
//                            "   and last_name like '%" + keyword + "%'";
//
//            rs = stmt.executeQuery(sql);
//
//            //5. 결과
//            while (rs.next()) {
//                Long empNo = rs.getLong(1);
//                String firstName = rs.getString(2);
//
//                String lastName = rs.getString(3);
//                System.out.println(empNo + ":" + firstName + ":" + lastName);
//            }


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        }
        catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            }catch (SQLException e) {
//                System.out.println("error" + e);
//            }
        }

    }
}
