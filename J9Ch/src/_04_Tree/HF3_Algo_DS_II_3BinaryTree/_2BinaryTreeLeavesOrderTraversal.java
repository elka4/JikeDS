package _04_Tree.HF3_Algo_DS_II_3BinaryTree;

import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//  Binary Tree Leaves Order Traversal
//  leetcode 366. Find Leaves of Binary Tree
public class _2BinaryTreeLeavesOrderTraversal {
    //jiuzhang
    /*
    这个是有点divide and conquer, dfs()获得左右子树level，level=max（左，右）+1，
    然后postorder处理，在每个level的list上加上当前node（也就是root）的val
    ArrayList 的index是level， 存的value是当前root的val

    另一个方案就是用hashmap
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        dfs(root, results);
        return results;
    }

    int dfs(TreeNode root, List<List<Integer>> results) {
        if (root == null) {
            return 0; //这样最下面的叶子的level是1
        }



        int level = Math.min( dfs(root.left, results), dfs(root.right, results)) + 1;

        int size = results.size();
        if (level > size) {
            results.add(new ArrayList<Integer>());
        }
        results.get(level - 1).add(root.val);



        return level;
    }

    @Test
    public void test01(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.print();

        List<List<Integer>> result = findLeaves(root);

        System.out.println(result);
    }
    /*
               1
              / \
             /   \
             2   3
            / \
            4 5

            [[4, 5, 3], [2], [1]]
     */

    /*
    如果先访问root.right， 每一个level就是从右向左

                   1
                  / \
                 /   \
                 2   3
                / \
                4 5

                [[3, 5, 4], [2], [1]]
     */

/*
以下是改造上面方法，首先要把level作为传递参数放进dfs，其次要preorder
 */
    public List<List<Integer>> findLeaves2(TreeNode root) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        dfs2(root, results, 1);
        return results;
    }

    int dfs2(TreeNode root, List<List<Integer>> results, int level) {
        if (root == null) {
            return 0;
        }

        int size = results.size();
        if (level > size) {
            results.add(new ArrayList<Integer>());
        }
        results.get(level - 1).add(root.val);

        level = Math.max( dfs2(root.left, results, level + 1),
                dfs2(root.right, results, level + 1)) + 1;





        return level;
    }
    @Test
    public void test02(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.print();

        List<List<Integer>> result = findLeaves2(root);

        System.out.println(result);
    }
    /*
                               1
                              / \
                             /   \
                            /     \
                           /       \
                           2       3
                          / \       \
                         /   \       \
                         4   5       6
                                      \
                                      7

                        [[1], [2, 3], [4, 5, 6], [7]]
     */
//-------------------------------------------------------------------------//

    // version: 高频题班

    Map<Integer, List<Integer>> depth = new HashMap<>();

    int dfs3(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int d = Math.max(dfs3(cur.left), dfs3(cur.right)) + 1;

        depth.putIfAbsent(d, new ArrayList<>());
        depth.get(d).add(cur.val);
        return d;
    }

    public List<List<Integer>> findLeaves3(TreeNode root) {
        // Write your code here
        List<List<Integer>> ans = new ArrayList<>();

        int max_depth = dfs3(root);

        for (int i = 1; i <= max_depth; i++){
            ans.add(depth.get(i));
        }
        return ans;
    }

    @Test
    public void test(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.print();

        List<List<Integer>> result = findLeaves3(root);

        System.out.println(result);
    }
    /*
               1
              / \
             /   \
             2   3
            / \
            4 5

            [[4, 5, 3], [2], [1]]

     */



    /*  从上向下
    我改了上一个方法，首先dfs要多一个depth参数d，然后dfs改成preorder

     */
    Map<Integer, List<Integer>> depth4 = new HashMap<>();

    int dfs4(TreeNode cur, int d) {
        if (cur == null) {
            return 0;
        }
        depth4.putIfAbsent(d, new ArrayList<>());
        depth4.get(d).add(cur.val);

        d = Math.max(dfs4(cur.left, d + 1), dfs4(cur.right, d + 1)) + 1;


        return d;
    }

    public List<List<Integer>> findLeaves4(TreeNode root) {
        // Write your code here
        List<List<Integer>> ans = new ArrayList<>();

        int max_depth = dfs4(root, 1);

        for (int i = 1; i <= max_depth; i++) {
            ans.add(depth4.get(i));
        }
        return ans;
    }

    @Test
    public void test4(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.print();

        List<List<Integer>> result = findLeaves4(root);

        System.out.println(result);
    }
    /*
                           1
                          / \
                         /   \
                        /     \
                       /       \
                       2       3
                      / \       \
                     /   \       \
                     4   5       6
                                  \
                                  7

                    [[1], [2, 3], [4, 5, 6], [7]]
     */

//-------------------------------------------------------------------------//
    //我的solution
    public List<List<Integer>> findLeaves5(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max_level = dfs5(root, map);
        for (int i = 1; i <= max_level; i++){// i代表从下向上数的level
            result.add(map.get(i));
        }
        return result;
    }

    private int dfs5(TreeNode root, Map<Integer, List<Integer>> map){
        if (root == null){
            return 0;
        }
        int level = Math.max(dfs5(root.left, map), dfs5(root.right, map)) + 1;
        map.putIfAbsent(level, new ArrayList<>());
        map.get(level).add(root.val);
        return level;
    }
    @Test
    public void test5(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.print();

        List<List<Integer>> result = findLeaves5(root);

        System.out.println(result);
    }
    /*
                           1
                          / \
                         /   \
                        /     \
                       /       \
                       2       3
                      / \       \
                     /   \       \
                     4   5       6
                                  \
                                  7

                    [[4, 5, 7], [2, 6], [3], [1]]
     */
//-------------------------------------------------------------------------//
    /*
    用我的方法从上向下层级遍历
     */
    public List<List<Integer>> findLeaves6(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max_level = dfs6(root, map, 1);
        for (int i = 1; i <= max_level; i++){// i代表从下向上数的level
            result.add(map.get(i));
        }
        return result;
    }

    private int dfs6(TreeNode root, Map<Integer, List<Integer>> map, int level){
        if (root == null){
            return 0;
        }
        map.putIfAbsent(level, new ArrayList<>());
        map.get(level).add(root.val);
        level = Math.max(dfs6(root.left, map, level + 1),
                dfs6(root.right, map, level + 1)) + 1;

        return level;
    }

    @Test
    public void test6(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.print();

        List<List<Integer>> result = findLeaves6(root);

        System.out.println(result);
    }

    /*
                           1
                          / \
                         /   \
                        /     \
                       /       \
                       2       3
                      / \       \
                     /   \       \
                     4   5       6
                                  \
                                  7

                    [[1], [2, 3], [4, 5, 6], [7]]
     */
//-------------------------------------------------------------------------//

}
/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Have you met this question in a real interview? Yes
Example
Given binary tree:

          1
         / \
        2   3
       / \
      4   5
Returns [[4, 5, 3], [2], [1]].


 */