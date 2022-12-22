package make;

import make.Levenshtein;
import make.ValueLog;

public class VarSim {
    public double varSim;


    public double getVarSim(){
        return varSim;
    }

    Levenshtein l=new Levenshtein();

    //変数v1とv2の類似度計算
    public void VarSim(ValueLog v1, ValueLog v2){

            int VarSize=0;
            int VS=0;
            double Var=0;
            double VarSim=0;
        //System.out.println(v1.getName()+","+v2.getName());
            if(v1.getValueLog().size()>v2.getValueLog().size()) {
                VarSize = v1.getValueLog().size();
                VS=v2.getValueLog().size();
            }else if(v1.getValueLog().size()<=v2.getValueLog().size()){
                VarSize=v2.getValueLog().size();
                VS=v1.getValueLog().size();
            }
        int ls=Levenshtein.getDistance(v1,v2);


                Var=(double)ls/VarSize;
                VarSim=(1-Var)/(1+Var);
                this.varSim=VarSim;
                 //System.out.println("v1:"+v1.getName()+" v2:"+v2.getName()+" varsim:"+varSim+" ls:"+ls+" var:"+Var);
                //System.out.println("VarSim="+VarSim);

                //System.out.println("v1="+v1.getName()+",v2="+v2.getName());
                //System.out.println("VarSize="+VarSize+", ls=="+ls);
                //System.out.println();

    }
}
