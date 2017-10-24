package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _039_BackTracking_Combination_Sum_M {
    class Solution{
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ret = new LinkedList<List<Integer>>();
            Arrays.sort(candidates); // sort the candidates
            // collect possible candidates from small to large to eliminate duplicates,
            recurse(new ArrayList<Integer>(), target, candidates, 0, ret);
            return ret;
        }

        // the index here means we are allowed to choose candidates from that index
        private void recurse(List<Integer> list, int target, int[] candidates, int index, List<List<Integer>> ret) {
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
    }

    public class Solution2 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            getResult(result, new ArrayList<Integer>(), candidates, target, 0);

            return result;
        }

        private void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start){
            if(target > 0){
                for(int i = start; i < candidates.length && target >= candidates[i]; i++){
                    cur.add(candidates[i]);
                    getResult(result, cur, candidates, target - candidates[i], i);
                    cur.remove(cur.size() - 1);
                }//for
            }//if
            else if(target == 0 ){
                result.add(new ArrayList<Integer>(cur));
            }//else if
        }
    }


    public class Solution3 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, new LinkedList<Integer>(), candidates, target);
            return result;
        }
        private void dfs(List<List<Integer>> result, LinkedList<Integer> list, int[] arr, int target) {
            if (target == 0) {
                result.add(new LinkedList<Integer>(list));
                return;
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] <= target) {
                    list.addFirst(arr[i]);
                    dfs(result, list, Arrays.copyOfRange(arr, 0, i + 1), target - arr[i]);
                    list.removeFirst();
                }
            }
        }
    }
}
