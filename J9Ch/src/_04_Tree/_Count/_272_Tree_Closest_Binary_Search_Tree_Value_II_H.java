package _04_Tree._Count;

import lib.TreeNode;

import java.util.*;


//  272. Closest Binary Search Tree Value II
//  https://leetcode.com/problems/closest-binary-search-tree-value-ii/description/
//
public class _272_Tree_Closest_Binary_Search_Tree_Value_II_H {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }

        return res;
    }

    // inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }


//O(logN) Java Solution with two stacks following hint
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret = new LinkedList<>();
        Stack<TreeNode> succ = new Stack<>();
        Stack<TreeNode> pred = new Stack<>();
        initializePredecessorStack(root, target, pred);
        initializeSuccessorStack(root, target, succ);
        if(!succ.isEmpty() && !pred.isEmpty() && succ.peek().val == pred.peek().val) {
            getNextPredecessor(pred);
        }
        while(k-- > 0) {
            if(succ.isEmpty()) {
                ret.add(getNextPredecessor(pred));
            } else if(pred.isEmpty()) {
                ret.add(getNextSuccessor(succ));
            } else {
                double succ_diff = Math.abs((double)succ.peek().val - target);
                double pred_diff = Math.abs((double)pred.peek().val - target);
                if(succ_diff < pred_diff) {
                    ret.add(getNextSuccessor(succ));
                } else {
                    ret.add(getNextPredecessor(pred));
                }
            }
        }
        return ret;
    }

    private void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> succ) {
        while(root != null) {
            if(root.val == target) {
                succ.push(root);
                break;
            } else if(root.val > target) {
                succ.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    private void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> pred) {
        while(root != null){
            if(root.val == target){
                pred.push(root);
                break;
            } else if(root.val < target){
                pred.push(root);
                root = root.right;
            } else{
                root = root.left;
            }
        }
    }

    private int getNextSuccessor(Stack<TreeNode> succ) {
        TreeNode curr = succ.pop();
        int ret = curr.val;
        curr = curr.right;
        while(curr != null) {
            succ.push(curr);
            curr = curr.left;
        }
        return ret;
    }

    private int getNextPredecessor(Stack<TreeNode> pred) {
        TreeNode curr = pred.pop();
        int ret = curr.val;
        curr = curr.left;
        while(curr != null) {
            pred.push(curr);
            curr = curr.right;
        }
        return ret;
    }
}

///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
    /*
    AC clean Java solution using two stacks
The idea is to compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors, then like what we do in merge sort, we compare and pick the closest one to the target and put it to the result list.

As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.

We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is the recursion version.
     */

    class Solution1{
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> res = new ArrayList<>();

            Stack<Integer> s1 = new Stack<>(); // predecessors
            Stack<Integer> s2 = new Stack<>(); // successors

            inorder(root, target, false, s1);
            inorder(root, target, true, s2);

            while (k-- > 0) {
                if (s1.isEmpty())
                    res.add(s2.pop());
                else if (s2.isEmpty())
                    res.add(s1.pop());
                else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                    res.add(s1.pop());
                else
                    res.add(s2.pop());
            }

            return res;
        }

        // inorder traversal
        void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
            if (root == null) return;

            inorder(reverse ? root.right : root.left, target, reverse, stack);
            // early terminate, no need to traverse the whole tree
            if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
            // track the value of current node
            stack.push(root.val);
            inorder(reverse ? root.left : root.right, target, reverse, stack);
        }
    }


