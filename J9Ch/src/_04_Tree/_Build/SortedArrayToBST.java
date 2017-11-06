package _04_Tree._Build;

import lib.TreeNode;
import java.util.*;
import org.junit.Test;
//  Convert Sorted Array to Binary Search Tree

// Sorted Array To BST
public class SortedArrayToBST {
    // jiuzhang
    private TreeNode buildTree(int[] num, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode node = new TreeNode(num[(start + end) / 2]);
        node.left = buildTree(num, start, (start + end) / 2 - 1);
        node.right = buildTree(num, (start + end) / 2 + 1, end);
        return node;
    }

    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null) {
            return null;
        }
        return buildTree(num, 0, num.length - 1);
    }
//////////////////////////////////////////////////////////////////
    //time O(n) space O(logn)
    public TreeNode sortedArrayToBST1(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }
        return buildtree(num, 0, num.length - 1);
    }
    private TreeNode buildtree(int[] num, int start, int end){
        if(start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(num[mid]);
        root.left = buildtree(num, start, mid - 1);
        root.right = buildtree(num, mid + 1, end);

        return root;
    }

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8};
        TreeNode root = sortedArrayToBST(arr);
        System.out.println("root: ");
        root.print();
    }

/////////////////////////////////////////////////////////////
    public TreeNode sortedArrayToBST2(int[] num) {
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
/////////////////////////////////////////////////////////////
    //Java Iterative Solution
    /*
    I came up with the recursion solution first and tried to translate it into an iterative solution.
    It is very similar to doing a tree inorder traversal,
    I use three stacks - nodeStack stores the node I am going to process next,
    and leftIndexStack and rightIndexStack store the range where this node need to read from the nums.
     */
    public TreeNode sortedArrayToBST3(int[] nums) {

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

/////////////////////////////////////////////////////////////

    //A typical DFS problem using recursion.

    // Definition for binary tree
    public TreeNode sortedArrayToBST11(int[] num) {
        if (num.length == 0)
            return null;

        return sortedArrayToBST(num, 0, num.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(num[mid]);

        root.left = sortedArrayToBST(num, start, mid - 1);
        root.right = sortedArrayToBST(num, mid + 1, end);

        return root;
    }


//////////////////////////////////////////////////////////////
}
