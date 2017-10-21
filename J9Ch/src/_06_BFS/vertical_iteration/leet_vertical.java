package _06_BFS.vertical_iteration;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;

public class leet_vertical {

/*
The following solution takes 5ms.

BFS, put node, col into queue at the same time
Every left child access col - 1 while right child col + 1
This maps node into different col buckets
Get col boundary min and max on the fly
Retrieve result from cols
Note that TreeMap version takes 9ms.
 */

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);
        cols.add(0);

        int min = 0;
        int max = 0;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);

            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }

            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }

    @Test
    public void test04(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(verticalOrder(root));
    }


//////////////////////////////////////////////////////////////////

//3ms java solution beats 100%

    private int min = 0, max = 0;
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)    return list;
        computeRange(root, 0);
        for(int i = min; i <= max; i++) list.add(new ArrayList<>());
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> idx = new LinkedList<>();
        idx.add(-min);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            int i = idx.poll();
            list.get(i).add(node.val);
            if(node.left != null){
                q.add(node.left);
                idx.add(i - 1);
            }
            if(node.right != null){
                q.add(node.right);
                idx.add(i + 1);
            }
        }
        return list;
    }
    private void computeRange(TreeNode root, int idx){
        if(root == null)    return;
        min = Math.min(min, idx);
        max = Math.max(max, idx);
        computeRange(root.left, idx - 1);
        computeRange(root.right, idx + 1);
    }
//////////////////////////////////////////////////////////////////
public List<List<Integer>> verticalOrder3(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    //map's key is column, we assume the root column is zero, the left node will minus 1
    // ,and the right node will plus 1
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
    Queue<TreeNode> queue = new LinkedList<>();
    //use a HashMap to store the TreeNode and the according cloumn value
    HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();
    queue.offer(root);
    weight.put(root, 0);
    int min = 0;
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        int w = weight.get(node);
        if (!map.containsKey(w)) {
            map.put(w, new ArrayList<>());
        }
        map.get(w).add(node.val);
        if (node.left != null) {
            queue.add(node.left);
            weight.put(node.left, w - 1);
        }
        if (node.right != null) {
            queue.add(node.right);
            weight.put(node.right, w + 1);
        }
        //update min ,min means the minimum column value, which is the left most node
        min = Math.min(min, w);
    }
    while (map.containsKey(min)) {
        res.add(map.get(min++));
    }
    return res;
}

//////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////



}
/*

    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

 */
