package main;

import Cluster.Cluster;
import Cluster.NearCodeDis;
import Cluster.ClusterBuilder;
import Cluster.ClusterList;
import Cluster.Item;
import Cluster.Node;
import Mysql.ReList;
import make.CheckCode;
import make.Code;
import make.ValueLog;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Teacher {
    static List<String> paths=new ArrayList<>();
    static List<Cluster> clusters=new ArrayList<>();
    public ArrayList<Double> valuelist;
    public static Code[] c;

    public ArrayList<Double> tofs(String filename){
        File file = new File(String.valueOf(Paths.get("").toAbsolutePath().getParent())+"\\anserfile\\"+filename);
        System.out.println(String.valueOf(Paths.get("").toAbsolutePath().getParent())+"\\anserfile\\"+filename);
        //File file = new File(String.valueOf(Paths.get("").toAbsolutePath())+"\\src\\testcode");
        File files[] = file.listFiles();
        int count=count(files);
        String pathname[]=new String[count];
        //for(int i=0;i<count;i++) pathname[i]=paths.get(i);
        this.c=new Code[count];
        List<Item> input = new ArrayList<>();
        System.out.println(files);


        for (int i=0;i<count;i++){
            this.c[i]=new Code(paths.get(i));
            this.c[i].Code();
        }
        /*for(int i=0;i<count;i++){
            Item item=new Item(c[i]);
            input.add(item);
        }*/

        ReList rl=new ReList(filename,c);
        rl.re();
        c=rl.ReCode();
        NearCodeDis ncd=new NearCodeDis(c);
        this.valuelist=ncd.getMinlist();
        //makeCluster(input);
        return this.valuelist;
    }


    public static void doto(){
        String path=FileGet.fileGet();
        File file = new File(path);
        //File file = new File(String.valueOf(Paths.get("").toAbsolutePath())+"\\src\\testcode");
        File files[] = file.listFiles();
        int count=count(files);
        String pathname[]=new String[count];
        //for(int i=0;i<count;i++) pathname[i]=paths.get(i);
        c=new Code[count];
        List<Item> input = new ArrayList<>();
        System.out.println(files);

        for (int i=0;i<count;i++){
            c[i]=new Code(paths.get(i));
            c[i].Code();
        }

        for(int i=0;i<count;i++){
            Item item=new Item(c[i]);
            input.add(item);
        }
        //c[0].getEll().show();
        makeCluster(input);
        System.out.println(list);
    }
    public static void main(String[] args){
        doto();
    }
    public static void makeCluster(List<Item> input){
        ClusterBuilder clusterBuilder=new ClusterBuilder();
        Node result=clusterBuilder.build(input); //クラスタ
        List<Node> nodes=clusterBuilder.getNodelist();
        output(result,0);
        List<ClusterList> clusterLists=new ArrayList<>();
        System.out.println("kokomade");
        for (int i=0;i<nodes.size();i++){
            int ans=0;
                System.out.println("koko"+i);
                System.out.println("cluster"+nodes.get(i).getpathName());
                for(int j=0;j<nodes.get(i).getcode().size();j++){
                    if(nodes.get(i).getcode().get(j).isCheck()){
                        ans+=1;
                    }
                }
                double anser=(double)ans/nodes.get(i).getpathName().size();
                System.out.println("true:"+anser);
                clusterLists.add(new ClusterList(nodes.get(i),anser));
        }
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
    static ArrayList list = new ArrayList<>();
    private static void output(Node node, int depth) {
        // インデントを表示

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        if (node instanceof Item) {
            // 末端ノードなら項目名を表示
            System.out.println(((Item) node).getname());
            list.add(((Item) node).getname());
        } else if (node instanceof Cluster) {
            // クラスタなら"+"を表示し、子ノードを再帰的に表示
            Cluster cluster = (Cluster) node;
            if(cluster.getDis()>=0.5) System.out.println("");
            System.out.println(depth+" clusterdis="+cluster.getDis());
            list.add(depth);
            output(cluster.getLeft(), depth + 1);
            output(cluster.getRight(), depth + 1);
        }
    }

    public static void anserCheck(Code[] c,String path){
        for(int i=0;i<c.length;i++){
            CheckCode cc = new CheckCode(path,c[i].path);
            c[i].setCheck(cc.doCheck());
            System.out.println(c[i].getPath()+","+c[i].isCheck());
        }
    }
    public Code[] getC(){return this.c;}
    public void getValuelist(String file){

    }
}
