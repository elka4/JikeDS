package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;

//108. Convert Sorted Array to Binary Search Tree

public class _108_DFS_Convert_Sorted_Array_to_Binary_Search_Tree_M {
    class Solution{
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
    }


    class Solution2{
        public TreeNode sortedArrayToBST(int[] num) {
            if (num == null || num.length == 0) return null;
            TreeNode root = new TreeNode(0);
            insertNode(root,num,0,num.length-1);
            return root;
        }

        public void insertNode(TreeNode root, int[] num, int s, int e){
            if (s==e){
                root.val = num[s];
                return;
            }
            int mid = (s+e)%2 == 0 ? (s+e)/2 : (s+e)/2+1;
            root.val = num[mid];
            if (mid-s >= 1){
                root.left = new TreeNode(0);
                insertNode(root.left,num,s,mid-1);
            }

            if (e-mid >=1){
                root.right = new TreeNode(0);
                insertNode(root.right,num,mid+1,e);
            }
            return;
        }
    }

    public class Solution3 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBST(nums, 0, nums.length-1);
        }
        public TreeNode sortedArrayToBST(int[] nums, int lo, int hi){
            if(lo > hi)
                return null;
            int mid = (hi-lo)/2+lo;
            TreeNode root = new TreeNode(nums[mid]);
            root.left =  sortedArrayToBST(nums, lo, mid-1);
            root.right =  sortedArrayToBST(nums, mid+1, hi);
            return root;
        }
    }

    class Solution4{
        public TreeNode sortedArrayToBST(int[] nums) {
            return createTree(nums, 0, nums.length - 1);
        }

        public TreeNode createTree(int[] nums, int start, int end){
            if(start > end) return null;
            int middle = (start + end) / 2;
            TreeNode node = new TreeNode(nums[middle]);
            node.left = createTree(nums, start, middle-1);
            node.right = createTree(nums, middle+1, end);
            return node;
        }
    }

    class Solution5{

        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null || nums.length == 0) return null;
            int len = nums.length;
            TreeNode root  = new TreeNode(nums[len/2]);
            root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, len/2));
            root.right = sortedArrayToBST(Arrays.copyOfRange(nums, len/2 + 1, len));
            return root;
        }
    }

    class Solution6{
        public TreeNode sortedArrayToBST(int[] nums) {
            if(nums.length == 0) return null;
            int mid = (nums.length-1)/2;
            TreeNode root = new TreeNode(nums[mid]);
            recursive(nums, 0, mid-1, root.left);
            recursive(nums, mid+1, nums.length-1, root.right);
            return root;
        }

        public void recursive(int [] nums, int start, int end, TreeNode root){
            if(start > end){
                root = null;
                return;
            };
            int mid = (end + start)/2;
            root = new TreeNode(nums[mid]);
            recursive(nums, start, mid-1, root.left);
            recursive(nums, mid+1, end, root.right);
        }
    }

    public class Solution7 {
        public TreeNode sortedArrayToBST(int[] nums) {

            return findNextInsert(nums, 0, nums.length - 1, null);
        }

        private TreeNode findNextInsert(int[] nums, int left, int right, TreeNode node) {
            if(left > right) return null;
            int mid = (left+right) / 2;
            node = new TreeNode(nums[mid]);
            node.left = findNextInsert(nums, left, mid-1, node.left);
            node.right = findNextInsert(nums, mid+1, right, node.right);
            return node;
        }
    }

    public class Solution8 {

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
//////////////////////////////////////////////////////////////////////////////////////

    public class Jiuzhang {
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
    }


//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */