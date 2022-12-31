package Mysql;

import make.Code;

import java.util.ArrayList;

public class TrueList {
    private ArrayList<String> checklist;
    private Code[] result;
    private Code[] c;
    public TrueList(String file, Code[] c){
        Mysql ms=new Mysql();
        ArrayList<Mysqllist> list=new ArrayList<>();
        checklist=new ArrayList<>();
        list=ms.boolist(file);
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
                if(this.c[i].checkName().equals(b)){
                    this.result[i]=this.c[i];
                }
            }
        }
        System.out.println(this.result);
    }

    public Code[] ReCode(){
        return this.result;
    }

}
