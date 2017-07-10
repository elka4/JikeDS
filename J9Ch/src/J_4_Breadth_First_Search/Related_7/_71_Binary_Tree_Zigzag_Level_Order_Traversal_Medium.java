package J_4_Breadth_First_Search.Related_7;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/** 71 Binary Tree Zigzag Level Order Traversal

 * Created by tianhuizhu on 6/28/17.
 */
public class _71_Binary_Tree_Zigzag_Level_Order_Traversal_Medium {

    public class Solution {
        public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

            if (root == null) {
                return result;
            }

            Stack<TreeNode> currLevel = new Stack<TreeNode>();
            Stack<TreeNode> nextLevel = new Stack<TreeNode>();
            Stack<TreeNode> tmp;

            currLevel.push(root);
            boolean normalOrder = true;

            while (!currLevel.isEmpty()) {
                ArrayList<Integer> currLevelResult = new ArrayList<Integer>();

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
    }
}
