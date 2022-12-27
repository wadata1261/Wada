package Mysql;

import main.Teacher;
import make.Code;

import java.util.ArrayList;

public class ReList {
    private ArrayList<String> namelist;
    private ArrayList<String> pathlist;
    private ArrayList<String> checklist;
    private Code[] result;
    private Code[] c;
    public ReList(String file, Code[] c){ //file:課題名、c:コード群
        Mysql ms=new Mysql();
        ArrayList<Mysqllist> list=new ArrayList<>();
        checklist=new ArrayList<>();
        list=ms.getMysqllist(file);
        for(int i=0;i<list.size();i++){
            this.checklist.add(list.get(i).getName()+"\\"+list.get(i).getPath()+".java");
        }
        this.c=c;
        for(int i=0;i<c.length;i++){
            this.c[i].Code();
        }
    }

    public void re(){
        this.result=new Code[c.length];
        for(int i=0;i<this.c.length;i++){

            for(String b:this.checklist){
                System.out.println("checkname:"+this.c[i].checkName());
                System.out.println("checklist:"+this.checklist);
                System.out.println("b:"+b);
                if(this.c[i].checkName().equals(b)){
                    this.result[i]=this.c[i];
                    System.out.println("result:"+this.result[i].getPath());
                }
            }
        }
        System.out.println(this.result);
    }

    public Code[] ReCode(){
        return this.result;
    }
}
