package bookmall.dao;

import bookmall.vo.BookVo;
import bookmall.vo.MemberVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public List<MemberVo> findAll() {

        List<MemberVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            //3. SQL 준비
            String sql =
                    "SELECT mbr_name, mbr_phone, mbr_email, mbr_pwd " +
                                                     "FROM member";
            pstmt = conn.prepareStatement(sql);

            //4. binding
//            pstmt.setString(1, "%" + keyword + "%");
//            pstmt.setString(2, "%" + keyword + "%");

            //5. SQL 실행
            rs = pstmt.executeQuery();

            //6. 결과 처리
            while(rs.next()) {

                String mbrName = rs.getString(1);
                String mbrPhone = rs.getString(2);
                String mbrEmail = rs.getString(3);
                String mbrPwd = rs.getString(4);

                MemberVo vo = new MemberVo();
                vo.setMbrName(mbrName);
                vo.setMbrPhone(mbrPhone);
                vo.setMbrEmail(mbrEmail);
                vo.setMbrPwd(mbrPwd);

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

    public void insertMember() {

        List<MemberVo> result = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;


        try {
            conn = getConnection();

            // 3. Statement 생성
            stmt = conn.createStatement();

            // 4. 여러 개의 SQL 문을 배치로 추가
            stmt.addBatch("insert into member values(1, '세균맨', '010-1234-5678', 'abc1234', 'segeun@gmail.com')");
            stmt.addBatch("insert into member values(2, '짤랑이', '010-1111-2222', 'def5678', 'zzalang@gmail.com')");

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