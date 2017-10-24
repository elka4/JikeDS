package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _491_DFS_Increasing_Subsequences_M {
    public class Solution {

        public List<List<Integer>> findSubsequences(int[] nums) {
            Set<List<Integer>> res= new HashSet<List<Integer>>();
            List<Integer> holder = new ArrayList<Integer>();
            findSequence(res, holder, 0, nums);
            List result = new ArrayList(res);
            return result;
        }

        public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
            if (holder.size() >= 2) {
                res.add(new ArrayList(holder));
            }
            for (int i = index; i < nums.length; i++) {
                if(holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
                    holder.add(nums[i]);
                    findSequence(res, holder, i + 1, nums);
                    holder.remove(holder.size() - 1);
                }
            }
        }
    }
    public class Solution2 {
        private void helper(int[] nums, int index, List<Integer> buffer, List<List<Integer>> result){
            if( buffer.size()>=2 )
                result.add( new ArrayList<Integer>(buffer) );

            for(int i=index; i<nums.length; i++){
                if( buffer.size()==0 || nums[i]>=buffer.get(buffer.size()-1) ){
                    buffer.add(nums[i]);
                    helper(nums, i+1, buffer, result);
                    buffer.remove(buffer.size()-1);
                }

                while(i+1<nums.length && nums[i]==nums[i+1])
                    i++;
            }
        }

        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if(nums.length<2)
                return result;

            helper(nums, 0, new ArrayList<Integer>(), result);
            return result;
        }
    }

    class Solution3{
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            helper(res, new ArrayList<Integer>(), nums, 0);
            return res;
        }

        private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int id) {
            if (list.size() > 1) res.add(new ArrayList<Integer>(list));
            List<Integer> unique = new ArrayList<Integer>();
            for (int i = id; i < nums.length; i++) {
                if (id > 0 && nums[i] < nums[id-1]) continue; // skip non-increase
                if (unique.contains(nums[i])) continue; // skip duplicate
                unique.add(nums[i]);
                list.add(nums[i]);
                helper(res, list, nums, i+1);
                list.remove(list.size()-1);
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */
