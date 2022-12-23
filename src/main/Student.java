package main;

import Flow.MinCostFlow;
import Flow.MinPear;
import make.Code;
import make.CodeDisMin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Student {
    static Code[] cs;
    public String source;
    public static void main(String[] args){
        Student s=new Student();
        s.run();
    }
    static List<String> paths=new ArrayList<>();
    public void run(){
        Path p=Paths.get(FileGet.fileGets()); //1こ
        String path=String.valueOf(p.toAbsolutePath());
        System.out.println("p:"+path);
        Code studentcode=new Code(path);
        studentcode.Code();
        File file=new File(path);
        String path1=FileGet.fileGet(); //複数
        File file1 = new File(path1);
        File files[] = file1.listFiles();
        int count=count(files);
        String pathname[]=new String[count];
        //for(int i=0;i<count;i++) pathname[i]=paths.get(i);
        Code[] c=new Code[count];
        for (int i=0;i<count;i++) {
            c[i] = new Code(paths.get(i));
            c[i].Code();
        }
        Code anser=getAnser(path,c);
        System.out.println("ANS"+anser.getPath());
        MinCostFlow mcf=new MinCostFlow();
        System.out.println(studentcode.getvll().getValueLogList());
        mcf.MinCostFlow(studentcode.getvll(), anser.getvll());
        mcf.getCostdis();
        MinPear mp= mcf.getmp();
        mp.Min();
        System.out.println(anser.getSource());
    }
    public void runs(String pathlist,String pa){ //list:複数,pa:1こ
    Path p=Paths.get(pa); //1
    String path=String.valueOf(p.toAbsolutePath());
    System.out.println("p:"+path);
    Code studentcode=new Code(path);
        studentcode.Code();
    File file=new File(path);
    String path1=pathlist;
    System.out.println("path1:"+path1);
    File file1 = new File(path1);
    File files[] = file1.listFiles();
    int count=count(files);
    String pathname[]=new String[count];
    //for(int i=0;i<count;i++) pathname[i]=paths.get(i);
    Code[] c=new Code[count];
        for (int i=0;i<count;i++) {
        c[i] = new Code(paths.get(i));
        c[i].Code();
    }
        this.cs=c;
    Code anser=getAnser(path,c);
        System.out.println("ANS"+anser.getPath());
    MinCostFlow mcf=new MinCostFlow();
        System.out.println(studentcode.getvll().getValueLogList());
        mcf.MinCostFlow(studentcode.getvll(), anser.getvll());
        mcf.getCostdis();
    MinPear mp= mcf.getmp();
        mp.Min();
        this.source=anser.getSource();
        System.out.println(anser.getSource());
}

    static int count=0;
    public static int count(File[] files){
        String path=null;
        for (File f : files) {
            path=f.getAbsolutePath();
            if (f.isDirectory()) {
                count(f.listFiles());
            } else if (path.substring(path.length()-5).equals(".java")) {
                paths.add(path);
                count++;
            }
            else {
                continue;
            }
        }
        return count;
    }
    public int getCount(){return this.count;}
    public static Code getAnser(String path,Code[] c){
        CodeDisMin cdm = new CodeDisMin(c, path);
        return cdm.codedismin();
    }

    public ArrayList<Double> getList(){
        ArrayList<Double> list=new ArrayList<>();
        MinCostFlow mcf=new MinCostFlow();
        for(int i=0;i<this.cs.length;i++){
            for(int j=i+1;j<this.cs.length;j++){
                mcf.MinCostFlow(this.cs[i].getvll(),this.cs[j].getvll());
                list.add(mcf.getCostdis());
            }
        }
        return list;
    }

    public String getSource(){return this.source;}

}
