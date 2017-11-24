package _04_Tree._Traverse;
import lib.TreeNode;
import java.util.*;

//  655. Print Binary Tree
//  https://leetcode.com/problems/print-binary-tree/description/
public class _655_Tree_Print_Binary_Tree_M {

    //https://leetcode.com/problems/print-binary-tree/solution/

    //Approach #1 Recursive Solution[Accepted]
    public class Solution01 {
        public List<List<String>> printTree(TreeNode root) {
            int height = getHeight(root);
            String[][] res = new String[height][(1 << height) - 1];
            for(String[] arr:res)
                Arrays.fill(arr,"");
            List<List<String>> ans = new ArrayList<>();
            fill(res, root, 0, 0, res[0].length);
            for(String[] arr:res)
                ans.add(Arrays.asList(arr));
            return ans;
        }
        public void fill(String[][] res, TreeNode root, int i, int l, int r) {
            if (root == null)
                return;
            res[i][(l + r) / 2] = "" + root.val;
            fill(res, root.left, i + 1, l, (l + r) / 2);
            fill(res, root.right, i + 1, (l + r + 1) / 2, r);
        }
        public int getHeight(TreeNode root) {
            if (root == null)
                return 0;
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }

    //Approach #2 Using queue(BFS)[Accepted]
    public class Solution02 {
        class Params {
            Params(TreeNode n, int ii, int ll, int rr) {
                root = n;
                i = ii;
                l = ll;
                r = rr;
            }
            TreeNode root;
            int i, l, r;
        }
        public List < List < String >> printTree(TreeNode root) {
            int height = getHeight(root);
            System.out.println(height);
            String[][] res = new String[height][(1 << height) - 1];
            for (String[] arr: res)
                Arrays.fill(arr, "");
            List < List < String >> ans = new ArrayList < > ();
            fill(res, root, 0, 0, res[0].length);
            for (String[] arr: res)
                ans.add(Arrays.asList(arr));
            return ans;
        }
        public void fill(String[][] res, TreeNode root, int i, int l, int r) {
            Queue < Params > queue = new LinkedList();
            queue.add(new Params(root, 0, 0, res[0].length));
            while (!queue.isEmpty()) {
                Params p = queue.remove();
                res[p.i][(p.l + p.r) / 2] = "" + p.root.val;
                if (p.root.left != null)
                    queue.add(new Params(p.root.left, p.i + 1, p.l, (p.l + p.r) / 2));
                if (p.root.right != null)
                    queue.add(new Params(p.root.right, p.i + 1, (p.l + p.r + 1) / 2, p.r));
            }
        }
        public int getHeight(TreeNode root) {
            Queue < TreeNode > queue = new LinkedList();
            queue.add(root);
            int height = 0;
            while (!queue.isEmpty()) {
                height++;
                Queue < TreeNode > temp = new LinkedList();
                while (!queue.isEmpty()) {
                    TreeNode node = queue.remove();
                    if (node.left != null)
                        temp.add(node.left);
                    if (node.right != null)
                        temp.add(node.right);
                }
                queue = temp;
            }
            return height;
        }
    }

//------------------------------------------------------------------------------//////////
    //Java Recursive Solution
    class Solution3{
        public List<List<String>> printTree(TreeNode root) {
            List<List<String>> res = new LinkedList<>();
            int height = root == null ? 1 : getHeight(root);
            int rows = height, columns = (int) (Math.pow(2, height) - 1);
            List<String> row = new ArrayList<>();
            for(int i = 0; i < columns; i++)  row.add("");
            for(int i = 0; i < rows; i++)  res.add(new ArrayList<>(row));
            populateRes(root, res, 0, rows, 0, columns - 1);
            return res;
        }

