package _04_Tree._Traverse;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//
//
//
public class _094_Tree_Binary_Tree_Inorder_Traversal_M {


    public List<Integer> inorderTraversal(TreeNode root) { List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>(); TreeNode cur = root;
        while(cur!=null || !stack.empty()){ while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
            cur = stack.pop(); list.add(cur.val); cur = cur.right;
        }
        return list; }
}
/*

 */
/*

 */
