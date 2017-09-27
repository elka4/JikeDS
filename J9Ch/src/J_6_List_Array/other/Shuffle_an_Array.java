package J_6_List_Array.other;

import java.util.Arrays;
import java.util.Random;

public class Shuffle_an_Array {
    /*
    洗牌算法流程：
遍历每个i，随机从i和i后面区间里面取一个数，和i交换
index = [i, n - 1]闭区间， 所有每个元素有留在原地的可能性！
原理类似于一群人在一个黑箱子里面抽彩票，每次迭代相当于一个人开始抽奖，
每次抽奖的范围是index = 箱子里面所有剩下的彩票数，数量依次递减。
但最后虽然每个人抽奖的彩票数量不同，但是概率是相同的。
     */

    public class Solution {

        int[] original;
        Random rand;

        public Solution(int[] nums) {
            original = nums;
            rand = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return original;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] nums = Arrays.copyOf(original, original.length);

            for(int i = 0; i < nums.length; i++) {
                // 在n - i ~ n 之间选一个index
                int index = rand.nextInt(nums.length - i) + i;

                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }

            return nums;
        }
    }

/////////////////////////////////////////////////////////////////////

    public class Solution2 {
        private int[] nums;
        private Random random;

        public Solution2(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            if(nums == null) return null;
            int[] a = nums.clone();
            for(int j = 1; j < a.length; j++) {
                int i = random.nextInt(j + 1);
                swap(a, i, j);
            }
            return a;
        }

        private void swap(int[] a, int i, int j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
/////////////////////////////////////////////////////////////////////
public class Solution3 {

    private int[] nums;

    public Solution3(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] rand = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            int r = (int) (Math.random() * (i+1));
            rand[i] = rand[r];
            rand[r] = nums[i];
        }
        return rand;
    }
}
/////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////


}
