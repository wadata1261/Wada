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
    public static void main(String[] args){
        Student s=new Student();
        s.run();
    }
    static List<String> paths=new ArrayList<>();
    public void run(){
        Path p=Paths.get(FileGet.fileGets());
        String path=String.valueOf(p.toAbsolutePath());
        Code studentcode=new Code(path);
        File file=new File(path);
        String path1=FileGet.fileGet();
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
        mcf.MinCostFlow(studentcode.getvll(), anser.getvll());
        mcf.getCostdis();
        MinPear mp= mcf.getmp();
        mp.Min();
        System.out.println(anser.getSource());
    }
    static int count=0;
    public static int count(File[] files){
        String path=null;
        for (File f : files) {
            path=f.getAbsolutePath();
            if (!path.substring(path.length()-5).equals(".java")) {
                count(f.listFiles());
            } else if (f.isFile()) {
                paths.add(path);
                count++;
            }
        }
        return count;
    }
    public static Code getAnser(String path,Code[] c){
        CodeDisMin cdm = new CodeDisMin(c, path);
        return cdm.codedismin();
    }
}
