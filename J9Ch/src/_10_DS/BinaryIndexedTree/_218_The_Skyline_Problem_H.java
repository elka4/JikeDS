package _10_DS.BinaryIndexedTree;
import java.util.*;

//  218. The Skyline Problem
//  https://leetcode.com/problems/the-skyline-problem/description/
//  Divide and Conquer Heap Binary Indexed Tree Segment Tree
public class _218_The_Skyline_Problem_H {
    //https://briangordon.github.io/2014/08/the-skyline-problem.html

////////////////////////////////////////////////////////////////////////////////////
    //My O(nlogn) solution using Binary Indexed Tree(BIT)/Fenwick Tree
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<>();
        if (buildings.length==0) return ret;

        List<int[]> points = new ArrayList<>();

        for (int i=0;i<buildings.length;i++) {
            int[] b = buildings[i];
            points.add(new int[]{b[0], 1, i});
            points.add(new int[]{b[1], 2, i});
        }

        Collections.sort(points, (a,b)->a[0]==b[0]?a[1]-b[1]: a[0]-b[0]);
        // binary indexed tree
        // stores the max height for each segment, bit[i] is the max height of segment between point i-1 and i
        int[] bit = new int[points.size()+1];

        Map<Integer, Integer> index = new HashMap<>();
        for (int i=0;i<points.size();i++) {
            index.putIfAbsent(points.get(i)[0], i);
        }

        int prevHeight = -1;

        for (int i=0;i<points.size();i++) {
            int[] pt = points.get(i);
            if (pt[1]==1) {
                // start of a building
                // put height in scope, scope ends when building end
                int[] building=buildings[pt[2]];
                add(bit, index.get(building[1]), building[2]);
            }
            int cur = find(bit, index.get(pt[0])+1);
            if (cur!=prevHeight) {
                if (ret.size()>0 && ret.get(ret.size()-1)[0]==pt[0]) {
                    ret.get(ret.size()-1)[1] = Math.max(cur, ret.get(ret.size()-1)[1]);
                } else {
                    ret.add(new int[]{pt[0], cur});
                }
                prevHeight=cur;
            }
        }

        return ret;
    }

    void add(int[] bit, int i, int h) {
        while (i>0) {
            bit[i]=Math.max(bit[i], h);
            i-=(i&-i);
        }
    }
    int find(int[] bit, int i) {
        int h=0;
        while (i<bit.length) {
            h=Math.max(h, bit[i]);
            i+=(i&-i);
        }
        return h;
    }
////////////////////////////////////////////////////////////////////////////////////
//Java Binary Indexed Tree Solution


/*    There is one point critical here :
    when sort the [x, i/o, idx] array, for entries with same 'x' value, put 'in' before 'out'

    My understand of the BIT solution:

    Sort [x, i/o, idx] from left to right, and deal them one by one
    i. If this is an 'in'(left) bound, use the related right to set hight for left points according to BIT; Then find the highest after 'in'.
    ii. If this is an 'out'(right) bound, find the highest after 'out'.
    Use HashMap to reduce lost of space and time: If the range of x_min to x_max is too large with lots of useless points (points without x_bound on it), use HashMap could reduce the space and also the time used for BIT.
    There must be a value VAL between any (2^n+k1) and (2^n+k2) (0<=k1<k2<=2^n), which:
            i. (2^n+k1) can reach VAL by adding its last bit and change value continuously
ii. (2^n+k2) can reach VAL by reducing its last bit and change value continuously*/
    public class Solution {
        private int[] biTree;

        private void add(int r, int h) { // here r is in the reduced array
            while (r > 0) {
                biTree[r] = Math.max(biTree[r], h);
                r -= r & (-r);
            }
        }

        private int find(int l) { // here l is in the reduced array
            int ret = 0;
            while (l < biTree.length) {
                ret = Math.max(ret, biTree[l]);
                l += l & (-l);
            }
            return ret;
        }

        public List<int[]> getSkyline(int[][] buildings) {
            List<int[]> list = new ArrayList<int[]>();

            int[][] xidx = new int[buildings.length * 2][3]; // array of [x, i/o, idx]
            for (int i = 0; i < buildings.length; i++) {
                xidx[2 * i] = new int[] {buildings[i][0], 1, i}; // in
                xidx[2 * i + 1] = new int[] {buildings[i][1], 2, i}; // out
            }

            Arrays.sort(xidx, (a,b) -> {
                if (a[0] != b[0])
                    return a[0] - b[0]; // sort from left to right
                else return a[1] - b[1]; // in before out; otherwise, <out, 0> will be add to the list and cause problem
            });

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            int num = 0;
            for (int[] x : xidx) {
                map.put(x[0], ++num); // map of (x : number), to reduce space usage
            }
            biTree = new int[num + 1];

            int l, r, h;
            int curhigh = 0; // start hight
            for (int[] x : xidx) {
                if (x[1] == 1) { // left side of a building
                    l = buildings[x[2]][0];
                    r = buildings[x[2]][1];
                    h = buildings[x[2]][2];
                    add(map.get(r), h);
                } else {
                    l = buildings[x[2]][1]; // assign r to l, to find hight after the building end
                }

                int tmp = find(map.get(l) + 1); // find the highest after

                if (tmp != curhigh) {
                    int size = list.size();
                    if (size > 0 && list.get(size - 1)[0] == l) { // 2 hight with the same x
                        curhigh = list.get(size - 1)[1] = Math.max(tmp, list.get(size - 1)[1]);
                    } else {
                        list.add(new int[]{l, tmp});
                        curhigh = tmp;
                    }
                }
            }
            return list;
        }
    }

////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
}
/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 */