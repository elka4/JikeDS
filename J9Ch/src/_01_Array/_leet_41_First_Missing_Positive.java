package _01_Array;

public class _leet_41_First_Missing_Positive {

    /*
    找到第一个不能正确对应到自己位置上的正整数。
首先0不是正整数，所以正确的对应顺序应该是
0 -> 1 1 -> 2 2 -> 3
第一想法是先排序，然后从1开始找第一个不连续的数。
更快的方法就是用一个HashSet，把所有的数加进去，然后再从1往length，开始找第一个不连续的。
还有一个更省空间的做法：就是位置映射法，把每个数都swap回自己正确的位置上.
     */

    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 1;
        }

        for(int i = 0; i < nums.length; i++) {
            // 1 ~ n
            if(nums[i] <= nums.length && nums[i] > 0
                    && nums[nums[i] - 1] != nums[i]) {
                // 如果不合法，放到自己正确的位置上，也就是index = nums[i] - 1
                swap(nums, nums[i] - 1, i);
                i--;
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

//-------------------------------------------------------------------------///////////
//Share my O(n)/O(1) solution
/*
The basic idea is for any k positive numbers (duplicates allowed),
 the first missing positive number must be within [1,k+1].
  The reason is like you put k balls into k+1 bins, there must be a bin empty,
   the empty bin can be viewed as the missing number.

Unfortunately, there are 0 and negative numbers in the array,
so firstly I think of using partition technique (used in quick sort) to
put all positive numbers together in one side. This can be finished in O(n) time, O(1) space.
After partition step, you get all the positive numbers lying within A[0,k-1].
Now, According to the basic idea, I infer the first missing number must be within [1,k+1].
I decide to use A[i] (0<=i<=k-1) to indicate whether the number (i+1) exists.
But here I still have to main the original information A[i] holds. Fortunately,
A[i] are all positive numbers, so I can set them to negative to indicate the
existence of (i+1) and I can still use abs(A[i]) to get the original information A[i] holds.
After step 2, I can again scan all elements between A[0,k-1] to find the first
 positive element A[i], that means (i+1) doesn't exist, which is what I want.
 */
    public int firstMissingPositive2(int[] A) {
        int n=A.length;
        if(n==0)
            return 1;
        int k=partition(A)+1;
        int temp=0;
        int first_missing_Index=k;
        for(int i=0;i<k;i++){
            temp=Math.abs(A[i]);
            if(temp<=k)
                A[temp-1]=(A[temp-1]<0)?A[temp-1]:-A[temp-1];
        }
        for(int i=0;i<k;i++){
            if(A[i]>0){
                first_missing_Index=i;
                break;
            }
        }
        return first_missing_Index+1;
    }

    public int partition(int[] A){
        int n=A.length;
        int q=-1;
        for(int i=0;i<n;i++){
            if(A[i]>0){
                q++;
                swap2(A,q,i);
            }
        }
        return q;
    }

    public void swap2(int[] A, int i, int j){
        if(i!=j){
            A[i]^=A[j];
            A[j]^=A[i];
            A[i]^=A[j];
        }
    }
//-------------------------------------------------------------------------///////////
//O(1) space Java Solution
/*
The key here is to use swapping to keep constant space and also make use
of the length of the array, which means there can be at most n positive integers.
 So each time we encounter an valid integer, find its correct position and swap.
  Otherwise we continue.
 */

    public int firstMissingPositive3(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
//-------------------------------------------------------------------------///////////
//Beat 100% Fast Elegant Java Index-Based Solution with Explanation

/*
The basic idea is to traversal and try to move the current value to position
 whose index is exactly the value (swap them). Then travelsal again to find
  the first unusal value, which can not be corresponding to its index.
 */

    public int firstMissingPositive4(int[] nums) {

        int i = 0, n = nums.length;
        while (i < n) {
        // If the current value is in the range of (0,length) and it's not at its correct position,
        // swap it to its correct position.
        // Else just continue;
            if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i])
                swap(nums, i, nums[i]);
            else
                i++;
        }
        int k = 1;

        // Check from k=1 to see whether each index and value can be corresponding.
        while (k < n && nums[k] == k)
            k++;

        // If it breaks because of empty array or reaching the end.
        // K must be the first missing number.
        if (n == 0 || k < n)
            return k;
        else   // If k is hiding at position 0, K+1 is the number.
            return nums[0] == k ? k + 1 : k;

    }

//-------------------------------------------------------------------------///////////


}
