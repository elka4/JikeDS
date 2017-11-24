package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;


public class _046_BackTracking_Permutations_M {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (num.length == 0) return ans;
        List<Integer> l0 = new ArrayList<Integer>();
        l0.add(num[0]);
        ans.add(l0);
        for (int i = 1; i < num.length; ++i) {
            List<List<Integer>> new_ans = new ArrayList<List<Integer>>();
            for (int j = 0; j <= i; ++j) {
                for (List<Integer> l : ans) {
                    List<Integer> new_l = new ArrayList<Integer>(l);
                    new_l.add(j, num[i]);
                    new_ans.add(new_l);
                }
            }
            ans = new_ans;
        }
        return ans;
    }


    public List<List<Integer>> permute2(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int n : num) {
            int size = res.size();
            for (; size > 0; size--) {
                List<Integer> r = res.pollFirst();
                for (int i = 0; i <= r.size(); i++) {
                    List<Integer> t = new ArrayList<Integer>(r);
                    t.add(i, n);
                    res.add(t);
                }
            }
        }
        return res;
    }

 //
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


//-------------------------------------------------------------------------//

    // 9Ch
    public class Jiuzhang1 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            if (nums == null) {
                return results;
            }

            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }

            List<Integer> permutation = new ArrayList<Integer>();
            Set<Integer> set = new HashSet<>();
            helper(nums, permutation, set, results);

            return results;
        }

        // 1. 找到所有以permutation 开头的排列
        public void helper(int[] nums,
                           List<Integer> permutation,
                           Set<Integer> set,
                           List<List<Integer>> results) {
            // 3. 递归的出口
            if (permutation.size() == nums.length) {
                results.add(new ArrayList<Integer>(permutation));
                return;
            }


            // [3] => [3,1], [3,2], [3,4] ...
            for (int i = 0; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    continue;
                }

                permutation.add(nums[i]);
                set.add(nums[i]);
                helper(nums, permutation, set, results);
                set.remove(nums[i]);
                permutation.remove(permutation.size() - 1);
            }

        }
    }

    // Non-Recursion
    class Jiuzhang2 {
        /**
         * @param nums: A list of integers.
         * @return: A list of permutations.
         */
        public List<List<Integer>> permute(int[] nums) {
            ArrayList<List<Integer>> permutations
                    = new ArrayList<List<Integer>>();
            if (nums == null) {

                return permutations;
            }

            if (nums.length == 0) {
                permutations.add(new ArrayList<Integer>());
                return permutations;
            }

            int n = nums.length;
            ArrayList<Integer> stack = new ArrayList<Integer>();

            stack.add(-1);
            while (stack.size() != 0) {
                Integer last = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);

                // increase the last number
                int next = -1;
                for (int i = last + 1; i < n; i++) {
                    if (!stack.contains(i)) {
                        next = i;
                        break;
                    }
                }
                if (next == -1) {
                    continue;
                }

                // generate the next permutation
                stack.add(next);
                for (int i = 0; i < n; i++) {
                    if (!stack.contains(i)) {
                        stack.add(i);
                    }
                }

                // copy to permutations set
                ArrayList<Integer> permutation = new ArrayList<Integer>();
                for (int i = 0; i < n; i++) {
                    permutation.add(nums[stack.get(i)]);
                }
                permutations.add(permutation);
            }

            return permutations;
        }
    }

}
/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

 */