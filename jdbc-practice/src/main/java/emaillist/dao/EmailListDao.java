package emaillist.dao;

import emaillist.vo.EmailListVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailListDao {

    public List<EmailListVo> findAll() {

        List<EmailListVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "select no, first_name, last_name, email " +
                            "from emaillist " +
                            "order by no";
            pstmt = conn.prepareStatement(sql);

            //4. binding
//            pstmt.setString(1, "%" + keyword + "%");
//            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while(rs.next()) {

                Long no = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String email = rs.getString(4);

                EmailListVo vo = new EmailListVo();
                vo.setNo(no);
                vo.setFirstName(firstName);
                vo.setLastName(lastName);
                vo.setEmail(email);

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

    public void insert(EmailListVo vo) {

        List<EmailListVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            //3. SQL 준비
            String sql =
                    "insert into emaillist values(null, ?,?,?)";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setString(1, vo.getFirstName());
            pstmt.setString(2, vo.getLastName());
            pstmt.setString(3, vo.getEmail());

            //5. SQL 실행
            rs = pstmt.executeQuery();

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

    }

    public void deleteByEmail(String email) {

        List<EmailListVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            //3. SQL 준비
            String sql =
                    "delete from emaillist " +
                            "where email = ?";
            pstmt = conn.prepareStatement(sql);

            //4. binding
            pstmt.setString(1, email);

            //5. SQL 실행
            rs = pstmt.executeQuery();

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

    }

    private Connection getConnection() throws SQLException {

        Connection conn = null;

        //1. JDBC Driver Class 로딩
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/shopping_db?charset=utf8";
            conn = DriverManager.getConnection(url, "shopping", "shopping");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }
        return conn;

    }
}
