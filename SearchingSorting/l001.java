public class l001
{
    public static void main(String[]args)
    {
        //
    }
    //
    public static void binarySearch(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length - 1;
        while(si<=ei)
        {
            int mid = si + (ei-si)/2;
            if(arr[mid] == data)
                return mid;
            else if(arr[mid]>data)
                si = mid+1;
            else
                ei = mid-1;
        }
        return -1;
    }
    //
    public static int firstIndex(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length - 1;

        while(si<=ei)
        {
            int mid = (si + ei)/2;
            if(arr[mid]== data)
            {
                if(mid-1>=0 && arr[mid-1] == data)
                    ei = mid-1;
                else
                    return mid;
            }
            else if(arr[mid]<data)
                si = mid + 1;
            else
                ei = mid - 1;
        } 
        return -1;
    }
    //
    public static int lastIndex(int[]arr,int data)
    {
        int si = 0;
        int ei = arr.length - 1;

        while(si<=ei)
        {
            int mid = (si + ei)/2;
            if(arr[mid]== data)
            {
                if(mid+1 < arr.length && arr[mid+1] == data)
                    si = mid+1;
                else
                    return mid;
            }
            else if(arr[mid]<data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return -1;
    }
    //
    public static int closestElement(int[]arr,int data)
    {
        if(data<arr[si])
            return si;
        if(data>arr[si])
            return ei;
        
        while(si<=ei)
        {
            int mid = (si + ei)/2;
            if(arr[mid] == data)
                return mid;
            else if(arr[mid]<data)
                si = mid + 1;
            else
                ei = mid - 1;
        }
        return arr[ei] - data < arr[si] - data ? ei : si;
    }
    //
    public int perfectLocation(int[]arr,int data)
    {
        int si = 0;        
        int ei = arr.length;
        while(si<=ei)
        {
            if(arr[mid]<=data)
                si = mid+1;
            else
                ei = mid;
        }
        return si;
    }
    //first and last index using perfectLocation
    class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = firstIndex(nums,target);
        ans[1] = lastIndex(nums,target);
        return ans;
    }
    
    public static int firstIndex(int[]nums,int target)
    {
        int n = nums.length;
        int si = 0;
        int ei = n;
        while(si<ei)
        {
            int mid = ((si+ei)>>1);
            if(nums[mid] < target)
                si = mid+1;
            else
                ei = mid;
        }
        return (si<n && nums[si] == target) ? si : -1;
    }

    public static int lastIndex(int[]nums,int target)
    {
        int n = nums.length;
        int si = 0;
        int ei = n;
        while(si<ei)
        {
            int mid = ((si+ei)>>1);
            if(nums[mid] <= target)
                si = mid+1;
            else
                ei = mid;
        }
        return (si-1>=0 && nums[si-1] == target) ? si-1:-1;
    }
}
//2d Search Matrix
class Solution {
    public boolean searchMatrix(int[][] arr, int data) {
        int n = arr.length;
        int m = arr[0].length;
        int si = 0;
        int ei = n*m-1;
        while(si<=ei)
        {
            int mid = si + (ei-si)/2;
            int r = mid/m;
            int c = mid%m;
            if(arr[r][c] == data)
                return true;
            else if(arr[r][c] > data)
                ei = mid - 1;
            else 
                si = mid + 1;
        }        
        return false;
    }
}
    // Count Inversions
    class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int local = localInversions(nums);
        int global = globalInversions(nums,new int[n],0,n-1);
        return local == global;
    }
    
    public int totalInversions(int[]nums,int[]sortedArr,int si,int ei,int mid)
    {
        int i = si;
        int j = mid+1;
        int k = si;
        int count = 0;
        
        while(i<=mid && j<=ei)
        {
            if(nums[i]<=nums[j])
            {
                sortedArr[k++] = nums[i++];
            }
            else
            {
                sortedArr[k++] = nums[j++];
                count+= mid-i+1;
            }
        }
            while (i <= mid || j <= ei)
                sortedArr[k++] = nums[i <= mid ? i++ : j++];

            while (si <= ei)
                nums[(int) si] = sortedArr[(int) si++];
            
            return count;
    }
    
    public int globalInversions(int[]nums,int[]sortedArr,int si,int ei)
    {
        if(si>=ei)
            return 0;
        int count = 0;
        int mid = si + (ei - si)/2;
        count+= globalInversions(nums,sortedArr,si,mid);
        count+= globalInversions(nums,sortedArr,mid+1,ei);  
        count+= totalInversions(nums,sortedArr,si,ei,mid);
        return count;
    }
    
    public int localInversions(int[] nums)
    {
        int n = nums.length;
        int count = 0;
        for(int i=0;i<n-1;i++)
        {
            if(nums[i] > nums[i+1])
                count++;
        }
        return count;
    }
}
    //33. Search in Rotated Sorted Array
    class Solution {
    public int search(int[] arr, int val) {
        
        int n = arr.length;
        int si = 0;
        int ei = n-1;
        
        while(si<=ei)
        {
            int mid = si + (ei-si)/2;
            if(arr[mid] == val)
                return mid;
            if(si == ei)
                return arr[mid] == val ? mid : -1;
            
            if(arr[si] <= arr[mid])  
            {
                if(val >= arr[si] && val <= arr[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            }
            else if(arr[mid] <= arr[ei])
            {
                if(val >= arr[mid] && val <= arr[ei])
                    si = mid + 1;
                else
                    ei = mid - 1;
            }            
        }
        return -1;
    }
}
    // 658. Find K Closest Elements
    class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int si = 0;
        int ei = n - 1;
        while ((ei - si + 1) > k)
        {
            if ((x - arr[si]) > (arr[ei] - x))
                si++;
            else
                ei--;
        }
        
        List<Integer> ans = new ArrayList<>();
        for(int i=si;i<=ei;i++)
            ans.add(arr[i]);
        return ans;
    }
}
    //two sum
    class Solution {
    public int[] twoSum(int[] arr, int tar) {
        
        int[] ans = new int[2];
        int si = 0;
        int ei = arr.length - 1;
        
        while(si<ei)
        {
            if(si!=0 && arr[si] == arr[si-1])
            {
                si++;
                continue;
            }
            
            if(arr[si] + arr[ei] >tar)
            {
                ei--;
            }
            else if(arr[si] + arr[ei] <tar)
            {
                si++;
            }
            else
            {
                ans[0] = si + 1;
                ans[1] = ei + 1;
                return ans;
            }
        }
        
        return ans;
    }
}
    //3 sum
    class Solution {
    public List<List<Integer>> threeSum(int[] arr) {
        return threeSum(arr,0);   
    }
        
       public static List<List<Integer>> twoSum(int[] arr, int si, int ei, int target) {
    List<List<Integer>> res = new ArrayList<>();
    int left = si;
    int right = ei;

    while (left < right) {
      if (left != si && arr[left] == arr[left - 1]) {
        left++;
        continue;
      }

      int sum = arr[left] + arr[right];
      if (sum == target) {
        List<Integer> list = new ArrayList<>();
        list.add(arr[left]);
        list.add(arr[right]);
        res.add(list);

        left++;
        right--;
      } else if (sum > target) {
        right--;
      } else {
        left++;
      }
    }

    return res;
  }

  public static List<List<Integer>> threeSum(int[] nums, int targ) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums.length < 3) {
      return res;
    }

    Arrays.sort(nums);
    for (int i = 0; i <= nums.length - 3; i++) {
      if (i != 0 && nums[i - 1] == nums[i]) continue;

      int val1 = nums[i];
      int target = targ - val1;
      List<List<Integer>> subRes = twoSum(nums, i + 1, nums.length - 1, target);

      for (List<Integer> val : subRes) {
        val.add(val1);
        res.add(val);
      }
    }
    return res;
  }

}
    //4sum
    //ksum --> recursive --> base case --> 2sum ||3sum || 4sum --> teri marzi

    //Search in Rotated Sorted Array
    class Solution {
    public int search(int[] arr, int val) {
        
        int n = arr.length;
        int si = 0;
        int ei = n-1;
        
        while(si<=ei)
        {
            int mid = si + (ei-si)/2;
            if(arr[mid] == val)
                return mid;
            if(si == ei)
                return arr[mid] == val ? mid : -1;
            
            if(arr[si] <= arr[mid])  
            {
                if(val >= arr[si] && val <= arr[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            }
            else if(arr[mid] <= arr[ei])
            {
                if(val >= arr[mid] && val <= arr[ei])
                    si = mid + 1;
                else
                    ei = mid - 1;
            }            
        }
        return -1;
    }
}
}