package Cluster;
import Flow.MinCostFlow;
import make.ValueLogList;

import java.util.ArrayList;
import java.util.List;

public class ClusterBuilder {
    public Node nn1;
    public Node nn2;
    public double value;
    public List<Node> nodelist;
    public double ClusterBuilder(Node n1,Node n2){
        return 0;
    }
    public List<Node> getNodelist(){return this.nodelist;}

    public Node build(List<? extends Node> nodes) {
        this.nodelist=new ArrayList<>();
        int c=nodes.size();
        int ch=0;
        //ClusterList cl=new ClusterList(nodes);
        // ノードが1つに集約されるまで繰り返す
        while (nodes.size() > 1) {

            Node merge1 = null;
            Node merge2 = null;
            double minDist = Double.MAX_VALUE;
            MinCostFlow mfc=new MinCostFlow();
            // 距離が最小となるノード対を探す
            for (int i = 0; i < nodes.size(); i++) {
                Node n1 = nodes.get(i);
                for (int j = i + 1; j < nodes.size(); j++) {
                    Node n2 = nodes.get(j);
                    //mfc.MinCostFlow(n1.getvllist().get(i),n2.getvllist().get(j));
                    //double dist = mfc.getCostdis();
                    for(ValueLogList v1:n1.getvllist()){
                        for(ValueLogList v2:n2.getvllist()){
                            mfc.MinCostFlow(v1,v2);
                            if (mfc.getCostdis() < minDist) { //最小値の更新
                                merge1 = n1; //最小距離のノードn1を代入
                                merge2 = n2; //最小距離のノードn2を代入
                                minDist = mfc.getCostdis(); //最小距離を代入
                            }
                        }
                    }

                }
                nodes.size();
                //cl.clusters(merge1,merge2,minDist);

                if(minDist>0.5){
                    boolean check=false;
                    if(this.nodelist.isEmpty()){
                        this.nodelist.add(merge1);
                        ch+=merge1.getpathName().size();
                    }
                    if(ch<c) {
                        for (int k = 0; k < this.nodelist.size(); k++) {
                            if (this.nodelist.get(k).getpathName().containsAll(merge1.getpathName())) {
                                check = true;
                            }
                        }
                        if (!check){
                            this.nodelist.add(merge1);
                            ch+=merge1.getpathName().size();
                        }
                    }
                    check=false;
                    if(ch<c) {
                        for (int l = 0; l < this.nodelist.size(); l++) {
                            if (this.nodelist.get(l).getpathName().containsAll(merge2.getpathName())) {
                                check = true;
                            }
                        }
                        if (!check){
                            this.nodelist.add(merge2);
                            ch+=merge2.getpathName().size();
                        }
                    }
                }



            }

            // 次ステップ用のノードリストを作成
            List<Node> nextNodes = new ArrayList<Node>();
            for (Node node : nodes) {
                // 統合対象にならなかったノードを追加
                if (node != merge1 && node != merge2) { //0.9030303030303031
                    nextNodes.add(node);
                }
            }
            //System.out.println("n1:"+merge1.getpathName());
            //System.out.println("n2:"+merge2.getpathName());
            //System.out.println("min:"+minDist);
            // 統合対象のノード対をクラスタ化して追加
            Cluster cluster=new Cluster(merge1,merge2,minDist);
            nextNodes.add(cluster);
            nodes = nextNodes;
        }

        System.out.println("c"+c+"ch");
        return nodes.get(0);
    }
}
