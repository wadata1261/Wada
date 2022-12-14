package Mysql;
import UI.AnserCheck;
import UI.FileSelect;
import main.FileCreate;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Mysql{
    public ArrayList<String> namelist;
    public ArrayList<String> pathlist;
    public ArrayList<Boolean> boolist;
    public ArrayList<Mysqllist> list;
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

            pstmt = con.prepareStatement("select * from anser."+file);
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

    public ArrayList<Mysqllist> getMysqllist(String file){
        FileCreate fc=new FileCreate();
        this.list=new ArrayList<>();
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


            String sql="select * from anser."+file+";";
            pstmt = con.prepareStatement(sql);
            Statement smt = con.createStatement();
            //int rowsCount = smt.sql);

            rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);

            while (rs.next()) {
                this.list.add(new Mysqllist(rs.getString("name"),rs.getString("path"),rs.getBoolean("boo")));
                //System.out.println(rs.getInt("boo"));
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
        //System.out.println("namelist:"+this.list);
        return this.list;
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
                    "jdbc:mysql://192.168.0.4:3306/anser?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true",
                    "root",
                    "mysqlroot"
            );// "password"の部分は，各自の環境に合わせて変更してください。


            String sql="select name from anser."+file+";";
            pstmt = con.prepareStatement(sql);
            Statement smt = con.createStatement();
            //int rowsCount = smt.sql);

            rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);

            while (rs.next()) {
                this.namelist.add(rs.getString("name"));
                //System.out.println(rs.getInt("boo"));
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
        System.out.println("namelist:"+this.namelist);
        return this.namelist;
    }

    public ArrayList<String> getpathlist(String file) {
        FileCreate fc=new FileCreate();
        this.pathlist=new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.4:3306/anser?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true",
                    "root",
                    "mysqlroot"
            );// "password"の部分は，各自の環境に合わせて変更してください。

            //
            String sql="select path from anser."+file+";";
            pstmt = con.prepareStatement(sql);
            Statement smt = con.createStatement();
            //int rowsCount = smt.sql);

            rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);

            while (rs.next()) {
               this.pathlist.add(rs.getString("path"));
               // System.out.println(rs.getInt("boo"));
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
        System.out.println("pathname:"+this.pathlist);
        return this.pathlist;
    }

    public ArrayList<Mysqllist> boolist(String file){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        this.boolist=new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.4:3306/anser?characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true",
                    "root",
                    "mysqlroot"
            );// "password"の部分は，各自の環境に合わせて変更してください。


            String sql="select * from anser.\""+file+"\" where boo = true;";
            pstmt = con.prepareStatement(sql);
            Statement smt = con.createStatement();
            int rowsCount = smt.executeUpdate(sql);

            rs = pstmt.executeQuery();
            pstmt = con.prepareStatement(sql);
            ArrayList<Mysqllist> lists=new ArrayList<>();
            while (rs.next()) {
                this.list.add(new Mysqllist(rs.getString("name"),rs.getString("path"),rs.getBoolean("boo")));
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
        return this.list;
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
        //this.getMysqllist("kadai12_1");
        //FileSelect fs=new FileSelect();
        //String filename=fs.getFilename();
        //AnserCheck ac=new AnserCheck();
        //ac.first();

    }
}
