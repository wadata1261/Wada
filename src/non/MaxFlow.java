package non;

import java.util.Scanner;
import java.util.Vector;

public class MaxFlow{
    static class Edge{
        int to,cap,rev;
        public Edge(int to,int cap,int rev){
            this.to = to;
            this.cap = cap;
            this.rev = rev;
        }
        public String toString(){
            return "#edge:["+to+","+cap+","+rev+"]";
        }
    }
    int MAX_V;
    Vector<Vector<Edge>> graph;
    boolean[] used;
    public MaxFlow(int n){ //1
        MAX_V = n;
        graph = new Vector<Vector<Edge>>();
        for(int i=0; i<MAX_V; i++) graph.add(new Vector<Edge>());
        used = new boolean[MAX_V];
    }
    void addEdge(int from,int to,int cap){ //2
        graph.get(from).add(new Edge(to,cap,graph.get(to).size()));
        graph.get(to).add(new Edge(from,0,graph.get(from).size()-1));
    }
    int dfs(int v,int t,int f){ //4
        if(v==t) return f;
        used[v] = true;
        for(int i=0; i<graph.get(v).size(); i++){
            Edge e = graph.get(v).get(i);
            if(!used[e.to]&&e.cap>0){
                int d = dfs(e.to,t,Math.min(f,e.cap));
                if(d > 0){
                    e.cap -= d;
                    graph.get(e.to).get(e.rev).cap += d;
                    return d;
                }
            }
        }
        return 0;
    }
    int solve(int s,int t){ //3
        int flow = 0;
        for(;;){
            for(int i=0; i<used.length; i++) used[i] = false;
            int f = dfs(s,t,Integer.MAX_VALUE);
            if(f==0) return flow;
            flow += f;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("n");
        int n = sc.nextInt();
        MaxFlow mf = new MaxFlow(n);
        System.out.println("m");
        int m = sc.nextInt();
        for(int i=0; i<m; i++){
            System.out.println("from");
            int from = sc.nextInt();
            System.out.println("to");
            int to = sc.nextInt();
            System.out.println("cap");
            int cap = sc.nextInt();
            mf.addEdge(from,to,cap);
        }
        System.out.println("src");
        int src = sc.nextInt();
        System.out.println("dst");
        int dst = sc.nextInt();
        System.out.println(mf.solve(src,dst));
    }
}
