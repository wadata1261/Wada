package Mysql;
import UI.AnserCheck;
import UI.FileSelect;

import java.sql.*;

public class Mysql{

    public void run(String file,String name,String path,String boo){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/anser?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true",
                    "root",
                    "mysqlroot"
            );// "password"の部分は，各自の環境に合わせて変更してください。

            pstmt = con.prepareStatement("select * from "+file);
            String sql="INSERT INTO "+file+" VALUES ('"+name+"','"+path+"',"+boo+")";
            Statement smt = con.createStatement();
            int rowsCount = smt.executeUpdate(sql);

            rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);

            while (rs.next()) {
                System.out.print(rs.getString("name")+" | ");
                System.out.println(rs.getInt("boo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FileSelect fs=new FileSelect();
        String filename=fs.getFilename();
        AnserCheck ac=new AnserCheck();
        ac.first();

    }
}
