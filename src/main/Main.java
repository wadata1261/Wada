package main;

import Cluster.*;
import Flow.MinCostFlow;
import make.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<String> paths=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        String path=FileGet.fileGet();
        File file = new File(path);
        //File file = new File(String.valueOf(Paths.get("").toAbsolutePath())+"\\src\\testcode");
        File files[] = file.listFiles();
        int count=count(files);
        String pathname[]=new String[count];
        //for(int i=0;i<count;i++) pathname[i]=paths.get(i);
        Code[] c=new Code[count];
        List<Item> input = new ArrayList<>();
        System.out.println(files);


        for (int i=0;i<count;i++){
            c[i]=new Code(paths.get(i));
            c[i].Code();
            Item item=new Item(c[i]);
            input.add(item);
            System.out.println(c[i].getDate());
            for(int j=0;j<c[i].getvll().getValueLogList().size();j++)
            System.out.println(c[i].getvll().getValueLogList().get(j).getName()+" "+c[i].getvll().getValueLogList().get(j).getValueLog());
        }

        //c[0].getEll().show();
       //String anser=getAnser("C:\\Users\\wadat\\IdeaProjects\\Wada\\src\\testcode\\Main.java",c);
        makeCluster(input);
        //System.out.println("ANS"+anser);
    }

    public static void makeCluster(List<Item> input){
        ClusterBuilder clusterBuilder=new ClusterBuilder();
        Node result=clusterBuilder.build(input); //クラスタ
        output(result,0);
    }

    public static String getAnser(String path,Code[] c){
        CodeDisMin cdm = new CodeDisMin(c, path);
        return cdm.codedismin().getPath();
    }
    //ファイルのフルパスからクラス名を取り出す
    public static String getFileName(final String fullPathString) {
        File file = new File(fullPathString);
        String basename = file.getName();
        String woext = basename.substring(0, basename.lastIndexOf('.'));
        return woext;
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
    static File[] fileList =new File[count];
    static List<Node> nodes=new ArrayList<>();
    private static void output(Node node, int depth) {
        // インデントを表示
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        if (node instanceof Item) {
            // 末端ノードなら項目名を表示
            System.out.println(((Item) node).getname()+" "+((Item) node).getCode().getDate());
        } else if (node instanceof Cluster) {
            // クラスタなら"+"を表示し、子ノードを再帰的に表示

            Cluster cluster = (Cluster) node;
            System.out.println(depth+" clusterdis="+cluster.getDis());
            if(cluster.getDis()>=0.5) nodes.add(cluster.getRight());
            output(cluster.getLeft(), depth + 1);
            output(cluster.getRight(), depth + 1);
        }
    }
}