package _04_Tree._Right;
import lib.TreeNode;

import java.util.*;
import java.util.List;

//  199. Binary Tree Right Side View
//  https://leetcode.com/problems/binary-tree-right-side-view/description/
//  Tree, BFS, DFS
//  Populating Next Right Pointers in Each Node Boundary of Binary Tree
//  5:  1 stack DFS, 2 BFS, 3 DFS, 4 HashMap DFS. 3最好理解!!!!
public class _199_Tree_Binary_Tree_Right_Side_View_M {
    //https://leetcode.com/articles/binary-tree-right-side-view/

//-------------------------------------------------------------------------------------
    //1
    //DFS
    //    Initial Thoughts
    //    Approach #1 Depth-First Search [Accepted]
    /*
    Intuition

We can efficiently obtain the right-hand view of the binary tree if we visit each node in the proper order.

Algorithm

One of the aforementioned orderings is defined by a depth-first search in which we

always visit the right subtree first.

This guarantees that the first time we visit a particular depth of the tree, the node that we are visiting is the rightmost node at that depth. Therefore, we can store the value of the first node that we visit at each depth, ultimately generating a final array of values once we know exactly how many layers are in the tree.
     */
    class Solution1 {
        public List<Integer> rightSideView(TreeNode root) {
            Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
            int max_depth = -1;

            /* These two stacks are always synchronized, providing an implicit
             * association values with the same offset on each stack. */
            Stack<TreeNode> nodeStack = new Stack<TreeNode>();
            Stack<Integer> depthStack = new Stack<Integer>();
            nodeStack.push(root);
            depthStack.push(0);

            //其实就是经典的使用stack，同时做两件事
            //一个是用双Stack做
            while (!nodeStack.isEmpty()) {
                TreeNode node = nodeStack.pop();
                int depth = depthStack.pop();

                if (node != null) {
                    max_depth = Math.max(max_depth, depth);

                    /* The first node that we encounter at a particular depth contains
                    * the correct value. */
                    if (!rightmostValueAtDepth.containsKey(depth)) {//用
                        rightmostValueAtDepth.put(depth, node.val);
                    }

                    nodeStack.push(node.left);//先push left，所以出来的时候是右边先
                    nodeStack.push(node.right);
                    depthStack.push(depth+1);//因为刚刚是push了两个node，所以depthStack也要push两次
                    depthStack.push(depth+1);
                }
            }

            /* Construct the solution based on the values that we end up with at the
             * end. */
            List<Integer> rightView = new ArrayList<Integer>();
            for (int depth = 0; depth <= max_depth; depth++) {
                rightView.add(rightmostValueAtDepth.get(depth));
            }

            return rightView;
        }
    }

//-------------------------------------------------------------------------------------
    //2
    //BFS
    //    Approach #2 Breadth-First Search [Accepted]
class Solution2 {
    public List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        /* These two Queues are always synchronized, providing an implicit
         * association values with the same offset on each Queue. */
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> depthQueue = new LinkedList<Integer>();
        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);

                /* The last node that we encounter at a particular depth contains
                * the correct value, so the correct value is never overwritten. */
                rightmostValueAtDepth.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth+1);
                depthQueue.add(depth+1);
            }
        }

        /* Construct the solution based on the values that we end up with at the
         * end. */
        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }
}

//-------------------------------------------------------------------------------------
    //3
    //DFS
    public List<Integer> rightSideView3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView3(root, result, 0);
        return result;
    }

    //preorder
    //1,2确保放进程序每层最右边的node被放进result。也就是右边的view。
    public void rightView3(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){//2.这个就是程序第一次到达某一层
            result.add(curr.val);
        }

        rightView3(curr.right, result, currDepth + 1);//1.关键就是这里，要先访问右树
        rightView3(curr.left, result, currDepth + 1);
    }

//-------------------------------------------------------------------------------------
    //4
    // 9Ch
    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, Integer> depthToValue = new HashMap<Integer, Integer>();
        dfs(depthToValue, root, 1);

        int depth = 1;
        List<Integer> result = new ArrayList<Integer>();
        while (depthToValue.containsKey(depth)) {
            result.add(depthToValue.get(depth));
            depth++;
        }
        return result;
    }

    private void dfs(HashMap<Integer, Integer> depthToValue, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        depthToValue.put(depth, node.val);
        dfs(depthToValue, node.left, depth + 1);
        dfs(depthToValue, node.right, depth + 1);
    }

//-------------------------------------------------------------------------------------
}
/*

 */

/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].


 */