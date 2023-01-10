package Cluster;

import Flow.MinCostFlow;
import make.Code;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class NearCodeDis {
    public ArrayList<Double> minlist;
    public NearCodeDis(Code[] c){
        this.minlist=new ArrayList<>();
        MinCostFlow mfc=new MinCostFlow();
        for (int i=0;i<c.length;i++){
            for(int j=i+1;j<c.length;j++){
                mfc.MinCostFlow(c[i].getvll(),c[j].getvll());
                this.minlist.add(mfc.getCostdis());
                //System.out.println("c1:"+c[i].getPath()+"c2:"+c[j].getPath()+"dis:"+mfc.getCostdis());
            }
        }
    }
    public ArrayList<Double> getMinlist(){return this.minlist;}

}