        public void populateRes(TreeNode root, List<List<String>> res, int row,
                                int totalRows, int i, int j) {
            if (row == totalRows || root == null) return;
            res.get(row).set((i+j)/2, Integer.toString(root.val));
            populateRes(root.left, res, row+1, totalRows, i, (i+j)/2 - 1);
            populateRes(root.right, res, row+1, totalRows, (i+j)/2+1, j);
        }

        public int getHeight(TreeNode root) {
            if (root == null) return 0;
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }
//-------------------------------------------------------------------------//////

    //Java Iterative Level Order Traversal with Queue
    /*
    Java Iterative Level Order Traversal with Queue
It could be fairly easy when we made our first observation on the problem. For the output matrix, the number of rows is height of the tree. What about the number of columns?

row = 1 => col = 1 = 2^1 - 1
row = 2 => col = 3 = 2^2 - 1
row = 3 => col = 7 = 2^3 - 1
row = 4 => col = 15 = 2^4 - 1
...
row = m => col = 2^m - 1
This can be derived from the number of leaves of a full tree (i.e 2^(height - 1)) with spaces joined (i.e 2^(height - 1) - 1).

Then we can fill the node in level by level. Another observation is we always print a node at the center of its subtree index range. What I mean is for the left or right child of a node, the subtree rooted at the child will use half of the indices of the node.

root is at the center of left and right, say mid
root.left (if not null) is at the center of left and mid - 1
root.right (if not null) is at the center of mid + 1 and right
Then we can easily have our solution as we always keep track of the left and right of the node.
     */
    public class Solution4 {
        public List<List<String>> printTree(TreeNode root) {
            List<List<String>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            int rows = getHeight(root);
            int cols = (int)Math.pow(2, rows) - 1;
            for (int i = 0; i < rows; i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    row.add("");
                }
                res.add(row);
            }

            Queue<TreeNode> queue = new LinkedList<>();
            Queue<int[]> indexQ = new LinkedList<>();
            queue.offer(root);
            indexQ.offer(new int[] { 0, cols - 1 });
            int row = -1;
            while (!queue.isEmpty()) {
                row++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    int[] indices = indexQ.poll();

                    if (cur == null) {
                        continue;
                    }

                    int left = indices[0];
                    int right = indices[1];
                    int mid = left + (right - left) / 2;
                    res.get(row).set(mid, String.valueOf(cur.val));

                    queue.offer(cur.left);
                    queue.offer(cur.right);
                    indexQ.offer(new int[] { left, mid - 1 });
                    indexQ.offer(new int[] { mid + 1, right });
                }
            }

            return res;
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }
    }

//------------------------------------------------------------------------------//////////
/*Java solution, two times traversal
    First traversal measures height and width of final output. Second traversal record where should we put each node by using a HashMap.*/

    public class Solution5 {
        int height = 0, width = 0;
        Map<String, String> map = new HashMap<>();

        public List<List<String>> printTree(TreeNode root) {
            List<List<String>> res = new ArrayList<List<String>>();
            if (root == null) return res;

            measure(root, 0);
            mark(root, 0, 0, width - 1);

            for (int i = 0; i < height; i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < width; j++) {
                    if (map.containsKey(i + "," + j)) {
                        row.add(map.get(i + "," + j));
                    }
                    else {
                        row.add("");
                    }
                }
                res.add(row);
            }

            return res;
        }

        private int measure(TreeNode root, int h) {
            if (root == null) return 0;

            height = Math.max(height, h + 1);

            int w = Math.max(measure(root.left, h + 1), measure(root.right, h + 1)) * 2 + 1;
            width = Math.max(width, w);

            return w;
        }

        private void mark(TreeNode root, int y, int l, int r) {
            if (root == null) return;

            int x = (r + l) / 2;
            map.put(y + "," + x, root.val + "");

            mark(root.left, y + 1, l, x - 1);
            mark(root.right, y + 1, x + 1, r);
        }
    }
//------------------------------------------------------------------------------//////////
}
/*

Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   /
  3
 /
4
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
Note: The height of binary tree is in the range of [1, 10].

 */
