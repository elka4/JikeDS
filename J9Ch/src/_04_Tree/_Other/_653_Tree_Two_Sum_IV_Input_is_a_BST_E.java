package _04_Tree._Other;
import java.util.*;

import a.d.L;
import a.d.e.a.S;
import lib.*;

//  653. Two Sum IV - Input is a BST
//  https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
//
public class _653_Tree_Two_Sum_IV_Input_is_a_BST_E {

    //https://leetcode.com/articles/two-sum-iv/

    //Approach #1 Using HashSet[Accepted]
    public boolean findTarget1(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return  find(root, k, set);
    }
    public boolean find(TreeNode root, int k, Set<Integer> set){
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }
    //Approach #2 Using BFS and HashSet [Accepted]
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node .val))
                    return true;
                set.add(node.val);
                queue.add(node.right);
                queue.add(node.left);
            } else {
                queue.remove();
            }
        }
        return false;
    }

    //Approach #3 Using BST [Accepted]
    public boolean findTarget3(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while(l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k)
                return true;
            if (sum < k)
                l++;
            else
                r--;
        }
        return false;
    }
    public void inorder1(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder1(root.left, list);
        list.add(root.val);
        inorder1(root.right, list);
    }
////////////////////////////////////////////////////////////////////////////////////

    /*[Java/C++] Three simple methods - choose one you like
    Method 1.
    This method also works for those who are not BSTs. The idea is to use a hashtable to save the values of the nodes in the BST. Each time when we insert the value of a new node into the hashtable, we check if the hashtable contains k - node.val.

    Time Complexity: O(n), Space Complexity: O(n).

    Java version:*/
    public boolean findTarget4(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }


////////////////////////////////////////////////////////////////////////////////////

    /* Method 2.
    The idea is to use a sorted array to save the values of the nodes in the BST by using an inorder traversal. Then, we use two pointers which begins from the start and end of the array to find if there is a sum k.

    Time Complexity: O(n), Space Complexity: O(n).

    Java version:*/
    public boolean findTarget5(TreeNode root, int k) {
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

////////////////////////////////////////////////////////////////////////////////////


    /*    Method 3.
    The idea is to use binary search method. For each node, we check if k - node.val exists in this BST.

    Time Complexity: O(nlogn), Space Complexity: O(h). h is the height of the tree, which is logn at best case, and n at worst case.
    Java version:*/
    public boolean findTarget6(TreeNode root, int k) {
        return dfs6(root, root,  k);
    }

    public boolean dfs6(TreeNode root,  TreeNode cur, int k){
        if(cur == null)return false;
        return search(root, cur, k - cur.val) || dfs6(root, cur.left, k) || dfs6(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null)return false;
        return (root.val == value) && (root != cur)
                || (root.val < value) && search(root.right, cur, value)
                || (root.val > value) && search(root.left, cur, value);
    }



////////////////////////////////////////////////////////////////////////////////////
}
/*

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

 */
