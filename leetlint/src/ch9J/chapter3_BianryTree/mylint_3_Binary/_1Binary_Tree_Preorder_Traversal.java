package ch9J.chapter3_BianryTree.mylint_3_Binary;
import java.util.*;
/**
 * Created by tzh on 3/2/17.
 */
public class _1Binary_Tree_Preorder_Traversal {
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
    public class Solution1 {
        /**
         * @param root: The root of binary tree.
         * @return: Preorder in ArrayList which contains node values.
         */
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            // write your code here
            ArrayList<Integer> result = new ArrayList<>();
            if (root == null){
                return result;
            }
            helper(result, root);
            return result;

        }

        private void helper(ArrayList<Integer> result, TreeNode root){

            if(root == null){
                return;
            }
            result.add(root.val);
            helper(result, root.left);
            helper(result, root.right);

        }
    }

    /////////////
    public class Solution2 {
        /**
         * @param root: The root of binary tree.
         * @return: Preorder in ArrayList which contains node values.
         */
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            // write your code here
            ArrayList<Integer> result = new ArrayList<>();
            if (root == null){
                return result;
            }
            ArrayList<Integer> left = preorderTraversal(root.left);
            ArrayList<Integer> right = preorderTraversal(root.right);

            result.add(root.val);
            result.addAll(left);
            result.addAll(right);
            return result;

        }
    }


    //////////////////////

    public class Solution3 {
        /**
         * @param root: The root of binary tree.
         * @return: Preorder in ArrayList which contains node values.
         */
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            // write your code here
            ArrayList<Integer> result = new ArrayList<>();
            if (root == null){
                return result;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.empty()){


                TreeNode temp = stack.pop();

                result.add(temp.val);

                if(temp.right != null){
                    stack.push(temp.right);
                }
                if(temp.left != null){
                    stack.push(temp.left);
                }

            }
            return result;


        }
    }
}
