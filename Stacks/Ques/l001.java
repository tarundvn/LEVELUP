public class l001{

    public static void main(String[]args)
    {
        //
    }

    public static int[] NGOR(int[]arr)
    {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(arr,n);
        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            while(st.size()!=0 && arr[i] > arr[st.peek()])
                ans[st.pop()] = i;
            st.push(i);
        }
        return ans;
    }

    public static int[] NGOL(int[]arr)
    {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(arr,-1);
        LinkedList<Integer> que = new LinkedList<>();
        for(int i=n-1;i>=0;i++)
        {
            while(st.size()!=0 && arr[i] > arr[st.peek()])
                ans[st.pop()] = i;
            st.push(i);
        }
        return ans;
    }

   public static int[] NSOR(int[]arr)
    {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(arr,n);
        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            while(st.size()!=0 && arr[i] < arr[st.peek()])
                ans[st.pop()] = i;
            st.push(i);
        }
        return ans;
    }

    public static int[] NSOL(int[]arr)
    {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(arr,-1);
        LinkedList<Integer> que = new LinkedList<>();
        for(int i=n-1;i>=0;i++)
        {
            while(st.size()!=0 && arr[i] < arr[st.peek()])
                ans[st.pop()] = i;
            st.push(i);
        }
        return ans;
    }

    //NGOR1
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] ans = NGOR(nums2);
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<nums2.length;i++)
                map.put(nums2[i],ans[i]);
            int[] mans = new int[nums1.length];
            for(int i=0;i<nums1.length;i++)
            {
                mans[i] = map.getOrDefault(nums1[i],-1);
            }
            return mans;
        }
    }
    //NGOR2
    class Solution {
        public int[] nextGreaterElements(int[] arr) {
            int n = arr.length;
            int [] ans = new int[n];
            Arrays.fill(ans,-1);
            LinkedList<Integer> st = new LinkedList<>();
            for(int i=0;i<2*n;i++)
            {
                while(st.size()!=0 && arr[i%n] > arr[st.peek()])
                    ans[st.pop()] = arr[i%n];
                if(i<n)
                    st.push(i);
            }
            return ans;
        }
    }
    //NGOR3
    class Solution {
        public int nextGreaterElement(int n) {
        
            String str = n + "";
            int m = str.length();
            char[] arr = str.toCharArray();
            //first dip //65:3:7421
            int i = arr.length - 2;
            while(i>=0 && arr[i] >= arr[i+1])
                i--;
                if(i==-1)
                return -1;
        
            //now find just greater than i;
            int k = arr.length - 1;
            while(arr[i] >= arr[k])
                k--;
        
            //swap
            char temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        
            //store
            String res = "";
            for(int j=0;j<=i;j++)
                res+=arr[j];
            for(int j = arr.length-1;j>i;j--)
                res+=arr[j];
        
            long val = Long.parseLong(res);
            return (val <= Integer.MAX_VALUE) ? (int) val : -1;
        }
    }
    //Next higher palindromic number using the same set of digits
    //INPUT IS PALINDROME : HENCE FIND NGOR3 AND UNION WITH ITS MIRROR
    //https://medium.com/geekculture/next-higher-palindrome-using-the-same-set-of-digits-2c8056e09755
        class Solution {
        public String nextPalindrome(String num) {
            char[] arr = num.toCharArray();
            int length = arr.length;
            boolean flag = nextPermutation(arr, length / 2);
            if (!flag)
                return "";
            StringBuffer sb = new StringBuffer(new String(arr, 0, (length + 1) / 2));
            for (int i = length / 2 - 1; i >= 0; i--)
                sb.append(sb.charAt(i));
            return sb.toString();
        }

        public boolean nextPermutation(char[] arr, int endIndex) {
            int changeIndex = -1;
            for (int i = endIndex - 2; i >= 0; i--) {
                if (arr[i] < arr[i + 1]) {
                    changeIndex = i;
                    break;
                }
            }
            if (changeIndex < 0)
                return false;
            int nextIndex = -1;
            for (int i = endIndex - 1; i > changeIndex; i--) {
                if (arr[i] > arr[changeIndex]) {
                    nextIndex = i;
                    break;
                }
            }
            char temp = arr[changeIndex];
            arr[changeIndex] = arr[nextIndex];
            arr[nextIndex] = temp;
            reverse(arr, changeIndex + 1, endIndex - 1);
            return true;
        }

        public void reverse(char[] arr, int start, int end) {
            int low = start, high = end;
            while (low < high) {
                char temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                low++;
                high--;
            }
        }
    }
    //NGOR4
    //564. Find the Closest Palindrome
    class Solution {
        public String nearestPalindromic(String n) {
            // edge cases, no
        
            int len = n.length();
            int i = len % 2 == 0 ? len / 2 - 1: len / 2;
            long left = Long.parseLong(n.substring(0, i+1));
        
            // input: n 12345
            List<Long> candidate = new ArrayList<>();
            candidate.add(getPalindrome(left, len % 2 == 0)); // 12321
            candidate.add(getPalindrome(left+1, len % 2 == 0)); // 12421
            candidate.add(getPalindrome(left-1, len % 2 == 0)); // 12221
            candidate.add((long)Math.pow(10, len-1) - 1); // 9999
            candidate.add((long)Math.pow(10, len) + 1); // 100001
        
            long diff = Long.MAX_VALUE, res = 0, nl = Long.parseLong(n);
            for (long cand : candidate) {
                if (cand == nl) continue;
                if (Math.abs(cand - nl) < diff) {
                    diff = Math.abs(cand - nl);
                    res = cand;
                } else if (Math.abs(cand - nl) == diff) {
                    res = Math.min(res, cand);
                }
            }
            return String.valueOf(res);
        }
    
        private long getPalindrome(long left, boolean even) {
            long res = left;
            if (!even) left = left / 10;
            while (left > 0) {
                res = res * 10 + left % 10;
                left /= 10;
            }
            return res;
        }
    }
    //Static Stock span mein idx - NGOR
    //Online Stock Span LC 901
    class StockSpanner {
        Stack<int[]> st;
        int idx;

        public StockSpanner() {
            st = new Stack<>();
            idx = 0;
            st.push(new int[]{-1,-1});
        }
    
        public int next(int price) {

            while (st.peek()[0] != -1 && st.peek()[1] <= price)
                    st.pop();

            int span = idx - st.peek()[0];
            st.push(new int[] { idx++, price });
            return span;
        }
    }
    //739. Daily Temperatures
    class Solution {
        public int[] dailyTemperatures(int[] arr) {
        
            int n = arr.length;
            int[]ans = NGOR(arr);
            int[] mans= new int[n];
            for(int i=0;i<n;i++)
                mans[i] = ans[i] == 0 ? 0 : ans[i] - i;
            return mans;
        }
        public static int[] NGOR(int[]arr)
        {
            int n = arr.length;
            int[] ans = new int[n];
            Arrays.fill(ans,0);
            LinkedList<Integer> st = new LinkedList<>();
            for(int i=0;i<n;i++)
            {
                while(st.size()!=0 && arr[i] > arr[st.peek()])
                    ans[st.pop()] = i;
                st.push(i);
            }
            return ans;
        }
    }
    //Largest_Rectangle Area in Histogram
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] nsol = new int[n];
            int[] nsor = new int[n];
            NSOL(heights, nsol);
            NSOR(heights, nsor);
           
            int maxArea = 0;
            for (int i = 0; i < n; i++) {
                int h = heights[i];
                int w = nsor[i] - nsol[i] - 1;
                maxArea = Math.max(maxArea, h<w ? h*h : w*w);
            }
            return maxArea;
        }

        public static void NSOL(int[] arr, int[] ans) {
            int n = arr.length;
            Arrays.fill(ans, -1);
            Stack<Integer> st = new Stack<>();
            for (int i = n - 1; i >= 0; i--) {
                while (st.size() != 0 && arr[st.peek()] > arr[i]) {
                    ans[st.pop()] = i;
                }
                st.push(i);
            }
        }
    
        public static void NSOR(int[] arr,int[]ans)
        {
            int n = arr.length;
            Arrays.fill(ans,n);
            Stack<Integer> st = new Stack<>();
            for(int i=0;i<n;i++)
            {
                while(st.size()!=0 && arr[st.peek()]>arr[i])
                {
                    ans[st.pop()] = i;
                }
                st.push(i);
            }
        }
    
    }
    // 85. Maximal Rectangle
    class Solution {
    public int maximalSquare(char[][] matrix) {
        
        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m];
        
        int max = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0; 
            }
            max = Math.max(max,largestRectangleArea(heights));
        }
        return max;
    }
    
     public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        NSOL(heights, nsol);
        NSOR(heights, nsor);
           
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;
            maxArea = Math.max(maxArea, h*w);
        }
        return maxArea;
    }

    public static void NSOL(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }
    
    public static void NSOR(int[] arr,int[]ans)
    {
        int n = arr.length;
        Arrays.fill(ans,n);
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<n;i++)
        {
            while(st.size()!=0 && arr[st.peek()]>arr[i])
            {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }
    
}
  // 221. Maximal Square
   class Solution {
    public int maximalSquare(char[][] matrix) {
        
        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m];
        
        int max = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0; 
            }
            max = Math.max(max,largestRectangleArea(heights));
        }
        return max;
    }
    
     public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        NSOL(heights, nsol);
        NSOR(heights, nsor);
           
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;
            maxArea = Math.max(maxArea, h<w ? h*h : w*w);
        }
        return maxArea;
    }

    public static void NSOL(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }
    
    public static void NSOR(int[] arr,int[]ans)
    {
        int n = arr.length;
        Arrays.fill(ans,n);
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<n;i++)
        {
            while(st.size()!=0 && arr[st.peek()]>arr[i])
            {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }
    
}
    //class Solution {
    public int maximalSquare(char[][] matrix) {
        
        if(matrix.length == 0) return 0;
        
        int[][] memo = new int[matrix.length][matrix[0].length];
        int max = 0;
        int currMax = 0;
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                currMax = findsquare(matrix,i,j,memo);
                if(max < currMax)
                    max = currMax;
            }
        }
        return max*max;
    }
    
    private int findsquare(char[][] matrix, int i, int j, int[][] dp){
        
        if(i >= matrix.length || j >= matrix[0].length || matrix[i][j] == '0')
            return 0;
        if(dp[i][j] != 0) return dp[i][j];
        
        return dp[i][j]  =  1 + Math.min(findsquare(matrix, i+1, j+1,dp),
                                Math.min(findsquare(matrix, i+1,j,dp),
                                         findsquare(matrix, i, j+1, dp)));
    }
}
//946. Validate Stack Sequences
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        
        boolean res = true;
        LinkedList<Integer> st = new LinkedList<>();
        int idx = 0;
        
        for(int i=0;i<pushed.length;i++)
        {
            st.addFirst(pushed[i]);
            while(st.size()!=0 && st.getFirst()==popped[idx])
            {
                st.removeFirst();
                idx++;
            }
        }
        
        while(st.size()!=0)
        {
            if(popped[idx] != st.removeFirst())
                return false;
            idx++;
        }
        
        return true;
    }
}
    //
    class Solution {
     public String minRemoveToMakeValid(String s) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(i);
            else if (ch == ')') {
                if (st.size() != 0 && s.charAt(st.getFirst()) == '(')
                    st.removeFirst();
                else
                    st.addFirst(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (st.size() != 0 && st.getLast() == i) {
                st.removeLast();
                continue;
            }

            ans.append(s.charAt(i));
        }

        return ans.toString();
    }
}
    //735. Asteroid Collision
    class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        
        Stack<Integer> st = new Stack<>();
        for(int ele : asteroids)
        {
            if(ele>0){
                st.push(ele);
                continue;
            }
            
            while(st.size()!=0 && st.peek()>0 && st.peek()<-ele)
                st.pop();
            
            if(st.size()!=0 && st.peek() == -ele)
                st.pop();
            else if(st.size()==0 || st.peek()<0)
                st.push(ele);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while(st.size()!=0)
            ans.add(st.pop());
        Collections.reverse(ans);
        return ans.stream().mapToInt(i->i).toArray();
    }
    //
    
}
    
}