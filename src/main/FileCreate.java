package main;

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
    public static void main(String[] args){
        FileCreate("test");
        File newfile = new File("C:\\Users\\wadat\\IdeaProjects\\createfiles\\test.txt");
        File renamefile = new File("C:\\Users\\wadat\\IdeaProjects\\createfiles\\test.html");

        if (newfile.exists()) {
            if (newfile.renameTo(renamefile)) {
                System.out.println("ファイルの移動に成功しました");
            } else {
                System.out.println("ファイルの移動に失敗しました");
            }
        } else {
            System.out.println("ファイルが存在しません");
        }


    }
    public static List<String> list1(){
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
        list1.add("X_ = np.array([2, 11, 5, 1, 7])");
        list1.add("X = X_[:,None]");
        list1.add("X");
        list1.add("D = np.array([ 10.,  8.,  7.,  8.,  4., 2.,  5.,  8.,  7.,  3.])");
        return list1;
    }
    public static List<String> list2(){
        List<String> list1=new ArrayList<>();
        list1.add("sp.distance.squareform(D)");
        list1.add("Z = cl.hierarchy.linkage(D, 'complete')");
        list1.add("Z");
        list1.add("fig, ax = plt.subplots(dpi=100)");
        list1.add("dn = cl.hierarchy.dendrogram(Z, labels=list(X_), ax=ax)");
        list1.add("ax.set_ylabel(\"Distance\")");
        list1.add("plt");
        list1.add("</py-script>");
        list1.add("</body>");
        list1.add("</html>");
        return list1;
    }
    public static void FileCreate(String path){
        Path p=Paths.get("C:\\Users\\wadat\\IdeaProjects\\createfiles\\"+path+".txt");
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

    private static boolean checkBeforeWritefile(File file){
        if (file.exists()){
            if (file.isFile() && file.canWrite()){
                return true;
            }
        }

        return false;
    }
}
