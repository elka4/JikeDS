package J_4_Breadth_First_Search.other.Binary_Tree_Level_Order_Traversal;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/** 70 Binary Tree Level Order Traversal II
 * Created by tianhuizhu on 6/28/17.
 */

// bottom to top
public class _70_Binary_Tree_Level_Order_Traversal_II_Medium {

    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        Collections.reverse(result);
        return result;
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(levelOrderBottom(root));
    }

}
