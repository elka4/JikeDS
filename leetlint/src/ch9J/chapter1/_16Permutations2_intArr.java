package ch9J.chapter1;
//I need some deep understanding of the nature of permuation
//especially when there is duplicate
import java.util.ArrayList;
import java.util.Arrays;

//this is the leetcode version, accepting int[] input
public class _16Permutations2_intArr {
public ArrayList<ArrayList<Integer>> permuteUnique(int[] nums) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if(nums == null || nums.length == 0)
        return result;
    ArrayList<Integer> list = new ArrayList<>();
    int[] visited = new int[nums.length];
    
    Arrays.sort(nums);
    helper(result, list, visited, nums);
    return result;
}

public void helper(ArrayList<ArrayList<Integer>> result, 
				   ArrayList<Integer> list, int[] visited, int[] nums) {
	
    if(list.size() == nums.length) {
        result.add(new ArrayList<>(list));
        return;
    }
    
    for(int i = 0; i < nums.length; i++) {
        if (visited[i] == 1 || 
        		(i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)){
            continue;
        /* 上面的判断主要是为了去除重复元素影响。
        比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
        我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
        当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就
        不应该让后面的2使用。*/
        }
        
        visited[i] = 1;
        list.add(nums[i]);
        helper(result, list, visited, nums);
        list.remove(list.size() - 1);
        visited[i] = 0;
    }
}
}


/*Given a list of numsbers with duplicate numsber in it. Find all unique permutations.

Example: For numsbers [1,2,2] the unique permutations are:

[
  [1,2,2],
  [2,1,2],
  [2,2,1]
]
Challenge 
Using recursion to do it is acceptable. 
If you can do it without recursion, that would be great!

Tags 
LinkedIn Recursion Depth First Search
Related Problems 
Medium Next Permutation II 32 %
Medium Permutation Sequence 27 %
Medium Next Permutation 23 %
Medium Permutations 25 %
*/
