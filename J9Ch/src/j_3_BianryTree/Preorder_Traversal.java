package j_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Version 2: Divide & Conquer
@SuppressWarnings("all")

//递归的定义：找到root为根的preorder并return
public class Preorder_Traversal {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
	    ArrayList<Integer> result = new ArrayList<>();
	    // 递归的出口  // null or leaf? null!
	    if (root == null) {
	        return result;
	    }
	    // 递归的拆解：left，right // 先divide，再想每个具体做什么
	    // Divide
	    ArrayList<Integer> left = preorderTraversal(root.left);
	    ArrayList<Integer> right = preorderTraversal(root.right);
	
	    // Conquer
	    result.add(root.val);
	    result.addAll(left);
	    result.addAll(right);
	    return result;
	}


    //Version 0: Non-Recursion (Recommend)
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }
//////////////////////////////////////////////////////
//Version 1: Traverse

    public ArrayList<Integer> preorderTraversal3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }
    // 1递归定义：把root为根的preorder加入result里面
    private void traverse(TreeNode root, ArrayList<Integer> result) {
        // 3.递归的出口：极端小的问题
        // 以root为null还是leaf作为出口？
        if (root == null) {
            return;
        }
        // 2.如何拆分为更小的情况（如何扒皮）。一边走一边构造。
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }

/* inorder
traverse(root.left, result);
result.add(root.val);
traverse(root.right, result);
 */

/* postorder
traverse(root.left, result);
traverse(root.right, result);
result.add(root.val);
 */

//////////////////////////////////////////////////////
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal4(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode curt = root;
        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            curt = stack.peek();
            stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }
////////////////////////////////////////////////////////

    public List<Integer> inorderTraversal5(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();

            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

////////////////////////////////////////////////////////

    public List<Integer> inorderTraversal6(TreeNode root) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        // if (root == null) {
        //     return list;
        // }
        helper(root, list);
        return list;
    }

    private void helper (TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }

////////////////////////////////////////////////////////


//////////////////////////////////////////////////////


//////////////////////////////////////////////////////



}
/* inorder
result.addAll(left);
result.add(root.val);
result.addAll(right);*/

/* postorder
result.addAll(left);
result.addAll(right);
result.add(root.val);*/