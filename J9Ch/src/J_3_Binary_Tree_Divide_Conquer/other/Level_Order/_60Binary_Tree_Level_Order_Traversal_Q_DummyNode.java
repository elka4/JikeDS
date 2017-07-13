package J_3_Binary_Tree_Divide_Conquer.other.Level_Order;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//version 4: BFS, queue with dummy node
public class _60Binary_Tree_Level_Order_Traversal_Q_DummyNode {
	 /**
	  * @param root: The root of binary tree.
	  * @return: Level order a list of lists of integer
	  */
	 public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
	     ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	     if (root == null) {
	         return result;
	     }
	     
	     Queue<TreeNode> Q = new LinkedList<TreeNode>();
	     Q.offer(root);
	     Q.offer(null); // dummy node
	     
	     ArrayList<Integer> level = new ArrayList<Integer>();
	     while (!Q.isEmpty()) {
	         TreeNode node = Q.poll();
	         if (node == null) {
	             if (level.size() == 0) {
	                 break;
	             }
	             result.add(level);
	             level = new ArrayList<Integer>();
	             Q.offer(null); // add a new dummy node
	             continue;
	         }
	         
	         level.add(node.val);
	         if (node.left != null) {
	             Q.offer(node.left);
	         }
	         if (node.right != null) {
	             Q.offer(node.right);
	         }
	     }
	     
	     return result;
	 }
}
