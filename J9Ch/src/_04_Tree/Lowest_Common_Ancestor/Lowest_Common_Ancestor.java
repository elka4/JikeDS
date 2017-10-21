package _04_Tree.Lowest_Common_Ancestor;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
88
 Lowest Common Ancestor
 * Created by tianhuizhu on 6/28/17.
 */
//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

//Assume two nodes are exist in tree.
public class Lowest_Common_Ancestor {
    //Version : Divide & Conquer

    // 在root为根的二叉树中找A,B的LCA:
    // 如果找到了就返回这个LCA
    // 如果只碰到A，就返回A
    // 如果只碰到B，就返回B
    // 如果都没有，就返回null
    public TreeNode lowestCommonAncestor(TreeNode root,
                         TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }

        // Divide
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);

        // Conquer
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
/*
  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7
 */
    @Test
    public void test01(){
        int[] arr = {4,3,7};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.right.setLeftChild(new TreeNode(5));
        root.right.setRightChild(new TreeNode(6));

        System.out.println("root: ");
        root.print();
        lowestCommonAncestor(root, root.right.left, root.right.right).print();

    }

////////////////////////////////////////////////////////////////////////
    
    //Version 1: Traditional Method， 如果有父亲节点
    @SuppressWarnings("all")

    public class _88Lowest_Common_Ancestor_parentNode {
        class TreeNode {
            public TreeNode parent, left, right;
        }

        private ArrayList<TreeNode> getPath2Root(TreeNode node) {
            ArrayList<TreeNode> list = new ArrayList<TreeNode>();
            while (node != null) {
                list.add(node);
                node = node.parent;
            }
            return list;
        }

        public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
            ArrayList<TreeNode> list1 = getPath2Root(node1);
            ArrayList<TreeNode> list2 = getPath2Root(node2);

            int i, j;
            for (i = list1.size() - 1, j = list2.size() - 1;
                 i >= 0 && j >= 0; i--, j--) {

                if (list1.get(i) != list2.get(j)) {
                    return list1.get(i).parent;
                }
            }
            return list1.get(i+1);
        }
    }
////////////////////////////////////////////////////////////////////////
    
/*
  //不用recursion的做法？
    class ResultType{
    		TreeNode lca;
    		int count;
    }
    class Lowest_Common_Ancestor3 {
    		
    }
 */



/*
 * Given the root and two nodes in a Binary Tree. Find the 
 * lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth
 which is the ancestor of both nodes.

Example: For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7

Tags：LintCode Copyright LinkedIn Binary Tree Facebook
Related Problems： Easy Lowest Common Ancestor II 29 %
 * 
 * */
////////////////////////////////////////////////////////////////////////
    
    
    // Bittigger

    static  class Node {
        int id;
        Node left;
        Node right;
        Node(int id) {
            left = null;
            right = null;
            this.id = id;
        }
    }

    static class Finder{
        Node findFromRoot(Node a, Node b, Node root){
            if(root == null || root == a || root == b) return root;
            Node left = findFromRoot(a, b, root.left);
            Node right = findFromRoot(a, b, root.right);
            if(left != null && right != null) return root;
            if(left != null) return left;
            return right;
        }
    }

    /*
        0
     1    2
    3 4  5
     6 7
 */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(
                "/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/input_63"));
        int n = in.nextInt();
        Finder finder = new Finder();
        while (n != -1) {
            //build tree
            Node[] tree = new Node[n];
            for (int i = 0; i < n; ++i) tree[i] = new Node(i);
            for (int i = 0; i < n; ++i) {
                int leftId = in.nextInt();
                if(leftId != -1) tree[i].left = tree[leftId];
                int rightId = in.nextInt();
                if(rightId != -1) tree[i].right = tree[rightId];
            }
            int m = in.nextInt();//3， 总共找几对node的公共祖先, 6和3， 2和5， 2和7
            while(m-- != 0) {
                System.out.println(finder.findFromRoot(
                        tree[in.nextInt()], tree[in.nextInt()], tree[0]).id);
            }
            n = in.nextInt();
        }
        in.close();
    }
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

    //Java Solution 1

    public TreeNode lowestCommonAncestor11(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        if(root==p || root==q)
            return root;

        TreeNode l = lowestCommonAncestor11(root.left, p, q);
        TreeNode r = lowestCommonAncestor11(root.right, p, q);

        if(l!=null&&r!=null){
            return root;
        }else if(l==null&&r==null){
            return null;
        }else{
            return l==null?r:l;
        }
    }
    //To calculate time complexity, we know that f(n)=2*f(n-1)=2*2*f(n-2)=2^(logn), so time=O(n).



//////////////////////////////////////////////////////////////////////////

    /*
    Java Solution 2 - deprecated

    Please use the following diagram to walk through the solution.


     */
    //Since each node is visited in the worst case, time complexity is O(n).

    class Entity{
        public int count;
        public TreeNode node;

        public Entity(int count, TreeNode node){
            this.count = count;
            this.node = node;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        return lcaHelper(root, p, q).node;
    }

    public Entity lcaHelper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return new Entity(0, null);

        Entity left = lcaHelper(root.left, p, q);
        if(left.count==2)
            return left;

        Entity right = lcaHelper(root.right,p,q);
        if(right.count==2)
            return right;

        int numTotal = left.count + right.count;
        if(root== p || root == q){
            numTotal++;
        }

        return new Entity(numTotal, root);
    }

////////////////////////////////////////////////////////////////////////

}
