##Closest Binary Search Tree Value II

	Total Accepted: 5710 Total Submissions: 17392 Difficulty: Hard
	Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

	Note:
	Given target value is a floating point.
	You may assume k is always valid, that is: k ≤ total nodes.
	You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
	Follow up:
	Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

	Hint:

	Consider implement these two helper functions:
	getPredecessor(N), which returns the next smaller node to N.
	getSuccessor(N), which returns the next larger node to N.
	Try to assume that each node has a parent pointer, it makes the problem much easier.
	Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
	You would need two stacks to track the path in finding predecessor and successor node separately.

####思路
- 先inorder遍历BST
- 再用两个同向指针找到K个数,要注意的是list的remove操作是O(n),所以这个方法会造成O(n2)的时间
- 或者将中序遍历的结果放进deque,然后进行删减和添加操作,这个比较好一点,只是0(n)

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        List<Integer> traverse = new ArrayList<Integer>();
        inorderTraverse(root, traverse);

        int size = traverse.size();
        for (int i = 0; i < k; i++) {
            result.add(traverse.get(i));
        }

        for (int i = k; i < size; i++) {
            int first = result.get(0);
            int cur = traverse.get(i);
            if (Math.abs((double)cur - target) < Math.abs((double)first - target)) {
                result.remove(0);
                result.add(cur);
            } else {
                break;
            }
        }
        return result;
    }

    public void inorderTraverse(TreeNode root, List<Integer> traverse) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left, traverse);
        traverse.add(root.val);
        inorderTraverse(root.right, traverse);

    }
}
```

####Follow Up
[Follow Up](https://leetcode.com/discuss/71820/java-5ms-iterative-following-hint-o-klogn-time-and-space)
- 最开始还不明白,然后把Balance BST 的图画出来就明白了
- 简直精妙
- 找predeccusor 和 successor
- 用pre和succ比较,如果在succ一边,那么久找到succ那边其他的小值,如果在pre这边,那么找到其他的最大值,直到找到k个

```java
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<Integer>();
        // populate the predecessor and successor stacks
        Stack<TreeNode> pred = new Stack<TreeNode>();
        Stack<TreeNode> succ = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null) {
            if (target <= curr.val) {
                succ.push(curr);
                curr = curr.left;
            } else {
                pred.push(curr);
                curr = curr.right;
            }
        }
        while (k > 0) {
            if (pred.empty() && succ.empty()) {
                break;
            } else if (pred.empty()) {
                result.add( getSuccessor(succ) );
            } else if (succ.empty()) {
                result.add( getPredecessor(pred) );
            } else if (Math.abs(target - pred.peek().val) < Math.abs(target - succ.peek().val)) {
                result.add( getPredecessor(pred) );
            } else {
                result.add( getSuccessor(succ) );
            }
            k--;
        }
        return result;
     }

    private int getPredecessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.left;
        while (p != null) {
            st.push(p);
            p = p.right;
        }
        return popped.val;
    }

    private int getSuccessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.right;
        while (p != null) {
            st.push(p);
            p = p.left;
        }
        return popped.val;
    }
}
```