//////////////////////////////////////////////////////


    //O(logN) Java Solution with two stacks following hint
    public class Solution2 {
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> ret = new LinkedList<>();
            Stack<TreeNode> succ = new Stack<>();
            Stack<TreeNode> pred = new Stack<>();
            initializePredecessorStack(root, target, pred);
            initializeSuccessorStack(root, target, succ);
            if(!succ.isEmpty() && !pred.isEmpty() && succ.peek().val == pred.peek().val) {
                getNextPredecessor(pred);
            }
            while(k-- > 0) {
                if(succ.isEmpty()) {
                    ret.add(getNextPredecessor(pred));
                } else if(pred.isEmpty()) {
                    ret.add(getNextSuccessor(succ));
                } else {
                    double succ_diff = Math.abs((double)succ.peek().val - target);
                    double pred_diff = Math.abs((double)pred.peek().val - target);
                    if(succ_diff < pred_diff) {
                        ret.add(getNextSuccessor(succ));
                    } else {
                        ret.add(getNextPredecessor(pred));
                    }
                }
            }
            return ret;
        }

        private void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> succ) {
            while(root != null) {
                if(root.val == target) {
                    succ.push(root);
                    break;
                } else if(root.val > target) {
                    succ.push(root);
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }

        private void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> pred) {
            while(root != null){
                if(root.val == target){
                    pred.push(root);
                    break;
                } else if(root.val < target){
                    pred.push(root);
                    root = root.right;
                } else{
                    root = root.left;
                }
            }
        }

        private int getNextSuccessor(Stack<TreeNode> succ) {
            TreeNode curr = succ.pop();
            int ret = curr.val;
            curr = curr.right;
            while(curr != null) {
                succ.push(curr);
                curr = curr.left;
            }
            return ret;
        }

        private int getNextPredecessor(Stack<TreeNode> pred) {
            TreeNode curr = pred.pop();
            int ret = curr.val;
            curr = curr.left;
            while(curr != null) {
                pred.push(curr);
                curr = curr.right;
            }
            return ret;
        }
    }



//////////////////////////////////////////////////////

/*
Java 5ms iterative, following hint, O(klogn) time and space
Following the hint, I use a predecessor stack and successor stack. I do a logn traversal to initialize them until I reach the null node. Then I use the getPredecessor and getSuccessor method to pop k closest nodes and update the stacks.

Time complexity is O(klogn), since k BST traversals are needed and each is bounded by O(logn) time. Note that it is not O(logn + k) which is the time complexity for k closest numbers in a linear array.

Space complexity is O(klogn), since each traversal brings O(logn) new nodes to the stack.
 */


    public class Solution3 {
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

//////////////////////////////////////////////////////

    //Java in-order traversal 1ms solution
    public class Solution4 {
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            LinkedList<Integer> list = new LinkedList<Integer>();
            closestKValuesHelper(list, root, target, k);
            return list;
        }

        /**
         * @return <code>true</code> if result is already found.
         */
        private boolean closestKValuesHelper(LinkedList<Integer> list, TreeNode root, double target, int k) {
            if (root == null) {
                return false;
            }

            if (closestKValuesHelper(list, root.left, target, k)) {
                return true;
            }

            if (list.size() == k) {
                if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) {
                    return true;
                } else {
                    list.removeFirst();
                }
            }

            list.addLast(root.val);
            return closestKValuesHelper(list, root.right, target, k);
        }
    }




//////////////////////////////////////////////////////

    //Java Two stacks Iterative solution
    public List<Integer> closestKValues5(TreeNode root, double target, int k) {
        Deque<TreeNode> bigger = new ArrayDeque<TreeNode>();
        Deque<TreeNode> smaller = new ArrayDeque<TreeNode>();
        TreeNode node = root;
        // log(n)
        while(node != null)
        {
            if(node.val > target)
            {
                bigger.push(node);
                node = node.left;
            }
            else
            {
                smaller.push(node);
                node = node.right;
            }
        }

        // k
        List<Integer> ret = new ArrayList<Integer>();
        while(ret.size() < k)
        {
            if(bigger.isEmpty() ||
                    !smaller.isEmpty() &&
                            ((bigger.peek().val - target) > (target - smaller.peek().val)))
            {
                node = smaller.pop();
                ret.add(node.val);

                // Get next smaller
                node = node.left;
                while(node != null)
                {
                    smaller.push(node);
                    node = node.right;
                }
            }
            else
            {
                node = bigger.pop();
                ret.add(node.val);

                // get next bigger
                node = node.right;
                while(node != null)
                {
                    bigger.push(node);
                    node = node.left;
                }
            }
        }

        return ret;
    }




