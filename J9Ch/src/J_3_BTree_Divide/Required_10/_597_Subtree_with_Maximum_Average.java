package J_3_BTree_Divide.Required_10;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
/** 597. Subtree with Maximum Average
 * Easy
 *
 * http://lintcode.com/en/problem/subtree-with-maximum-average/
 *
 * http://www.jianshu.com/p/45b45e40ccf9
 思路：
 1.定义一个私有类ResultType中包含两个参数，一个为sum，一个为size
 2.定义全局变量subtree，代表的是全局最大的average节点的信息，定义全局变量subtreeResult，内部包含全局最大average节点的sum和size
 3.定义一个返回值为ResultType的helper函数
 4.将问题递归简化，同时定义类型为ResultType的result变量，用于代表当前节点信息.
 5.异常情况，root == null
 6.交换全局变量信息的条件

 错误：
 1.= 和 == 不仔细写错
 2.异常情况的返回值写错，写成了return 0，应仔细思考
 3.helper函数的返回类型写错，由ResultType写成int
 4.new resultType后面接的应该是（）而不是{},括号里面是逗号
 5.在定义resultType接口时，参数顺序写反了，导致后续调用出错


 * Created by tianhuizhu on 6/27/17.
 */
public class _597_Subtree_with_Maximum_Average {

    // version 1: Traverse + Divide Conquer
    private class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    private TreeNode subtree = null;
    private ResultType subtreeResult = null;

    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        helper(root);
        return subtree;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        root.print();
        ResultType result = new ResultType(
                left.sum + right.sum + root.val,
                left.size + right.size + 1
        );

        if (subtree == null ||
                subtreeResult.sum * result.size < result.sum * subtreeResult.size
                ) {
            notNull( result,  root);

            subtree = root;
            subtreeResult = result;
        }
        return result;
    }
    private void notNull(ResultType result, TreeNode root){
        if(subtree != null){
            System.out.println("subtreeResult.sum " + subtreeResult.sum);
            System.out.println("result.size " + result.size);
            System.out.println("result.sum  " + result.sum);
            System.out.println("subtreeResult.size " + subtreeResult.size);
            root.print();
        }
    }

    @Test
    public void test01(){
        int[] arr = {1,-5,11,1,2,4,-2};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        TreeNode result = findSubtree2(root);
        System.out.println("root: ");
        result.print();

    }
    /*
    root:
   1
  / \
 /   \
 -5   11
/ \ / \
1 2 4 -2

subtreeResult.sum 1
result.size 1
result.sum  2
subtreeResult.size 1
2

subtreeResult.sum 2
result.size 1
result.sum  4
subtreeResult.size 1
4

subtreeResult.sum 4
result.size 3
result.sum  13
subtreeResult.size 1
 11
/ \
4 -2

root:
 11
/ \
4 -2
     */
}
