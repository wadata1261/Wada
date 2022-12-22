package Flow;
/*
import java.util.ArrayList;
import java.util.List;

public class MinFlow {
    public static int MAX_V=1000;
    public static int INF=100000000;
    public static void main(String[] args){
        String[] worker = new String[]{"A", "B", "C", "D", "E"};
        int[] job=new int[]{1,2,3,4,5,6,7,8,9,10};
        int num_worker= worker.length;
        int num_job= job.length;
        ArrayList<ArrayList<Edge>> G=new ArrayList<>();
        Graph graph=new Graph(num_worker,num_job);
        graph.make2Dlist();
        int S_node = num_job+num_worker;
        int T_node = S_node+1;
        int[][] as={{8,9,12,16,21,2,12,15,20,3},
                    {7,12,5,14,13,10,16,12,18,11},
                    {19,2,14,7,16,5,8,7,1,4,},
                    {3,7,8,2,9,1,4,3,1,2},
                    {20,27,33,39,26,21,15,30,28,19}};

        for (int i=0;i< graph.getV();i++) G.add(new ArrayList<>());
        for(int i=0;i<num_worker;i++){
            for (int j=0;j<num_job;j++){
                int gain=as[i][j];
                //G.get(i).add(graph.setfromEdge(G,i,j+num_worker,1,-gain));
                //G.get(j+num_worker).add(graph.settoEdge(G,i,j+num_worker,1,-gain));
                graph.addEdge(i,j+num_worker,1,-gain);
                System.out.println("from:"+graph.graph.get(i).get(j).from+" to:"+graph.graph.get(i).get(j).to+" rev:"+graph.graph.get(i).get(j).rev);
            }
        }

        for(int i=0;i<num_worker;i++){
            //G.get(S_node).add(graph.setfromEdge(G,S_node,i,2,0));
            //G.get(i).add(graph.settoEdge(G,S_node,i,2,0));
            graph.addEdge(S_node,i,2,0);
        }

        for (int j=0;j<num_job;j++){
            //G.get(j+num_worker).add(graph.setfromEdge(G,j+num_worker,T_node,1,0));
            //G.get(T_node).add(graph.settoEdge(G,j+num_worker,T_node,1,0));
            graph.addEdge(j+num_worker,T_node,1,0);
            //graph.addEdge(T_node,j+num_worker,1,0);
        }
        //graph.setGraph(G);

        for(int i=0;i<G.size();i++){
            for(int j=0;j<G.get(i).size();j++){
                System.out.print("fromG:" + graph.graph.get(i).get(j).from);
                System.out.print(" toG:" + graph.graph.get(i).get(j).to);
                System.out.print(" costG:" + graph.graph.get(i).get(j).cost);
                System.out.println(" revG:" + graph.graph.get(i).get(j).rev);
            }
            System.out.println(i);
        }
        System.out.println(G.size());
        for (int i=0;i<G.size();i++) System.out.print(" "+i+":"+G.get(i).size());
        System.out.println();

        for (int i=0;i<G.size();i++) System.out.println(G.get(i).size());
        MinCostFlow minCostFlow =new MinCostFlow();
        System.out.println("mcf1");
        int res=minCostFlow.MinCostFlow(graph,S_node,T_node,num_job);
        System.out.println("mcf2");
        System.out.println("Max Gain: "+-res);

        for (int i=0;i<num_worker;i++){
            for (Edge e: graph.getGraph().get(i)){
                if(e.icap == 1 && e.cap ==0){
                    System.out.println("worker "+worker[i]+"and job"+job[e.to-num_worker]+" are matched (gain: "+-e.cost+")");
                }
            }
        }

    }


    static class MinCostFlow {
        public int MinCostFlow(Graph graph, int s, int t, int inif) {
            int[] dist = new int[MAX_V];
            int[] prevv = new int[MAX_V];
            int[] preve = new int[MAX_V];

            int res = 0;
            int f = inif;
            while (f > 0) {
                printIterable(dist);
                dist[s] = 0;

                while (true) {
                    boolean update = false;
                    for (int v = 0; v < graph.V; v++) {
                        if (dist[v] == INF) continue;
                        for (int i = 0; i < graph.graph.get(v).size(); i++) {
                            Edge e = graph.getGraph().get(v).get(i);
                            if (e.cap > 0 && dist[e.to] > dist[v] + e.cost) {
                                dist[e.to] = dist[v] + e.cost;
                                prevv[e.to] = v;
                                preve[e.to] = i;
                                update = true;
                            }
                        }
                    }
                    if (!update) break;
                }

                if (dist[t] == INF) return 0;
                int d = f;
                for (int v = t; v != s; v = prevv[v]) {
                    d=Math.min(d,graph.graph.get(prevv[v]).get(preve[v]).cap);
                }
                f-=d;
                res += dist[t] * d;
                for (int v = t; v != s; v = prevv[v]) {
                    Edge e = graph.getGraph().get(prevv[v]).get(preve[v]);
                    Edge re = graph.redge(e);
                    e.cap -= d;
                    re.cap += d;
                }
                System.out.println("sese");
            }
            System.out.println("res");
            return res;
        }
    }

    public static void printIterable(int[] list) {
        for(int i=0;i<list.length;i++) {
            list[i]=INF;
        }
    }
}


 */