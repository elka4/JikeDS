package _04_Tree._Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import lib.*;
import org.junit.Test;

//  314. Binary Tree Vertical Order Traversal
//  https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
//  Hash Table
//  10: 1 or 7 BFS 双Queue。10最好的地方是DFS，用了TreeMap。
//  主要都是用BFS+HashTable。也可以用DFS:5。
public class _314_Binary_Tree_Vertical_Order_Traversal {
//-----------------------------------------------------------------------------------

    //1
    //5ms Java Clean Solution
    //BFS
    //双Queue平行运行。HashTable存储col index和value，最后一次性导出。

    public List<List<Integer>> verticalOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);
        cols.add(0);

        //因为不知道树最左最右分别到哪儿，也就是col 的index，所以要在过程求
        //这个值也是
        int min = 0; int max = 0;

        while (!q.isEmpty()) {
            //poll双queue
            TreeNode node = q.poll(); int col = cols.poll();

            //处理HashTable
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            //map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(node.val);

            //处理Queue。
            if (node.left != null) {
                q.add(node.left); cols.add(col - 1);//add双Queue
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                q.add(node.right); cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;
    }
    @Test
    public void test1(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        root.print();List<List<Integer>> result = verticalOrder1(root);
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
//-----------------------------------------------------------------------------------
    //2
    //Alternatively, we can calculate the rang first, then insert into buckets.
    //BFS
    //双Queue并行。不用HashTable。
    //这个BFS的while有点意思，每次循环就是处理一个node。一开始求出最左边的node加进去。按照从左向右的加入每一个node。
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        int[] range = new int[] {0, 0};
        getRange(root, range, 0);

        for (int i = range[0]; i <= range[1]; i++) {
            result.add(new ArrayList<Integer>());
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        queue.add(root);
        colQueue.add(-range[0]);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = colQueue.poll();

            result.get(col).add(node.val);

            if (node.left != null) {
                queue.add(node.left);
                colQueue.add(col - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                colQueue.add(col + 1);
            }
        }

        return result;
    }

    //这个有点意思
    public void getRange(TreeNode root, int[] range, int col) {
        if (root == null) {
            return;
        }
        range[0] = Math.min(range[0], col);
        range[1] = Math.max(range[1], col);

        getRange(root.left, range, col - 1);
        getRange(root.right, range, col + 1);
    }

    @Test
    public void test2(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        root.print();List<List<Integer>> result = verticalOrder2(root);
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
//-----------------------------------------------------------------------------------
    //3
    //BFS 和1相同
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
    @Test
    public void test3(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        root.print();List<List<Integer>> result = verticalOrder3(root);
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
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
    //4
    //Bittigger
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
    public void test4()throws  FileNotFoundException {
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
    //5
    //Bittigger
    // VerticalTraversal_DFS
    // DFS,
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
            if(!rank.containsKey(pos)) rank.put(pos,new LinkedList());
            rank.get(pos).add(ptr.value);
            traverse(ptr.left, pos-1);
            traverse(ptr.right, pos+1);
        }
        void display(){
            //find the min left
            int pos = 0;
            while(rank.containsKey(pos)){
                pos--;//最后得到的pos是最左的前一个
            }
            for(int i = pos + 1; rank.containsKey(i); ++i){
                rank.get(i).forEach((value) -> System.out.print(" " + value));
            }
            System.out.println();
        }
    }
    
    @Test
    public void test5() throws  FileNotFoundException{
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
//-----------------------------------------------------------------------------------

    //6
    //jiuzhang
    //BFS。和1一样。
    public List<List<Integer>> verticalOrder_J1(TreeNode root) {
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
            //其实这么写没有1那么简洁。
            //不过要记住后面的操作。记得Arrays.asList（）怎么回事儿。
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

    @Test
    public void test6(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        root.print();List<List<Integer>> result = verticalOrder_J1(root);
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
//-----------------------------------------------------------------------------------

    //7
    // version: 高频题班
    //BFS
    // Java8
    public List<List<Integer>> verticalOrder_J2(TreeNode root) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
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
        //要记住这里Collections的操作！！！
        for (int i = Collections.min(col.keySet()); i <= Collections.max(col.keySet()); i++) {
            result.add(col.get(i));
        }
        return result;
    }


    @Test
    public void test7(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        root.print();List<List<Integer>> result = verticalOrder_J2(root);
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

//---------------------------------------------------------------------------------------
    //8
    //DFS by me
    //这个不能AC因为会：
    public List<List<Integer>> verticalOrder8(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        HashMap<Integer, LinkedList> map = new HashMap<>();
        helper(root, map, 0);
        for (int i = Collections.min(map.keySet()); i <= Collections.max(map.keySet()); i++) {
            result.add(map.get(i));
        }
        return result;

    }


    void helper(TreeNode node, HashMap<Integer, LinkedList> map, int pos){
        if(node == null) return;
        map.putIfAbsent(pos, new LinkedList());
        map.get(pos).add(node.val);
        helper(node.left, map, pos-1);
        helper(node.right, map,  pos+1);
    }
//---------------------------------------------------------------------------------------
    //9
    //DFS

    /*
    The code runs in 4 ms about 50% of the times.

As is often the case, preallocating memory tends to make algorithms faster. With my DFS implementation I am doing a couple of things that most BFS solutions do not do:

First I traverse the tree and determine the min, max indices as well as the depth of the tree.
I use these limits to construct a 2D array of size (max index - min index + 1). e.g. if min index = -2, and max index = 3, then the length of the array rows is 3 + 2 + 1(root).
The length of the array columns is determined by the depth (I call it 'height') of the tree.
I then create an empty ArrayList in each cell of our 2D array to handle the collisions. Note that I preallocate 2 slots of capacity for each ArrayList, since we can have at most 2 nodes competing for the same cell.
Now that all the memory is preallocated I traverse the tree the second time and fill in the cells of the 2D array. Note that some of the cells will contain empty arrayLists in the end, so this is not the most space efficient method.
     */
    public List<List<Integer>> verticalOrder9(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        int[] minmax = new int[3];

        getEnds(root, 0, 1, minmax);

        ArrayList<Integer>[][] tree = new ArrayList[minmax[1] - minmax[0] + 1][minmax[2]];
        for (int i = 0; i < tree.length; i++)
            for (int j = 0; j < tree[0].length; j++)
                tree[i][j] = new ArrayList<Integer>(2);

        order(root, 0, -minmax[0], 0, tree);

        for (int i = 0; i < tree.length; i++) {
            res.add(new ArrayList<Integer>());
            for (int j = 0; j < tree[0].length; j++) {
                if (!tree[i][j].isEmpty()) {
                    res.get(i).addAll(tree[i][j]);
                }
            }
        }
        return res;
    }

    private void order(TreeNode root, int level, int l, int height, ArrayList<Integer>[][] tree) {
        if (root == null) return;
        tree[l + level][height].add(root.val);
        order(root.left, level-1, l, height + 1, tree);
        order(root.right, level+1, l, height + 1, tree);
    }

    private void getEnds(TreeNode root, int level, int height, int[] minmax) {
        if (root == null) return;
        minmax[0] = Math.min(level, minmax[0]);
        minmax[1] = Math.max(level, minmax[1]);
        minmax[2] = Math.max(height, minmax[2]);
        getEnds(root.left, level-1, height + 1, minmax);
        getEnds(root.right, level+1, height + 1, minmax);
    }
//---------------------------------------------------------------------------------------
    //10
    //Java DFS Solution with TreeMap
    //TreeMap的value是TreeMap，有意思。
    public List<List<Integer>> verticalOrder10(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> tmap = new TreeMap<>();

        dfs(root, 0, 0, tmap);

        List<List<Integer>> result = new ArrayList<>();

        for(TreeMap<Integer, List<Integer>> col : tmap.values()){
            List<Integer> list = new ArrayList<>();
            for(List<Integer> v : col.values()){
                list.addAll(v);
            }
            result.add(list);
        }
        return result;
    }

    private void dfs(TreeNode node, int depth, int col,
                     TreeMap<Integer, TreeMap<Integer, List<Integer>>> tmap){
        if(node == null)return;

        TreeMap<Integer, List<Integer>> cMap =
                tmap.getOrDefault(col, new TreeMap<Integer, List<Integer>>());

        List<Integer> list = cMap.getOrDefault(depth, new ArrayList<Integer>());
        list.add(node.val);
        cMap.put(depth, list);
        tmap.put(col, cMap);

        dfs(node.left, depth+1, col-1, tmap);
        dfs(node.right, depth+1, col+1, tmap);

    }

//---------------------------------------------------------------------------------------

}
/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

 */