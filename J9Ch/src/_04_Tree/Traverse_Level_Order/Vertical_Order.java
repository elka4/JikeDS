package _04_Tree.Traverse_Level_Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import lib.*;
import org.junit.Test;

/*
LeetCode – Binary Tree Vertical Order Traversal (Java)

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

Java Solution

For each node, its left child's degree is -1 and is right child's degree is +1. We can do a level order traversal and save the degree information.
 */
public class Vertical_Order {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        qCol.offer(0);

        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = qCol.poll();
            if(!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>(Arrays.asList(curr.val)));
            } else {
                map.get(col).add(curr.val);
            }
            if(curr.left != null) {
                queue.offer(curr.left);
                qCol.offer(col - 1);
            }
            if(curr.right != null) {
                queue.offer(curr.right);
                qCol.offer(col + 1);
            }
        }
        for(int n : map.keySet()) {
            results.add(map.get(n));
        }
        return results;
    }


//////////////////////////////////////////////////////////////////////////

    // version: 高频题班
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        // Write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Map<Integer, List<Integer>> col = new HashMap<>();
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> qNode = new LinkedList<>();

        qCol.offer(0);
        qNode.offer(root);
        // 以qCol为主，同时操作qNode
        //
        while (!qCol.isEmpty()) {                      // bfs
            int c = qCol.poll();
            TreeNode node = qNode.poll();

            col.putIfAbsent(c, new ArrayList<>());
            col.get(c).add(node.val);

            if (node.left != null) {
                qCol.offer(c - 1);
                qNode.offer(node.left);
            }
            if (node.right != null) {
                qCol.offer(c + 1);
                qNode.offer(node.right);
            }
        }

        for (int i = Collections.min(col.keySet()); i <= Collections.max(col.keySet()); i++) {
            ans.add(col.get(i));
        }
        return ans;
    }


    @Test
    public void test(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        root.print();

        List<List<Integer>> result = verticalOrder2(root);

        System.out.println(result);
    }
    /*
                   3
                  / \
                 /   \
                 9   20
                    / \
                    15 7

                [[9], [3, 15], [20], [7]]
     */
////////////////////////////////////////////////////////////
    public List<List<Integer>> verticalOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null)
            return result;

        // level and list
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<Integer> level = new LinkedList<Integer>();

        queue.offer(root);
        level.offer(0);

        int minLevel=0;
        int maxLevel=0;

        while(!queue.isEmpty()){
            TreeNode p = queue.poll();
            int l = level.poll();

            //track min and max levels
            minLevel=Math.min(minLevel, l);
            maxLevel=Math.max(maxLevel, l);

            if(map.containsKey(l)){
                map.get(l).add(p.val);
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(p.val);
                map.put(l, list);
            }

            if(p.left!=null){
                queue.offer(p.left);
                level.offer(l-1);
            }

            if(p.right!=null){
                queue.offer(p.right);
                level.offer(l+1);
            }
        }


        for(int i=minLevel; i<=maxLevel; i++){
            if(map.containsKey(i)){
                result.add(map.get(i));
            }
        }

        return result;
    }
    
/////////////////////////////////////////////////////////////////////
    // VerticalTraversal_BFS
    class Node implements Comparable<Node>{
        int value;
        int id;
        int column;
        @Override
        public int compareTo(final Node temp){
            if(column == temp.column) return Integer.compare(id, temp.id);
            else return Integer.compare(column, temp.column);
        }

    }

    @Test
    public void test02()throws  FileNotFoundException {
        Scanner in = new Scanner(new File(
                "/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/intpu_verticalTraversal"));
        //read input process
        int n = in.nextInt();
        while(n != -1){
            //build tree
            Node[] tree = new Node[n];
            for(int i = 0; i < n; ++i) tree[i] = new Node();
            tree[0].column = 0;
            for(int i = 0; i < n; ++i){
                tree[i].value = in.nextInt();
                int leftID = in.nextInt();
                if(leftID != -1) tree[i].column = tree[i].column - 1;
                int rightID = in.nextInt();
                if(rightID != -1) tree[i].column = tree[i].column + 1;
            }

            Arrays.sort(tree);

            for(int i = 0; i < n; ++i) System.out.print(tree[i].value + " ");

            System.out.println();

            n = in.nextInt();
        }
        in.close();
    }
    /*
        1
     2     3
         4
2 1 4 3
Process finished with exit code 0


 */

/////////////////////////////////////////////////////////////////////

    // VerticalTraversal_DFS
    class Node3{
        int value;
        Node3 left;
        Node3 right;
    }
    static  class DFS{
        HashMap<Integer, LinkedList> rank; //id, list
        DFS(){
            rank = new HashMap<>();
        }
        void traverse(Node3 ptr, int pos){
            if(ptr == null) return;
            if(rank.containsKey(pos) == false) rank.put(pos,new LinkedList());
            rank.get(pos).add(ptr.value);
            traverse(ptr.left, pos-1);
            traverse(ptr.right, pos+1);
        }
        void display(){
            //find the min left
            int pos = 0;
            while(rank.containsKey(pos) == true){
                pos--;//最后得到的pos是最左的前一个
            }
            for(int i = pos + 1; rank.containsKey(i); ++i){
                rank.get(i).forEach((value) -> System.out.print(" " + value));
            }
            System.out.println();
        }
    }
    
    @Test
    public void test03() throws  FileNotFoundException{
        Scanner in = new Scanner(new File(
                "/Users/tianhuizhu/Downloads/uber/code/JikeDS/top100/src/_2Tree/intpu_verticalTraversal"));
        //read input process
        int n = in.nextInt();
        while(n != -1){
            //build tree
            Node3[] tree = new Node3[n];
            for(int i = 0; i < n; ++i) tree[i] = new Node3();
            for(int i = 0; i < n; ++i){
                tree[i].value = in.nextInt(); //第一个是数值
                int leftID = in.nextInt();//第二个是编号
                if(leftID != -1) tree[i].left = tree[leftID];
                int rightID = in.nextInt();//第三个是编号
                if(rightID != -1) tree[i].right = tree[rightID];
            }

            DFS dfs = new DFS();
            dfs.traverse(tree[0], 0);
            dfs.display();
            n = in.nextInt();
        }
        in.close();
        
    }
/*
        1
     2     3
         4
2 1 4 3
Process finished with exit code 0

            4
          3   2
         1  5
            7   6
7
4 1 2
3 3 4
2 5 6
1 -1 -1
5 -1 -1
7 -1 -1
6 -1 -1

 */

    
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
}
