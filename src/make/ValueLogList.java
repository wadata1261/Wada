package make;

import make.ValueLog;

import java.util.ArrayList;
import java.util.List;

public class ValueLogList {

    private List<ValueLog> valueLogList;
    private ValueLog vl;

    public ValueLogList(){
        this.valueLogList=new ArrayList<>();
    }

    public void addValueLogList(ValueLog vl) {
        if(!vl.getType().equals("java.lang.String[]") && !vl.getName().equals("args"))
            this.valueLogList.add(vl);

    }


    public List<ValueLog> getValueLogList() {
        return valueLogList;
    }

    /*public ValueLog getVl() {
        vl=valueLogList.get(valueLogList.size()-1);
        return vl;
    }

     */


    public void print(ValueLog vl){
        vl.addNameLog(vl.getName(),vl.getType(),vl.getValue());
        vl.addTypeLog(vl.getType());
        System.out.println("nameLog="+vl.getNameLog());
        System.out.println("typeLog="+vl.getTypeLog());
    }




    public void setValueLogList(List<ValueLog> valueLogList) {
        this.valueLogList = valueLogList;
    }

    public int addLog(String name,String type,String value) {
        if(valueLogList.contains(new ValueLog(name, type))){

        }else {
            valueLogList.add(new ValueLog(name, type));
        }
        return valueLogList.size()-1;
    }

    public void check(ValueLog valueLog){
        valueLogList.remove(valueLog);
    }

    public void show(){
        for(ValueLog vl:this.valueLogList){
            //System.out.println("VLL="+this.getValueLogList());
        }

    }
}
