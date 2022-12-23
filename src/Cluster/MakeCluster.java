package Cluster;

import make.CodeDis;

import java.util.ArrayList;
import java.util.List;

public class MakeCluster {
    List<Item> list;
    ArrayList<ArrayList<Item>> lists;
    public MakeCluster(List<Item> list){
        this.lists=new ArrayList<>();
        this.list=list;
    }
    public ArrayList<ArrayList<Item>> makeCluster(){
        int c=0;
        this.lists.get(c).add(this.list.get(0));
        this.list.remove(0);
        double min=Double.MAX_VALUE;
        Item item=null;
        CodeDis cd=new CodeDis();
        int j=0;
        while (!this.list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                cd.CodeDis(this.lists.get(j).get(0).getCode(),this.list.get(i).getCode());
                if(cd.getCodedis()<min){
                    min= cd.getCodedis();
                    item=this.list.get(i);
                }
            }
            this.lists.get(j).add(item);
            this.list.remove(item);
        }
        return this.lists;
    }
}
