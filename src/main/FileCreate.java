package main;

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
    public ArrayList<Double> valuelist;
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
        list1.add("<script language=\"javascript\" type=\"text/javascript\">");
        list1.add("function OnButtonClick() {");
        list1.add("target = document.getElementById(\"output\");");
        list1.add("target.innerHTML = \""+a+"\";");
        list1.add("}");
        list1.add("</script>");
        list1.add("<input type=\"button\" value=\"Exec\" onclick=\"OnButtonClick();\"/><br />");
        list1.add("<br />");
        list1.add("<div id=\"output\"></div>");
        list1.add("</body>");
        list1.add("</html>");
        return list1;
    }
    public void FileCreate(String path){
        File files=new File("");
        String name= files.getAbsolutePath();
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

    public void setlist(int num,ArrayList<Double> dis){
        this.makelist=new ArrayList<>();
        String xlist="[";
        for(int i=0;i<num;i++){
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
