package main;

import Mysql.Mysqllist;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCreate {
    private ArrayList<String> tablelist;
    private ArrayList<String> makelist;
     public String sorce;
    public static void main(String[] args){
       // FileCreate("test");

    }
    public List<String> list1(){
        List<String> list1=new ArrayList<>();
        list1.add("<!DOCTYPE html>");
        list1.add("<html>");
        list1.add("<head>");
        list1.add("<meta charset=\"UTF-8\">");
        list1.add("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        list1.add("<meta name=\"description\" content=\"ここにページの概要が入ります。\">");
        list1.add("<title>PyScriptテスト</title>");
        list1.add("    <link rel=\"stylesheet\" href=\"https://pyscript.net/alpha/pyscript.css\" />");
        list1.add("    <script defer src=\"https://pyscript.net/alpha/pyscript.js\"></script>");
        list1.add("<py-env>");
        list1.add("- matplotlib");
        list1.add("- numpy");
        list1.add("- scipy");
        list1.add("</py-env>");
        list1.add("<body>");
        list1.add("<div id=\"mpl\"></div>");
        list1.add("<py-script output=\"mpl\">");
        list1.add("import numpy as np");
        list1.add("import scipy.spatial as sp");
        list1.add("import scipy.cluster as cl");
        list1.add("import matplotlib.pyplot as plt");


        return list1;
    }
    public List<String> list2(){
        String a= "af";
        List<String> list1=new ArrayList<>();
        list1.add("sp.distance.squareform(D)");
        list1.add("Z = cl.hierarchy.linkage(D, 'complete')");
        list1.add("Z");
        list1.add("fig, ax = plt.subplots(dpi=100)");
        list1.add("dn = cl.hierarchy.dendrogram(Z, labels=list(X_), ax=ax)");
        list1.add("ax.set_ylabel(\"Distance\")");
        list1.add("plt");
        list1.add("</py-script>");
        for(int i=0;i<this.tablelist.size();i++){
            list1.add(list3().get(i));
        }
        list1.add("</html>");
        return list1;
    }

    public List<String> list3(){
        List<String> list3=new ArrayList<>();
        list3.add("<body>");
        list3.add("<table border=\"5\">");
        list3.add("<th>");
        list3.add("<td>番号</td> <td>解答者</td> <td>正誤</td>");
        list3.add("</th>");
        for(int i=0;i<this.tablelist.size();i++){
            list3.add(this.tablelist.get(i));

        }
        list3.add("</table>");
        list3.add("</body>");
        return list3;
    }

    public void FileCreate(String path){
        File files=new File("");
        String name= files.getAbsolutePath();
        //Path p=Paths.get(name+"\\"+path+".html");
        String paths=name.substring(0,name.length()-5);
        Path p=Paths.get(paths+"\\htmlfiles\\"+path+".html");
        System.out.println(p);
        if (Files.exists(p)) {
            try{
                Files.delete(p);
            }catch(IOException e){
                System.out.println(e);
            }
        }
        try{
            Files.createFile(p);
        }catch(IOException e){
            System.out.println(e);
        }
        try{
            File file = new File(String.valueOf(p));

            if (checkBeforeWritefile(file)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                List<String> list1=list1();
                List<String> list2=list2();
                for(int i=0;i<list1.size();i++){
                    bw.write(list1.get(i));
                    bw.newLine();
                }

                for(int l=0;l<this.makelist.size();l++){
                    bw.write(this.makelist.get(l));
                    bw.newLine();
                }

                //ここで距離を入れる
                for(int j=0;j<list2.size();j++){
                    bw.write(list2.get(j));
                    bw.newLine();
                }
                bw.close();
            }else{
                System.out.println("ファイルに書き込めません");
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    static int count=0;

    public void setlist(ArrayList<Mysqllist> msl, ArrayList<Double> dis){
        this.makelist=new ArrayList<>();
        String xlist="[";
        for(int i=1;i<=msl.size();i++){
            xlist+=i+",";
        }
        xlist+="]";
        makelist.add("X_ = np.array("+xlist+")");
        makelist.add("X = X_[:,None]");
        makelist.add("X");
        String dlist="[";
        for(int j=0;j<dis.size();j++){
            dlist+=dis.get(j)+",";

        }
        dlist+="]";
        makelist.add("D = np.array("+dlist+")");
    }

    public void setTable(ArrayList<Mysqllist> msl){
        this.tablelist=new ArrayList<>();
        System.out.println(msl.size());
        for(int i=0;i<msl.size();i++){
            int a=i+1;
            this.tablelist.add("<tr>");
            this.tablelist.add("<td>"+a+" ： </td>");
            this.tablelist.add("<td>"+msl.get(i).getName()+" ： </td>");
            this.tablelist.add("<td>"+msl.get(i).isBoo()+"</td>");
            this.tablelist.add("</tr>");
        }
        this.tablelist.add("<tr>");
        this.tablelist.add("<td>  </td>");
        this.tablelist.add("<td>  </td>");
        this.tablelist.add("<td>  </td>");
        this.tablelist.add("</tr>");

        for(int i=0;i<this.tablelist.size();i++){
            System.out.println(this.tablelist.get(i));
        }

    }

    private static boolean checkBeforeWritefile(File file){
        if (file.exists()){
            if (file.isFile() && file.canWrite()){
                return true;
            }
        }
        return false;
    }

    public void setSorce(String sorce){this.sorce=sorce;}


}
