    public int mrPresident() {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        int M = scn.nextInt();
        long K = scn.nextLong();

        ArrayList<int[]> Edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
            Edges.add(new int[] { u, v, w });
        }

        Collections.sort(Edges, (a, b) -> {
            return a[2] - b[2];
        });

        par = new int[N + 1];
        for (int i = 0; i <= N; i++)
            par[i] = i;

        long totalCost = 0;
        int conversions = 0;
        int components = N;
        ArrayList<Integer> costOfRoad = new ArrayList<>();
        for (int[] e : Edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);

            if (p1 != p2) {
                par[p1] = p2;
                totalCost += w;
                costOfRoad.add(w);
                components--;
            }
        }

        if (components > 1)
            return -1;

        for (int i = costOfRoad.size() - 1; i >= 0; i--) {
            if (totalCost > K) {
                totalCost = totalCost - costOfRoad.get(i) + 1;
                conversions++;
            } else {
                break;
            }
        }

        return totalCost > K ? -1 : conversions;
    }
