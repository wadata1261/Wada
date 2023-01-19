package Flow;
import Cluster.Node;
import make.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class MinCostFlow {
    public int MAX_V = 1000;
    public int INF = 100000000;
    public ArrayList<ArrayList<Edge>> list;
    public double costdis;
    public MinPear mp;

    public void setCostdis(double costdis) {
        this.costdis = costdis;
    }

    public double getCostdis() {
        return costdis;
    }

    public void setList(ArrayList<ArrayList<Edge>> list) {
        this.list = list;
    }

    public ArrayList<ArrayList<Edge>> getList() {
        return list;
    }
    public void NMinCostFlow(Node n1,Node n2){

    }

    public void MinCostFlow(ValueLogList v1, ValueLogList v2) {
        List<ValueLog> vl1=v1.getValueLogList();
        String[] c1name = new String[v1.getValueLogList().size()];
        for (int i = 0; i < v1.getValueLogList().size(); i++) {
            c1name[i] = v1.getValueLogList().get(i).getName();
        }
        List<ValueLog> vl2=v2.getValueLogList();
        String[] c2name = new String[v2.getValueLogList().size()];
        for (int i = 0; i < v2.getValueLogList().size(); i++) {
            c2name[i] = v2.getValueLogList().get(i).getName();
        }
        //int num_c1 = c1name.length;
        //int num_c2 = c2name.length;
        int num_c1=vl1.size();
        int num_c2=vl2.size();
        ArrayList<ArrayList<Edge>> G = new ArrayList<>();
        Graph graph = new Graph(num_c1, num_c2);
        graph.make2Dlist();
        int S_node = num_c2 + num_c1;
        int T_node = S_node + 1;
        double[][] as = new double[num_c1][num_c2];
        VarSim vs = new VarSim();

        for(ValueLog vl:v1.getValueLogList()){
            System.out.println(vl.getName()+":"+vl.getValueLog());
        }
        for(ValueLog vl:v2.getValueLogList()){
            System.out.println(vl.getName()+":"+vl.getValueLog());
        }


        for (int i = 0; i < num_c1; i++) {
            for (int j = 0; j < num_c2; j++) {
                vs.VarSim(v1.getValueLogList().get(i), v2.getValueLogList().get(j));
                //System.out.println("v1:"+v1.getValueLogList().get(i).getName()+" v2:"+v2.getValueLogList().get(j).getName()+" vs:"+vs.getVarSim());
                as[i][j] = vs.getVarSim();
            }
        }


        for (int i = 0; i < graph.getV(); i++) G.add(new ArrayList<>());
        for (int i = 0; i < num_c1; i++) {
            for (int j = 0; j < num_c2; j++) {
                double gain = as[i][j];
                G.get(i).add(graph.fromEdge(G, i, j + num_c1, 1, -gain));
                G.get(j + num_c1).add(graph.toEdge(G, i, j + num_c1, 1, -gain));
            }
        }
        //System.out.println("78");
        for (int i = 0; i < num_c1; i++) {
            G.get(S_node).add(graph.fromEdge(G, S_node, i, 1, 0));
            G.get(i).add(graph.toEdge(G, S_node, i, 1, 0));
        }
        //System.out.println("83");
        for (int j = 0; j < num_c2; j++) {
            G.get(j + num_c1).add(graph.fromEdge(G, j + num_c1, T_node, 1, 0));
            G.get(T_node).add(graph.toEdge(G, j + num_c1, T_node, 1, 0));
        }
        graph.setGraph(G);

        setList(G);

/*
        for (int i = 0; i < G.size(); i++) {
            for (int j = 0; j < G.get(i).size(); j++) {
                System.out.print("fromG:" + G.get(i).get(j).from);
                System.out.print(" toG:" + G.get(i).get(j).to);
                System.out.print(" costG:" + G.get(i).get(j).cost);
                System.out.println(" revG:" + G.get(i).get(j).rev);
            }
            System.out.println(i);
        }
        System.out.println(G.size());
        for (int i = 0; i < G.size(); i++) System.out.print(" " + i + ":" + G.get(i).size());
        System.out.println();

 */

        MinCostFlow minCostFlow = new MinCostFlow();
        int res = MinCostFlow(graph, S_node, T_node, num_c2);
        double cost=0;
        int j=0;
        int k=0;
        String x=null;
        String xtype=null;
        String y=null;
        double mincost=Double.MAX_VALUE;

        for (int i = 0; i < num_c1; i++) {
            //System.out.println("i:"+i);
            for (Edge e : graph.getGraph().get(i)) {
                //System.out.println("icap="+e.icap+" cap="+e.cap);
                if (e.icap == 1 && e.cap == 0) {
                    //System.out.println("worker " + c1name[i] + " and job " + c2name[e.to - num_c1] + " are matched (gain: " + -e.cost + ")");
                    System.out.println("worker " + vl1.get(i).getName() + " and job " + vl2.get(e.to - num_c1).getName() + " are matched (gain: " + -e.cost + ")");
                    if(-e.cost<mincost) {
                        //x=c1name[i];
                        x=vl1.get(i).getName();
                        xtype=vl1.get(i).getType();
                        j=i;
                        k=e.to-num_c1;
                        //y=c2name[e.to-num_c1];
                        y=vl2.get(e.to - num_c1).getName();
                        mincost=-e.cost;
                    }
                    cost+=-e.cost;
                }
            }
        }

        ValueLog vl=new ValueLog(x,xtype);
        //System.out.println("vl1"+v1.getValueLogList().get(j).getValueLog());
        //System.out.println("l1"+v1.getValueLogList().get(j).getLineLog());
        //System.out.println("vl2"+v2.getValueLogList().get(k).getValueLog());
        //System.out.println("l2"+v2.getValueLogList().get(k).getLineLog());
        double codedis=1.0;
        if(v1.getValueLogList().size()!=0&&v2.getValueLogList().size()!=0) {
            mp = new MinPear(v1.getValueLogList().get(j), v2.getValueLogList().get(k));
            setMp(mp);
            mp.Min();
            codedis = 1 - cost / Math.max(c1name.length, c2name.length);
        }
        //System.out.println("mfc:"+(codedis));//1.0666666666666667
        setCostdis(codedis);
        System.out.println("DIS:"+codedis);
    }
    public void setMp(MinPear mp){this.mp=mp;}
    public MinPear getmp(){return this.mp;}

    public int MinCostFlow(Graph graph, int s, int t, int inif) {
        //System.out.println("s:"+s+"t"+t+"inif:"+inif);
        double[] dist = new double[MAX_V];
        int[] prevv = new int[MAX_V];
        int[] preve = new int[MAX_V];

        int res = 0;
        int f = inif;
        while (f > 0) {
            printIterable(dist);
            dist[s] = 0;

            while (true) {
                //System.out.println("true");
                boolean update = false;
                for (int v = 0; v < graph.V; v++) {
                    if (dist[v] == INF) continue;
                    for (int i = 0; i < graph.graph.get(v).size(); i++) {
                        Edge e = graph.getGraph().get(v).get(i);
                        if (e.cap > 0 && dist[e.to] > dist[v] + e.cost) {
                            dist[e.to] = dist[v] + e.cost;
                            prevv[e.to] = v;
                            //System.out.println("e.to="+e.to+" prevv[e.to]="+preve[e.to]);
                            preve[e.to] = i;
                            update = true;
                        }
                    }
                }
                if (!update) break;
            }
            if (dist[t] == INF) return 0;
            int d = f;
            //System.out.println("d:"+d);
            //System.out.println("t="+t);
            //System.out.println("s="+s);
            for (int v = t; v != s; v = prevv[v]) {//無限ループ
                //System.out.println("v="+v+" prevv[v]="+prevv[v]+" s="+s);
                d = Math.min(d, graph.graph.get(prevv[v]).get(preve[v]).cap);
            }
            f -= d;
            res += dist[t] * d;
            for (int v = t; v != s; v = prevv[v]) {
                Edge e = graph.getGraph().get(prevv[v]).get(preve[v]);
                Edge re = graph.redge(e);
                e.cap -= d;
                re.cap += d;
                //System.out.println("e.cap:"+e.cap);
                //System.out.println("re.cap:"+re.cap);
            }
            //System.out.println("f:"+f);
        }
        System.out.println("finish");
        return res;
    }


    public void printIterable(double[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = INF;
        }
    }
}
