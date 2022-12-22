package make;

import Cluster.Node;
import Flow.MinCostFlow;

//ソースコードvll1,vll2のソースコード間距離の計測
public class CodeDis {
    public double codeDis;

    public double getCodeDis(){return this.codeDis;}

    //code間の距離
    public void CodeDis(Code c1,Code c2){
        MinCostFlow mcf=new MinCostFlow();
        mcf.MinCostFlow(c1.getvll(),c2.getvll());
        double cost=mcf.getCostdis();
        this.codeDis=1-(cost/Math.max(c1.getvll().getValueLogList().size(),c2.getvll().getValueLogList().size()));
        System.out.println("c1:"+c1.getvll().getValueLogList().size()+" c2:"+c2.getvll().getValueLogList().size());
        System.out.println("cd:"+this.codeDis);
    }

    public double nCodeDis(Node n1,Node n2){
        double dnum=0;
        double cd=0;
        double min=Double.MIN_VALUE;
        VarSim vs=new VarSim();
        MinCostFlow mcf=new MinCostFlow();
        for (ValueLogList v1:n1.getvllist()){
            for (ValueLogList v2: n2.getvllist()){
                //CodeDis
                /*
                for (ValueLog v11:v1.getValueLogList()){
                    for (ValueLog v22:v2.getValueLogList()){
                        vs.VarSim(v11,v22);
                        Matching m=new Matching(v11,v22,vs.getVarSim());
                        dnum+=m.getVarsim();
                    }
                }
                 */
                mcf.MinCostFlow(v1,v2);
                dnum+=mcf.getCostdis();
                cd= 1-(dnum/(Math.max(v1.getValueLogList().size(),v2.getValueLogList().size())));
                if(min<cd) min=cd;
                dnum=0;
                //CodeDis
            }
        }
        return min;
    }


    public void vllCodeDis(ValueLogList vll1, ValueLogList vll2){
        if(vll1.getValueLogList().size()<vll2.getValueLogList().size()) {
            ValueLogList va=new ValueLogList();
            va=vll1;
            vll1=vll2;
            vll2=va;
        }
        codeDis=0;
        double dnum=0;
        VarSim vs=new VarSim();
        for(ValueLog v2:vll2.getValueLogList()){
            double maxvarsim=Double.MIN_VALUE;
            for(ValueLog v1:vll1.getValueLogList()){
                vs.VarSim(v1,v2);
                if(maxvarsim<=vs.getVarSim()) maxvarsim=vs.getVarSim();
            }
            dnum+=maxvarsim;
        }

        /*for(Matching m:ml.getMlist()){
            //System.out.println("m.getVarsim()="+m.getVarsim());
            dnum+=m.getVarsim();
        }*/
        codeDis=(1-(dnum/vll1.getValueLogList().size()));
        this.codeDis=codeDis;
        //System.out.println((dnum/vll2.getValueLogList().size()));
    }
}
