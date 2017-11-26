package J_5_DFS.other.backtracking;

import java.util.*;

public class backtracking_iter {

    // subset
    // Simple Java Solution with For-Each loops
    // No messy indexing. Avoid the ConcurrentModificationException by using a temp list.
    public List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());

        Arrays.sort(S);

        for(int i : S) {
            List<List<Integer>> tmp = new ArrayList<>();
            for(List<Integer> sub : res) {
                List<Integer> a = new ArrayList<>(sub);
                a.add(i);
                tmp.add(a);
                System.out.println("a : " + a);
                System.out.println("tmp : " + tmp);
            }
            res.addAll(tmp);
            System.out.println("res : " + res);
            System.out.println("=================");
        }
        return res;
    }

    public List<List<Integer>> subsets5(int[] S) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        Arrays.sort(S);
        for(int i = S.length - 1; i >= 0; i--){
            int size = res.size() - 1;
            for(int j = size; j >= 0; j--){
                List<Integer> newList1 = new ArrayList<>();
                newList1.add(S[i]);
                newList1.addAll(res.get(j));
                res.add(newList1);
                System.out.println("newList1 " + newList1);
                System.out.println("res " + res);
            }
            System.out.println("=================");
        }
        return res;
    }

//--------------------------------------------------------------------------------

    //_2_Subsets_II
    //Share my 2ms java iteration solution (very simple and short)

    public List<List<Integer>> subsetsWithDup4(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());

        int begin = 0;

        for(int i = 0; i < nums.length; i++){

            if(i == 0 || nums[i] != nums[i - 1]) begin = 0;

            int size = result.size();

            for(int j = begin; j < size; j++){
                List<Integer> cur = new ArrayList<Integer>(result.get(j));
                cur.add(nums[i]);
                result.add(cur);
            }
            begin = size;
        }
        return result;
    }

    //Accepted java iterative solution
    public List<List<Integer>> subsetsWithDup5(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int len = num.length;
        if (len == 0) return ans;

        ans.add(new ArrayList<Integer>()); // first, need to add the subset of num[0]
        ans.add(new ArrayList<Integer>());
        ans.get(1).add(num[0]);

        int nprev = 1; // this is the number of lists that the previous number was added in.
        // if the current number is same as the prev one, it'll be only added in the
        // lists that has the prev number.

        for (int i = 1; i < len ; ++i){
            int size = ans.size();
            if (num[i]!=num[i-1])   // if different
                nprev = size;        // this means add num[i] to all lists in ans;
            for (int j = size-nprev; j < size; ++j){
                List<Integer> l = new ArrayList<Integer>(ans.get(j));
                l.add(num[i]);
                ans.add(l);
            }
        }
        return ans;
    }



//--------------------------------------------------------------------------------

    //_3_Permutations

    //My AC simple iterative java/python solution

/*
the basic idea is, to permute n numbers, we can add the nth number into the resulting
 List<List<Integer>> from the n-1 numbers, in every possible position.

For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>>
 (let's call it "answer").

Then, 2 can be added in front or after 1. So we have to copy the List<Integer> in answer
(it's just {1}), add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in
 position 1. Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.

Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and
{1,2}, and add 3 into position 1, then do the same thing for position 3. Finally we have 2*3=6
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


//I used your idea of adding each next value to every possible position of current list,
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

//Very nice solution! you can simply a little bit by removing a couple of lines:

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


//Good idea! I was thinking we can optimize the solution by adding on to a single
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


    //I used your idea, but have a slightly different starting point before getting into the loop.
// I think it's logically more clear and don't need to handle corner case when num is empty.

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
    就是遍历nums（第一个for loop），当开始进行nums[i]时，res中的结果是nums[0]至nums[i-1]的全排列。
    每一次循环中，需要将nums[i]加入到res中的每一个结果中的每一个位置，然后开始下一次循环。
    具体做法是，每一次循环开始，先记录当前res的大小（size），取出res中的第一个，
    在每个位置添加nums[i]然后加到res末尾（第三个for loop，循环r.size()次），一共进行size次（第二个for loop）。
     */


