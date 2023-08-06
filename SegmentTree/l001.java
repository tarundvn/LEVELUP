public class l001 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, -5, 6 , 4};
        int[] segTree = new int[25];
        build(segTree, 0, arr, 0, 5);
        int res = query(segTree, 0, 0, 5, 2, 4);
    }

    /*
        Size of Segement Tree Array is taken to be 4(n) + 1;
        build : O(n)
        query : O(logn)
        point update : O(logn)
        range update : O(n)
        While Writing these functions think about partial, total and no overlap conditions
    */
    public static int build(int[] segTree, int idx, int[] arr, int l, int r) {
        if(l == r)  {
            return segTree[idx] = arr[l];
        }
        int min = (int)1e9;
        int mid = l + (r - l)/2;
        min = Math.min(min, build(segTree, (2*idx + 1), arr, l, mid));
        min = Math.min(min, build(segTree, (2*idx + 2), arr, mid + 1, r));
        return segTree[idx] = min;
    }

    public static int query(int[] segTree, int idx, int l, int r, int x, int y) {
        int min = (int)1e9;
        if(x>=l && y<=r) {  //Complete OverLap
            min = Math.min(min,segTree[idx]);
        }
        else if(y < l || x > r) //No OverLap
        {
            return min;
        }
        else {  //Partial OverLap
            int mid = l + (r - l)/2;
            min = Math.min(min, query(segTree, (2*idx + 1), l, mid, x, y));
            min = Math.min(min, query(segTree, (2*idx + 2), mid + 1, r, x, y));
        }   
        return min;
    }

    //Point Update
    public static void update(int[] segTree, int idx, int l, int r, int x) {
        if(l == idx && r == idx) {  //Complete OverLap
            segTree[idx] = x;
        }
        else if(y < l || x > r) //No OverLap
        {
            return;
        }
        else {  //Partial OverLap
            int mid = l + (r - l)/2;
            query(segTree, (2*idx + 1), l, mid, x);
            query(segTree, (2*idx + 2), mid + 1, r, x);
        }   
    }

    //Range Update
    public static void update(int[] segTree, int idx, int l, int r, int x, int y, int val) {
        if(l == idx && r == idx) {  //Complete OverLap
            segTree[idx] = x;
        }
        else if(y < l || x > r) //No OverLap
        {
            return;
        }
        else {  //Partial OverLap
            int mid = l + (r - l)/2;
            query(segTree, (2*idx + 1), l, mid, x);
            query(segTree, (2*idx + 2), mid + 1, r, x);
        }   
    }
}