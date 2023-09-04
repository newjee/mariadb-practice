package test;

import java.sql.*;

public class InsertTest01 {

    public static void main(String[] args) {

        boolean result = insertDepartment("기획1팀");
        System.out.println(result ? "성공" : "실패");
    }

    private static boolean insertDepartment(String name) {
        boolean result = false;
        Connection connection = null;
        Statement stmt = null;
        try {
            //1. jdbc MyDriver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");



            //2. 연결
            String url = "jdbc:mariadb://localhost:3306/shopping_db?charset=utf8";
            connection = DriverManager.getConnection(url, "shopping", "shopping");

            System.out.println("연결 성공!");

            //3. statement
            stmt = connection.createStatement();

            //4. sql 실행
            String sql =
                    "insert" +
                    "   into member_name" +
                    "value ( null, '" + name + "')";


            int count = stmt.executeUpdate(sql);

            //5. 결과
            result = count == 1;


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                System.out.println("error" + e);
            }
        }
        return result;
    }
}
