package Cluster;

import java.util.ArrayList;
import java.util.List;

public class ClusterList {
    Node node;
    double ans;
    public ClusterList(Node node,double ans){
        this.node=node;
        this.ans=ans;
    }
    public Node getNode(){return this.node;}
    public double getAns(){return this.ans;}

}
