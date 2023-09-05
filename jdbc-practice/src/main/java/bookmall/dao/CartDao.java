package bookmall.dao;

import bookmall.vo.CartVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    //    FindByUserNo
    public List<CartVo> findByUserNo(Long userNo) {

        List<CartVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "select b.bk_title, a.ct_count, b.bk_price " +
                            "from cart a, book b " +
                            "where a.book_bk_no = b.bk_no " +
                            "and member_mbr_no = ?";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setLong(1, userNo);
//            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while (rs.next()) {

                String cartTitle = rs.getString(1);
                Long cartCount = rs.getLong(2);
                Long cartPrice = rs.getLong(3);

                CartVo vo = new CartVo();
                vo.setCartTitle(cartTitle);
                vo.setCartCount(cartCount);
                vo.setCartPrice(cartPrice);

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

    public void insertCartByUserNo(Long UserNo) {

//        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            conn = getConnection();


            String sql1 =
                    "INSERT INTO cart (ct_no, ct_count, book_bk_no, member_mbr_no) " +
                            "VALUES (1, 3, " +
                            "(SELECT bk_no FROM book WHERE bk_title = '뉴문'),? )";

            String sql2 =
                    "INSERT INTO cart (ct_no, ct_count, book_bk_no, member_mbr_no) " +
                            "VALUES (2, 2, " +
                            "(SELECT bk_no FROM book WHERE bk_title = '트와일라잇'), ?)";
            //3. statement
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);



            //4. 값 바인딩
            pstmt1.setLong(1, UserNo);
            pstmt2.setLong(1, UserNo);

            //5. 실행
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();


        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {

                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e) {
                System.out.println("error" + e);
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
