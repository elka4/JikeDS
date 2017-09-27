package Classification.Depth_first_Search_54;
import java.util.*;
/**
 * Created by tianhuizhu on 6/19/17.
 */
/*Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right
 child points to the next node of a pre-order traversal.
*/
public class b_114_Flatten_Binary_Tree_to_Linked_List {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //1. My short post order traversal Java solution for share
    class solution1{
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }
    }
//2. Share my simple NON-recursive solution, O(1) space complexity!
//            3. Straightforward Java Solution
    class solution3{
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }

}
//4. Accepted simple Java solution , iterative
//it is DFS so u need a stack. Dont forget to set
// the left child to null, or u'll get TLE. (tricky!)
    class solution4{
        public void flatten(TreeNode root) {
            if (root == null) return;
            Stack<TreeNode> stk = new Stack<TreeNode>();
            stk.push(root);
            while (!stk.isEmpty()){
                TreeNode curr = stk.pop();
                if (curr.right!=null)
                    stk.push(curr.right);
                if (curr.left!=null)
                    stk.push(curr.left);
                if (!stk.isEmpty())
                    curr.right = stk.peek();
                curr.left = null;  // dont forget this!!
            }
        }
    }

}
