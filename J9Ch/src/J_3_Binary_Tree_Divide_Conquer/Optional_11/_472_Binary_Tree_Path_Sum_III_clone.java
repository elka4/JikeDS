package J_3_Binary_Tree_Divide_Conquer.Optional_11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _472_Binary_Tree_Path_Sum_III_clone {
    class ParentTreeNode {
      public int val;
      public ParentTreeNode parent, left, right;
  }

    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        dfs(root, target, results);
        return results;
    }

    public void dfs(ParentTreeNode root, int target, List<List<Integer>> results) {
        if (root == null)
            return;

        List<Integer> path = new ArrayList<Integer>();
        findSum(root, null, target, path, results);

        dfs(root.left, target, results);
        dfs(root.right, target, results);
    }

    public void findSum(ParentTreeNode root, ParentTreeNode father, int target,
                        List<Integer> path, List<List<Integer>> results) {
        path.add(root.val);
        target -= root.val;

        if (target == 0) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            Collections.addAll(tmp,  new  Integer[path.size()]);
            Collections.copy(tmp, path);
            results.add(tmp);
        }

        if (root.parent != null && root.parent != father)
            findSum(root.parent, root, target, path, results);

        if (root.left != null && root.left  != father)
            findSum(root.left, root, target, path, results);

        if (root.right != null && root.right != father)
            findSum(root.right, root, target, path, results);

        path.remove(path.size() - 1);
    }

    /*
        1
       / \
      2   3
     /
    4
    and target = 6. Return :

    [
      [2, 4],
      [2, 1, 3],
      [3, 1, 2],
      [4, 2]
    ]
     */
    @Test
    public void test01(){

    }
}
