package bookmall.dao;

import bookmall.vo.MemberVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {



    public List<MemberVo> findAll() {
        List<MemberVo> result = new ArrayList<>();

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1. jdbc MyDriver Class 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            //2. 연결
            String url = "jdbc:mariadb://192.168.0.148:3306/bookmall?charset=utf8";
            connection = DriverManager.getConnection(url, "bookmall", "bookmall");

//            System.out.println("연결 성공!");

            //3. statement
            stmt = connection.createStatement();

            //4. sql 실행
            String sql =
                    "SELECT mbr_name, mbr_phone, mbr_email, mbr_pwd " +
                            "FROM member";


            rs = stmt.executeQuery(sql);

            //5. 결과
            while (rs.next()) {
                String mbrName = rs.getString(1);
                String mbrPhone = rs.getString(2);
                String mbrPwd = rs.getString(3);
                String mbrEmail = rs.getString(4);

                System.out.println(mbrName + "        "
                        + mbrPhone + " " + mbrPwd + " " + mbrEmail);

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
            } catch (SQLException e) {
                System.out.println("error" + e);
            }
        }
        return result;
    }
}