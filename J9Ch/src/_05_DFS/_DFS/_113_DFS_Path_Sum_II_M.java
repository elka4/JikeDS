package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _113_DFS_Path_Sum_II_M {
//DFS with one LinkedList , accepted java solution
public List<List<Integer>> pathSum(TreeNode root, int sum){
    List<List<Integer>> result  = new LinkedList<List<Integer>>();
    List<Integer> currentResult  = new LinkedList<Integer>();
    pathSum(root,sum,currentResult,result);
    return result;
}

    public void pathSum(TreeNode root, int sum, List<Integer> currentResult,
                        List<List<Integer>> result) {

        if (root == null)
            return;
        currentResult.add(new Integer(root.val));
        if (root.left == null && root.right == null && sum == root.val) {
            result.add(new LinkedList(currentResult));
            currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
            return;
        } else {
            pathSum(root.left, sum - root.val, currentResult, result);
            pathSum(root.right, sum - root.val, currentResult, result);
        }
        currentResult.remove(currentResult.size() - 1);
    }






//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */