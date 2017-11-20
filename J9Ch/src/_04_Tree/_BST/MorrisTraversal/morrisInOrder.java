package _04_Tree._BST.MorrisTraversal;
import lib.*;
import org.junit.Test;


//http://blog.csdn.net/guoyuguang0/article/details/51015944
/*
4、morris遍历是一种很神奇的遍历，上述的三种方法和递归遍历方法都需要O(n)的时间，O(n)的空间，而morris遍历则只需要O(n)的时间，O(1)的空间。下面以morris中序遍历来说明。
算法伪代码如下：
MorrisInOrder()：
while 没有结束
    如果当前节点没有左孩子
        访问该节点
        转向该节点的右孩子
    否则
        找到左孩子的最右节点。
        如果最右节点的右孩子不为空则说明最右节点已和当前节点连接，访问当前节点，并把最右节点的只有孩子置为空，转向当前节点的右孩子。
        如果最右节点的右孩子为空，则使最右节点的右孩子指向当前节点，转向当前节点的左孩子。
 */
public class morrisInOrder {
    public static void morrisInOrder(TreeNode root){
        TreeNode cur=root;
        while(cur!=null){
            if(cur.left==null){
                System.out.println(cur.val);
                cur=cur.right;
            }else{
                TreeNode temp=cur.left;
                while(temp.right!=null&&temp.right!=cur){
                    temp=temp.right;
                }
                if(temp.right==cur){
                    System.out.println(cur.val);
                    temp.right=null;
                    cur=cur.right;
                }else{
                    temp.right=cur;
                    cur=cur.left;
                }
            }
        }
    }


    @Test
    public void test02() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        morrisInOrder(root);
        root.print();
    }

    /*
    root:
   7
  / \
 /   \
 9   5
/ \
4 6

4
9
6
7
5
   7
  / \
 /   \
 9   5
/ \
4 6

     */
}
