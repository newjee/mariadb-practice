package bookmall.dao;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
//    insertOrder() {
//        return ord_no
//    }
//    insertOrderBooks() {
//        ord_no;
//        book_no;
//    }

    public List<OrderVo> findByUserNo(Long userNo) {

        List<OrderVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "select a.ord_no, CONCAT(b.mbr_name, '/', b.mbr_email), a.ord_price, a.ord_address " +
                            "from orders a, member b " +
                            "where a.member_mbr_no = b.mbr_no " +
                            "and a.member_mbr_no = ?";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setLong(1, userNo);
//            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while (rs.next()) {

                String ordNo = rs.getString(1);
                String ordName = rs.getString(2);
                Long ordPrice = rs.getLong(3);
                String ordAddress = rs.getString(4);

                OrderVo vo = new OrderVo();
                vo.setOrdNo(ordNo);
                vo.setOrdName(ordName);
                vo.setOrdPrice(ordPrice);
                vo.setOrdAddress(ordAddress);

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

    public List<String> insertOrderByUserNo(Long userNo) {

        List<String> ordNoList = new ArrayList<>();
        // 생성된 ord_no를 저장할 리스트

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;

        try {
            conn = getConnection();


            String sql1 =
                    "INSERT INTO orders (ord_no, ord_price, ord_address, member_mbr_no, ord_count) " +
                            "VALUES (CONCAT(DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'), LPAD(FLOOR(RAND() * 100), 2, '0')) " +
                            ", null, '경기도 성남시', ?, null)";

            //3. statement
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setLong(1, userNo);
            pstmt1.executeUpdate();

            //ord_no
            String sql2 = "select ord_no from orders where member_mbr_no = ?";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setLong(1, userNo);

            rs = pstmt2.executeQuery();

            while (rs.next()) {
                String ordNo = rs.getString(1);
                ordNoList.add(ordNo);

            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {

                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e) {
                System.out.println("error" + e);
            }
        }
        return ordNoList;
    }




    public List<OrderBookVo> findOrderBook(List<String> ordNoList) {

        List<OrderBookVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "select b.bk_no, b.bk_title, a.obk_count " +
                            "from orders_book a, book b " +
                            "where a.book_bk_no = b.bk_no " +
                            "and a.orders_ord_no = ?";
            pstmt = conn.prepareStatement(sql);

            //4. binding

//            pstmt.setString(2, "%" + keyword + "%");
            for (String ordNo : ordNoList) {
                pstmt.setString(1, ordNo);

                //5. SQL 실행
                rs = pstmt.executeQuery();

                while (rs.next()) {

                    Long ordBookNo = rs.getLong(1);
                    String ordBookName = rs.getString(2);
                    Long ordBookCount = rs.getLong(3);

                    OrderBookVo vo = new OrderBookVo();
                    vo.setOrdBookNo(ordBookNo);
                    vo.setOrdBookName(ordBookName);
                    vo.setOrdBookCount(ordBookCount);

                    result.add(vo);

                }

            }

            //6. 결과 처리


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


    public void insertOrderBook(List<String> ordNoList, Long userNo) {

//        boolean result = false;
        Connection conn = null;

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = getConnection();

            String sql1 =
                    "INSERT INTO orders_book (book_bk_no, orders_ord_no, obk_no, obk_count) " +
                            "VALUES (1, ?, 1, 1)";

            String sql2 =
                    "INSERT INTO orders_book (book_bk_no, orders_ord_no, obk_no, obk_count) " +
                            "VALUES (2, ?, 2, 2)";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);

            //4. 값 바인딩
            for (String ordNo : ordNoList) {
                pstmt1.setString(1, ordNo);
                pstmt1.executeUpdate();

                pstmt2.setString(1, ordNo);
                pstmt2.executeUpdate();


            }

            //5. 실행

            //update
            UpdateOrder(userNo);


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

    private void UpdateOrder(Long userNo) {

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        Long orderPrice = 0L;
        try {
            conn = getConnection();

            // price
            String sql1 = "select a.book_bk_no, a.obk_count from orders_book a, orders b " +
                    " where a.orders_ord_no = b.ord_no " +
                    " and b.member_mbr_no = ?";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setLong(1, userNo);
            rs1 = pstmt1.executeQuery();

            List<Long> bookNoList = new ArrayList<>();
            List<Long> orderBookCountList = new ArrayList<>();

            while (rs1.next()) {
                Long bookNo = rs1.getLong("book_bk_no");
                Long orderBookCount = rs1.getLong("obk_count");
                // 결과 값을 리스트에 저장
                bookNoList.add(bookNo);
                orderBookCountList.add(orderBookCount);
            }
            //price

            String sql2 = "select bk_price from book where bk_no = ?";
            // total price 초기화
            long totalPrice = 0;

            pstmt2 = conn.prepareStatement(sql2);
            for (int i = 0; i < bookNoList.size(); i++) {
                long bookNo = bookNoList.get(i);
                pstmt2.setLong(1, bookNo);
                rs2 = pstmt2.executeQuery();

                if (rs2.next()) {
                    long bookPrice = rs2.getLong("bk_price");
                    long orderBookCount = orderBookCountList.get(i);

                    // 각 주문의 가격을 합산
                    totalPrice += (bookPrice * orderBookCount);
                }
            }


            String sql3=
                    "update orders " +
                            "set ord_price = ?, ord_count = ? " +
                            "where member_mbr_no = ?";

            pstmt3 = conn.prepareStatement(sql3);
            pstmt3.setLong(1, totalPrice); // totalPrice 값을 설정
            pstmt3.setLong(2, orderBookCountList.stream().mapToLong(Long::longValue).sum()); // orderBookCountList 합계 설정
            pstmt3.setLong(3, userNo);

            int updatedRows = pstmt3.executeUpdate();



        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {

                if (rs1 != null) {
                    rs1.close();
                }
                if (rs2 != null) {
                    rs2.close();
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
