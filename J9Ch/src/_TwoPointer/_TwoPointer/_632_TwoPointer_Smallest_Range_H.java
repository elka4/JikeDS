package _TwoPointer._TwoPointer;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;


//  632. Smallest Range
//  https://leetcode.com/problems/smallest-range/description/
public class _632_TwoPointer_Smallest_Range_H {
    //  https://leetcode.com/articles/smallest-range/
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public int[] smallestRange(int[][] nums) {
            int minx = 0, miny = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums[i].length; j++) {
                    for (int k = i; k < nums.length; k++) {
                        for (int l = (k == i ? j : 0);
                             l < nums[k].length;
                             l++) {
                            int min = Math.min(nums[i][j], nums[k][l]);
                            int max = Math.max(nums[i][j], nums[k][1]);
                            int n, m;
                            for (m = 0; m < nums.length; m++) {
                                for (n = 0; n < nums[m].length; n++) {
                                    if (nums[m][n] >= min && nums[m][n] <= max) break;
                                }
                                if (n == nums[m].length) break;
                            }
                            if (m == nums.length) {
                                if (miny - minx > max - min || (miny - minx == max - min && minx > min)) {
                                    miny = max;
                                    minx = min;
                                }
                            }
                        }
                    }
                }
            }
            return new int[]{minx, miny};
        }
    }


    //Approach #2 Better Brute Force [Time Limit Exceeded]
    public class Solution2 {
        public int[] smallestRange(int[][] nums) {
            int minx = 0, miny = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums[i].length; j++) {
                    for (int k = i; k < nums.length; k++) {
                        for (int l = (k == i ? j : 0); 1 < nums[k].length; l++) {
                            int min = Math.min(nums[i][j], nums[k][l]);
                            int max = Math.max(nums[i][j], nums[k][1]);
                            int n, m;
                            for (m = 0; m < nums.length; m++) {
                                n = Arrays.binarySearch(nums[m], min);
                                if (n < 0)
                                    n = -1 - n;
                                if (n == nums[m].length || nums[m][n] < min || nums[m][n] > max) break;
                            }
                            if (m == nums.length) {
                                if (miny - minx > max - min || (miny - minx == max - min && minx > min)) {
                                    miny = max;
                                    minx = min;
                                }
                            }
                        }
                    }
                }
            }
            return new int[]{minx, miny};
        }
    }

    //Approach #3 Using Pointers [Time Limit Exceeded]
    public class Solution3 {
        public int[] smallestRange(int[][] nums) {
            int minx = 0, miny = Integer.MAX_VALUE;
            int[] next = new int[nums.length];
            boolean flag = true;
            for (int i = 0; i < nums.length && flag; i++) {
                for (int j = 0; j < nums[i].length && flag; j++) {
                    int min_i = 0, max_i = 0;
                    for (int k = 0; k < nums.length; k++) {
                        if (nums[min_i][next[min_i]] > nums[k][next[k]]) min_i = k;
                        if (nums[max_i][next[max_i]] < nums[k][next[k]]) max_i = k;
                    }
                    if (miny - minx > nums[max_i][next[max_i]] - nums[min_i][next[min_i]]) {
                        miny = nums[max_i][next[max_i]];
                        minx = nums[min_i][next[min_i]];
                    }
                    next[min_i]++;
                    if (next[min_i] == nums[min_i].length) {
                        flag = false;
                    }
                }
            }
            return new int[] {minx, miny};
        }
    }


    //Approach #4 Using Priority Queue [Accepted]:
    public class Solution4 {
        public int[] smallestRange(int[][] nums) {
            int minx = 0, miny = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            int[] next = new int[nums.length];
            boolean flag = true;
            PriorityQueue<Integer> min_queue =
                    new PriorityQueue<Integer>((i, j) -> nums[i][next[i]] - nums[j][next[j]]);
            for (int i = 0; i < nums.length; i++) {
                min_queue.offer(i);
                max = Math.max(max, nums[i][0]);
            }
            for (int i = 0; i < nums.length && flag; i++) {
                for (int j = 0; j < nums[i].length && flag; j++) {
                    int min_i = min_queue.poll();
                    if (miny - minx > max - nums[min_i][next[min_i]]) {
                        minx = nums[min_i][next[min_i]];
                        miny = max;
                    }
                    next[min_i]++;
                    if (next[min_i] == nums[min_i].length) {
                        flag = false;
                        break;
                    }
                    min_queue.offer(min_i);
                    max = Math.max(max, nums[min_i][next[min_i]]);
                }
            }
            return new int[]{minx, miny};
        }
    }
