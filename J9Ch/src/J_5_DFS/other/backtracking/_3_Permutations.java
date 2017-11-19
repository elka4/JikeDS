package J_5_DFS.other.backtracking;
import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/permutations/#/discuss

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
public class _3_Permutations {

///Permutations : https://leetcode.com/problems/permutations/


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list,
                           List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                // element already exists, skip
                if(tempList.contains(nums[i])) continue;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

//-------------------------------------------------------------------------/
//My AC simple iterative java/python solution

/*
the basic idea is, to permute n numbers, we can add the nth number into the
 resulting List<List<Integer>> from the n-1 numbers, in every possible position.

For example, if the input num[] is {1,2,3}: First, add 1 into the initial
 List<List<Integer>>
 (let's call it "answer").

Then, 2 can be added in front or after 1. So we have to copy the
List<Integer> in answer
(it's just {1}), add 2 in position 0 of {1}, then copy the original
 {1} again, and add 2 in
 position 1. Now we have an answer of {{2,1},{1,2}}. There are 2
  lists in the current answer.

Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0;
 then copy {2,1} and
{1,2}, and add 3 into position 1, then do the same thing for position 3.
 Finally we have 2*3=6
 lists in answer, which is what we want.


 */

    public List<List<Integer>> permute2(int[] num) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (num.length ==0) return ans;
        List<Integer> l0 = new ArrayList<Integer>();
        l0.add(num[0]);
        ans.add(l0);

        for (int i = 1; i< num.length; ++i){
            List<List<Integer>> new_ans = new ArrayList<List<Integer>>();

            for (int j = 0; j<=i; ++j){

                for (List<Integer> l : ans){
                    List<Integer> new_l = new ArrayList<Integer>(l);
                    new_l.add(j,num[i]);
                    new_ans.add(new_l);
                }
            }

            ans = new_ans;
        }

        return ans;
    }
/*
def permute(self, nums):
    perms = [[]]
    for n in nums:
        new_perms = []
        for perm in perms:
            for i in xrange(len(perm)+1):
                new_perms.append(perm[:i] + [n] + perm[i:])   ###insert n
        perms = new_perms
    return perms
 */

//-------------------------------------------------------------------------////////
//I used your idea of adding each next value to every
// possible position of current list,
// but have done it with recursion.

    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length == 0) return result;

        backtrack(result, nums, new ArrayList<Integer>(), 0);

        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] nums,
                           List<Integer> currentList, int index) {
        if (currentList.size() == nums.length) {
            result.add(currentList);
            return;
        }

        int n = nums[index];
        for (int i = 0; i <= currentList.size(); i++) {
            List<Integer> copy = new ArrayList<Integer>(currentList);
            copy.add(i, n);
            backtrack(result, nums, copy, index + 1);
        }


    }




//-------------------------------------------------------------------------//////
//Very nice solution! you can simply a little bit
// by removing a couple of lines:

    public List<List<Integer>> permute4(int[] num) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (num.length ==0)
            return ans;
        ans.add(new ArrayList<Integer>());

        for (int i = 0; i< num.length; ++i){
            List<List<Integer>> new_ans = new ArrayList<List<Integer>>();

            for (int j = 0; j<=i; ++j){

                for (List<Integer> l : ans){
                    List<Integer> new_l = new ArrayList<Integer>(l);
                    new_l.add(j,num[i]);
                    new_ans.add(new_l);
                }
            }
            ans = new_ans;
        }
        return ans;
    }



//-------------------------------------------------------------------------///
//Good idea! I was thinking we can optimize the solution by
// adding on to a single
// answer list instead of recreating a new list every time!

    public List<List<Integer>> permute5(int[] nums) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(nums.length == 0) return result;
        List<Integer> cur = new LinkedList<Integer>();
        cur.add(nums[0]);
        result.add(cur);

        for(int i = 1; i < nums.length; i++) {
            int curSize = result.size();

            for(int j = 0; j < curSize; j++) {

                for(int x = 0; x < result.get(j).size(); x++) {
                    List<Integer> newList = new LinkedList<Integer>(result.get(j));
                    newList.add(x, nums[i]);
                    result.add(newList);
                }
                result.get(j).add(nums[i]);
            }
        }
        return result;
    }



//-------------------------------------------------------------------------/////
//I used your idea, but have a slightly different starting point before
// getting into the loop.
// I think it's logically more clear and don't need to handle corner
// case when num is empty.

    public List<List<Integer>> permute6(int[] num) {

        List<List<Integer>> rst = new ArrayList<List<Integer>>();

        rst.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            List<List<Integer>> tmpRst = new ArrayList<List<Integer>>();

            for (List<Integer> pm : rst) {

                for (int j = 0; j < pm.size() + 1; j++) {
                    List<Integer> tmpPm = new ArrayList<Integer>(pm);
                    tmpPm.add(j, num[i]);
                    tmpRst.add(tmpPm);
                }
            }

            rst = new ArrayList<List<Integer>>(tmpRst);
        }

        return rst;
    }


