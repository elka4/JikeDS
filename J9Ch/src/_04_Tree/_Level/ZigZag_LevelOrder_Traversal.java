package _04_Tree._Level;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;


public class ZigZag_LevelOrder_Traversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>>  res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        //Method: Using a deque to maintain the current nodes in
        // the same level with the same order from left to right
        Deque<TreeNode> deque = new LinkedList<TreeNode>();

        boolean lefttoRight = true;//The order to put into result

        deque.addFirst(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                if(lefttoRight) {//Case 1
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left != null)
                        deque.addLast(cur.left);
                    if (cur.right != null)
                        deque.addLast(cur.right);
                } else {//Case 2
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right != null)
                        deque.addFirst(cur.right);
                    if (cur.left != null)
                        deque.addFirst(cur.left);
                }
            }
            lefttoRight = lefttoRight ? false: true;
            res.add(list);
        }
        return res;
    }

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(zigzagLevelOrder(root));
    }

/////////////////////////////////////////////////////////////////////////////

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> currLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;

        currLevel.push(root);
        boolean normalOrder = true;

        while (!currLevel.isEmpty()) {
            ArrayList<Integer> currLevelResult = new ArrayList<>();

            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                currLevelResult.add(node.val);

                if (normalOrder) {
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                }
            }

            result.add(currLevelResult);

            tmp = currLevel;
            currLevel = nextLevel;
            nextLevel = tmp;

            normalOrder = !normalOrder;
        }

        return result;
    }
/////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////


}
/*Given a binary tree, return the zigzag level order
 * traversal of its nodes' values. (ie, from left to right,
 * then right to left for the next level and alternate between).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]
Tags
LinkedIn Queue Binary Tree Binary Tree Traversal Breadth First Search*/
