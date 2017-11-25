package _01_Array.Range_Sum_Query;

/* 307. Range Sum Query - Mutable

Given an integer array nums, find the sum of the elements between
indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element
at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function
is distributed evenly.
 */

public class _307_Range_Sum_Query_Mutable {

    //Approach #1 (Naive) [Time Limit Exceeded]
/*
    public class NumArray {
        private int[] nums;
        public int sumRange(int i, int j) {
            int sum = 0;
            for (int l = i; l <= j; l++) {
                sum += data[l];
            }
            return sum;
        }

        public int update(int i, int val) {
            nums[i] = val;
        }
        // Time Limit Exceeded
    }
*/

//-----------------------------------------------------------------------------/

    //Approach #2 (Sqrt decomposition) [Accepted]

    public class NumArray2 {
        private int[] b;
        private int len;
        private int[] nums;

        public NumArray2(int[] nums) {
            this.nums = nums;
            double l = Math.sqrt(nums.length);
            len = (int) Math.ceil(nums.length/l);
            b = new int [len];
            for (int i = 0; i < nums.length; i++)
                b[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) {
                for (int k = i; k <= j; k++)
                    sum += nums[k];
            } else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++)
                    sum += nums[k];
                for (int k = startBlock + 1; k <= endBlock - 1; k++)
                    sum += b[k];
                for (int k = endBlock * len; k <= j; k++)
                    sum += nums[k];
            }
            return sum;
        }

        public void update(int i, int val) {
            int b_l = i / len;
            b[b_l] = b[b_l] - nums[i] + val;
            nums[i] = val;
        }
        // Accepted
    }



//-----------------------------------------------------------------------------/

    //Approach #3 (Segment tree) [Accepted]

    public class NumArray3 {
        int[] tree;
        int n;
        public NumArray3(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }

        private void buildTree(int[] nums) {
            for (int i = n, j = 0;  i < 2 * n; i++,  j++)
                tree[i] = nums[j];
            for (int i = n - 1; i > 0; --i)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        void update(int pos, int val) {
            pos += n;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos;
                int right = pos;
                if (pos % 2 == 0) {
                    right = pos + 1;
                } else {
                    left = pos - 1;
                }
                // parent is updated after child is updated
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int l, int r) {
            // get leaf with value 'l'
            l += n;
            // get leaf with value 'r'
            r += n;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }

    }


//-----------------------------------------------------------------------------/




//-----------------------------------------------------------------------------/




//-----------------------------------------------------------------------------/




//-----------------------------------------------------------------------------/




//-----------------------------------------------------------------------------/




//-----------------------------------------------------------------------------/




//-----------------------------------------------------------------------------/





}
