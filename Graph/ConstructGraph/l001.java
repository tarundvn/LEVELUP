import java.util.*;
public class l001
{
    public static class Edge
    {
        int v;
        int w;
        Edge(int v,int w)
        {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph,int u,int v,int w)
    {
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void removeEdge(int u,int v,ArrayList<Edge>[] graph)
    {
        int idx1 = findEdge(u,v,graph);
        graph[u].remove(idx1);
        
        int idx2 = findEdge(v,u,graph);
        graph[v].remove(idx2);
    }

    public static int findEdge(int u,int v,ArrayList<Edge>[] graph)
    {
        int idx = -1;
        for(int i=0;i<graph[u].size();i++)
        {
            if(graph[i].get(i).v == v)
            {
                return i;
            }
        }
        return idx;
    }

    public static void removeVertex(int u,int v,ArrayList<Edge>[] graph)
    {
        for(int i=0;i<graph[u].size();i++)
        {
            Edge e = graph[u].get(i);
            removeEdge(u,e.v,graph);
        }
    }

    public static void display(ArrayList<Edge>[] graph, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void levelOrder(ArrayList<Edge>[]graph,int src,boolean[]vis)
    {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;
        int level = 0;
        while(que.size()!=0)
        {
            int size = que.size();
            System.out.print("level " + level + " :");
            while(size-->0)
            {
                int rv = que.removeFirst();
                System.out.print(rv + ",");
                for(Edge e : graph[rv])
                {
                    if(!vis[e.v])
                    {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            level++;
            System.out.println();
        }
    }

    public static void constructGraph()
    {
        int N = 9;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++)
        {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 2, 7, 2);
        addEdge(graph, 2, 8, 4);
        addEdge(graph, 7, 8, 3);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        // addEdge(graph, 0, 6, 3);
        display(graph,N);
        boolean[] vis = new boolean[9];
        pair p = heaviestPath(graph,vis,0,6);
        System.out.println(p.wt + " " + p.hpath);
    }
    

    public static class pair   
    {
        int wt;
        String hpath;

        pair(int wt,String hpath)
        {
            this.wt = wt;
            this.hpath = hpath;
        }
    }

    public static pair heaviestPath(ArrayList<Edge>[]graph,boolean[]vis,int src,int dest)
    {
        if(src == dest)
        {
            pair bp = new pair(0,"" + src);
            return bp;
        }

        pair mp = new pair(-1,"");
        vis[src] = true;
        for(Edge e : graph[src])
        {
            if(!vis[e.v])
            {
                pair rp = heaviestPath(graph,vis,e.v,dest);
                if(rp.wt!=-1 && rp.wt + e.w > mp.wt)
                {
                    mp.wt = rp.wt + e.w;
                    mp.hpath = src +  rp.hpath;
                }
            }
        }
        vis[src] = false;
        return mp;
    }
    
    public static void main(String[]args)
    {
        constructGraph();
    }
}