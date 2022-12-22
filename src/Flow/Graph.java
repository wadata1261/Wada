package Flow;

import java.util.ArrayList;

public class Graph {
    public int num_worker;
    public int num_job;
    public int V;
    ArrayList<ArrayList<Edge>> graph;
    ArrayList<Edge> array;
    public Graph(int num_worker,int num_job){
        this.num_job=num_job;
        this.num_worker=num_worker;
        this.V=num_job+num_worker+2;
        this.graph =new ArrayList<>(V);
        this.array=new ArrayList<>();
    }
    public void make2Dlist(){
        ArrayList<Edge> list =new ArrayList<>();
        for(int i=0;i<V;i++){
            graph.add(i,list);
        }
    }

    public void addEdge(int from,int to,int cap,double cost){ //2
        graph.get(from).add(new Edge(graph.get(to).size(),from,to,cap,cost));
        graph.get(to).add(new Edge(graph.get(from).size()-1 , to, from, 0, -cost));

    }
    public Edge fromEdge(ArrayList<ArrayList<Edge>> list,int from,int to,int cap,double cost){
        return new Edge(list.get(to).size(),from,to,cap,cost);

    }
    public Edge toEdge(ArrayList<ArrayList<Edge>> list,int from,int to,int cap,double cost){
        return new Edge(list.get(from).size()-1,to,from,0,-cost);
    }
    public int getV(){
        return this.num_job+this.num_worker+2;
    }
    public ArrayList<ArrayList<Edge>> getGraph(){
        return this.graph;
    }

    public void setGraph(ArrayList<ArrayList<Edge>> list){
        this.graph=list;
    }


    public Edge redge(Edge e){
        if(e.from != e.to) return graph.get(e.to).get(e.rev);
        else return graph.get(e.to).get(e.rev+1);
    }
}
