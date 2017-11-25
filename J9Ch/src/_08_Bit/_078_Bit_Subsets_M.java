package _08_Bit;
import java.util.*;
import org.junit.Test;

//  78. Subsets
//  https://leetcode.com/problems/subsets/description/
//  http://www.lintcode.com/zh-cn/problem/subsets/
//  3:
//
public class _078_Bit_Subsets_M {
//------------------------------------------------------------------------------
    //1
    //https://leetcode.com/problems/subsets/discuss/
    //Java codes in a similar way.
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        int totalNumber = 1 << nums.length;
        List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
        for (int i=0; i<totalNumber; i++) {
            List<Integer> set = new LinkedList<Integer>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    set.add(nums[j]);
                }
            }
            collection.add(set);
        }
        return collection;
    }

//------------------------------------------------------------------------------
    //2
    //Simple Java Solution Using Bit Operations
    public class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            int n = nums.length;
            List<List<Integer>> subsets = new ArrayList<>();
            for (int i = 0; i < Math.pow(2, n); i++) {
                List<Integer> subset = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (((1 << j) & i) != 0)
                        subset.add(nums[j]);
                }
                Collections.sort(subset);
                subsets.add(subset);
            }
            return subsets;
        }
    }

//------------------------------------------------------------------------------
    //3
    // 9Ch
    // Non Recursion
    class Jiuzhang {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            int n = nums.length;
            Arrays.sort(nums);

            // 1 << n is 2^n
            // each subset equals to an binary integer between 0 .. 2^n - 1
            // 0 -> 000 -> []
            // 1 -> 001 -> [1]
            // 2 -> 010 -> [2]
            // ..
            // 7 -> 111 -> [1,2,3]
            for (int i = 0; i < (1 << n); i++) {
                List<Integer> subset = new ArrayList<Integer>();
                for (int j = 0; j < n; j++) {
                    // check whether the jth digit in i's binary representation is 1
                    if ((i & (1 << j)) != 0) {
                        subset.add(nums[j]);
                    }
                }
                result.add(subset);
            }
            return result;
        }
    }

//------------------------------------------------------------------------------
}
/*
给定一个含不同整数的集合，返回其所有的子集

 注意事项

子集中的元素排列必须是非降序的，解集必须不包含重复的子集

样例
如果 S = [1,2,3]，有如下的解：

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */