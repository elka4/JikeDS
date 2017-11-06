package _04_Tree._PathSum;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//
//
//
public class _257_Tree_Binary_Tree_Paths_E {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }
    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }


    public List<String> binaryTreePaths2(TreeNode root) {

        List<String> paths = new LinkedList<>();

        if(root == null) return paths;

        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }

        for (String path : binaryTreePaths(root.left)) {
            paths.add(root.val + "->" + path);
        }

        for (String path : binaryTreePaths(root.right)) {
            paths.add(root.val + "->" + path);
        }

        return paths;

    }
}
/*

 */
/*

 */
