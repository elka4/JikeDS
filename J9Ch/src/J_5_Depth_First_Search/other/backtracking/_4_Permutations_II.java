package J_5_Depth_First_Search.other.backtracking;
import java.util.*;

//https://leetcode.com/problems/permutations/#/discuss


/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

 */

//
public class _4_Permutations_II {

    //Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }



/////////////////////////////////////////////////////////////////////////////
//Really easy Java solution, much easier than the solutions with very high vote

/*
Use an extra boolean array " boolean[] used" to indicate whether the value is added to list.

Sort the array "int[] nums" to make sure we can skip the same value.

when a number has the same value with its previous, we can use this number only if his previous is used
 */

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums==null || nums.length==0) return res;
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        dfs(nums, used, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if(list.size()==nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]) continue;
            if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
            used[i]=true;
            list.add(nums[i]);
            dfs(nums,used,list,res);
            used[i]=false;
            list.remove(list.size()-1);
        }
    }

/////////////////////////////////////////////////////////////////////////////
/*
Another option to avoid duplicate num without sorting 'int[] nums' first,
is to utilize a HashSet to track what numbers are used before,
if we've used that number then skip it

 */


    public List<List<Integer>> permuteUnique3(int[] nums) {
        if(nums != null || nums.length != 0)
            permutationHelper(nums, new int[nums.length], new ArrayList<Integer>());
        return unique_perms;
    }

    List<List<Integer>> unique_perms = new ArrayList<List<Integer>>();

    void permutationHelper(int[] nums, int[] numsVisited, List<Integer> result){

        if(result.size() == nums.length){
            unique_perms.add(new ArrayList<Integer>(result));
            return;
        }


        HashSet<Integer> existingNums = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){

            //if this num was visited before then skip
            if(numsVisited[i] == 1) continue;

            //if we use this num before then skip it.
            if(existingNums.contains(nums[i]))
                continue;
            else
                existingNums.add(nums[i]);

            result.add(nums[i]);
            numsVisited[i] = 1;

            permutationHelper(nums, numsVisited, result);

            result.remove(result.size()-1);
            numsVisited[i] = 0;
        }
    }
/////////////////////////////////////////////////////////////////////////////
/*
Since we only need permutations of the array, the actual "content" does not change,
we could find each permutation by swapping the elements in the array.

The idea is for each recursion level, swap the current element at 1st index with each
element that comes after it (including itself). For example, permute[1,2,3]:

At recursion level 0, current element at 1st index is 1, there are 3 possibilities:
[1] + permute[2,3], [2] + permute[1,3], [3] + permute[2,1].

Take "2+permute[1,3]" as the example at recursion level 0. At recursion level 1,
current elemenet at 1st index is 1, there are 2 possibilities: [2,1] + permute[3], [2,3] + permute[1].

... and so on.

Let's look at another example, permute[1,2,3,4,1].

At recursion level 0, we have [1] + permute[2,3,4,1], [2] + permute[1,3,4,1],
[3] + permute[2,1,4,1], [4] + permute[2,3,1,1], [1] + permute[2,3,4,1].

1 has already been at the 1st index of current recursion level, so the last possibility is redundant.
We can use a hash set to mark which elements have been at the 1st index of current recursion level,
so that if we meet the element again, we can just skip it.
 */
    public List<List<Integer>> permuteUnique4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) { return ans; }
        permute(ans, nums, 0);
        return ans;
    }

    private void permute(List<List<Integer>> ans, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num: nums) { temp.add(num); }
            ans.add(temp);
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int i=index; i<nums.length; ++i) {
            if (appeared.add(nums[i])) {
                swap(nums, index, i);
                permute(ans, nums, index+1);
                swap(nums, index, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
/////////////////////////////////////////////////////////////////////////////

    //Share my recursive solution
    public List<List<Integer>> permuteUnique5(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
        permute(result, current, num, visited);
        return result;
    }

    private void permute(List<List<Integer>> result, List<Integer> current,
                         int[] num, boolean[] visited) {
        if (current.size() == num.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && num[i] == num[i-1] && visited[i-1]) {
                    return;
                }
                visited[i] = true;
                current.add(num[i]);
                permute(result, current, num, visited);
                current.remove(current.size()-1);
                visited[i] = false;
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////
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

/////////////////////////////////////////////////////////////////////////////
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



/////////////////////////////////////////////////////////////////////////////
//Short and Fast Recursive Java solution Easy to understand with Explaination

    public List<List<Integer>> permuteUnique8(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int num : nums) list.add(num);
        permute(list, 0, res);
        return res;
    }
    private void permute(LinkedList<Integer> nums, int start, List<List<Integer>> res){
        if (start == nums.size() - 1){
            res.add(new LinkedList<Integer>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++){
            if (i > start && nums.get(i) == nums.get(i - 1)) continue;
            nums.add(start, nums.get(i));
            nums.remove(i + 1);
            permute(nums, start + 1, res);
            nums.add(i + 1, nums.get(start));
            nums.remove(start);
        }
    }

/////////////////////////////////////////////////////////////////////////////


    //Small modification of Permutation I , using a set

    public static List<List<Integer>> permuteUnique9(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        permute(nums,0,nums.length,res);
        return res;
    }
    public static void permute(int[] nums, int i, int j, List<List<Integer>> res){

        if(i == j-1){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int x:nums) list.add(x);
            res.add(list);
            return;
        }
        HashSet<Integer> visited= new HashSet<Integer>();
        for(int k=i;k<j;k++){
            if(!visited.contains(nums[k])){
                swap2(nums,i,k);
                permute(nums,i+1,j,res);
                swap2(nums,i,k);
                visited.add(nums[k]);
            }

        }
    }
    public static void swap2(int[] nums, int i,int j){
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

///////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

}
