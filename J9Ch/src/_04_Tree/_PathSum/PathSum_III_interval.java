package _04_Tree._PathSum;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;

// leetcode 437. Path Sum III
//  The path does not need to start or end at the root or a leaf,
// but it must go downwards (traveling only from parent nodes to child nodes).


public class PathSum_III_interval {
/*17 ms O(n) java Prefix sum method

 Runtime: 18 ms  Your runtime beats 82.92 % of java submissions.

    So the idea is similar as Two sum, using HashMap to store (

    key : the prefix sum,
    value : how many ways get to this prefix sum

    ) , and whenever reach a node, we check if prefix sum - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
    For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want to find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.

    I used global variable count, but obviously we can avoid global variable by passing the count from bottom up. The time complexity is O(n). This is my first post in discuss, open to any improvement or criticism. :)*/

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        helper(root, 0, sum, preSum);
        return count;
    }

    int count = 0;

    public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }

        currSum += root.val;

        if (preSum.containsKey(currSum - target)) {
            count += preSum.get(currSum - target);
        }

        //经典dfs的包围。用hashmap记录状态
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1); // 死死记住这个做法


        helper(root.left, currSum, target, preSum);
        helper(root.right, currSum, target, preSum);

        preSum.put(currSum, preSum.get(currSum) - 1);
    }

/*        if (!preSum.containsKey(currSum)) {
            preSum.put(currSum, 1);
        } else {
            preSum.put(currSum, preSum.get(currSum) + 1);
        }*/

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 11));
    }
/*
            root:
               1
              / \
             /   \
             3   5
            / \ / \
            6 7 8 5

            2
 */

    @Test
    public void test02(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 10));

    }

//-------------------------------------------------------------------------------

//    Thanks for your advice, @StefanPochmann . Here is the modified version, concise and shorter:
    /*
    Runtime: 20 ms   Your runtime beats 75.90 % of java submissions.

这个做法比上面更好看一些，因为不用全局变量，而是利用method的返回值来记录到当前node的，也没有浪费使用这个参数
     */
    class Solution{
        public int pathSum(TreeNode root, int sum) {
            HashMap<Integer, Integer> preSum = new HashMap();
            preSum.put(0,1);
            return helper(root, 0, sum, preSum);
        }

        public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
            if (root == null) {
                return 0;
            }

            currSum += root.val;

            int res = preSum.getOrDefault(currSum - target, 0);

            preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);  //头

            res += helper(root.left, currSum, target, preSum) +
                    helper(root.right, currSum, target, preSum);

            preSum.put(currSum, preSum.get(currSum) - 1);                          //尾

            return res;
        }
    }

//-------------------------------------------------------------------------------

/*    Simple Java DFS 和Solution2一样
Runtime: 31 ms           Your runtime beats 34.90 % of java submissions.
    Typical recursive DFS.
            Space: O(n) due to recursion.
    Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).*/

    public class Solution1 {
        public int pathSum(TreeNode root, int sum) {
            if (root == null) return 0;
            return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        private int pathSumFrom(TreeNode node, int sum) {
            if (node == null) return 0;
            return (node.val == sum ? 1 : 0)
                    + pathSumFrom(node.left, sum - node.val)
                    + pathSumFrom(node.right, sum - node.val);
        }
    }



//-------------------------------------------------------------------------------

/*    Simple AC Java Solution DFS  和Solution1一样
Runtime: 29 ms       Your runtime beats 59.80 % of java submissions.
    Each time find all the path start from current node
    Then move start node to the child and repeat.
    Time Complexity should be O(N^2) for the worst case and O(NlogN) for balanced binary Tree.*/

    public class Solution2 {
        public int pathSum(TreeNode root, int sum) {
            if(root == null)
                return 0;
            return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        public int findPath(TreeNode root, int sum){
            int res = 0;
            if(root == null)
                return res;

            if(sum == root.val)
                res++;

            res += findPath(root.left, sum - root.val);
            res += findPath(root.right, sum - root.val);
            return res;
        }
    }



/*    A better solution is suggested in 17ms O(n) java prefix sum by tankztc.

Runtime: 16 ms Your runtime beats 97.07 % of java submissions.
It use a hash map to store all the prefix sum and each time check if the any
subarray sum to the target, add with some comments:*/
    class Solution3{
        public int pathSum(TreeNode root, int sum) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);  //Default sum = 0 has one count
            return backtrack(root, 0, sum, map);
        }
        //BackTrack one pass
        public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
            if(root == null)
                return 0;

            sum += root.val;

            //See if there is a subarray sum equals to target
            int res = map.getOrDefault(sum - target, 0);

            map.put(sum, map.getOrDefault(sum, 0)+1);

            //Extend to left and right child
            res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);

            map.put(sum, map.get(sum)-1);   //Remove the current node so it wont affect other path

            return res;
        }

    }

    //Easy to understand Java solution with comment.  性能极差


    /*  Runtime: 97 ms   Your runtime beats 4.33 % of java submissions.
    for each parent node in the tree, we have 2 choices:
    1. include it in the path to reach sum.
    2. not include it in the path to reach sum.

    for each child node in the tree, we have 2 choices:
    1. take what your parent left you.
    2. start from yourself to form the path.

    one little thing to be careful:
    every node in the tree can only try to be the start point once.

    for example, When we try to start with node 1, node 3, as a child, could choose to start by itself.
                 Later when we try to start with 2, node 3, still as a child,
                 could choose to start by itself again, but we don't want to add the count to result again.
         1
          \
           2
            \
             3

    */
    public class Solution4 {
        int target;
        Set<TreeNode> visited;

        public int pathSum(TreeNode root, int sum) {
            target = sum;

            // to store the nodes that have already tried to start path by themselves.
            visited = new HashSet<TreeNode>();

            return pathSumHelper(root, sum, false);
        }

        public int pathSumHelper(TreeNode root, int sum, boolean hasParent) {
            if(root == null) return 0;

            //the hasParent flag is used to handle the case when parent path sum is 0.
            //in this case we still want to explore the current node.
            if(sum == target && visited.contains(root) && !hasParent) return 0;
            if(sum == target && !hasParent) visited.add(root);

            int count = (root.val == sum)?1:0;
            count += pathSumHelper(root.left, sum - root.val, true);
            count += pathSumHelper(root.right, sum - root.val, true);
            count += pathSumHelper(root.left, target , false);
            count += pathSumHelper(root.right, target, false);

            return count;
        }
    }


    //Java: Never Start or Stick to the End
    //
    // Your runtime beats 14.15 % of java submissions.
/*    Either the path has not started, or it has to go all the way to the end.
    Isn't it like the career we chose? : )

    Time Complexity: O(n log n): each node is visited by its ancestors.
    Space Complexity: O(1): we don't store nodes.*/
    class Solution5{
        public int pathSum(TreeNode root, int sum) {

            return helper(root, sum, false);
        }


        // Either the path has not started, or it has to go all the way to the end.
        private int helper(TreeNode root, int sum, boolean hasStarted) {
            if (root == null) return 0;

            // if the path has not started, we start now or not.
            if (!hasStarted) {
                return helper(root, sum, true) +
                        helper(root.left, sum, false) +
                        helper(root.right, sum, false);
            }

            // if the path has started
            sum -= root.val;

            return helper(root.left, sum, true) +
                    helper(root.right, sum, true) + (sum == 0? 1 : 0);
        }
    }

    //Neat Java Solution With Comment
    // Your runtime beats 34.90 % of java submissions.
    public class Solution6 {
        public int pathSum(TreeNode root, int sum) {
            int count = findPathWithRoot(root, sum);
            //add case when root is excluded.
            if (root != null) count += pathSum(root.left, sum) + pathSum(root.right, sum);
            return count;
        }

        //Must contain root.val when calculating sum.
        private int findPathWithRoot(TreeNode root, int sum) {
            if (root == null) return 0;
            int count = 0;
            if (root.val == sum) count = 1;

            return count + findPathWithRoot(root.left, sum - root.val) +
                    findPathWithRoot(root.right, sum - root.val);
        }
    }



    //JAVA solution by using DFS two times
    // Your runtime beats 66.78 % of java submissions.
    public class Solution7 {
        int count = 0;
        public int pathSum(TreeNode root, int sum) {
            if (root != null){
                fun(root,sum);
                pathSum(root.left,sum);
                pathSum(root.right,sum);
            }
            return count;
        }
        public void fun(TreeNode root,int sum){
            if (root != null){
                if (sum - root.val == 0){
                    count++;
                }
                fun(root.left,sum - root.val);
                fun(root.right,sum - root.val);
            }
        }
    }

//-------------------------------------------------------------------------------
    // 9Ch
    //   Your runtime beats 0.23 % of java submissions.
    class Jiuzhang {
        public int pathSum(TreeNode root, int sum) {
            if (root == null) {
                return 0;
            }

            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            int count = 0;

            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
                count += helper(cur, sum);
            }

            return count;
        }

        private int helper(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            dfsHelper(res, list, root, sum - root.val);

            return res.size();
        }

        private void dfsHelper(List<List<Integer>> res,
                               List<Integer> path,
                               TreeNode root,
                               int sum) {
            if (root == null) {
                return;
            }

            if (sum == 0) {
                res.add(new ArrayList<>(path));
                // return;
            }

            if (root.left != null) {
                path.add(root.left.val);
                dfsHelper(res, path, root.left, sum - root.left.val);
                path.remove(path.size() - 1);
            }

            if (root.right != null) {
                path.add(root.right.val);
                dfsHelper(res, path, root.right, sum - root.right.val);
                path.remove(path.size() - 1);
            }

        }
    }
}
/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 */