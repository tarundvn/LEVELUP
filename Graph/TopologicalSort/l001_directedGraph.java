import java.util.*;
public class l001_directedGraph
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
        display(graph,9);
        topological_DFS(9,graph);
    }

    public static void topological_DFS(int n,ArrayList<Edge>[] graph)
    {
        boolean[] vis = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
                topologicalSort(i,graph,vis,ans);
        }

        for(int i=ans.size()-1;i>=0;i--)
        {
            System.out.println(ans.get(i));
        }
    }

    public static void topologicalSort(int src,ArrayList<Edge>[] graph,boolean[]vis,ArrayList<Integer> ans)
    {
        vis[src] = true;
        for(Edge e : graph[src])
        {
            if(!vis[e.v])
                topologicalSort(e.v,graph,vis,ans);
        }
        ans.add(src);   //Post Order
    }
    
    public static void main(String[]args)
    {
        constructGraph();
    }
}