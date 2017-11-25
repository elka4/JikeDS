package _10_DS._Heap;
import java.util.*;
import org.junit.Test;

//  239. Sliding Window Maximum
public class _239_Heap_Sliding_Window_Maximum_H {

//-----------------------------------------------------------------------------
//Java O(n) solution using deque with explanation
/*
We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]
 */

class solution1{
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n-k+1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }
}

//O(n) solution in Java with two simple pass in the array
/*
For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4

partition the array in blocks of size w=4. The last block may have less then w.
2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|

Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56

Similarly calculate max in future by traversing from end to start.
right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56

now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
 */

class solution2{
    public int[] slidingWindowMax(final int[] in, final int w) {
        final int[] max_left = new int[in.length];
        final int[] max_right = new int[in.length];

        max_left[0] = in[0];
        max_right[in.length - 1] = in[in.length - 1];

        for (int i = 1; i < in.length; i++) {
            max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

            final int j = in.length - i - 1;
            max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
        }

        final int[] sliding_max = new int[in.length - w + 1];
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
        }

        return sliding_max;
    }
}

//My Java Solution Using PriorityQueue
    /*
    Not a linear solution, instead, it is of O(nlogk) complexity, since add, pop and remove operation of PriorityQueue cost O(logk) time.

What we need to do is just maintain a heap, that heap top gets the maximal value of the k elements.
Since we know which element is removed and which is added to the queue, the solution seems easy to understand.
     */
class solution3{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len - k + 1];
        if(nums.length == 0) return new int[0];
        Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return Integer.compare(i2, i1);
            }
        });

        for(int i = 0; i < k; i ++){
            queue.add(nums[i]);
        }
        result[0] = queue.peek();
        for(int i = k; i < len; i ++){
            queue.remove(nums[i - k]);
            queue.add(nums[i]);
            result[i - k + 1] = queue.peek();
        }

        return result;
    }
}


//Concise JAVA solution based on Deque
/*
The basic idea is referred from here: use two-ends deque to make the first element in the
 deque is the largest number in the window, and keep the above steps recursively.

Time complexity is O(n), as each number in the array is only offered or polled once.
 */
class solution4{
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) return new int[0];
        int[] res = new int[a.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        int index  = 0;
        for (int i = 0; i < a.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) // Ensure deque's size doesn't exceed k
                deque.poll();

            // Remove numbers smaller than a[i] from right(a[i-1]) to left,
            // to make the first number in the deque the largest one in the window
            while (!deque.isEmpty() && a[deque.peekLast()] < a[i])
                deque.pollLast();

            deque.offer(i);// Offer the current index to the deque's tail

            if (i >= k - 1)// Starts recording when i is big enough to make the window has k elements
                res[index++] = a[deque.peek()];
        }
        return res;
    }
}
//-----------------------------------------------------------------------------
    // 9Ch

    public class Jiuzhang {

        /**
         * @param deque: A list of integers.
         * @return: The maximum number inside the window at each moving.
         */
        void inQueue(Deque<Integer> deque, int num) {
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.offer(num);
        }

        void outQueue(Deque<Integer> deque, int num) {
            if (deque.peekFirst() == num) {
                deque.pollFirst();
            }
        }

        public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
            // write your code here
            ArrayList<Integer> ans = new ArrayList<Integer>();
            Deque<Integer> deque = new ArrayDeque<Integer>();
            if (nums.length == 0) {
                return ans;
            }
            for (int i = 0; i < k - 1; i++) {
                inQueue(deque, nums[i]);
            }

            for(int i = k - 1; i < nums.length; i++) {
                inQueue(deque, nums[i]);
                ans.add(deque.peekFirst());
                outQueue(deque, nums[i - k + 1]);
            }
            return ans;

        }
    }


//-----------------------------------------------------------------------------




}
/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */

/*
滑动窗口的最大值

 描述
 笔记
 数据
 评测
给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出数组 [1,2,7,7,8], 滑动窗口大小为 k = 3. 返回 [7,7,8].

解释：

最开始，窗口的状态如下：

[|1, 2 ,7| ,7 , 8], 最大值为 7;

然后窗口向右移动一位：

[1, |2, 7, 7|, 8], 最大值为 7;

最后窗口再向右移动一位：

[1, 2, |7, 7, 8|], 最大值为 8.

挑战
O(n)时间，O(k)的额外空间


 */