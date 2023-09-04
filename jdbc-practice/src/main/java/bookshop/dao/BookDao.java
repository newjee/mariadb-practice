package bookshop.dao;

import hr.dao.EmployeesVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public List<BookVo> findAll() {
        List<BookVo> result = new ArrayList<>();

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1. jdbc MyDriver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결
            String url = "jdbc:mariadb://localhost:3306/shopping_db?charset=utf8";
            connection = DriverManager.getConnection(url, "shopping", "shopping");

//            System.out.println("연결 성공!");

            //3. statement
            stmt = connection.createStatement();

            //4. sql 실행
            String sql =
                    "select *"
                    + "from book";

            rs = stmt.executeQuery(sql);

            //5. 결과
            while (rs.next()) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);

                String rent = rs.getString(3);
                int authorNo = rs.getInt(4);
                System.out.println(no + "        "
                        + title + " " + rent + " "+authorNo);

            }


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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



    public void updateRent(BookVo vo) {
        if (vo.equals('Y')) {
            System.out.println("대여 중인 책입니다.");
            return;
        }
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1. JDBC Driver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결하기
            String url = "jdbc:mariadb://localhost:3306/shopping_db?charset=utf8";
            conn = DriverManager.getConnection(url, "shopping", "shopping");


            //3. SQL 준비
            String sql =
                    " update book" +
                            "    set rent=?" +
                            "  where no=?";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setString(1, vo.getRent());
            pstmt.setLong(2, vo.getNo());

            //5. SQL 실행
            int count = pstmt.executeUpdate();

            //6. 결과 처리
            result = count == 1;
            System.out.println("책이 대여되었습니다.");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 7. 자원정리
                if(pstmt != null) {
                    pstmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        return result;
    }


}