//-------------------------------------------------------------------------/////////
//Share my short iterative JAVA solution
    public List<List<Integer>> permute7(int[] num) {
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

    /*
    就是遍历nums（第一个for loop），当开始进行nums[i]时，
    res中的结果是nums[0]至nums[i-1]的全排列。
    每一次循环中，需要将nums[i]加入到res中的每一个结果中的每一个位置，
    然后开始下一次循环。
    具体做法是，每一次循环开始，先记录当前res的大小（size），取出res中的第一个，
    在每个位置添加nums[i]然后加到res末尾（第三个for loop，循环r.size()次），
    一共进行size次（第二个for loop）。
     */



//-------------------------------------------------------------------------/
//Java Clean Code - Two recursive solutions

   // Bottom up? approach - 280ms
   public List<List<Integer>> permute8(int[] nums) {
       List<List<Integer>> permutations = new ArrayList<>();
       if (nums.length == 0) {
           return permutations;
       }

       collectPermutations(nums, 0, new ArrayList<>(), permutations);
       return permutations;
   }

    private void collectPermutations(int[] nums, int start,
                                     List<Integer> permutation,
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


   /* Code flow

    nums = 1,2,3

    start = 0, permutation = []
    i = 0, newPermutation = [1]
    start = 1, permutation = [1]
    i = 0, newPermutation = [2, 1]
    start = 2, permutation = [2, 1]
    i = 0, newPermutation = [3, 2, 1]
    i = 1, newPermutation = [2, 3, 1]
    i = 2, newPermutation = [2, 1, 3]
    i = 1, newPermutation = [1, 2]
    start = 2, permutation = [1, 2]
    i = 0, newPermutation = [3, 1, 2]
    i = 1, newPermutation = [1, 3, 2]
    i = 2, newPermutation = [1, 2, 3]
    Base case and build approach - 524ms*/
   public List<List<Integer>> permute9(int[] nums) {
       return permute9(Arrays.stream(nums).boxed().collect(Collectors.toList()));
   }

    private List<List<Integer>> permute9(List<Integer> nums) {
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

        List<List<Integer>> smallPermutations = permute9(nums.subList(1, nums.size()));
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

  /*  Code flow

    nums = 1,2,3

    smallPermutations(2, 3)
    smallPermutations(3)
		return [[3]]
    first = 2
    permutation = [3]
    i = 0, newPermutation = [2, 3]
    i = 1, newPermutation = [3, 2]
            return [[2, 3], [3, 2]]
    first = 1
    permutation = [2, 3]
    i = 0, newPermutation = [1, 2, 3]
    i = 1, newPermutation = [2, 1, 3]
    i = 2, newPermutation = [2, 3, 1]
    permutation = [3, 2]
    i = 0, newPermutation = [1, 3, 2]
    i = 1, newPermutation = [3, 1, 2]
    i = 2, newPermutation = [3, 2, 1]
*/

//-------------------------------------------------------------------------/
    // 这个也是Top100的版本

//My Java Accepted solution without additional space

    public List<List<Integer>> permute10(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permute(result, num, 0);
        return result;
    }

    private void permute(List<List<Integer>> result, int[] array, int start) {
        if (start >= array.length) {
            List<Integer> current = new ArrayList<Integer>();
            for (int a : array) {
                current.add(a);
            }
            result.add(current);
        } else {
            for (int i=start; i<array.length; i++) {
                swap(array, start, i);
                permute(result, array, start+1);
                swap(array, start, i);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


//-------------------------------------------------------------------------/
//If you want it to be in order, just sort the array before you go.
// It won't affect the complexity.


    public  List<List<Integer>> permute11(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        permute(num, 0, res);
        return res;
    }

    public  void permute(int[] num, int level, List<List<Integer>> res) {
        if (level == num.length) {
            List<Integer> row = new ArrayList<Integer>();
            for (int a : num) row.add(a);
            res.add(row);
            return;
        }
        for (int i = level; i < num.length; i++) {
            swap2(num, level, i);
            permute(num, level + 1, res);
            swap2(num, level, i); // reset
        }
    }

    // 留意这个swap的方法，还有一种用位运算的
    public  void swap2(int[] num, int i, int j) {
        if (i == j) return;
        num[i] = num[j] - num[i];
        num[j] = num[j] - num[i];
        num[i] = num[j] + num[i];
    }

//-------------------------------------------------------------------------/
//I used your idea and revised a little bitbit on using extra space in the way I think
// will reduce times of array and list copy. The running time is now 2ms and beats 96%.

    public class Solution {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        int[] numcopy;
        int n;
        public List<List<Integer>> permute(int[] num) {
            numcopy = num;
            n = numcopy.length;
            subPermute(0);
            return rst;
        }

        private void subPermute(int start){

            if(start >= n) {
                List<Integer> perm = new ArrayList<Integer>();
                for(int elm : numcopy) {
                    perm.add(elm);
                }
                rst.add(perm);
            } else {
                for(int i = start; i < n; i++) {
                    swap(start, i);
                    subPermute(start+1);
                    swap(start, i);
                }
            }
        }

        private void swap(int i, int j){
            int tmp = numcopy[i];
            numcopy[i] = numcopy[j];
            numcopy[j] = tmp;
        }}

//-------------------------------------------------------------------------/
//Java solution easy to understand (backtracking)
public class Solution2 {

    List<List<Integer>> list;

    public List<List<Integer>> permute(int[] nums) {

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
        for(int j=0;j<=i;j++){
            newPerm.add(j,nums[i]);
            backTrack(newPerm,i+1,nums);
            newPerm.remove(j);
        }

    }
}






//-------------------------------------------------------------------------////////

   // A new ArrayList<Integer> newPerm is not necessary.

   //         Try this:

    public List<List<Integer>> permute12(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(new ArrayList<Integer>(), 0, nums, res);
        return res;
    }

    private void permute(ArrayList<Integer> path, int index,
                         int[] nums, List<List<Integer>> res){
        if(index == nums.length){
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for(int j = 0; j <=index; ++j ){
            path.add(j, nums[index]);
            permute(path, index + 1, nums, res);
            path.remove(j);
        }
    }

//-------------------------------------------------------------------------//////////

    //Accepted Recursive Solution in Java

    class solution{
        int len;
        boolean[] used;
        List<List<Integer>> result;
        List<Integer> temp;
        public List<List<Integer>> permute(int[] num) {
            len = num.length;
            used = new boolean[len];
            result = new ArrayList<List<Integer>>();
            temp = new ArrayList<>();
            doPermute(num, 0);

            return result;
        }

        public void doPermute(int[] in, int level) {
            if (level == len) {
                result.add(new ArrayList<Integer>(temp));
                return;
            }

            for (int i = 0; i < len; i++) {
                if (used[i]) {
                    continue;
                }

                temp.add(in[i]);
                used[i] = true;
                doPermute(in, level + 1);
                used[i] = false;
                temp.remove(level);
            }
        }
    }

//-------------------------------------------------------------------------////////////////
//    2ms Java solution beats 93%, I think it could be optimized

    public class Solution4 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            perm(result,nums,0,nums.length-1);
            return result;
        }
        public  void perm(List<List<Integer>> result, int[] nums,
                          int start, int end){
            if(start==end){
                Integer[] ele = new Integer[nums.length];
                for(int i=0; i<nums.length; i++){
                    ele[i] = nums[i];
                }
                result.add(Arrays.asList(ele));
            }
            else{
                for(int i=start; i<=end; i++){
                    int temp = nums[start];
                    nums[start] = nums[i];
                    nums[i] = temp;

                    perm(result, nums,start+1,end);

                    temp = nums[start];
                    nums[start] = nums[i];
                    nums[i] = temp;
                }
            }
        }
    }

//-------------------------------------------------------------------------////////////////

    //Java Backtracking Solution
    public class Solution6 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> lists = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return lists;
            }

            dfs(nums, lists, new ArrayList<Integer>());
            return lists;
        }

        private void dfs(int[] nums, List<List<Integer>> lists, List<Integer> cur) {
            if (cur.size() == nums.length) {
                List<Integer> list = new ArrayList<>(cur);
                lists.add(list);
            }

            for (int i = 0; i < nums.length; i++) {
                if (cur.contains(nums[i])) {
                    continue;
                }
                cur.add(nums[i]);
                dfs(nums, lists, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }


//-------------------------------------------------------------------------//
    //Improving a little using hashset as you commented based on your solution:

    public class Solution7 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if(nums==null||nums.length==0) return res;
            List<Integer> curtPermu = new ArrayList<Integer>();
            HashSet<Integer> set = new HashSet<Integer>();
            dfs(res,curtPermu,nums,set);
            return res;
        }
        private void dfs(List<List<Integer>> res, List<Integer> curtPermu,
                         int[] nums, HashSet<Integer> set){
            if(curtPermu.size()==nums.length){
                res.add(new ArrayList<Integer>(curtPermu));
                return;
            }
            for(int i=0; i<nums.length; i++){
                if(set.contains(nums[i])) continue;
                curtPermu.add(nums[i]);
                set.add(nums[i]);
                dfs(res, curtPermu, nums, set);
                set.remove(curtPermu.get(curtPermu.size()-1));
                curtPermu.remove(curtPermu.size()-1);
            }
        }
    }

//-------------------------------------------------------------------------////








}
