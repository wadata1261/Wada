package non;

import Flow.MinCostFlow;
import main.Teacher;
import make.Code;
import make.ValueLog;

import java.util.ArrayList;

public class FlowTest {
    public static void main(String[] args){
        Teacher t=new Teacher();
        ArrayList<Double> list=t.tofs("nuke");

        /*
        Code c1=new Code("C:\\Users\\wadat\\IdeaProjects\\File\\anserfile\\kadai12_2\\Tuika7\\student35\\Train02.java");
        Code c2=new Code("C:\\Users\\wadat\\IdeaProjects\\File\\anserfile\\kadai12_2\\Tuika7\\student34\\Train02.java");
        c1.Code();
        c2.Code();
        MinCostFlow mcf=new MinCostFlow();
        for(ValueLog vl:c1.getvll().getValueLogList()){
            System.out.println(vl.getName()+","+vl.getValueLog());
        }
        for (ValueLog vl:c2.getvll().getValueLogList()){
            System.out.println(vl.getName()+","+vl.getValueLog());
        }
        mcf.MinCostFlow(c1.getvll(),c2.getvll());
        System.out.println(mcf.getCostdis());

         */
    }
}
