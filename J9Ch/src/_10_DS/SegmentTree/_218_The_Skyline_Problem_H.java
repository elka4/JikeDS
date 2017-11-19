package _10_DS.SegmentTree;
import java.util.*;

//  218. The Skyline Problem
//  https://leetcode.com/problems/the-skyline-problem/description/
//  Divide and Conquer Heap Binary Indexed Tree Segment Tree
public class _218_The_Skyline_Problem_H {
    //https://briangordon.github.io/2014/08/the-skyline-problem.html

//-------------------------------------------------------------------------/
    //1
    //Java Segment Tree Solution, 47 ms
    private static class Node{
        int start, end;
        Node left, right;
        int height;

        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        Set<Integer> endpointSet = new HashSet<Integer>();
        for(int[] building : buildings){
            endpointSet.add(building[0]);
            endpointSet.add(building[1]);
        }

        List<Integer> endpointList = new ArrayList<Integer>(endpointSet);
        Collections.sort(endpointList);

        HashMap<Integer, Integer> endpointMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < endpointList.size(); i++){
            endpointMap.put(endpointList.get(i), i);
        }

        Node root = buildNode(0, endpointList.size() - 1);
        for(int[] building : buildings){
            add(root, endpointMap.get(building[0]), endpointMap.get(building[1]), building[2]);
        }

        List<int[]> result = new ArrayList<int[]>();
        explore(result, endpointList, root);

        if(endpointList.size() > 0){
            result.add(new int[]{endpointList.get(endpointList.size() - 1), 0});
        }

        return result;
    }

    private Node buildNode(int start, int end){
        if(start > end){
            return null;
        }else{
            Node result = new Node(start, end);
            if(start + 1 < end){
                int center = start + (end - start) / 2;
                result.left = buildNode(start, center);
                result.right = buildNode(center, end);
            }

            return result;
        }
    }

    private void add(Node node, int start, int end, int height){
        if(node == null || start >= node.end || end <= node.start || height < node.height){
            return;
        }else{
            if(node.left == null && node.right == null){
                node.height = Math.max(node.height, height);
            }else{
                add(node.left, start, end, height);
                add(node.right, start, end, height);
                node.height = Math.min(node.left.height, node.right.height);
            }
        }
    }

    private void explore(List<int[]> result, List<Integer> endpointList, Node node){
        if(node == null){
            return;
        }else{
            if(node.left == null && node.right == null && (result.size() == 0 ||
                    result.get(result.size() - 1)[1] != node.height)){
                result.add(new int[]{endpointList.get(node.start), node.height});
            }else{
                explore(result, endpointList, node.left);
                explore(result, endpointList, node.right);
            }
        }
    }

//-------------------------------------------------------------------------/
    //2
    //A segment tree solution
    //Segment Tree Range Minimum Query
    //https://www.youtube.com/watch?v=ZBHKZF5w4YU&list=PLXjkndB54dnJ9Ml7J7M85TB_pyvcVVuGi&index=2
    //Lazy Propagation Segment Tree
    //https://www.youtube.com/watch?v=xuoQdt5pHj0&list=PLXjkndB54dnJ9Ml7J7M85TB_pyvcVVuGi&index=3
    class SegTree {
        int[] segTree;  //从node = 1开始有效

        public SegTree(int size) {
            segTree = new int[size];
        }

        void update(int node, int startRange, int endRange, int left, int right, int value) {
            if (left > right || right < startRange || left > endRange)
                return;
            //类似lazy propagation
            if (startRange == left && endRange == right) {
                segTree[node] = Math.max(segTree[node], value);
                return;
            }
            int mid = (startRange + endRange) >> 1;
            update(node << 1, startRange, mid, left, Math.min(mid, right), value);
            update((node << 1) + 1, mid + 1, endRange, Math.max(mid + 1, left), right, value);
        }

        int query(int node, int startRange, int endRange, int index) {
            if (startRange == endRange)
                return segTree[node];
            int mid = (startRange + endRange) >> 1;
            int res = index <= mid ? query(node << 1, startRange, mid, index) :
                    query((node << 1) + 1, mid + 1, endRange, index);
            return Math.max(res, segTree[node]); //do this, because of lazy propagation
        }
    }

    public class Solution2 {

        public List<int[]> getSkyline(int[][] buildings) {
            Set<Integer> ts = new TreeSet<>(); //sorted by ascending order
            for (int i = 0; i < buildings.length; i++) {
                ts.add(buildings[i][0]);
                ts.add(buildings[i][3]);
            }
            //将X坐标区间到映射成1,2..连续值区间
            Map<Integer, Integer> map = new HashMap<>(), rMap = new HashMap<>();
            int k = 0;
            for (Integer it : ts) {
                map.put(it, k);
                rMap.put(k++, it);
            }
            k = k - 1;
            SegTree segTree = new SegTree((k + 1) << 4);
            //build segTree
            for (int i = 0; i < buildings.length; i++) {
                segTree.update(1, 0, k, map.get(buildings[i][0]), map.get(buildings[i][4]) - 1, buildings[i][2]);
            }
            int preHeight = 0, height;
            List<int[]> ret = new ArrayList<>();
            for (int i = 0; i <= k; i++) {
                height = segTree.query(1, 0, k, i);  //ith 区间max height
                if (preHeight == height) continue; //连续区间相同高度 取第一个即可
                ret.add(new int[]{rMap.get(i), height});  //转化为实际区间
                preHeight = height;
            }
            return ret;
        }
    }
//-------------------------------------------------------------------------/


//-------------------------------------------------------------------------/


//-------------------------------------------------------------------------/
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