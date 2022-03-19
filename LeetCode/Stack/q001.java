    public String smallestSubsequence(String s, int k, char letter, int repeat) {
        int count = 0;
        
        //count total number of letters in S
        for (char c : s.toCharArray()) {
            count += c == letter ? 1 : 0;
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // stack.peek() > c means: 
            // stack.peek() is lexicographically bigger than c, so we want kick off stack.peek() 
            // There are two conditions that can garuantee we can kick off stack.peek(): 
            // -----------------------------------------------
            // 1. (s.length() - i + stack.size() > k)
            //          s.length() - i = remain characters we can pick
            //          stack.size()  = characters we already pick
            //          remain characters + characters we already pick must bigger than k so we may can kick off stack.peek()  
            //          【why not >= k , but > k ? because in characters we already pick, we already counted stack.peek(), we need to delete this one
            //.           so you can also write:   s.length() - i + stack.size() - 1 >= k 】
            //-----------------------------------------
            // 2. stack.peek() != letter || count > repeat
            //   stack.peek() != letter  means the one we want kick off is not a Letter, so we do not care
           //  if stack.peek() is a Letter,   and we want remove it, so we need  count >= repeat + 1 or  equally "count > repeat"
            while (!stack.isEmpty() && stack.peek() > c && (s.length() - i + stack.size()  > k) && (stack.peek() != letter || count > repeat)) {
                if (stack.pop() == letter) repeat++;
            }
            
            if (stack.size() < k) {
                if (c == letter) {
                    stack.push(c);
                    repeat--;
                } else if (k - stack.size() - repeat > 0) { 
                    // k - stack.size() = remain character we need put 
                    // k - stack.size() - repeat = remain non-letter we need put 
                    // why not count total non-letter at beginning, and use numOfNonletter > 0 as condition ? because in while we pop the letter, 
                    // so we need count this dynamiclly 
                    stack.push(c);
                }
            }
            
            if (c == letter) count--;
        }
        
        StringBuilder sb = new StringBuilder(stack.size());
        for(Character c : stack) sb.append(c);
        return sb.toString();
    }



//https://leetcode.com/problems/create-maximum-number/discuss/77285/Share-my-greedy-solution) 
//316-Remove Duplicate Letters
//2030. Smallest K-Length Subsequence With Occurrences of a Letter