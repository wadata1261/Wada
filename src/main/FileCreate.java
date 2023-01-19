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
    public List<String> list1(){ //1
        List<String> list1=new ArrayList<>();
        list1.add("<!DOCTYPE html>");
        list1.add("<html>");
        list1.add("<head>");
        list1.add("<meta charset=\"UTF-8\">");
        list1.add("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />");
        list1.add("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
        list1.add("    <link rel=\"stylesheet\" href=\"https://pyscript.net/alpha/pyscript.css\" />");
        list1.add("    <script defer src=\"https://pyscript.net/alpha/pyscript.js\"></script>");

        list1.add("<py-env>");
        list1.add("- matplotlib");
        list1.add("- numpy");
        list1.add("- scipy");
        list1.add("- pandas");
        list1.add("</py-env>");
        list1.add("<title>PyScript Playground</title>");
        list1.add("</head>");
        list1.add("<body>");
        list1.add("<main class=\"py-5 grid gap-y-4 grid-cols-1 place-items-center\">");
        list1.add("     <py-button id=\"graph-button\" label=\"データを表示\"></py-button>");
        list1.add("     <div id=\"graph-container\"></div>");
        list1.add("     <py-button id=\"iris-button\" label=\"データセットを分析\"></py-button>");
        list1.add("     <div id=\"iris-container\">");
        list1.add("         <p id=\"data-description\"></p>");
        list1.add("         <div id=\"plot-area\"></div>");
        list1.add("     </div>");
        list1.add("</main>");
        list1.add("<py-script>");
        list1.add("import numpy as np");
        list1.add("import pandas as pd");
        list1.add("import scipy.spatial as sp");
        list1.add("import scipy.cluster as cl");
        list1.add("import matplotlib.pyplot as plt");
        list1.add("graph_button = Element('graph-button')");


        return list1;
    }
    public List<String> list2(){
        String a= "af";
        List<String> list1=new ArrayList<>();
        list1.add("Z = cl.hierarchy.linkage(D, 'complete')");
        list1.add("Z");
        list1.add("clu=cl.hierarchy.fcluster(Z ,0.5, criterion='distance')");
        list1.add("data['cluster']=clu");
        list1.add("fig, ax = plt.subplots(dpi=100)");
        list1.add("dn = cl.hierarchy.dendrogram(Z,color_threshold=0.5, labels=list(X_), ax=ax)");
        list1.add("ax.set_ylabel(\"Distance\")");
        list1.add("clus=data.sort_values('cluster')");
        list1.add("clunum=clus.iloc[-1]['cluster']");
        list1.add("a = int(clunum)");
        list1.add("clus = []");
        list1.add("datas = []");
        list1.add("clus.append(['all',len(data),len(data.query('boo.str.match(\"true\")',engine='python')),len(data.query('boo.str.match(\"false\")',engine='python'))])");
        list1.add("for i in range(1,a+1):");
        list1.add("     cs=data[data['cluster']==i]");
        list1.add("     datas.append([cs.iloc[0,0],cs.iloc[0,1],cs.iloc[0,2],cs.iloc[0,3]])");
        list1.add("     clus.append([i,len(cs),len(cs.query('boo.str.match(\"true\")',engine='python')),len(cs.query('boo.str.match(\"false\")',engine='python'))])");
        list1.add("ds=pd.DataFrame(clus,columns=['クラスタ','総数','true','false'])");
        list1.add("dat=pd.DataFrame(datas,columns=['name','path','boo','cluster'])");
        list1.add("def display_irisdata(*ags, **kwgs):");
        list1.add("     pyscript.write('data-description', ds)");
        list1.add("     pyscript.write('plot-area', fig)");
        list1.add("iris_button.element.onclick = display_irisdata");
        list1.add("</py-script>");
        list1.add("</body>");
        list1.add("</html>");



        list1.add("</py-script>");
        list1.add("</html>");
        return list1;
    }

    public List<String> list3(){ //3
        List<String> list3=new ArrayList<>();
        list3.add("def display_graph(*ags, **kwgs):");
        list3.add(" pyscript.write('graph-container', dat)");
        list3.add("graph_button.element.onclick = display_graph");
        list3.add("</py-script>");
        list3.add("<py-script>");
        list3.add("import pandas as pd");
        list3.add("import numpy as np");
        list3.add("import scipy.cluster as cl");
        list3.add("iris_button = Element('iris-button')");

        for(int i=0;i<this.makelist.size();i++){
            list3.add(this.makelist.get(i));
        }
        for(int i=0;i<this.tablelist.size();i++){
            list3.add(this.tablelist.get(i));

        }
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
                List<String> list3=list3();
                for(int i=0;i<list1.size();i++){
                    bw.write(list1.get(i));
                    bw.newLine();
                }


                for(int o=0;o<list3.size();o++){
                    bw.write(list3.get(o));
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

    public void setlist(ArrayList<Mysqllist> msl){ //2
        this.makelist=new ArrayList<>();
        String num="num=[";
        String name="name=[";
        String path="path=[";
        String boo="boo=[";
        for(int i=0;i<msl.size();i++){
            num+="\""+msl.get(i).getName().replaceAll("[^0-9]", "")+"\",";
            name+="'"+msl.get(i).getName()+"',";
            path+="'"+msl.get(i).getPath()+"',";
            boo+="'"+msl.get(i).isBoo()+"',";
        }
        num+="]";
        name+="]";
        path+="]";
        boo+="]";
        makelist.add(num);
        makelist.add(name);
        makelist.add(path);
        makelist.add(boo);
        makelist.add("data = pd.DataFrame({'name':name,'path':path,'boo':boo})");

    }

    public void setTable(ArrayList<Double> dis){
        this.tablelist=new ArrayList<>();
        tablelist.add("X_ = np.array(num)");
        tablelist.add("X = X_[:,None]");
        tablelist.add("X");
        String dlist="[";
        for(int j=0;j<dis.size();j++){
            dlist+=dis.get(j)+",";

        }
        dlist+="]";
        tablelist.add("D = np.array("+dlist+")");

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
