package _04_Tree._Traverse;

import lib.TreeNode;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Traverse_Iterative {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();

            preorder.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }

////////////////////////////////////////////////////////////////////////

    public List<Integer> inorderTraversal2(TreeNode root) {
        // write your code here

        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();

            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }


////////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null; // previously traversed node
        TreeNode curr = root;

        if (root == null) {
            return result;
        }

        stack.push(root);
        while (!stack.empty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                // traverse down the tree
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                // traverse up the tree from the left
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else { // traverse up the tree from the right
                result.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }

        return result;
    }

////////////////////////////////////////////////////////////////////////

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        return result;
    }

////////////////////////////////////////////////////////////////////////
    
    // Bittiger
    class Node{
        int value;
        Node left;
        Node right;
        Node(){
            value = -1;
            left = null;
            right = null;
        }
    }

    class Pair{
        Node node;
        int op;// 0 visit, 1 print
        Pair(Node node, int op){
            this.node = node;
            this.op = op;
        }
    }

    class Traverser{//BFS
        void order(Node root, int order){ // 0 pre; 1 in; 2 post
            Stack<Pair> processing = new Stack<Pair>();
            processing.add(new Pair(root, 0));

            while(processing.isEmpty() == false){
                Pair temp = processing.pop();
                if(temp.node == null) continue;
                if(temp.op == 1){
                    System.out.print(temp.node.value + " ");
                    continue;
                }
                //op == 0
                switch (order){
                    case 0:
                        processing.add(new Pair(temp.node.right, 0));
                        processing.add(new Pair(temp.node.left, 0));
                        processing.add(new Pair(temp.node, 1));
                        break;

                    case 1:
                        processing.add(new Pair(temp.node.right, 0));
                        processing.add(new Pair(temp.node, 1));
                        processing.add(new Pair(temp.node.left, 0));
                        break;

                    case 2:
                        processing.add(new Pair(temp.node, 1));
                        processing.add(new Pair(temp.node.right, 0));
                        processing.add(new Pair(temp.node.left, 0));
                        break;

                }
            }
        }

    }
    public static void main(String[] args) throws FileNotFoundException {

    }
/*
        0
     1    2
    3 4  5
     6 7
 */

    @Test
    public void test01() throws FileNotFoundException{
        Scanner in = new Scanner(new File(
                "/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/64_67"));
        int n = in.nextInt();
        Traverser traverser = new Traverser();
        while (n != -1) {
            //build tree
            Node[] tree = new Node[n];
            for (int i = 0; i < n; ++i) tree[i] = new Node();

            for (int i = 0; i < n; ++i) {
                tree[i].value = in.nextInt();
                int leftId = in.nextInt();
                if(leftId != -1)  tree[i].left = tree[leftId];
                int rightId = in.nextInt();
                if(rightId != -1) tree[i].right = tree[rightId];
            }

            //calculate
            traverser.order(tree[0], 0);
            System.out.println();

            traverser.order(tree[0], 1);
            System.out.println();

            traverser.order(tree[0], 2);
            System.out.println();

            n = in.nextInt();
        }
        in.close();
    }

/*
        0
     1    2
    3 4  5
     6 7
 */
////////////////////////////////////////////////////////////////////////
}
