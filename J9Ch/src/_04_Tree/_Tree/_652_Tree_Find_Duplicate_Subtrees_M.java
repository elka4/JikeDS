package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _652_Tree_Find_Duplicate_Subtrees_M {

//method1
    class Solution1{
        public boolean findTarget(TreeNode root, int k) {
            HashSet<Integer> set = new HashSet<>();
            return dfs(root, set, k);
        }

        public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
            if(root == null)return false;
            if(set.contains(k - root.val))return true;
            set.add(root.val);
            return dfs(root.left, set, k) || dfs(root.right, set, k);
        }
    }

    //method2
    class Solution2{
        public boolean findTarget(TreeNode root, int k) {
            List<Integer> nums = new ArrayList<>();
            inorder(root, nums);
            for(int i = 0, j = nums.size()-1; i<j;){
                if(nums.get(i) + nums.get(j) == k)return true;
                if(nums.get(i) + nums.get(j) < k)i++;
                else j--;
            }
            return false;
        }

        public void inorder(TreeNode root, List<Integer> nums){
            if(root == null)return;
            inorder(root.left, nums);
            nums.add(root.val);
            inorder(root.right, nums);
        }
    }

    // method3
    class Solution3{
        public boolean findTarget(TreeNode root, int k) {
            return dfs(root, root,  k);
        }

        public boolean dfs(TreeNode root,  TreeNode cur, int k){
            if(cur == null)return false;
            return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
        }

        public boolean search(TreeNode root, TreeNode cur, int value){
            if(root == null)return false;
            return (root.val == value) && (root != cur)
                    || (root.val < value) && search(root.right, cur, value)
                    || (root.val > value) && search(root.left, cur, value);
        }
    }

}
