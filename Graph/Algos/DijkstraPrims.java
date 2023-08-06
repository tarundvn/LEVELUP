import java.util.*;
public class DijkstraPrims
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

    public static void display(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static class pair
    {
        int src = 0;
        int par = 0;
        int w = 0;
        int wsf = 0;

        pair(int src,int par,int w) //prims
        {
            this.src = src;
            this.par = par;
            this.w = w; 
        }

        pair(int src,int par,int w,int wsf) //dijkstra
        {
            this.src = src;
            this.par = par;
            this.w = w; 
            this.wsf = wsf;
        }

        pair(int src,int wsf) //dijkstraBtr
        {
            this.src = src;
            this.wsf = wsf;
        }
    }

    public static void constructGraph()
    {
        int V = 9;
        ArrayList<Edge>[] graph = new ArrayList[V];
        for(int i=0;i<N;i++)
        {
            graph[i] = new ArrayList<>();
        }
        boolean[] vis = new boolean[V];
    }

    public void dijkstra(ArrayList<Edge>[]graph,int src,boolean[]vis)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return a.wsf - b.wsf;
        });

        pq.add(new pair(src,-1,0,0));
        while(pq.size()!=0)
        {
            pair rp = pq.remove();
            if (vis[rp.src])
                continue;

            vis[rp.src] = true;

            if(par!=-1)
                addEdge(graph,rp.src,rp.par,rp.w);
            
            for(Edge e : graph[rp.src])
            {
                if(!vis[e.v])
                {
                    pq.add(new pair(e.v,rp.src,e.w,e.w+rp.wsf));
                }
            }
        }

    }

    public void prims(ArrayList<Edge>[]graph,int src,boolean[]vis)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return a.w - b.w;
        });
        pq.add(new pair(src,-1,0,0));
        while(pq.size()!=0)
        {
            pair rp = pq.remove();
            if (vis[rp.src])
                continue;

            vis[rp.src] = true;

            if(par!=-1)
                addEdge(graph,rp.src,rp.par,rp.w);
            
            for(Edge e : graph[rp.src])
            {
                if(!vis[e.v])
                {
                    pq.add(new pair(e.v,rp.src,e.w,e.w+rp.wsf));
                }
            }
        }
    }

    public static void dijikstra_Btr(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] mygraph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        boolean[] vis = new boolean[V];

        int[] dis = new int[V];
        Arrays.fill(dis, (int) 1e9);

        int[] par = new int[V];
        Arrays.fill(par, -1);

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        pq.add(new pair(src, 0));
        par[src] = -1;
        dis[src] = 0;

        while (pq.size() != 0) {
            pair p = pq.remove();
            if (vis[p.src]) // if (p.wsf >= dis[p.src])
                continue;

            vis[p.src] = true;
            for (Edge e : graph[p.src]) {
                if (!vis[e.v] && e.w + p.wsf < dis[e.v]) {
                    dis[e.v] = e.w + p.wsf;
                    par[e.v] = p.src;
                    pq.add(new pair(e.v, p.wsf + e.w));
                }
            }
        }
    }

}
//Questions
//743. Network Delay Time
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        ArrayList<int[]> graph [] = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(int i=0;i<times.length;i++)
        {
            int u = times[i][0]-1;
            int v = times[i][1]-1;
            int w = times[i][2];           
            graph[u].add(new int[]{v,w});
        }
        
        boolean[]vis = new boolean[n];
        int count = 0;
        int val = 0;
        PriorityQueue<int[]> pque = new PriorityQueue<>((a,b)->{
            return a[3] - b[3];
        });
        pque.add(new int[]{k-1,-1,0,0});
        while(pque.size()!=0)
        {
            int[] rv = pque.remove();
            if(vis[rv[0]])
                continue;
            
            count++;
            vis[rv[0]] = true;
            val = Math.max(val,rv[3]);
            
            for(int[] ele : graph[rv[0]])
            {
                if(!vis[ele[0]])
                {
                    pque.add(new int[]{ele[0],rv[0],ele[1],ele[1] + rv[3]});
                }
            }   
        }
        if(count != n)
            return -1;
        return val;
    }
}
//743 // using distance array
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        ArrayList<int[]> graph [] = new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        
        for(int i=0;i<times.length;i++)
        {
            int u = times[i][0]-1;
            int v = times[i][1]-1;
            int w = times[i][2];           
            graph[u].add(new int[]{v,w});
        }
        
        boolean[]vis = new boolean[n];
        int[] dis = new int[n];
        for(int i=0;i<n;i++)
            dis[i] = (int)1e9;
            
        PriorityQueue<int[]> pque = new PriorityQueue<>((a,b)->{
            return a[3] - b[3];
        });
        pque.add(new int[]{k-1,-1,0,0});
        while(pque.size()!=0)
        {
            int[] rv = pque.remove();
            if(vis[rv[0]])
                continue;
            
            vis[rv[0]] = true;
            dis[rv[0]] = rv[3];
            
            for(int[] ele : graph[rv[0]])
            {
                if(!vis[ele[0]] && )
                {
                    pque.add(new int[]{ele[0],rv[0],ele[1],ele[1] + rv[3]});
                }
            }
            
        }

        int maxTime = 0;
        for(int i =0;i<n;i++)
        {
            if(dis[i] == (int)1e9)
                return -1;
            maxTime = Math.max(maxTime,dis[i]);
        }
        return maxTime;
        
    }
}
//BellmanFord
    public static void bellmanFord(int[][] edges, int N, int src) {
        int[] prev = new int[N];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        boolean isNegativeCycle = false;
        for (int edgeCount = 1; edgeCount <= N; edgeCount++) {
            int[] curr = new int[N];
            for (int i = 0; i < N; i++)
                curr[i] = prev[i];

            boolean isAnyUpdate = false;
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    isAnyUpdate = true;
                }
            }

            if (edgeCount == N && isAnyUpdate)
                isNegativeCycle = true;

            if (!isAnyUpdate)
                break;
        }
    }
//787. Cheapest Flights Within K Stops
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prev = new int[n];
        Arrays.fill(prev, (int)1e9);
        prev[src] = 0;
        for (int i = 0; i <= k; ++i) {
			boolean anyUpdate = false;
            int[] curr = new int[n];
            for (int j = 0; j < n; ++j)
                curr[j] = prev[j];
            
            for (int[] f: flights) {
                int u = f[0], v = f[1], w = f[2];
                if (prev[u] != (int)1e9 && prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    anyUpdate = true;
                }
            }
            if (!anyUpdate) break;
            prev = curr;
        }
        return prev[dst] != (int)1e9 ? prev[dst] : -1;
    }
}
