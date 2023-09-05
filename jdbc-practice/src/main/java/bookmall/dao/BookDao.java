package bookmall.dao;

import bookmall.vo.BookVo;
import emaillist.vo.EmailListVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {


    public List<BookVo> findAll() {

        List<BookVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "select bk_title, bk_price " +
                            "from book";
            pstmt = conn.prepareStatement(sql);

            //4. binding
//            pstmt.setString(1, "%" + keyword + "%");
//            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while(rs.next()) {

                String bookTitle = rs.getString(1);
                Long bookPrice = rs.getLong(2);

                BookVo vo = new BookVo();
                vo.setBookTitle(bookTitle);
                vo.setPrice(bookPrice);

                result.add(vo);

            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 6. 자원정리
                if(rs != null) {
                    rs.close();
                }
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
        return result;
    }

    public void insertBook() {

        List<BookVo> result = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;


        try {
            conn = getConnection();

            // 3. Statement 생성
            stmt = conn.createStatement();

            // 4. 여러 개의 SQL 문을 배치로 추가
            stmt.addBatch("INSERT INTO book(bk_no, bk_title, bk_price, category_ctg_no) VALUES(1, '트와일라잇', 20000, 1)");
            stmt.addBatch("INSERT INTO book(bk_no, bk_title, bk_price, category_ctg_no) VALUES(2, '뉴문', 12000, 2)");
            stmt.addBatch("INSERT INTO book(bk_no, bk_title, bk_price, category_ctg_no) VALUES(3, '이클립스', 15000, 1)");

            // 5. 배치를 실행
            int[] results = stmt.executeBatch();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 6. 자원정리
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private Connection getConnection() throws SQLException {

        Connection conn = null;

        //1. JDBC Driver Class 로딩
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.0.148:3306/bookmall?charset=utf8";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }
        return conn;

    }
}
