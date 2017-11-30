package _04_Tree._Level;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;

//  103. Binary Tree Zigzag Level Order Traversal
//  https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-zigzag-level-order-traversal/
//  Stack Tree Breadth-first Search
//  4:  1 DFS,    2 iterative,
public class _103_Binary_Tree_Zigzag_Level_Order_Traversal_M {
        //1 DFS

        /*
        这题是从root往下，用preorder。和_366_Tree_Find_Leaves_of_Binary_Tree_M用postorder从下网上
        可以形成一对很好的对比。
         */
        public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            travel(root, result, 0);
            return result;
        }

        //preorder
        //用level % 2来在奇偶行进行。相比较之下两个容器切换就傻了点。
        //用mod这方法可以进行任意行循环切换：level % n。
        private void travel(TreeNode node, List<List<Integer>> result, int level) {
            if(node == null) return;

            if(result.size() == level) {//原来为<=。类似_366_Tree_Find_Leaves_of_Binary_Tree_M
                List<Integer> newLevel = new LinkedList<>();
                result.add(newLevel);
            }

            List<Integer> list  = result.get(level);

            if(level % 2 == 0) list.add(node.val);//在后面加

            else list.add(0, node.val); //在前面加。Deque不能从指定index取物品。3用Deque。

            travel(node.left, result, level + 1);
            travel(node.right, result, level + 1);
        }
        /*
               1                level 0   result.size():0 -> 1
              / \
             /   \
             2   3              level 1   result.size():1 -> 2
            / \
            4 5                 level 2   result.size():2 -> 3

            [[4, 5, 3], [2], [1]]
            1
         */


        @Test
        public void test01(){
            int[] arr = {2,1,3};
            TreeNode root = TreeNode.createMinimalBST(arr);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.print();
            System.out.println(zigzagLevelOrder1(root));
//            root.print();
        }
        /*
                   1
                  / \
                 /   \
                 2   3
                / \
                4 5

                [[1], [3, 2], [4, 5]]
         */
//----------------------------------------------------------------------------
        //2
        //BFS
        //和BFS的iterative操作基本一致，只是中间
        public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if(root == null) return result;

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            boolean order = true;

            while(!q.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int size = q.size();
                for(int i = 0; i < size; ++i) {
                    TreeNode n = q.poll();
                    //对list操作
                    if(order) {
                        list.add(n.val);
                    } else {
                        list.add(0, n.val);
                    }
                    //对Queue操作
                    if(n.left != null) q.add(n.left);
                    if(n.right != null) q.add(n.right);
                }
                result.add(list);

                order = order ? false : true;//好
            }
            return result;
        }

        @Test
        public void test02(){
            int[] arr = {2,1,3};
            TreeNode root = TreeNode.createMinimalBST(arr);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.print();
            System.out.println(zigzagLevelOrder2(root));
//            root.print();
        }
        /*
                   1
                  / \
                 /   \
                 2   3
                / \
                4 5

                [[1], [3, 2], [4, 5]]
         */
//----------------------------------------------------------------------------
    //3
    //Deque
    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>>  res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        //Method: Using a deque to maintain the current nodes in
        // the same level with the same order from left to right
        Deque<TreeNode> deque = new LinkedList<TreeNode>();

        boolean lefttoRight = true;//The order to put into result

        deque.addFirst(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {

                if(lefttoRight) {//Case 1
                    //对list操作
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);

                    if (cur.left != null)  deque.addLast(cur.left);
                    if (cur.right != null) deque.addLast(cur.right);
                } else {//Case 2
                    //对list操作
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);

                    if (cur.right != null) deque.addFirst(cur.right);
                    if (cur.left != null) deque.addFirst(cur.left);
                }
            }
            lefttoRight = lefttoRight ? false: true;
            res.add(list);
        }
        return res;
    }

    @Test
    public void test03(){
        int[] arr = {1,3,5,6,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(zigzagLevelOrder3(root));
    }
    /*
                root:
               1
              / \
             /   \
             3   5
            / \ /
            6 7 8

            [[1], [5, 3], [6, 7, 8]]
     */
//------------------------------------------------------------------------------
    //4
    // 9Ch   双Stack切换
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Stack<TreeNode> currLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;

        currLevel.push(root);
        boolean normalOrder = true;

        while (!currLevel.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();

            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                list.add(node.val);

                if (normalOrder) {//压栈时先左后右，出栈的时候就会是先右后左
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                } else {//反过来
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                }
            }

            result.add(list);

            //swap 两个stack
            tmp = currLevel;
            currLevel = nextLevel;
            nextLevel = tmp;

            normalOrder = !normalOrder;
        }

        return result;

    }


        @Test
        public void test04(){
            int[] arr = {2,1,3};
            TreeNode root = TreeNode.createMinimalBST(arr);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.print();
            System.out.println(zigzagLevelOrder(root));
//            root.print();
        }
/*
                   1
                  / \
                 /   \
                 2   3
                / \
                4 5

                [[1], [3, 2], [4, 5]]
 */
//------------------------------------------------------------------------------
}
/*
二叉树的锯齿形层次遍历

 描述
 笔记
 数据
 评测
给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行）

样例
给出一棵二叉树 {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
返回其锯齿形的层次遍历为：

[
  [3],
  [20,9],
  [15,7]
]
 */

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/