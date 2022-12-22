package Flow;

public class Edge {
        public int to, cap, rev,from,icap;
        public double cost;
        public Edge(int rev, int from, int to, int cap, double cost) {
            this.from=from;
            this.to = to;
            this.cap = cap;
            this.icap=cap;
            this.rev = rev;
            this.cost = cost;
        }

}
