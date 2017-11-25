package J_6_List_Array.other.Range_Sum_Query;
/* 303. Range Sum Query - Immutable

Given an integer array nums, find the sum of the elements
between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
 */

public class _303_Range_Sum_Query_Immutable {

    //Approach #1 (Brute Force) [Time Limit Exceeded]
    public class NumArray {
        private int[] data;

        public NumArray(int[] nums) {
            data = nums;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += data[k];
            }
            return sum;
        }

    }

//-----------------------------------------------------------------------------/

    //Approach #2 (Caching) [Accepted]
/*
    public class NumArray2 {
        private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();

        public NumArray2(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    map.put(Pair.create(i, j), sum);
                }
            }
        }

        public int sumRange2(int i, int j) {
            return map.get(Pair.create(i, j));
        }

    }
*/




//-----------------------------------------------------------------------------/

    //Approach #3 (Caching) [Accepted]
    public class NumArray3 {
        private int[] sum;

        public NumArray3(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange3(int i, int j) {
            return sum[j + 1] - sum[i];
        }

    }



//-----------------------------------------------------------------------------/

    //Java simple O(n) init and O(1) query solution

    public class NumArray4 {
        int[] nums;

        public NumArray4(int[] nums) {
            for(int i = 1; i < nums.length; i++)
                nums[i] += nums[i - 1];

            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            if(i == 0)
                return nums[j];

            return nums[j] - nums[i - 1];
        }
    }



//-----------------------------------------------------------------------------/

    //My java 3ms solution

    public class NumArray5 {
        private int[] sums;

        public NumArray5(int[] nums) {
            if(nums.length != 0){
                sums = new int[nums.length];

                sums[0] = nums[0];
                for(int i=1; i<nums.length; i++){
                    sums[i] = nums[i] + sums[i-1];
                }
            }
        }

        public int sumRange(int i, int j) {
            return i==0 ? sums[j] : sums[j]-sums[i-1];
        }
    }




//-----------------------------------------------------------------------------/

    //My 3ms clean Java DP solution may help u

    public class NumArray6 {

        private  int[] sum;

        public NumArray6(int[] nums) {
            for (int i = 1; i < nums.length; ++i)
                nums[i] += nums[i - 1];
            this.sum = nums;
        }

        public int sumRange(int i, int j) {
            return sum[j] - (i == 0 ? 0 : sum[i - 1]);
        }
    }



//-----------------------------------------------------------------------------/






//-----------------------------------------------------------------------------/



}
