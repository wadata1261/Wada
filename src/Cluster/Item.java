package Cluster;

import make.Code;
import make.ValueLog;
import make.ValueLogList;

import java.util.Collections;
import java.util.List;

public class Item implements Node{
    Code code;
    ValueLogList v;
    public Item(Code code) {
        this.code =code;
        this.v = this.code.getvll();

    }

    public Code getCode(){return this.code;}
    public ValueLogList getV(){return this.v;}
    public String getname(){return this.code.getPath();}
    public List<ValueLogList> getvllist(){
        return Collections.singletonList(this.v);
    }
    public List<String> getpathName(){
        return Collections.singletonList(this.code.getPath());
    }
    public List<Code> getcode(){return Collections.singletonList(this.code);}


}
