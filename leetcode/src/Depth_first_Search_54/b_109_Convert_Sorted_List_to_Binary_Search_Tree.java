package Depth_first_Search_54;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class b_109_Convert_Sorted_List_to_Binary_Search_Tree {

    //    1. Share my JAVA solution, 1ms, very short and concise.
    public class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) return null;
            return toBST(head, null);
        }

        public TreeNode toBST(ListNode head, ListNode tail) {
            ListNode slow = head;
            ListNode fast = head;
            if (head == tail) return null;

            while (fast != tail && fast.next != tail) {
                fast = fast.next.next;
                slow = slow.next;
            }
            TreeNode thead = new TreeNode(slow.val);
            thead.left = toBST(head, slow);
            thead.right = toBST(slow.next, tail);
            return thead;
        }
    }

    //2. Share my O(1) space and O(n) time Java code
    class solution2{
        private ListNode node;

        public TreeNode sortedListToBST(ListNode head) {
            if(head == null){
                return null;
            }

            int size = 0;
            ListNode runner = head;
            node = head;

            while(runner != null){
                runner = runner.next;
                size ++;
            }

            return inorderHelper(0, size - 1);
        }

        public TreeNode inorderHelper(int start, int end){
            if(start > end){
                return null;
            }

            int mid = start + (end - start) / 2;
            TreeNode left = inorderHelper(start, mid - 1);

            TreeNode treenode = new TreeNode(node.val);
            treenode.left = left;
            node = node.next;

            TreeNode right = inorderHelper(mid + 1, end);
            treenode.right = right;

            return treenode;
        }
    }
//3. Share my code with O(n) time and O(1) space
//4. My Accepted C++ solution
//5. Recursive BST construction using slow-fast traversal on linked list
    class solution5{
        public TreeNode sortedListToBST(ListNode head) {
            if(head == null)
                return null;
            ListNode fast = head;
            ListNode slow = head;
            ListNode prev =null;
            while(fast != null && fast.next != null)
            {
                fast = fast.next.next;
                prev =slow;
                slow=slow.next;
            }
            TreeNode root = new TreeNode(slow.val);
            if(prev != null)
                prev.next = null;
            else
                head  = null;

            root.left = sortedListToBST(head);
            root.right = sortedListToBST(slow.next);
            return root;
        }
    }


}
