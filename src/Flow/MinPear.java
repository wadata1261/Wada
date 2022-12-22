package Flow;

import make.ValueLog;
import make.ValueLogList;

import java.util.List;

public class MinPear {
    ValueLog v1;
    ValueLog v2;
    public MinPear(ValueLog v1, ValueLog v2){
        this.v1=v1;
        this.v2=v2;
    }
    public void Min(){
        int i=0;
        int line=0;
        if(this.v1.getValueLog().size()<this.v2.getValueLog().size()) i=this.v1.getValueLog().size();
        else i=this.v2.getValueLog().size();
        for (int j=0;j<i;j++){
            if(!v1.getValueLog().get(j).equals(v2.getValueLog().get(j))) line=v1.getLineLog().get(j);

        }
        //System.out.println(v1.getName()+","+v1.getLineLog());
        //System.out.println(v2.getName()+","+v2.getLineLog());
        //System.out.println("falseline:"+line);
    }
}
