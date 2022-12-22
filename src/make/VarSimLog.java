package make;

import make.ValueLog;

//implements Serializable,Comparable
public class VarSimLog {
    public ValueLog v2;
    public double varSim;
    //変数名と類似度
    public VarSimLog(ValueLog v2,double varSim){
        this.v2=v2;
        this.varSim = varSim;
    }




    public ValueLog getV2() {
        return v2;
    }

    public double getVarSim() {
        return varSim;
    }


}
