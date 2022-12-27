package Mysql;

public class Mysqllist {
    String name;
    String path;
    boolean boo;
    public Mysqllist(String name,String path,boolean boo){
        this.name=name;
        this.path=path;
        this.boo=boo;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isBoo() {
        return boo;
    }
}
