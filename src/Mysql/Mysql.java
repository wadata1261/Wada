package Mysql;
import UI.AnserCheck;
import UI.FileSelect;
import main.FileCreate;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Mysql{
    public ArrayList<String> namelist;
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

    public ArrayList<String> getNamelist(String file){
        FileCreate fc=new FileCreate();
        this.namelist=new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/anser?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true",
                    "root",
                    "mysqlroot"
            );// "password"の部分は，各自の環境に合わせて変更してください。

            //pstmt = con.prepareStatement("select * from "+file);
            String sql="select name from anser."+file+";";
            Statement smt = con.createStatement();
            //int rowsCount = smt.sql);

            //rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);


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
        return this.namelist;
    }

    public void maketable(String file){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        System.out.println(file);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/anser?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true",
                    "root",
                    "mysqlroot"
            );// "password"の部分は，各自の環境に合わせて変更してください。

            //pstmt = con.prepareStatement("create table anser."+file+"(name varchar(100),path varchar(100),boo boolean);");
            String sql="create table anser."+file+"(name varchar(100),path varchar(100),boo boolean);";
            Statement smt = con.createStatement();
            int rowsCount = smt.executeUpdate(sql);

            //rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);


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
        //AnserCheck ac=new AnserCheck();
        //ac.first();

    }
}
