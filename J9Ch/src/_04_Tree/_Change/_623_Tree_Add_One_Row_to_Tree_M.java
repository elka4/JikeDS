package _04_Tree._Change;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//  623. Add One Row to Tree
//  https://leetcode.com/problems/add-one-row-to-tree/description/
//  6:
//  Tree
public class _623_Tree_Add_One_Row_to_Tree_M {
    //https://leetcode.com/problems/add-one-row-to-tree/solution/
//------------------------------------------------------------------------------------------
    //1
    //Approach #1 Using Recursion(DFS) [Accepted]
    public class Solution1 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 1) {
                TreeNode n = new TreeNode(v);
                n.left = root;
                return n;
            }
            insert(v, root, 1, d);
            return root;
        }

        public void insert(int val, TreeNode node, int depth, int n) {
            if (node == null)
                return;
            if (depth == n - 1) {
                TreeNode t = node.left;
                node.left = new TreeNode(val);
                node.left.left = t;
                t = node.right;
                node.right = new TreeNode(val);
                node.right.right = t;
            } else {
                insert(val, node.left, depth + 1, n);
                insert(val, node.right, depth + 1, n);
            }
        }
    }
    @Test
    public void test01() {
        int[] arr = {4,2,6,3,1,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        new Solution1().addOneRow(root, 1, 2);
        root.print();
    }
//------------------------------------------------------------------------------------------
    //2
    //Approach #2 Using stack(DFS) [Accepted]
    public class Solution2 {
        class Node{
            Node(TreeNode n,int d){
                node=n;
                depth=d;
            }
            TreeNode node;
            int depth;
        }
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 1) {
                TreeNode n = new TreeNode(v);
                n.left = root;
                return n;
            }
            Stack<Node> stack=new Stack<>();
            stack.push(new Node(root,1));

            while(!stack.isEmpty()) {
                Node n=stack.pop();
                if(n.node==null)
                    continue;
                if (n.depth == d - 1 ) {
                    TreeNode temp = n.node.left;
                    n.node.left = new TreeNode(v);
                    n.node.left.left = temp;
                    temp = n.node.right;
                    n.node.right = new TreeNode(v);
                    n.node.right.right = temp;

                } else{
                    stack.push(new Node(n.node.left, n.depth + 1));
                    stack.push(new Node(n.node.right, n.depth + 1));
                }
            }
            return root;
        }
    }

    @Test
    public void test02() {
        int[] arr = {4,2,6,3,1,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        new Solution2().addOneRow(root, 1, 2);
        root.print();
    }
//------------------------------------------------------------------------------------------
    //3
    //Approach #3 Using queue(BFS) [Accepted]
    public class Solution3 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 1) {
                TreeNode n = new TreeNode(v);
                n.left = root;
                return n;
            }
            Queue< TreeNode > queue = new LinkedList< >();
            queue.add(root);
            int depth = 1;
            while (depth < d - 1) {
                Queue < TreeNode > temp = new LinkedList < > ();
                while (!queue.isEmpty()) {
                    TreeNode node = queue.remove();
                    if (node.left != null) temp.add(node.left);
                    if (node.right != null) temp.add(node.right);
                }
                queue = temp;
                depth++;
            }
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                TreeNode temp = node.left;
                node.left = new TreeNode(v);
                node.left.left = temp;
                temp = node.right;
                node.right = new TreeNode(v);
                node.right.right = temp;
            }
            return root;
        }
    }

    @Test
    public void test03() {
        int[] arr = {4,2,6,3,1,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        new Solution3().addOneRow(root, 1, 2);
        root.print();
    }
//------------------------------------------------------------------------------------------
    //4
    //Simple Java solution - O(N)
    //Simply traverse recursively to the depth d - 1 and add nodes accordingly.
    public class Solution4 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 1) {
                TreeNode newRoot = new TreeNode(v);
                newRoot.left = root;
                return newRoot;
            }
            add(root, v, d, 1);
            return root;
        }

        private void add(TreeNode node, int v, int d, int currentDepth) {
            if (node == null) {
                return;
            }

            if (currentDepth == d - 1) {
                TreeNode temp = node.left;
                node.left = new TreeNode(v);
                node.left.left = temp;

                temp = node.right;
                node.right = new TreeNode(v);
                node.right.right = temp;
                return;
            }

            add(node.left, v, d, currentDepth + 1);
            add(node.right, v, d, currentDepth + 1);
        }
    }
    @Test
    public void test04() {
        int[] arr = {4,2,6,3,1,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        new Solution4().addOneRow(root, 1, 2);
        root.print();
    }
//------------------------------------------------------------------------------------------
    //5
    public class Solution5 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if(d == 1) {
                TreeNode newRoot = new TreeNode(v);
                newRoot.left = root;
                return newRoot;
            }
            addSubNodes(root,v,d,2);
            return root;
        }
        private void addSubNodes(TreeNode root, int v, int d, int p) {
            if(d == p) {
                TreeNode newLeft = new TreeNode(v);
                TreeNode newRight = new TreeNode(v);
                newLeft.left = root.left;
                root.left = newLeft;
                newRight.right = root.right;
                root.right = newRight;
                return;
            }
            if(root.left != null)//put null check here to avoid 1/2 stack.
                addSubNodes(root.left,v,d,p+1);
            if(root.right != null)
                addSubNodes(root.right,v,d,p+1);
        }
    }
    @Test
    public void test05() {
        int[] arr = {4,2,6,3,1,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        new Solution5().addOneRow(root, 1, 2);
        root.print();
    }
//------------------------------------------------------------------------------------------
    //6
    public class Solution6 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 0 || d == 1) {
                TreeNode newroot = new TreeNode(v);
                newroot.left = d == 1 ? root : null;
                newroot.right = d == 0 ? root : null;
                return newroot;
            }
            if (root != null && d >= 2) {
                root.left = addOneRow(root.left, v, d > 2 ? d - 1 : 1);
                root.right = addOneRow(root.right, v, d > 2 ? d - 1 : 0);
            }
            return root;
        }
    }
    @Test
    public void test06() {
        int[] arr = {4,2,6,3,1,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        new Solution6().addOneRow(root, 1, 2);
        root.print();
    }
//------------------------------------------------------------------------------------------
}
/*
Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.

Example 1:
Input:
A binary tree as following:
       4
     /   \
    2     6
   / \   /
  3   1 5

v = 1

d = 2

Output:
       4
      / \
     1   1
    /     \
   2       6
  / \     /
 3   1   5

Example 2:
Input:
A binary tree as following:
      4
     /
    2
   / \
  3   1

v = 1

d = 3

Output:
      4
     /
    2
   / \
  1   1
 /     \
3       1
Note:
The given d is in range [1, maximum depth of the given tree + 1].
The given binary tree has at least one tree node.
 */