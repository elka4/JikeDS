package HF.HF4_Search_BFS;

import java.util.*;
import lib.*;
import org.junit.Test;

//Binary Tree Level Order Traversal
public class _7BinaryTreeLevelOrderTraversal {
    // version 1: BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List result = new ArrayList();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
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

    @Test
    public void test(){
        int[] arr = {1,2,3,4,5,6,7};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.print();
        System.out.println(levelOrder(root));
    }

//-------------------------------------------------------------------------////////////


    // version 2:  DFS
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();

        if (root == null) {
            return results;
        }

        int maxLevel = 0;
        while (true) {
            List<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, maxLevel);
            if (level.size() == 0) {
                break;
            }

            results.add(level);
            maxLevel++;
        }

        return results;
    }

    private void dfs(TreeNode root,
                     List<Integer> level,
                     int curtLevel,
                     int maxLevel) {
        if (root == null || curtLevel > maxLevel) {
            return;
        }

        if (curtLevel == maxLevel) {
            level.add(root.val);
            return;
        }

        dfs(root.left, level, curtLevel + 1, maxLevel);
        dfs(root.right, level, curtLevel + 1, maxLevel);
    }


//-------------------------------------------------------------------------////////////

    // version 3: BFS. two queues
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        List<TreeNode> Q1 = new ArrayList<TreeNode>();
        List<TreeNode> Q2 = new ArrayList<TreeNode>();

        Q1.add(root);
        while (Q1.size() != 0) {
            List<Integer> level = new ArrayList<Integer>();
            Q2.clear();
            for (int i = 0; i < Q1.size(); i++) {
                TreeNode node = Q1.get(i);
                level.add(node.val);
                if (node.left != null) {
                    Q2.add(node.left);
                }
                if (node.right != null) {
                    Q2.add(node.right);
                }
            }

            // swap q1 and q2
            List<TreeNode> temp = Q1;
            Q1 = Q2;
            Q2 = temp;

            // add to result
            result.add(level);
        }

        return result;
    }



//-------------------------------------------------------------------------////////////

    // version 4: BFS, queue with dummy node
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);
        Q.offer(null); // dummy node

        List<Integer> level = new ArrayList<Integer>();
        while (!Q.isEmpty()) {
            TreeNode node = Q.poll();
            if (node == null) {
                if (level.size() == 0) {
                    break;
                }
                result.add(level);
                level = new ArrayList<Integer>();
                Q.offer(null); // add a new dummy node
                continue;
            }

            level.add(node.val);
            if (node.left != null) {
                Q.offer(node.left);
            }
            if (node.right != null) {
                Q.offer(node.right);
            }
        }

        return result;
    }

//-------------------------------------------------------------------------////////////

}
