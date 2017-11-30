package _04_Tree._PathSum;
import java.util.*;
import java.util.stream.*;
import lib.*;

//  666. Path Sum IV
//  https://leetcode.com/problems/path-sum-iv
//
public class _666_Path_Sum_IV_M {
/*    Java solution, Represent tree using HashMap
    How do we solve problem like this if we were given a normal tree? Yes, traverse it, keep a root to leaf running sum. If we see a leaf node (node.left == null && node.right == null), we add the running sum to the final result.

    Now each tree node is represented by a number. 1st digits is the level, 2nd is the position in that level (note that it starts from 1 instead of 0). 3rd digit is the value. We need to find a way to traverse this tree and get the sum.

    The idea is, we can form a tree using a HashMap. The key is first two digits which marks the position of a node in the tree. The value is value of that node. Thus, we can easily find a node's left and right children using math.
    Formula: For node xy? its left child is (x+1)(y*2-1)? and right child is (x+1)(y*2)?

    Given above HashMap and formula, we can traverse the tree. Problem is solved!*/
    class Solution {
        int sum = 0;
        Map<Integer, Integer> tree = new HashMap<>();

        public int pathSum(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            for (int num : nums) {
                int key = num / 10;
                int value = num % 10;
                tree.put(key, value);
            }

            traverse(nums[0] / 10, 0);

            return sum;
        }

        private void traverse(int root, int preSum) {
            int level = root / 10;
            int pos = root % 10;
            int left = (level + 1) * 10 + pos * 2 - 1;
            int right = (level + 1) * 10 + pos * 2;

            int curSum = preSum + tree.get(root);

            if (!tree.containsKey(left) && !tree.containsKey(right)) {
                sum += curSum;
                return;
            }

            if (tree.containsKey(left)) traverse(left, curSum);
            if (tree.containsKey(right)) traverse(right, curSum);
        }
    }



    /*
    [C++] [Java] Clean Code
         0
     0       1
  0   1     2   3
0 1  2 3   4 5  6 7
Regardless whether these nodes exist:

the position of left child is always parent_pos * 2;
the position of right child is alwaysparent_pos * 2 + 1;
the position of parent is always child_pos / 2;
     */
    class Solution2 {
        public int pathSum(int[] nums) {
            int[][] m = new int[5][8];
            for (int n : nums) {
                int i = n / 100; // i is 1 based index;
                int j = (n % 100) / 10 - 1; // j used 0 based index;
                int v = n % 10;
                m[i][j] = m[i - 1][j / 2] + v;
            }

            int sum = 0;
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i == 4 || m[i][j] != 0 && m[i + 1][j * 2] == 0 && m[i + 1][j * 2 + 1] == 0){
                        sum += m[i][j];
                    }
                }
            }
            return sum;
        }
    }


    /*
    Short Java 8 and dfs solution
The idea is to first represent the tree as a map, that contains the tree in the form of
Node position number --> Node value , where the Node number is the position of the node in a complete tree.

Then, we just do a dfs on the above tree to accumulate all the root to leaf path sums.

Lets take a tree example
                                          10
                                         /  \
                                       20     30
                                      / \     / \
                                    40  50  60   70
For the above tree,
the node positions are as follows,
root's position = 1,
left child position = parent_position * 2
right child position = parent_position * 2 + 1
Based on the above complete tree positions, the map generated for the above example would be
1 --> 10
2 --> 20
3 --> 30
4 --> 40
5 --> 50
6 --> 60
7 --> 70

To generate these positions from the given input,
if the input number is 314
we extract the digits to level = 3, positionInLevel = 1, value = 4
and the formula I arrived at to get the node's position in complete tree = [2 ^ (level-1)] + positionInLevel - 1

Now that we have the above map generated,
we do a simple dfs starting from the root, and keep accumulating the sum, once we reach a leaf, we add the accumulated sum to the result.
     */

    class Solution3{
        public int pathSum(int[] nums) {
            Map<Integer, Integer> positionToNodeMap = new HashMap<>();
            Arrays.stream(nums).forEach( num -> {
                int[] digits = IntStream.range(0, 3).map(i -> (num + "").charAt(i) - '0').toArray();
                positionToNodeMap.put((int)Math.pow(2, digits[0] - 1) - 1 + digits[1], digits[2]);
            });
            int[] res = new int[1];
            dfs(1, 0, res, positionToNodeMap);
            return res[0];
        }

        private void dfs(int cur, int sum, int[] res, Map<Integer, Integer> map) {
            if(!map.containsKey(cur)) return;
            int left = cur * 2, right = cur * 2 + 1, totalSum = sum + map.get(cur);
            if(!map.containsKey(left) && !map.containsKey(right)) { res[0] += totalSum; return; } // Leaf node
            dfs(left, totalSum, res, map);
            dfs(right, totalSum, res, map);
        }
    }



//    Java DFS, with explanation
//    Find the left child and right child based on the first two digits;
//
//[abc] 's left child = [(a+1)(b*2-1)(...)]
//
//            [abc]'s right child = [(a+1)(b*2)(...)]
//
//    dfs, when current node is a leaf, add the sum of path to totalSum

    class Solution4 {

        int totalSum;
        public int pathSum(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            totalSum = 0;
            dfs(nums, 0, 0);
            return totalSum;
        }

        private void dfs(int[] nums, int i, int sum){
            int n = nums[i];
            int v = n%10; //value
            int idx = (n/10)%10; //index
            int d = n/100; //depth
            sum += v;
            int left = (d+1)*10+idx*2-1; //left child's depth and index
            int right = (d+1)*10+idx*2; //right child's depth and index
            boolean foundLeft = false, foundRight = false;
            for(int j = i+1; j<nums.length; j++){
                int tmp = nums[j]/10;
                if (tmp > right) break;
                if (tmp == left) {
                    foundLeft = true;
                    dfs(nums, j, sum);
                }
                if (tmp == right) {
                    foundRight = true;
                    dfs(nums, j, sum);
                }
            }
            if (!foundLeft && !foundRight)
                totalSum+=sum; //if is leaf, add the sum of the path to total sum;
        }
    }

}
/*

If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation:
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: [113, 221]
Output: 4
Explanation:
The tree that the list represents is:
    3
     \
      1

The path sum is (3 + 1) = 4.


 */