//    2ms Java solution beats 93%, I think it could be optimized

    public class Solution4 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            perm(result,nums,0,nums.length-1);
            return result;
        }
        public  void perm(List<List<Integer>> result, int[] nums, int start, int end){
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


//--------------------------------------------------------------------------------

    //_4_Permutations_II

    //Short iterative Java solution

    /*
    Here's an iterative solution which doesn't use nextPermutation helper.
    It builds the permutations for i-1 first elements of an input array and tries
    to insert the ith element into all positions of each prebuilt i-1 permutation.
    I couldn't come up with more effective controling of uniqueness than just using a Set.
     */
    public List<List<Integer>> permuteUnique6(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < num.length; i++) {
            Set<String> cache = new HashSet<>();
            while (res.peekFirst().size() == i) {
                List<Integer> l = res.removeFirst();
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>(l.subList(0,j));
                    newL.add(num[i]);
                    newL.addAll(l.subList(j,l.size()));
                    if (cache.add(newL.toString())) res.add(newL);
                }
            }
        }
        return res;
    }


    //here is my iterative solution with only a queue. FYI.

    public List<List<Integer>> permuteUnique7(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                List<Integer> record = queue.poll();
                for (int j = 0; j <= record.size(); j++) {
                    boolean shouldBreak = j == record.size() || nums[i] == record.get(j);
                    List<Integer> tmp = new ArrayList<>(record);
                    tmp.add(j, nums[i]);
                    queue.add(tmp);
                    if (shouldBreak) break;
                }
            }
        }
        return new ArrayList<>(queue);
    }



    //Java Iterative solution, no Set needed!

    /*
    In each iteration, put the new number to all possible place.
    To avoid duplicate we also have to:

    For duplicate numbers in a row, only add same number in in front of them.
    Break when same number exists in the permutation.
     */

    public List<List<Integer>> permuteUnique10(int[] nums) {
        LinkedList<List<Integer>> r = new LinkedList<>();
        r.add(new ArrayList<Integer>());
        for(int i=0; i<nums.length; i++){
            int n = r.size();
            for(int j=0; j<n; j++){
                List<Integer> list = r.poll();
                for(int k=0; k<=list.size(); k++){
                    if(k > 0 && list.get(k-1) == nums[i])
                        break;
                    ArrayList<Integer> t = new ArrayList<>(list);
                    t.add(k, nums[i]);
                    r.offer(t);
                }
            }
        }
        return r;
    }

//--------------------------------------------------------------------------------

    //_5_Combination_Sum

    //Iterative Java DP solution

/*
The main idea reminds an approach for solving coins/knapsack problem - to store
the result for all i < target and create the solution from them. For that for each
 t from 1 to our target we try every candidate which is less or equal to t in ascending
 order. For each candidate "c" we run through all combinations for target t-c starting
 with the value greater or equal than c to avoid duplicates and store only ordered combinations.

 */

    public List<List<Integer>> combinationSum2(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { // run through all targets from 1 to t
            List<List<Integer>> newList = new ArrayList(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                    // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }



    //A solution avoid using set
    /*
    Sort the candidates and we choose from small to large recursively,
    every time we add a candidate to our possible sub result,
    we subtract the target to a new smaller one.
     */

    public List<List<Integer>> combinationSum6(int[] candidates, int target) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Arrays.sort(candidates); // sort the candidates
        // collect possible candidates from small to large to eliminate duplicates,
        recurse(new ArrayList<Integer>(), target, candidates, 0, ret);
        return ret;
    }

    // the index here means we are allowed to choose candidates from that index
    private void recurse(List<Integer> list, int target, int[] candidates,
                         int index, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(list);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int newTarget = target - candidates[i];
            if (newTarget >= 0) {
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(candidates[i]);
                recurse(copy, newTarget, candidates, i, ret);
            } else {
                break;
            }
        }
    }



    //Easy to understand 96% performance Java solution


    public List<List<Integer>> combinationSum7(int[] candidates, int target) {
        return combinationSum(candidates, target, 0);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, int start) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <target) {
                for (List<Integer> ar : combinationSum(candidates, target - candidates[i], i)) {
                    ar.add(0, candidates[i]);
                    res.add(ar);
                }
            } else if (candidates[i] == target) {
                List<Integer> lst = new ArrayList<>();
                lst.add(candidates[i]);
                res.add(lst);
            } else
                break;
        }
        return res;
    }


    //Non-Recursive JAVA solution
    public List<List<Integer>> combinationSum10(int[] candidates, int target) {
        Arrays.sort(candidates);
        int i=0, size = candidates.length, sum=0;
        Stack<Integer> combi = new Stack<>(), indices = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        while (i < size) {
            if (sum + candidates[i]>= target) {
                if (sum + candidates[i] == target) {
                    combi.push(candidates[i]);
                    result.add(new ArrayList<>(combi));
                    combi.pop();
                }
                // indices stack and combination stack should have the same size all the time
                if (!indices.empty()){
                    sum -= combi.pop();
                    i = indices.pop();
                    while (i == size-1 && !indices.empty()) {
                        i = indices.pop();
                        sum -= combi.pop();

                    }
                }
                i++;
            } else {
                combi.push(candidates[i]);
                sum +=candidates[i];
                indices.push(i);
            }
        }
        return result;
    }




//--------------------------------------------------------------------------------

    //_6_Combination_Sum_II

//--------------------------------------------------------------------------------

    //_7_Palindrome_Partitioning

//--------------------------------------------------------------------------------


}
