package bookmall.dao;

import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {


    public List<CategoryVo> findAll() {

        List<CategoryVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "select ctg_no, ctg_name " +
                            "from category";
            pstmt = conn.prepareStatement(sql);

            //4. binding
//            pstmt.setString(1, "%" + keyword + "%");
//            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while (rs.next()) {

                Long ctgNo = rs.getLong(1);
                String ctgName = rs.getString(2);

                CategoryVo vo = new CategoryVo();
                vo.setCtgNo(ctgNo);
                vo.setCtgName(ctgName);

                result.add(vo);

            }

        } catch (SQLException e) {
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

    public void insertCategory() {

        List<CategoryVo> result = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;


        try {
            conn = getConnection();

            // 3. Statement 생성
            stmt = conn.createStatement();

            // 4. 여러 개의 SQL 문을 배치로 추가
            stmt.addBatch("insert into category values(1, '소설')");
            stmt.addBatch("insert into category values(2, '수필')");
            stmt.addBatch("insert into category values(3, '예술')");

            // 5. 배치를 실행
            int[] results = stmt.executeBatch();

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                // 6. 자원정리
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
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
