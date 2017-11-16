package _05_DFS._Back_Subset_Permutation_Combination;
import org.junit.Test;

import java.util.*;
import java.util.stream.*;

//  46. Permutations
//  https://leetcode.com/problems/permutations/description/
//  http://www.lintcode.com/zh-cn/problem/permutations/
public class _046_BackTracking_Permutations_M {

    //  https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning

////////////////////////////////////////////////////////////////////////////////////
    //My AC simple iterative java/python solution
    /*
    the basic idea is, to permute n numbers, we can add the nth number into the resulting List<List<Integer>> from the n-1 numbers, in every possible position.

For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>> (let's call it "answer").

Then, 2 can be added in front or after 1. So we have to copy the List<Integer> in answer (it's just {1}), add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in position 1. Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.

Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and {1,2}, and add 3 into position 1, then do the same thing for position 3. Finally we have 2*3=6 lists in answer, which is what we want.
     */
    public List<List<Integer>> permute01(int[] num) {
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
    @Test public void test01(){ System.out.println(permute01(new int[]{1,2,3})); }
    //[[3, 2, 1], [3, 1, 2], [2, 3, 1], [1, 3, 2], [2, 1, 3], [1, 2, 3]]

////////////////////////////////////////////////////////////////////////////////////

    public List<List<Integer>> permute02(int[] num) {
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
    @Test public void test02(){ System.out.println(permute02(new int[]{1,2,3})); }

////////////////////////////////////////////////////////////////////////////////////
    //  A general approach to backtracking questions in Java
    // (Subsets, Permutations, Combination Sum, Palindrome Partioning)
    public List<List<Integer>> permute03(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(result, new ArrayList<>(), nums);
        return result;
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
    @Test public void test03(){ System.out.println(permute03(new int[]{1,2,3})); }


/////////////////////////////////////////////////////////////////////////////////////
    //Java Clean Code - Two recursive solutions
    //Bottom up? approach - 280ms
    public List<List<Integer>> permute04(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0) {
            return permutations;
        }

        collectPermutations(nums, 0, new ArrayList<>(), permutations);
        return permutations;
    }

    private void collectPermutations(int[] nums, int start, List<Integer> permutation,
                                     List<List<Integer>>  permutations) {

        if (permutation.size() == nums.length) {
            permutations.add(permutation);
            return;
        }

        for (int i = 0; i <= permutation.size(); i++) {
            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(i, nums[start]);
            collectPermutations(nums, start + 1, newPermutation, permutations);
        }
    }
    @Test public void test04(){ System.out.println(permute04(new int[]{1,2,3})); }

//////////////////////////////////////////////////////////////////////////////////////

    //Base case and build approach - 524ms
    public List<List<Integer>> permute05(int[] nums) {
        return permute05(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    private List<List<Integer>> permute05(List<Integer> nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.size() == 0) {
            return permutations;
        }
        if (nums.size() == 1) {
            List<Integer> permutation = new ArrayList<>();
            permutation.add(nums.get(0));
            permutations.add(permutation);
            return permutations;
        }

        List<List<Integer>> smallPermutations = permute05(nums.subList(1, nums.size()));
        int first = nums.get(0);
        for(List<Integer> permutation : smallPermutations) {
            for (int i = 0; i <= permutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<>(permutation);
                newPermutation.add(i, first);
                permutations.add(newPermutation);
            }
        }
        return permutations;
    }
    @Test public void test05(){ System.out.println(permute05(new int[]{1,2,3})); }


/////////////////////////////////////////////////////////////////////////////////////
    //https://discuss.leetcode.com/topic/6740/share-my-three-different-solutions
    //Share my three different solutions
    public List<List<Integer>> permute06(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(nums[0]);
        result.add(first);
        for (int i = 1; i < nums.length; ++i) {
            List<List<Integer>> newResult = new ArrayList<>();
            for (List<Integer> existing : result) {
                for (int pos = 0; pos <= existing.size(); ++pos) {
                    List<Integer> copy = new ArrayList<>(existing);
                    copy.add(pos, nums[i]);
                    newResult.add(copy);
                }
            }
            result = newResult;
        }
        return result;
    }
    @Test public void test06(){ System.out.println(permute06(new int[]{1,2,3})); }

/////////////////////////////////////////////////////////////////////////////////////
    //Java solution easy to understand (backtracking)
    List<List<Integer>> list;

    public List<List<Integer>> permute07(int[] nums) {

        list = new ArrayList<>();
        ArrayList<Integer> perm = new ArrayList<Integer>();
        backTrack(perm,0,nums);
        return list;
    }

    void backTrack (ArrayList<Integer> perm,int i,int[] nums){
        //Permutation completes
        if(i==nums.length){
            list.add(new ArrayList(perm));
            return;
        }

        ArrayList<Integer> newPerm = new ArrayList<Integer>(perm);

        //Insert elements in the array by increasing index
        for(int j = 0; j <= i; j++){
            newPerm.add(j, nums[i]);
            backTrack(newPerm, i + 1, nums);
            newPerm.remove(j);
        }
    }
    @Test public void test07(){ System.out.println(permute07(new int[]{1,2,3})); }

//////////////////////////////////////////////////////////////////////////////////
    //8
//    A new ArrayList<Integer> newPerm is not necessary.
//
//            Try this:

    public List<List<Integer>> permute08(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute08(new ArrayList<Integer>(), 0, nums, result);
        return result;
    }

    private void permute08(ArrayList<Integer> path, int index,
                           int[] nums, List<List<Integer>> result){
        if(index == nums.length){
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for(int j = 0; j <= index; ++j ){
            path.add(j, nums[index]);
            permute08(path, index + 1, nums, result);
            path.remove(j);
        }
    }
    @Test public void test08(){ System.out.println(permute08(new int[]{1,2,3})); }


/////////////////////////////////////////////////////////////////////////////////////
    //9
    //jiuzhang
    public List<List<Integer>> permute_J1(int[] nums) {
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
    @Test public void test09(){ System.out.println(permute_J1(new int[]{1,2,3})); }


////////////////////////////////////////////////////////////////////////////////////
    //10
    // 9Ch
    // Non-Recursion
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute_J2(int[] nums) {
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
    @Test public void test10(){ System.out.println(permute_J2(new int[]{1,2,3})); }
    //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

////////////////////////////////////////////////////////////////////////////////////
    //11
    //bit
/*Clean Java, 3 ms using bit-manipulation with explanation

2
    A anton15
    Reputation:  120
    We know that the number of permutations is n!. Hence, the maximum number of elements we can handle without overflowing the integer is 12 (12! < Integer.MAX_VALUE). So instead of using a boolean array to store the information about the elements that we have already used, we can just set/unset bits of our integer.

    For those who are not too familiar with bit manipulation:

            (set & (1 << i)) == 0 --------------------- checks if the bit at index i is unset.
            alternatively (set & (1 << i)) > 0 -------- checks if the bit at index i is set.
            set |= (1 << i) --------------------------- sets the bit at index i to 1.
    set &= ~(1 << i) -------------------------- clears the bit at index i to 0.
    In this case set is a 32-bit integer. One might think that somehow a short (16-bit) can be more efficient than a 32-bit integer, since we only need 12 bits. This is not true, most CPUs are 32-bit based, so having a short of length 16 will simply mask the remaining 16 bits, which doesn't give us any extra value.*/

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int set = 0;
        List<Integer> perm = new ArrayList<>(nums.length);
        permute(nums, set, perm);
        return res;
    }

    private void permute(int[] nums, int set, List<Integer> perm) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((set & (1 << i)) == 0) {
                set |= (1 << i);
                perm.add(nums[i]);
                permute(nums, set, perm);
                set &= ~(1 << i);
                perm.remove(perm.size()-1);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////
    //12
/*My Java Solution beats 90% javasubmissions using 2ms

4
    A ashitakasan
    Reputation:  4*/
    public class Solution12 {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> list = new LinkedList<>();
            permute(list, nums, 0);
            return list;
        }

        private void permute(List<List<Integer>> list, int[] nums, int start){
            if(start == nums.length - 1){
                List<Integer> l = new LinkedList<>();
                for(int n:nums)
                    l.add(n);
                list.add(l);
            }
            else{
                for(int i=start; i<nums.length; i++){
                    int tmp = nums[i];
                    nums[i] = nums[start];
                    nums[start] = tmp;
                    permute(list, nums, start+1);
                    tmp = nums[i];
                    nums[i] = nums[start];
                    nums[start] = tmp;
                }
            }
        }

    }
////////////////////////////////////////////////////////////////////////////////////
}

/*
全排列

给定一个数字列表，返回其所有可能的排列。

 注意事项

你可以假设没有重复数字。

样例
给出一个列表[1,2,3]，其全排列为：

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
挑战
使用递归和非递归分别解决。

标签
领英 递归
相关题目
中等 用递归打印数字 26 %
中等 第k个排列 29 %
中等 带重复元素的排列
 */

/*  Leetcode
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