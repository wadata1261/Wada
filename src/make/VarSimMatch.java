package make;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class VarSimMatch {
    public ValueLog v1;
    public List<VarSimLog> vsll;
    public VarSimLog vslog;
    public List<VarSimLog> vlist;
    public List<Matching> Mlist;
    public int[] box;

    public MatchingList mlist=new MatchingList();
    //変数v1と各変数名と類似度
    public VarSimMatch(ValueLog v1){
        this.v1=v1;
        this.vsll=new ArrayList<>();
        this.vlist=new ArrayList<>();
        this.Mlist=new ArrayList<>();
        this.box= new int[]{0,1,2};
    }




    public ValueLog getV1() {
        return v1;
    }

    public List<VarSimLog> getVsll() {
        return vsll;
    }

    public VarSimLog getVslog() {
        return vslog;
    }

    public void setVslog(VarSimLog vslog) {
        this.vslog = vslog;
    }

    public List<VarSimLog> getVlist() {
        return vlist;
    }

    public void addVlist(VarSimLog v) {
        if(v.getVarSim()>=0.7)
        this.vlist.add(v);
    }

    public void Vsm(VarSimLog vsl){
        this.vsll.add(vsl);
    }

    public List<Matching> getMlist() {return Mlist;}

    public void Show(){
        //System.out.println("");
        //System.out.println("VSM:"+this.v1.getName());
        //System.out.println("VSM-");
        /*for(VarSimLog vsl:this.vsll){

            System.out.println(vsl.getV2().getName());
            System.out.println(vsl.getVarSim());

        }*/
        //System.out.println("-VSM");
        //System.out.println("");
    }

    public void Check() {

        int i = 0;
        setVslog(this.getVsll().get(0));

        // sort用(逆順にしたい)
        ToDoubleFunction<VarSimLog> varsim = d -> d.getVarSim();
        Collections.sort(this.vsll, Comparator.comparingDouble(varsim));
        Collections.reverse(this.vsll);
        //System.out.println("sort");
        for(VarSimLog vl : this.getVsll()){
            //System.out.println("type="+vl.getV2().getType()+", name=" +vl.getV2().getName()  + ", value=" + vl.getVarSim());
        }
        //System.out.println("");
        setVslog(this.getVsll().get(0));//最大値を入れてる
        for (VarSimLog vsl : this.getVsll()) {
            //System.out.println(i);

            /*
            if(vsl.getVarSim()>=0.7) {
                addVlist(vsl);
            }else{
                this.getVlist().remove(vsl);
            }
            //System.out.println("VSM.sVS=" + getVslog().getV2().getName());
            //if (this.getVslog().getVarSim() < vsl.getVarSim()) {
                //System.out.println("VSM.gVS=" + getVslog().getV2().getName());
            //}
             */
    }
        //if(this.getVslog().getVarSim()>=0.7) {
            Matching m=new Matching(this.getV1(),this.getVslog().getV2(),this.getVslog().getVarSim());

        //}else{
            //System.out.println("NoPair");
        //}
        }

    public void show2(){
        for (VarSimLog vsl:this.getVlist()){
            //System.out.println("vsmName="+vsl.getV2().getName()+"vsmValue="+vsl.getVarSim());

        }
    }

}
