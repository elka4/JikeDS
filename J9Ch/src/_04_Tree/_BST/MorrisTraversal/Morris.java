package _04_Tree._BST.MorrisTraversal;
import java.util.*;
import lib.*;

public class Morris {
//https://www.cnblogs.com/reboot329/p/6107818.html
/*
那天做了个SWAP NODE的题，要求constant space，不得不Morris Traversal。

稍微研究了一下，真正意义上的O(1)space对二叉树进行遍历。好像是1979年的算法。

第一次看着挺乱的，智商严重不足，不得不在纸上画出来，一目了然。。。建议大家自己动手画一下。

每个没有右节点的Node要建1个辅助path，回到他来自的那个左节点的parent。很绕口= =然后第二次来到这个点的时候，要去掉这个Path。所以每个点都遍历了2次，时间上还是O(n)..

和那个树状数组有点像，以每个左节点为中心。


 */

    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode temp = root;
        while (temp != null) {
            TreeNode morisTemp = temp;
            if (temp.left != null) {
                morisTemp = temp.left;

                while (morisTemp.right != null && morisTemp.right != temp) {
                    morisTemp = morisTemp.right;
                }

                // first time, need to add a path
                if (morisTemp.right == null) {
                    morisTemp.right = temp;
                    //res.add(temp.val);    //pre-Order
                    temp = temp.left;
                } else {    // second time, remove the path we added,
                    morisTemp.right = null;
                    //res.add(temp.val);    //in-Order
                    temp = temp.right;
                }

            } else {
                res.add(temp.val);
                temp = temp.right;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode n10 = new TreeNode(10);
        TreeNode n4 = new TreeNode(4);
        TreeNode n1 = new TreeNode(1);
        TreeNode n5 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n12 = new TreeNode(12);
        TreeNode n11 = new TreeNode(11);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);

        n10.left = n4;
        n10.right = n12;
        n4.left = n1;
        n4.right = n5;
        n5.left = n3;
        n5.right = n6;
        n12.left = n11;
        n12.right = n14;
        n14.left = n15;
        n10.print();
        for (int i : preOrder(n10)) {
            System.out.print(i + " ");
        }
        System.out.println();
        n10.print();
    }
    /*
       10
      / \
     /   \
    /     \
   /       \
   4       12
  / \     / \
 /   \   /   \
 1   5   11   14
    / \     /
    3 6     15

1 3 6 11 15
     */
}
