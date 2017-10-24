package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
import java.util.stream.*;

public class _666_Tree_Path_Sum_IV_M {
    class Solution {
        int sum = 0;
        Map<Integer, Integer> tree = new HashMap<>();

        public int pathSum(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            for (int num : nums) {
                int key = num / 10;
                int value = num % 10;
                tree.put(key, value);
            }

            traverse(nums[0] / 10, 0);

            return sum;
        }

        private void traverse(int root, int preSum) {
            int level = root / 10;
            int pos = root % 10;
            int left = (level + 1) * 10 + pos * 2 - 1;
            int right = (level + 1) * 10 + pos * 2;

            int curSum = preSum + tree.get(root);

            if (!tree.containsKey(left) && !tree.containsKey(right)) {
                sum += curSum;
                return;
            }

            if (tree.containsKey(left)) traverse(left, curSum);
            if (tree.containsKey(right)) traverse(right, curSum);
        }
    }


    class Solution2 {
        public int pathSum(int[] nums) {
            int[][] m = new int[5][8];
            for (int n : nums) {
                int i = n / 100; // i is 1 based index;
                int j = (n % 100) / 10 - 1; // j used 0 based index;
                int v = n % 10;
                m[i][j] = m[i - 1][j / 2] + v;
            }

            int sum = 0;
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i == 4 || m[i][j] != 0 && m[i + 1][j * 2] == 0 && m[i + 1][j * 2 + 1] == 0){
                        sum += m[i][j];
                    }
                }
            }
            return sum;
        }
    }


    class Solution3{
        public int pathSum(int[] nums) {
            Map<Integer, Integer> positionToNodeMap = new HashMap<>();
            Arrays.stream(nums).forEach( num -> {
                int[] digits = IntStream.range(0, 3).map(i -> (num + "").charAt(i) - '0').toArray();
                positionToNodeMap.put((int)Math.pow(2, digits[0] - 1) - 1 + digits[1], digits[2]);
            });
            int[] res = new int[1];
            dfs(1, 0, res, positionToNodeMap);
            return res[0];
        }

        private void dfs(int cur, int sum, int[] res, Map<Integer, Integer> map) {
            if(!map.containsKey(cur)) return;
            int left = cur * 2, right = cur * 2 + 1, totalSum = sum + map.get(cur);
            if(!map.containsKey(left) && !map.containsKey(right)) { res[0] += totalSum; return; } // Leaf node
            dfs(left, totalSum, res, map);
            dfs(right, totalSum, res, map);
        }
    }
}
