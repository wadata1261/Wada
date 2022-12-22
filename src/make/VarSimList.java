package make;

import java.util.ArrayList;
import java.util.List;
//片方のソースコードの各変数ともう片方のソースコードの変数との類似度
public class VarSimList {
    private List<VarSimMatch> vsml;
    public List<VarSimMatch> Vsml2;
    public double varsimsum;
    //public List<int[]> box;
    //public int[] box1;

    public VarSimList(){
        this.vsml=new ArrayList<>();
        this.Vsml2=new ArrayList<>();
        this.varsimsum = getVarsimsum();
        //this.box=new ArrayList<>();
        //this.box1=new int[100];
    }

    public void addVarSimList(VarSimMatch vsm){
        this.vsml.add(vsm);
    }

    public double getVarsimsum() {
        return varsimsum;
    }

    public void setVarsimsum(double varsimsum) {
        this.varsimsum = varsimsum;
    }

    public List<VarSimMatch> getVsml() {
        return vsml;
    }

    public void Show(){
        for(VarSimMatch vsm:this.getVsml()){
            System.out.println();
            System.out.println("vsl="+vsm.getV1().getName());
            //System.out.println();
            for(VarSimLog vsl:vsm.getVsll()){
                //System.out.print("vslname="+vsl.getV2().getName()+",");
                //System.out.println("vslvalue="+vsl.getVarSim());
            }
            vsm.Check();
            System.out.println("TEST!"+vsm.getVsll().get(0).getV2().getName());
            //box.add(vsm.getBox());
            //box1[0]=(vsm.getBox()[0]);
            //System.out.println(box.get(0));

            System.out.println("max="+vsm.getVslog().getV2().getName()+", value"+vsm.getVslog().getVarSim());
        }


        double nn=0;
        for(VarSimMatch vsm:this.getVsml()){
            //System.out.println("name= " + vsm.getVlist().getV2().getName() + ",value= " + vsm.getVslog().getVarSim());
            //if(vsm.getVslog().getVarSim()>=0.7) {
                System.out.println("vsl=" + vsm.getV1().getName());
                //vsm.show2();
                nn+=vsm.getVslog().getVarSim();
                System.out.println("name= " + vsm.getVslog().getV2().getName() + ",value= " + vsm.getVslog().getVarSim());
            //}
        }
        setVarsimsum(nn);
    }
}
