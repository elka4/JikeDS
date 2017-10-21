package _04_Tree.Validate;

import lib.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/*
LeetCode – Unique Binary Search Trees II (Java)

Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Analysis

Check out Unique Binary Search Trees I.

This problem can be solved by recursively forming left and right subtrees. The different combinations of left and right subtrees form the set of all unique binary search trees.
 */


public class Unique_Binary_Search_Trees_II {

    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new ArrayList<TreeNode>();
        }

        return helper(1, n);
    }

    public List<TreeNode> helper(int m, int n){
        List<TreeNode> result = new ArrayList<TreeNode>();
        if(m>n){
            result.add(null);
            return result;
        }

        for(int i=m; i<=n; i++){
            List<TreeNode> ls = helper(m, i-1);
            List<TreeNode> rs = helper(i+1, n);
            for(TreeNode l: ls){
                for(TreeNode r: rs){
                    TreeNode curr = new TreeNode(i);
                    curr.left=l;
                    curr.right=r;
                    result.add(curr);
                }
            }
        }

        return result;
    }



////////////////////////////////////////////////////////////////////

    //A simple recursive solution
    /*I start by noting that 1..n is the in-order traversal for any BST with nodes 1 to n. So if I pick i-th node as my root, the left subtree will contain elements 1 to (i-1), and the right subtree will contain elements (i+1) to n. I use recursive calls to get back all possible trees for left and right subtrees and combine them in all possible ways with the root.*/

    public class Solution2 {
        public List<TreeNode> generateTrees(int n) {

            return genTrees(1,n);
        }

        public List<TreeNode> genTrees (int start, int end)
        {

            List<TreeNode> list = new ArrayList<TreeNode>();

            if(start>end)
            {
                list.add(null);
                return list;
            }

            if(start == end){
                list.add(new TreeNode(start));
                return list;
            }

            List<TreeNode> left,right;
            for(int i=start;i<=end;i++)
            {

                left = genTrees(start, i-1);
                right = genTrees(i+1,end);

                for(TreeNode lnode: left)
                {
                    for(TreeNode rnode: right)
                    {
                        TreeNode root = new TreeNode(i);
                        root.left = lnode;
                        root.right = rnode;
                        list.add(root);
                    }
                }

            }

            return list;
        }
    }




////////////////////////////////////////////////////////////////////

    //Java Solution with DP

    class Solution3{
        public List<TreeNode> generateTrees(int n) {
            List<TreeNode>[] result = new List[n + 1];
            result[0] = new ArrayList<TreeNode>();
            if (n == 0) {
                return result[0];
            }

            result[0].add(null);
            for (int len = 1; len <= n; len++) {
                result[len] = new ArrayList<TreeNode>();
                for (int j = 0; j < len; j++) {
                    for (TreeNode nodeL : result[j]) {
                        for (TreeNode nodeR : result[len - j - 1]) {
                            TreeNode node = new TreeNode(j + 1);
                            node.left = nodeL;
                            node.right = clone(nodeR, j + 1);
                            result[len].add(node);
                        }
                    }
                }
            }
            return result[n];
        }

        private TreeNode clone(TreeNode n, int offset) {
            if (n == null) {
                return null;
            }
            TreeNode node = new TreeNode(n.val + offset);
            node.left = clone(n.left, offset);
            node.right = clone(n.right, offset);
            return node;
        }
    }






