/*
package sourcehandling;


import java.io.IOException;
import java.util.*;
import static java.lang.Math.min;

public class Global
{
    public static final int MAX_V = 100; // グラフの最大ノード数
    public static final int INF = 100000000; // 十分大きい値
    public int getMaxV(){return MAX_V;}
    public static int MinCostFlow(Graph G, int s, int t, int inif)
    {
        int[] dist = new int[MAX_V];
        int[] prevv = new int[MAX_V];
        int[] preve = new int[MAX_V];
        int res = 0;
        int f = inif;
        while (f > 0)
        {
            fill(dist, dist + G.V, INF);
            dist[s] = 0;
            while (true)
            {
                boolean update = false;
                for (int v = 0; v < G.V; ++v)
                {
                    if (dist[v] == INF)
                        continue;
                    for (int i = 0; i < G.getItem(v).size(); ++i)
                    {
                        Edge e = G.getItem(v).get(i);
                        if (e.cap > 0 && dist[e.to] > dist[v] + e.cost)
                        {
                            dist[e.to] = dist[v] + e.cost;
                            prevv[e.to] = v;
                            preve[e.to] = i;
                            update = true;
                        }
                    }
                }
                if (!update)
                    break;
            }
            if (dist[t] == INF)
            {
                return 0;
            }
            int d = f;
            for (int v = t; v != s; v = prevv[v])
            {
                d = min(d, G.getItem(prevv[v]).get(preve[v]).cap);
            }
            f -= d;
            res += dist[t] * d;
            for (int v = t; v != s; v = prevv[v])
            {
                Edge e = G.getItem(prevv[v]).get(preve[v]);
                Edge re = G.redge(e);
                e.cap -= d;
                re.cap += d;
            }
        }
        return res;
    }
    public static int Main()
    {
        String workers = "ABCDE";
        int[] jobs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int NUM_WORKER = 0;
        int NUM_JOB = 0;
        NUM_WORKER = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
        NUM_JOB = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
        Graph G = new Graph(NUM_WORKER + NUM_JOB + 2); // +2 は S, T の分
        int S_node = NUM_WORKER + NUM_JOB;
        int T_node = NUM_WORKER + NUM_JOB + 1;
        for (int i = 0; i < NUM_WORKER; ++i)
        {
            for (int j = 0; j < NUM_JOB; ++j)
            {
                int gain;
                gain = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
                G.addedge(i, j + NUM_WORKER, 1, -gain);
            }
        }
        for (int i = 0; i < NUM_WORKER; ++i)
        {
            G.addedge(S_node, i, 2, 0);
        }
        for (int j = 0; j < NUM_JOB; ++j)
        {
            G.addedge(j + NUM_WORKER, T_node, 1, 0);
        }
        int res = MinCostFlow(G, S_node, T_node, NUM_JOB);
        System.out.print("Max Gain: ");
        System.out.print(-res);
        System.out.print("\n");
        for (int i = 0; i < NUM_WORKER; ++i)
        {
            for (Graph e : G.getItem(i))
            {
                if (e.icap == 1 && e.cap == 0)
                {
                    System.out.print("Worker ");
                    System.out.print(workers.charAt(i));
                    System.out.print(" and ");
                    System.out.print("Job ");
                    System.out.print(jobs[e.to - NUM_WORKER]);
                    System.out.print(" are matched (gain: ");
                    System.out.print(-e.cost);
                    System.out.print(")");
                    System.out.print("\n");
                }
            }
        }
        return 0;
    }
}

class Edge
{
    public int rev;
    public int from;
    public int to;
    public int cap;
    public int icap;
    public int cost;
    public Edge(int r, int f, int t, int ca, int co)
    {
        this.rev = r;
        this.from = f;
        this.to = t;
        this.cap = ca;
        this.icap = ca;
        this.cost = co;
    }
}
class Graph
{
    GlobalMembers gl=new GlobalMembers();
    public int V;
    public ArrayList<Edge>[] list = new ArrayList[gl.getMaxV()];
    public Graph()
    {
        this(0);
    }
    //C++ TO JAVA CONVERTER NOTE: Java does not allow default values for parameters. Overloaded methods are inserted above:
//ORIGINAL LINE: Graph(int n = 0) : V(n)
    public Graph(int n)
    {
        this.V = n;
        for (int i = 0; i < GlobalMembers.MAX_V; ++i)
        {
            list[i].clear();
        }
    }
    public final void init()
    {
        init(0);
    }
    //C++ TO JAVA CONVERTER NOTE: Java does not allow default values for parameters. Overloaded methods are inserted above:
//ORIGINAL LINE: void init(int n = 0)
    public final void init(int n)
    {
        V = n;
        for (int i = 0; i < GlobalMembers.MAX_V; ++i)
        {
            list[i].clear();
        }
    }
    public final void resize()
    {
        resize(0);
    }
    //C++ TO JAVA CONVERTER NOTE: Java does not allow default values for parameters. Overloaded methods are inserted above:
//ORIGINAL LINE: void resize(int n = 0)
    public final void resize(int n)
    {
        V = n;
    }
    public final void reset()
    {
        for (int i = 0; i < V; ++i)
        {
            for (int j = 0; j < list[i].size(); ++j)
            {
                list[i].get(j).cap = list[i].get(j).icap;
            }
        }
    }
    //C++ TO JAVA CONVERTER TODO TASK: C++ to Java Converter cannot determine the 'set' logic for this indexer:
    public final ArrayList<Edge> getItem (int i)
    {
        return list[i];
    }
    public final Edge redge(Edge e)
    {
        if (e.from != e.to)
        {
            return list[e.to].get(e.rev);
        }
        else
        {
            return list[e.to].get(e.rev + 1);
        }
    }
    public final void addedge(int from, int to, int cap, int cost)
    {
        list[from].add(new Edge((int)list[to].size(), from, to, cap, cost));
        list[to].add(new Edge((int)list[from].size() - 1, to, from, 0, -cost));
    }
}



public final class ConsoleInput
{
    private static boolean goodLastRead = false;
    public static boolean lastReadWasGood()
    {
        return goodLastRead;
    }

    public static String readToWhiteSpace(boolean skipLeadingWhiteSpace)
    {
        String input = "";
        char nextChar;
        while (Character.isWhitespace(nextChar = (char)System.in.read()))
        {
            //accumulate leading white space if skipLeadingWhiteSpace is false:
            if (!skipLeadingWhiteSpace)
            {
                input += nextChar;
            }
        }
        //the first non white space character:
        input += nextChar;

        //accumulate characters until white space is reached:
        while (!Character.isWhitespace(nextChar = (char)System.in.read()))
        {
            input += nextChar;
        }

        goodLastRead = input.length() > 0;
        return input;
    }

    public static String scanfRead()
    {
        return scanfRead(null, -1);
    }

    public static String scanfRead(String unwantedSequence)
    {
        return scanfRead(unwantedSequence, -1);
    }

    public static String scanfRead(String unwantedSequence, int maxFieldLength)
    {
        String input = "";

        char nextChar;
        if (unwantedSequence != null)
        {
            nextChar = '\0';
            for (int charIndex = 0; charIndex < unwantedSequence.length(); charIndex++)
            {
                if (Character.isWhitespace(unwantedSequence.charAt(charIndex)))
                {
                    //ignore all subsequent white space:
                    while (Character.isWhitespace(nextChar = (char)System.in.read()))
                    {
                    }
                }
                else
                {
                    //ensure each character matches the expected character in the sequence:
                    try {
                        nextChar = (char)System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (nextChar != unwantedSequence.charAt(charIndex))
                        return null;
                }
            }

            input = (new Character(nextChar)).toString();
            if (maxFieldLength == 1)
                return input;
        }

        while (!Character.isWhitespace(nextChar = (char)System.in.read()))
        {
            input += nextChar;
            if (maxFieldLength == input.length())
                return input;
        }

        return input;
    }
}

*/
