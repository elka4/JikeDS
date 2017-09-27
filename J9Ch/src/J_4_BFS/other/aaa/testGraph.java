package J_4_BFS.other.aaa;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by tianhuizhu on 7/9/17.
 */
public class testGraph {

    /*
  1
 / \
/   \
0 --- 2
 / \
 \_/
 */
    @Test
    public void test01(){
        TreeNode node0 = new TreeNode("node0", null );
        TreeNode node1 = new TreeNode("node1", null );
        TreeNode node2 = new TreeNode("node2", null );
        node0.children = new ArrayList<>();
        node1.children = new ArrayList<>();
        node2.children = new ArrayList<>();

        node0.children.add(node1);
        node1.children.add(node2);
       // node1.children.add(node0);
        //node2.children.add(node0);



        //node1.children.add(node1);
        node0.print();
    }
}