//////////////////////////////////////////////////////

    //6

    /*
    2 ms O(N) and 6 ms O(logN) java solution


     */

    class Solution6{
        /*O(N), 2ms: In-order recursive traversal and maintaining a size k LinkedList which is sorted because of in-order*/

        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            LinkedList<Integer> list = new LinkedList<>();
            closestKValuesHelper(root, target, k, list);
            return list;
        }
        private void closestKValuesHelper(TreeNode root, double target, int k, LinkedList<Integer> list){
            if(root == null || list.size() == k && list.get(0) >= target)    return;
            closestKValuesHelper(root.left, target, k, list);
            if(list.size() == k && list.get(0) < target && target - list.get(0) > Math.abs(root.val - target)){
                list.removeFirst();
                list.addLast(root.val);
            }
            else if(list.size() < k)    list.addLast(root.val);
            closestKValuesHelper(root.right, target, k, list);
        }
    }



    class Solution7{
        /*
        O(logN), 6ms: The Stack initialization costs O(logN), and getPredecessor & getSuccessor actually cost O(MAX(logN, K)) in worst case, which will work better when logN is much greater than K.

         */
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> list = new ArrayList<>();
            Stack<TreeNode> pred = new Stack<>(), succ = new Stack<>();
            initStack(pred, succ, root, target);
            while(k-- > 0){
                if(succ.isEmpty() || !pred.isEmpty() && target - pred.peek().val < succ.peek().val - target){
                    list.add(pred.peek().val);
                    getPredecessor(pred);
                }
                else{//Since N > k, always have something to add
                    list.add(succ.peek().val);
                    getSuccessor(succ);
                }
            }
            return list;
        }

        private void initStack(Stack<TreeNode> pred, Stack<TreeNode> succ, TreeNode root, double target){
            while(root != null){
                if(root.val <= target){
                    pred.push(root);
                    root = root.right;
                }
                else{
                    succ.push(root);
                    root = root.left;
                }
            }
        }
        private void getPredecessor(Stack<TreeNode> st){
            TreeNode node = st.pop();
            if(node.left != null){
                st.push(node.left);
                while(st.peek().right != null)  st.push(st.peek().right);
            }
        }
        private void getSuccessor(Stack<TreeNode> st){
            TreeNode node = st.pop();
            if(node.right != null){
                st.push(node.right);
                while(st.peek().left != null)   st.push(st.peek().left);
            }
        }

    }

//////////////////////////////////////////////////////

    //Inorder One LinkedList Java solution beat 85%

    class Solution8{
        //This solution is maintaining a linkedlist and break the travelsal when the rightmost is larger than the leftmost.

        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> res = new LinkedList<Integer>();
            helper(root, target, k, res);
            return res;
        }
        private void helper(TreeNode root, double target, int k, List<Integer> res) {
            if (root == null) {
                return;
            }
            helper(root.left,target,k,res);
            if (res.size()< k) {
                res.add(root.val);
            } else {
                if (Math.abs(res.get(0)-target) > Math.abs(root.val-target)) {
                    res.remove(0);
                    res.add(root.val);
                } else {
                    return;
                }
            }
            helper(root.right,target,k,res);
        }
    }




//////////////////////////////////////////////////////

    //Clear Java Solution with one stack one linkedlist

    class Solution9{
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            Stack<TreeNode> stack = new Stack<>();
            LinkedList<Integer> ret = new LinkedList<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                if (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pop();
                    if(ret.size() < k) {
                        ret.addLast(curr.val);
                    } else {
                        if(Math.abs(ret.getFirst()-target) > Math.abs(curr.val-target)) {
                            ret.removeFirst();
                            ret.addLast(curr.val);
                        } else break;
                    }
                    curr = curr.right;
                }
            }
            return ret;
        }
    }


}
/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */