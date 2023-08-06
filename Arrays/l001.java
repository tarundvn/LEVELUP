public class l001 {
    Sys
  //rotate an array
  class Solution {

    public void rotate(int[] nums, int k) {
      int n = nums.length;
      k = ((k % n) + n) % n;
      reverse(nums, 0, n - k - 1);
      reverse(nums, n - k, n - 1);
      reverse(nums, 0, n - 1);
    }

    public void reverse(int[] arr, int si, int ei) {
      while (si <= ei) {
        int temp = arr[si];
        arr[si] = arr[ei];
        arr[ei] = temp;
        si++;
        ei--;
      }
    }
  }
  //segregate even odd

  //905. Sort Array By Parity
  class Solution {
    public int[] sortArrayByParity(int[] arr) {
        int p = -1;
        int i = 0;
        int n = arr.length;
        while(i<n)
        {
            if((arr[i]&1)==0)
            {
                p++;
                int temp = arr[p];
                arr[p] = arr[i];
                arr[i] = temp;
            }
            i++;
        }
        return arr;
    }    
}
  //922. Sort Array By Parity II
  class Solution {
    public int[] sortArrayByParityII(int[] arr) {
        int e = 0;
        int o = 1; 
        int n = arr.length;
        while(true)
        {
            while(e<n && (arr[e]&1)==0)
                e+=2;
            while(o<n && (arr[o]&1)!=0)
                o+=2;
            if(e>=n || o>=n)
                return arr;
            int temp = arr[e];
            arr[e] = arr[o];
            arr[o]= temp;
        }
    }
}
  //2149. Rearrange Array Elements by Sign
  class Solution {
    public int[] rearrangeArray(int[] arr) {
        
        int n = arr.length;
        int[]ans = new int[n];
        int eidx = 0;
        int[] earr = new int[n/2];
        int oidx = 0;
        int[] oarr = new int[n/2];
        
        for(int i=0;i<n;i++)
        {
            if(arr[i]>=0)
            {
                earr[eidx] = arr[i];
                eidx++;
            }
            else
            {
                oarr[oidx] = arr[i];
                oidx++;
            }
        }
        int idx = 0;
        for(int i=0;i<n/2;i++)
        {
            ans[idx] = earr[i];
            idx = idx + 2;
        }
        idx = 1;
        for(int i=0;i<n/2;i++)
        {
            ans[idx] = oarr[i];
            idx = idx + 2;
        }
        return ans;
    }
}
  //283. Move Zeroes
  class Solution {
    public void moveZeroes(int[] arr) {
        int p = -1;
        int i = 0;
        int n = arr.length;
        while(i<n)
        {
            if(arr[i] != 0)
            {
                p++;
                int temp = arr[p];
                arr[p] = arr[i];
                arr[i] = temp;
            }
            i++;
        }
    }
}
  //27. Remove Element
  class Solution {
    public int removeElement(int[] arr, int val) {
        int p = -1;
        int i = 0;
        int n = arr.length;
        while(i<n)
        {
            if(arr[i] != val)
            {
                p++;
                int temp = arr[p];
                arr[p] = arr[i];
                arr[i] = temp;
            }
            i++;
        }
        return p+1;
    }
}
  //26. Remove Duplicates from Sorted Array
  class Solution {
    public int removeDuplicates(int[] nums) {
        
        if(nums.length == 0)
            return 0;
        
        int i=0;
        for(int j=0;j<nums.length;j++)
        {
            if(nums[j]!=nums[i])
            {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
  //


}
