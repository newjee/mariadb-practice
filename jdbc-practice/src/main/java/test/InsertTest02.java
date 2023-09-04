package test;

import java.sql.*;

public class InsertTest02 {

    public static void main(String[] args) {

        boolean result = insertDepartment("둘리");
        System.out.println(result ? "성공" : "실패");
    }

    private static boolean insertDepartment(String name) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            //1. jdbc MyDriver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");



            //2. 연결
            String url = "jdbc:mariadb://localhost:3306/shopping_db?charset=utf8";
            connection = DriverManager.getConnection(url, "shopping", "shopping");

            System.out.println("연결 성공!");


            //4. sql 준비
            String sql =
                    "INSERT INTO member (member_id, member_name, member_addr) " +
                            "   VALUES ('가나', ?, '또치')";

            //3. statement
            pstmt = connection.prepareStatement(sql);


            //4. 값 바인딩
            pstmt.setString(1, name);

            //5. 실행
            int count = pstmt.executeUpdate();
            //6. 결과
            result = count == 1;


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {

                if (pstmt != null) {
                    pstmt.close();
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
