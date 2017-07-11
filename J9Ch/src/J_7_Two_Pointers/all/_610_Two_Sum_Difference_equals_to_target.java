package J_7_Two_Pointers.all;

import java.util.Arrays;
import java.util.Comparator;

/** 610 Two Sum - Difference equals to target


 * Created by tianhuizhu on 6/28/17.
 */
public class _610_Two_Sum_Difference_equals_to_target {

    class Pair {
        public int idx, num;
        public Pair(int i, int n) {
            this.idx = i;
            this.num = n;
        }
    }

    public class Solution {
        /*
         * @param nums an array of Integer
         * @param target an integer
         * @return [index1 + 1, index2 + 1] (index1 < index2)
         */
        public int[] twoSum7(int[] nums, int target) {
            // write your code here
            int[] indexs = new int[2];
            if (nums == null || nums.length < 2)
                return indexs;

            if (target < 0)
                target = -target;

            int n = nums.length;
            Pair[] pairs = new Pair[n];
            for (int i = 0; i < n; ++i)
                pairs[i] = new Pair(i, nums[i]);

            Arrays.sort(pairs, new Comparator<Pair>(){
                public int compare(Pair p1, Pair p2){
                    return p1.num - p2.num;
                }
            });

            int j = 0;
            for (int i = 0; i < n; ++i) {
                if (i == j)
                    j ++;
                while (j < n && pairs[j].num - pairs[i].num < target)
                    j ++;

                if (j < n && pairs[j].num - pairs[i].num == target) {
                    indexs[0] = pairs[i].idx + 1;
                    indexs[1] = pairs[j].idx + 1;
                    if (indexs[0] > indexs[1]) {
                        int temp = indexs[0];
                        indexs[0] = indexs[1];
                        indexs[1] = temp;
                    }
                    return indexs;
                }
            }
            return indexs;
        }
    }
}
