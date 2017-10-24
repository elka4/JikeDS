package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _671_Tree_Second_Minimum_Node_In_a_Binary_Tree_E {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Set<Integer> set = new TreeSet<>();
        dfs(root, set);
        Iterator<Integer> iterator = set.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            int result = iterator.next();
            if (count == 2) {
                return result;
            }
        }
        return -1;
    }

    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
        return;
    }

    //BFS
    public int findSecondMinimumValue2(TreeNode root)
    {
        int rootVal = root.val;
        int secondSmall =Integer.MAX_VALUE;
        boolean diffFound = false;
        Queue<TreeNode> Q= new LinkedList<TreeNode>();
        Q.add(root);

        while(!Q.isEmpty())
        {
            TreeNode curr=Q.poll();
            if(curr.val!=rootVal && curr.val < secondSmall)
            {
                secondSmall=curr.val;
                diffFound=true;
            }
            if(curr.left!=null)
            {
                Q.add(curr.left);
                Q.add(curr.right);
            }
        }

        return (secondSmall == Integer.MAX_VALUE && !diffFound) ? -1 : secondSmall;
    }
}
