package Cluster;

import make.Code;
import make.ValueLogList;

import java.util.ArrayList;
import java.util.List;

public class Cluster implements Node{
    Code code;
    int counter;
    Node left;
    Node right;
    double dis;
    private List<ValueLogList> cachedVectors;
    private List<String> pathnames;
    private List<Code> codes;

    public Cluster(Node left,Node right,double dis){
        this.left=left;
        this.right=right;
        this.counter=2;
        this.dis=dis;
    }

    public int getCounter(){return this.counter;}
    public Node getLeft(){return this.left;}
    public Node getRight(){return this.right;}
    public double getDis(){return this.dis;}

    public Code getCode(){return this.code;}

    public List<ValueLogList> getvllist() {
        if (cachedVectors == null) {
            cachedVectors = new ArrayList<>();
            // leftノードとrightノードのベクトル集合を連結
            cachedVectors.addAll(left.getvllist());
            cachedVectors.addAll(right.getvllist());
        }
        return cachedVectors;

    }

    public List<String> getpathName(){
        if(pathnames == null){
            pathnames = new ArrayList<>();
            pathnames.addAll(left.getpathName());
            pathnames.addAll(right.getpathName());
        }
        return pathnames;
    }
    public List<Code> getcode(){
        if(codes == null){
            codes = new ArrayList<>();
            codes.addAll(left.getcode());
            codes.addAll(right.getcode());
        }
        return codes;
    }
}
