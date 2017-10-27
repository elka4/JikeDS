package _10_DS._Heap;
import java.util.*;
import org.junit.Test;

//  295. Find Median from Data Stream

public class _295_Heap_Find_Median_from_Data_Stream_H {

//Short simple Java/C++/Python, O(log n) + O(1)
/*
I keep two heaps (or priority queues):

Max-heap small has the smaller half of the numbers.
Min-heap large has the larger half of the numbers.
This gives me direct access to the one or two middle values (they're the tops of the heaps), so getting the median takes O(1) time. And adding a number takes O(log n) time.

Supporting both min- and max-heap is more or less cumbersome, depending on the language, so I simply negate the numbers in the heap in which I want the reverse of the default order. To prevent this from causing a bug with -231 (which negated is itself, when using 32-bit ints), I use integer types larger than 32 bits.

Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers. I think almost all solutions posted previously have that bug.

Update: These are pretty short already, but by now I wrote even shorter ones.
 */

class Solution1{
    class MedianFinder {

        private Queue<Long> small = new PriorityQueue(),
                large = new PriorityQueue();

        public void addNum(int num) {
            large.add((long) num);
            small.add(-large.poll());
            if (large.size() < small.size())
                large.add(-small.poll());
        }

        public double findMedian() {
            return large.size() > small.size()
                    ? large.peek()
                    : (large.peek() - small.peek()) / 2.0;
        }
    };
}

//Very Short, O(log n) + O(1)
/*
Same idea as before, but really exploiting the symmetry of the two heaps by switching them whenever a number is added. Still O(log n) for adding and O(1) for median. Partially inspired by peisi's updated solution.

Update: Added a new Java version (the first one).
 */

class Solution2{
    class MedianFinder {

        Queue<Integer> q = new PriorityQueue(), z = q, t,
                Q = new PriorityQueue(Collections.reverseOrder());

        public void addNum(int num) {
            (t=Q).add(num);
            (Q=q).add((q=t).poll());
        }

        public double findMedian() {
            return (Q.peek() + z.peek()) / 2.;
        }
    };
}

}
/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
 */