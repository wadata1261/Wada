package make;

import com.sun.jdi.LocalVariable;
import sourcehandling.ExecutedLine;
import sourcehandling.ExecutedLineList;

import java.util.Map;

public class ValueLogGet {
    ValueLogList vll = new ValueLogList();
    ValueLogList vll2 = new ValueLogList();

    public ValueLogList MakeList(ExecutedLineList ell) {


        for (ExecutedLine el : ell.getExecutedLineList()) {

            el.getVariables();

            for (Map.Entry<LocalVariable, Object> e : el.getVariables().entrySet()) {
                //System.out.println("**************************");

                ValueLog vl = new ValueLog(e.getKey().name(), e.getKey().typeName());
                //System.out.println("vl=" + vl);
                //System.out.println("line:" + el.getLineNumber() + " type:" + e.getKey().typeName() + " name:" + e.getKey().name() + " value:"+e.getValue());
                vl.setName(e.getKey().name());
                vl.setType(e.getKey().typeName());
                vl.setValue(String.valueOf(e.getValue()));
                vl.setLine(el.getLineNumber());
                for (ValueLog v : vll.getValueLogList()) {

                    //System.out.println("type="+v.getType()+",name="+v.getName()+",value="+v.getValue());
                    if (v.getName().equals(e.getKey().name()) && v.getType().equals(e.getKey().typeName())) {
                        //System.out.println("name=" + vl.getName() + ",type=" + vl.getType() + ",value=" + vl.getValue()+"vvalue="+v.getValue());
                        //v.check(vl.getValue());
                        if(v.getValueLog() != null && v.getValueLog().isEmpty() == false && v.getValueLog().size()>1){
                            if(v.getValueLog().get(v.getValueLog().size()-1).equals(vl.getValue())){

                            }
                        }
                        v.addValueLog(vl.getValue()); //加える
                        v.addLineLog(vl.getLine());
                        v.check(); //最後の値と後ろから2番目の値を比較
                        //System.out.println("line:"+v.getLineLog()+",type=" + v.getType() + ",name=" + v.getName() + ",valueLog=" + v.getValueLog());
                        break; //最初のvlに値を保存できてる
                        //}
                    } else {
                        //System.out.println("type="+v.getType()+",name="+v.getName()+",valueLog="+v.getValueLog());
                    }
                    //if(v.getValueLog().get(v.getValueLog().size()).equals(vl.getValue())) System.out.println("NICE");
                }

                //System.out.println("vlname=" + vl.getName());
                //System.out.println("vltype=" + vl.getType());

                vll.addValueLogList(vl);

                if (vl.getValueLog().isEmpty() == false)
                    vll.check(vl);
                //vlList.add(vl);
                //System.out.println("vll="+vll);
               // System.out.println("valueLogList=" + vll.getValueLogList().get(vll.getValueLogList().size() - 1));

                vll.show();
                //
                e.getKey().typeName(); //型
                //System.out.println("type "+e.getKey().typeName());
                e.getKey().name(); //名前
                //System.out.println("name "+e.getKey().name());
                e.getValue(); //変数の値(Object)
                //System.out.println("value "+e.getValue());
                el.getValue(e.getKey().name(), e.getKey().typeName());
                //System.out.println("elvalue "+el.getValue(e.getKey().name(),e.getKey().typeName()));
                //System.out.println("**************************");
            }

        }
            for (ValueLog v : vll.getValueLogList()) {
                if (v.getValueLog().isEmpty() == false) {
                    vll2.addValueLogList(v); //使われている変数だけを抜き取り
                    //System.out.println("type=" + v.getType() + ",name=" + v.getName() + ",valueLog=" + v.getValueLog());
                }
                //System.out.println("type=" + v.getType() + ",name=" + v.getName() + ",valueLog=" + v.getValueLog());
            }

            /*for (ValueLog v : vll2.getValueLogList()) {
                System.out.println("koretype=" + v.getType() + ",name=" + v.getName() + ",valueLog=" + v.getValueLog());
            }*/
        return vll2;
    }
}
