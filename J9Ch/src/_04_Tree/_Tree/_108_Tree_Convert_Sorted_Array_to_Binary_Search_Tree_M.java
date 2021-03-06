package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _108_Tree_Convert_Sorted_Array_to_Binary_Search_Tree_M {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }




    public class Solution {

        public TreeNode sortedArrayToBST(int[] nums) {

            int len = nums.length;
            if ( len == 0 ) { return null; }

            // 0 as a placeholder
            TreeNode head = new TreeNode(0);

            Deque<TreeNode> nodeStack       = new LinkedList<TreeNode>() {{ push(head);  }};
            Deque<Integer>  leftIndexStack  = new LinkedList<Integer>()  {{ push(0);     }};
            Deque<Integer>  rightIndexStack = new LinkedList<Integer>()  {{ push(len-1); }};

            while ( !nodeStack.isEmpty() ) {
                TreeNode currNode = nodeStack.pop();
                int left  = leftIndexStack.pop();
                int right = rightIndexStack.pop();
                int mid   = left + (right-left)/2; // avoid overflow
                currNode.val = nums[mid];
                if ( left <= mid-1 ) {
                    currNode.left = new TreeNode(0);
                    nodeStack.push(currNode.left);
                    leftIndexStack.push(left);
                    rightIndexStack.push(mid-1);
                }
                if ( mid+1 <= right ) {
                    currNode.right = new TreeNode(0);
                    nodeStack.push(currNode.right);
                    leftIndexStack.push(mid+1);
                    rightIndexStack.push(right);
                }
            }
            return head;
        }

    }

}
