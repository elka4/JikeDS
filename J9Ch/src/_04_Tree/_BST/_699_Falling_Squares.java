package _04_Tree._BST;
import lib.*;
import java.util.*;

//  699. Falling Squares
//  https://leetcode.com/problems/falling-squares/description/
//
public class _699_Falling_Squares {
    //https://leetcode.com/articles/falling-squares/
    /*
    Set<Integer> coords = new HashSet();
    for (int[] pos: positions) {
        coords.add(pos[0]);
        coords.add(pos[0] + pos[1] - 1);
    }
    List<Integer> sortedCoords = new ArrayList(coords);
    Collections.sort(sortedCoords);

    Map<Integer, Integer> index = new HashMap();
    int t = 0;
    for (int coord: sortedCoords) index.put(coord, t++);
     */


    //Approach #1: Offline Propagation [Accepted]
    public List<Integer> fallingSquares1(int[][] positions) {
        int[] qans = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int left = positions[i][0];
            int size = positions[i][1];
            int right = left + size;
            qans[i] += size;

            for (int j = i+1; j < positions.length; j++) {
                int left2 = positions[j][0];
                int size2 = positions[j][1];
                int right2 = left2 + size2;
                if (left2 < right && left < right2) { //intersect
                    qans[j] = Math.max(qans[j], qans[i]);
                }
            }
        }

        List<Integer> ans = new ArrayList();
        int cur = -1;
        for (int x: qans) {
            cur = Math.max(cur, x);
            ans.add(cur);
        }
        return ans;
    }


//-------------------------------------------------------------------------///
    //Approach #2: Brute Force with Coordinate Compression [Accepted]
        Map<Integer, Integer> index = new HashMap();
        int t = 0;


        int[] heights;

        public int query(int L, int R) {
            int ans = 0;
            for (int i = L; i <= R; i++) {
                ans = Math.max(ans, heights[i]);
            }
            return ans;
        }

        public void update(int L, int R, int h) {
            for (int i = L; i <= R; i++) {
                heights[i] = Math.max(heights[i], h);
            }
        }

        public List<Integer> fallingSquares2(int[][] positions) {
            //Coordinate Compression
            //HashMap<Integer, Integer> index = ...;
            //int t = ...;

            heights = new int[t];
            int best = 0;
            List<Integer> ans = new ArrayList();

            for (int[] pos: positions) {
                int L = index.get(pos[0]);
                int R = index.get(pos[0] + pos[1] - 1);
                int h = query(L, R) + pos[1];
                update(L, R, h);
                best = Math.max(best, h);
                ans.add(best);
            }
            return ans;
        }

//-------------------------------------------------------------------------///
    //Approach #3: Block (Square Root) Decomposition [Accepted]
    class Solution3 {
        Map<Integer, Integer> index = new HashMap();
        int t = 0;

        int[] heights;
        int[] blocks;
        int[] blocks_read;
        int B;

        public int query(int left, int right) {
            int ans = 0;
            while (left % B > 0 && left <= right) {
                ans = Math.max(ans, heights[left]);
                ans = Math.max(ans, blocks[left / B]);
                left++;
            }
            while (right % B != B - 1 && left <= right) {
                ans = Math.max(ans, heights[right]);
                ans = Math.max(ans, blocks[right / B]);
                right--;
            }
            while (left <= right) {
                ans = Math.max(ans, blocks[left / B]);
                ans = Math.max(ans, blocks_read[left / B]);
                left += B;
            }
            return ans;
        }

        public void update(int left, int right, int h) {
            while (left % B > 0 && left <= right) {
                heights[left] = Math.max(heights[left], h);
                blocks_read[left / B] = Math.max(blocks_read[left / B], h);
                left++;
            }
            while (right % B != B - 1 && left <= right) {
                heights[right] = Math.max(heights[right], h);
                blocks_read[right / B] = Math.max(blocks_read[right / B], h);
                right--;
            }
            while (left <= right) {
                blocks[left / B] = Math.max(blocks[left / B], h);
                left += B;
            }
        }

        public List<Integer> fallingSquares(int[][] positions) {
            //Coordinate Compression
            //HashMap<Integer, Integer> index = ...;
            //int t = ...;

            heights = new int[t];
            B = (int) Math.sqrt(t);
            blocks = new int[B+2];
            blocks_read = new int[B+2];

            int best = 0;
            List<Integer> ans = new ArrayList();

            for (int[] pos: positions) {
                int L = index.get(pos[0]);
                int R = index.get(pos[0] + pos[1] - 1);
                int h = query(L, R) + pos[1];
                update(L, R, h);
                best = Math.max(best, h);
                ans.add(best);
            }
            return ans;
        }
    }

//-------------------------------------------------------------------------///

    //Approach #4: Segment Tree with Lazy Propagation [Accepted]
    class Solution4 {
        Set<Integer> coords = new HashSet();

        List<Integer> sortedCoords = new ArrayList(coords);
        //Collections.sort(sortedCoords);

        Map<Integer, Integer> index = new HashMap();

        public List<Integer> fallingSquares(int[][] positions) {
            //Coordinate Compression
            //HashMap<Integer, Integer> index = ...;

            SegmentTree tree = new SegmentTree(sortedCoords.size());
            int best = 0;
            List<Integer> ans = new ArrayList();

            for (int[] pos: positions) {
                int L = index.get(pos[0]);
                int R = index.get(pos[0] + pos[1] - 1);
                int h = tree.query(L, R) + pos[1];
                tree.update(L, R, h);
                best = Math.max(best, h);
                ans.add(best);
            }
            return ans;
        }
    }

    class SegmentTree {
        int N, H;
        int[] tree, lazy;

        SegmentTree(int N) {
            this.N = N;
            H = 1;
            while ((1 << H) < N) H++;
            tree = new int[2 * N];
            lazy = new int[N];
        }

        private void apply(int x, int val) {
            tree[x] = Math.max(tree[x], val);
            if (x < N) lazy[x] = Math.max(lazy[x], val);
        }

        private void pull(int x) {
            while (x > 1) {
                x >>= 1;
                tree[x] = Math.max(tree[x * 2], tree[x * 2 + 1]);
                tree[x] = Math.max(tree[x], lazy[x]);
            }
        }

        private void push(int x) {
            for (int h = H; h > 0; h--) {
                int y = x >> h;
                if (lazy[y] > 0) {
                    apply(y * 2, lazy[y]);
                    apply(y * 2 + 1, lazy[y]);
                    lazy[y] = 0;
                }
            }
        }

        public void update(int L, int R, int h) {
            L += N; R += N;
            int L0 = L, R0 = R, ans = 0;
            while (L <= R) {
                if ((L & 1) == 1) apply(L++, h);
                if ((R & 1) == 0) apply(R--, h);
                L >>= 1; R >>= 1;
            }
            pull(L0); pull(R0);
        }

        public int query(int L, int R) {
            L += N; R += N;
            int ans = 0;
            push(L); push(R);
            while (L <= R) {
                if ((L & 1) == 1) ans = Math.max(ans, tree[L++]);
                if ((R & 1) == 0) ans = Math.max(ans, tree[R--]);
                L >>= 1; R >>= 1;
            }
            return ans;
        }
    }

//-------------------------------------------------------------------------///
}
/*
On an infinite number line (x-axis), we drop given squares in the order they are given.

The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

Example 1:
Input: [[1, 2], [2, 3], [6, 1]]
Output: [2, 5, 5]
Explanation:

After the first drop of
positions[0] = [1, 2]:
_aa
_aa
-------
The maximum height of any square is 2.


After the second drop of
positions[1] = [2, 3]:
__aaa
__aaa
__aaa
_aa__
_aa__
--------------
The maximum height of any square is 5.
The larger square stays on top of the smaller square despite where its center
of gravity is, because squares are infinitely sticky on their bottom edge.


After the third drop of
positions[1] = [6, 1]:
__aaa
__aaa
__aaa
_aa
_aa___a
--------------
The maximum height of any square is still 5.

Thus, we return an answer of
[2, 5, 5]
.


Example 2:
Input: [[100, 100], [200, 100]]
Output: [100, 100]
Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
Note:

1 <= positions.length <= 1000.
1 <= positions[i][0] <= 10^8.
1 <= positions[i][1] <= 10^6.
 */