////////////////////////////////////////////////////////////////////

    class Solutoin4{
/*        Divide-and-conquer. F(i) = G(i-1) * G(n-i)
        This problem is a variant of the problem of Unique Binary Search Trees.

        I provided a solution along with explanation for the above problem, in the question "DP solution in 6 lines with explanation"

        It is intuitive to solve this problem by following the same algorithm. Here is the code in a divide-and-conquer style.*/

        public List<TreeNode> generateTrees(int n) {
            return generateSubtrees(1, n);
        }

        private List<TreeNode> generateSubtrees(int s, int e) {
            List<TreeNode> res = new LinkedList<TreeNode>();
            if (s > e) {
                res.add(null); // empty tree
                return res;
            }

            for (int i = s; i <= e; ++i) {
                List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
                List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);

                for (TreeNode left : leftSubtrees) {
                    for (TreeNode right : rightSubtrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
            return res;
        }
    }



////////////////////////////////////////////////////////////////////

    //The first method that came to mind was the brute force solution as below.

    class Solution5{
        public List<TreeNode> generateTrees(int n) {
            return generateTrees(1,n);
        }

        public List<TreeNode> generateTrees(int start,int end){
            List<TreeNode> trees = new ArrayList<TreeNode>();
            if(start>end){  trees.add(null); return trees;}

            for(int rootValue=start;rootValue<=end;rootValue++){
                List<TreeNode> leftSubTrees=generateTrees(start,rootValue-1);
                List<TreeNode> rightSubTrees=generateTrees(rootValue+1,end);

                for(TreeNode leftSubTree:leftSubTrees){
                    for(TreeNode rightSubTree:rightSubTrees){
                        TreeNode root=new TreeNode(rootValue);
                        root.left=leftSubTree;
                        root.right=rightSubTree;
                        trees.add(root);
                    }
                }
            }
            return trees;
        }
    }






////////////////////////////////////////////////////////////////////

/*
Then @6219221 reminded me it is unnecessary to create the BSTs with all brand new nodes.
Assume you have a list of all BSTs with values from 1 to n-1, every possible way to insert value n only involves changing the right tree (root inclusive) because n is always greater than root.val and the left subtree structure is fixed. So all we gotta do is to create a new copy of the right part of the tree and point the new root.left to the original left subtree. This way we reuse the left tree, which saves time and space.

How to insert Node n into the right subtree?
Given any BST on the n - 1 level, it will be only valid to put n on the root.right, root.right.right or root.right.....right locations and then move the right subtree of n.right...right to become the left child of n, in order to keep n on the right-most position as the greatest value in the tree.

Here is my implementation. Note that I do the dp from [n] back to [n to 1]. Therefore all the right subtree structures are fixed and new values are inserted into the left side, opposite to making BSTs from 1 to [1 to n].
 */






////////////////////////////////////////////////////////////////////

    /*
    Then @6219221 reminded me it is unnecessary to create the BSTs with all brand new nodes.
Assume you have a list of all BSTs with values from 1 to n-1, every possible way to insert value n only involves changing the right tree (root inclusive) because n is always greater than root.val and the left subtree structure is fixed. So all we gotta do is to create a new copy of the right part of the tree and point the new root.left to the original left subtree. This way we reuse the left tree, which saves time and space.

How to insert Node n into the right subtree?
Given any BST on the n - 1 level, it will be only valid to put n on the root.right, root.right.right or root.right.....right locations and then move the right subtree of n.right...right to become the left child of n, in order to keep n on the right-most position as the greatest value in the tree.

Here is my implementation. Note that I do the dp from [n] back to [n to 1]. Therefore all the right subtree structures are fixed and new values are inserted into the left side, opposite to making BSTs from 1 to [1 to n].
     */

    class Solution6{
        public List<TreeNode> generateTrees(int n) {
            List<TreeNode> res = new ArrayList<>();
            res.add(null);
            for(; n > 0; n--) {
                List<TreeNode> next = new ArrayList<>();
                for(TreeNode node: res) {
                    //the special case when Node(n) is root of new tree
                    TreeNode root = new TreeNode(n);
                    root.right = node;
                    next.add(root);
                    //while loop inserts new value to every possible position into the left tree side
                    while(node != null) {
                        TreeNode cRoot = new TreeNode(root.right.val);
                        //clone left subtree
                        cRoot.left = copyTree(root.right.left);
                        //reusing - point new root.right to the original right subtree
                        cRoot.right = root.right.right;
                        //curr is the cutoff node whose right child will be replaced by the new n
                        TreeNode curr = getValNode(cRoot, node.val);
                        //place n as curr's right child, make curr's original right child as the left child of n.
                        TreeNode tmp = curr.left;
                        curr.left = new TreeNode(n);
                        curr.left.right = tmp;

                        next.add(cRoot);
                        node = node.left;
                    }
                }
                res = next;
            }
            return res;
        }
        private TreeNode getValNode(TreeNode n, int val) { //find the cutoff node in the new tree
            while(n != null) {
                if(n.val == val) break;
                n = n.left;
            }
            return n;
        }

        private TreeNode copyTree(TreeNode root) { //clone the right subtree
            if(root == null) return null;
            TreeNode cRoot = new TreeNode(root.val);
            cRoot.left = copyTree(root.left);
            cRoot.right = copyTree(root.right);
            return cRoot;
        }
    }
////////////////////////////////////////////////////////////////////

    //Java 2ms solution beats 92%

    public class Solution7 {
        public List<TreeNode> generateTrees(int n) {
            if(n==0) return new ArrayList<TreeNode>();
            return generateTress(1, n);
        }

        private List<TreeNode> generateTress(int start, int end){
            if(start>end) {
                List<TreeNode> list = new ArrayList<TreeNode>();
                list.add(null);
                return list;
            }
            if(start==end) {
                List<TreeNode> list = new ArrayList<TreeNode>();
                list.add(new TreeNode(start)); return list;
            }
            List<TreeNode> roots = new ArrayList<TreeNode>();
            for(int i=start;i<=end;i++){
                List<TreeNode> leftTrees = generateTress(start, i-1);
                List<TreeNode> rightTrees = generateTress(i+1, end);
                for(int j=0;j<leftTrees.size();j++){
                    for(int k=0;k<rightTrees.size();k++){
                        TreeNode root = new TreeNode(i);
                        root.left = leftTrees.get(j);
                        root.right = rightTrees.get(k);
                        roots.add(root);
                    }
                }

            }
            return roots;
        }
    }



////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////








////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////








////////////////////////////////////////////////////////////////////


}
