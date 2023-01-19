package Cluster;

import Flow.MinCostFlow;
import make.Code;
import make.ValueLog;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class NearCodeDis {
    public ArrayList<Double> minlist;
    public NearCodeDis(Code[] c){
        this.minlist=new ArrayList<>();
        Double max=0.0;
        Code c1 = null,c2=null;
        System.out.println("c.length:"+c.length);
        for (int i=0;i<c.length;i++){
            for(int j=i+1;j<c.length;j++){
                System.out.println("c1:"+c[i].CodeName()+"c2:"+c[j].CodeName());
                if(i==c.length-1) {
                    for(ValueLog vl:c[j].getvll().getValueLogList()){
                        System.out.println(vl.getName()+vl.getValueLog());
                    }
                }
                MinCostFlow mfc=new MinCostFlow();
                mfc.MinCostFlow(c[i].getvll(),c[j].getvll());
                this.minlist.add(mfc.getCostdis());
                System.out.println(" dis:"+mfc.getCostdis());
                if(max>mfc.getCostdis()) c1=c[i];c2=c[j];
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(c1.CodeName()+":"+c2.CodeName());
        /*
        for (int i=c.length-1;i>0;i--){
            System.out.println("c1:"+c[i].CodeName()+" i="+i);
            for(int j=i-1;j>=0;j--){
                MinCostFlow mfc=new MinCostFlow();
                mfc.MinCostFlow(c[i].getvll(),c[j].getvll());
                this.minlist.add(mfc.getCostdis());
                System.out.println("j="+j+" c2:"+c[j].CodeName()+" dis:"+mfc.getCostdis());
            }
            System.out.println();
        }
         */
    }
    public ArrayList<Double> getMinlist(){return this.minlist;}

}