//-------------------------------------------------------------------------////

/*    Java 8, Sliding window
    The idea is to sort all the elements in the k lists and run a sliding window over the sorted list, to find the minimum window that satisfies the criteria of having atleast one element from each list.*/

    public int[] smallestRange5(List<List<Integer>> nums) {
        List<int[]> list = IntStream.range(0, nums.size())
                .mapToObj( i -> nums.get(i).stream().map(x -> new int[]{x, i}))
                .flatMap(y -> y)
                .sorted(Comparator.comparingInt(p -> p[0])).collect(Collectors.toList());
        int[] counts = new int[nums.size()];
        BitSet set = new BitSet(nums.size());
        int start = -1;
        int[] res = new int[2];
        for(int i = 0; i < list.size(); i++) {
            int[] p = list.get(i);
            set.set(p[1]);
            counts[p[1]] += 1;
            if(start == -1) { start = 0; }
            while(start < i && counts[list.get(start)[1]] > 1) {
                counts[list.get(start)[1]]--;
                start++;
            }
            if(set.cardinality() == nums.size()) {
                if( (res[0] == 0 && res[1] == 0) || (list.get(i)[0] - list.get(start)[0]) < res[1] - res[0]) {
                    res[0] = list.get(start)[0];
                    res[1] = list.get(i)[0];
                }
            }
        }
        return res;
    }

    //Java Code using PriorityQueue. similar to merge k array

//    Image you are merging k sorted array using a heap. Then everytime you pop the smallest element out and add the next element of that array to the heap. By keep doing this, you will have the smallest range.

    public int[] smallestRange6(int[][] nums) {
        PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {
            public int compare(Element a, Element b) {
                return a.val - b.val;
            }
        });
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            Element e = new Element(i, 0, nums[i][0]);
            pq.offer(e);
            max = Math.max(max, nums[i][0]);
        }
        int range = Integer.MAX_VALUE;
        int start = -1, end = -1;
        while (pq.size() == nums.length) {

            Element curr = pq.poll();
            if (max - curr.val < range) {
                range = max - curr.val;
                start = curr.val;
                end = max;
            }
            if (curr.idx + 1 < nums[curr.row].length) {
                curr.idx = curr.idx + 1;
                curr.val = nums[curr.row][curr.idx];
                pq.offer(curr);
                if (curr.val > max) {
                    max = curr.val;
                }
            }
        }

        return new int[] { start, end };
    }

    class Element {
        int val;
        int idx;
        int row;

        public Element(int r, int i, int v) {
            val = v;
            idx = i;
            row = r;
        }
    }
//    Based on your idea, here is my a little simpler code using int[] instead of Class Element

    public int[] smallestRange7(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(nums.size(), new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int max = nums.get(0).get(0);
        for(int i=0; i<nums.size(); i++) {
            minHeap.add(new int[]{nums.get(i).get(0), i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }

        int minRange = Integer.MAX_VALUE;
        int start = -1;
        while(minHeap.size() == nums.size()) {
            int[] t = minHeap.poll();
            if(max - t[0] < minRange) {
                minRange = max - t[0];
                start = t[0];
            }

            if(t[2]+1 < nums.get(t[1]).size()) {
                t[0] = nums.get(t[1]).get(t[2]+1);
                t[2] ++;
                minHeap.add(t);
                max = Math.max(max, t[0]);
            }
        }

        return new int[]{start, start+minRange};
    }
}
/*
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
 */