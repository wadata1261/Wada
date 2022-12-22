package make;

import java.util.ArrayList;
import java.util.List;

public class ValueLog {
    private String name;
    private String type;
    private String value;
    private int line;
    private List<String> valueLog;
    private List<String> nameLog;
    private List<String> typeLog;
    private List<Integer> lineLog;


    //変数の型、名前、値の変化
    public ValueLog(String name, String type) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.line=line;
        this.nameLog = new ArrayList<>();
        this.typeLog = new ArrayList<>();
        this.valueLog =new ArrayList<>();
        this.lineLog = new ArrayList<Integer>();

    }
    public void setLine(int line){
        this.line=line;
    }

    public int getLine(){
        return this.line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addValueLog(String value) {

        if((this.getValueLog() != null) && (this.getValueLog().isEmpty()==false) && this.getValueLog().size()>1) {
            if(this.getValueLog().get(this.getValueLog().size()-2).equals(value)) {

                    //System.out.println("Mae" + this.valueLog);
                    //System.out.println(value);
                    //System.out.println("mae" + this.getValueLog().get(this.getValueLog().size() - 1));

            }else {
                //System.out.println("Mae"+this.valueLog);
                //System.out.println("jogai");
            }
            }
            this.valueLog.add(value);
        //if(this.getName().equals("j"))
        //System.out.println("ato"+this.valueLog);

    }

    public void addIndex(String value){

    }

    public List<String> getValueLog() {
        return valueLog;
    }

    public List<String> getNameLog() {
        return nameLog;
    }

    public void addNameLog(String name,String type,String value) {
        if(!this.valueLog.contains(new ValueLog(name,type))) {//System.out.println("sss");
             }
            if (!this.nameLog.contains(name))
                this.nameLog.add(name);

    }

    public void addLineLog(int line){
        if (this.lineLog.isEmpty()) this.lineLog.add(line-1);
        else this.lineLog.add(line+2);
        //if(this.getName().equals("j"))
        //System.out.println("ato"+this.valueLog);
    }

    public List<Integer> getLineLog(){
        return lineLog;
    }

    public List<String> getTypeLog() {
        return typeLog;
    }

    public void addTypeLog(String type) {
        if(!this.typeLog.contains(type))
        this.typeLog.add(type);
    }

    public void check(){
        if((this.getValueLog() != null) && (this.getValueLog().isEmpty()==false) && (this.getValueLog().size()>1)&& (this.getValueLog().get(0).equals(this.getValueLog().get(1)))) {
            this.getValueLog().remove(this.getValueLog().get(1));
            this.getLineLog().remove(this.getLineLog().get(1));
        }
        if((this.getValueLog() != null) && (this.getValueLog().isEmpty()==false) && (this.getValueLog().size()>2)) {
            if((this.getValueLog().get(this.getValueLog().size()-1).equals(this.getValueLog().get(this.getValueLog().size()-2)))){

                //System.out.println("Remove:" + this.getValueLog().get(this.getValueLog().size() - 1));
                //System.out.println(this.getValueLog().size() - 1);
                if(this.getValueLog().get(0).equals(this.getValueLog().get(this.getValueLog().size()-1))){
                    //System.out.println("RemoveNice");
                }
                this.getValueLog().remove((this.getValueLog().size()-1));
                this.getLineLog().remove((this.getLineLog().size()-1));
                //this.addValueLog(value);
            }
        }
    }

    public void show(){
        System.out.println(this.getNameLog());
    }

    public void print(){
        this.addNameLog(this.getName(),this.getType(),this.getValue());
        this.addTypeLog(this.getType());
        //System.out.println("nameLog="+this.getNameLog());
        //System.out.println("typeLog="+this.getTypeLog());
    }
}
