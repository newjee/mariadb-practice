package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {

    public static void main(String[] args) {
        DeptVo vo = new DeptVo();
        vo.setNo(2L);
        vo.setName("기획팀");
//        boolean result = UpdateDepartment(2L, "기획팀");
        boolean result = UpdateDepartment(vo);
        System.out.println(result ? "성공" : "실패");
    }

    private static boolean UpdateDepartment(DeptVo vo) {
        boolean result = false;
        Connection connection = null;
        Statement stmt = null;
        try {
            //1. jdbc MyDriver Class 로딩
            Class.forName("org.mariadb.jdbc.MyDriver");



            //2. 연결
            String url = "jdbc:mariadb://localhost:3306/shopping_db?charset=utf8";
            connection = DriverManager.getConnection(url, "shopping", "shopping");

            System.out.println("연결 성공!");

            //3. statement
            stmt = connection.createStatement();

            //4. sql 실행
            String sql =
                    " update member" +
                            "   set member_name'" +vo.getName() +"'" +
                            " where no =" + vo.getNo();


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
