package _BinarySearch.Count;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//
//
//
public class _658_BinarySearch_Find_K_Closest_Elements_M {
    //Approach #1 Using Collection.sort( ) [Accepted]
        /*
        ntuitively, we can sort the elements in list arr by their absolute
        difference values to the target x. Then the sublist of the first k
        elements is the result after sorting the elements by the natural order.
         */
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
        arr = arr.subList(0, k);
        Collections.sort(arr);
        return arr;
    }


    //Approach #2 Using Binary Search and Two Pointers [Accepted]
    public List<Integer> findClosestElements2(List<Integer> arr, int k, int x) {
        int n = arr.size();

        if (x <= arr.get(0)) {
            return arr.subList(0, k);
        } else if (arr.get(n - 1) <= x){
            return arr.subList(n - k, n);
        } else {
            int index = Collections.binarySearch(arr, x);
            if (index < 0)
                index = -index - 1;

            int low = Math.max(0, index - k - 1);
            int high = Math.min(arr.size() - 1, index + k - 1);

            while(high - low > k - 1){
                if(low < 0 || (x - arr.get(low) <= (arr.get(high) - x))){
                    high--;
                } else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x)) {
                    low++;
                } else {
                    System.out.println("unhandled case: " + low + " " +  high);
                }
            }
        return  arr.subList(low, high + 1);
        }
    }

//------------------------------------------------------------------------------///

    //O(log n) Java, 1 line O(log(n) + k) Ruby
    public List<Integer> findClosestElements33(List<Integer> arr, int k, int x) {
        int lo = 0, hi = arr.size() - k;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (x - arr.get(mid) > arr.get(mid+k) - x)
                lo = mid + 1;
            else
                hi = mid;
        }
        return arr.subList(lo, lo + k);
    }


    //    Java 4-Liner and O(n) Time Solution
    //    O(nlog(n)) Time Solution:

    public List<Integer> findClosestElements3(List<Integer> arr, int k, int x) {
        Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
        arr = arr.subList(0, k);
        Collections.sort(arr);
        return arr;
    }

    //    O(n) Time Solution:
    public List<Integer> findClosestElements4(List<Integer> arr, int k, int x) {
        List<Integer> less = new ArrayList<>(), greater = new ArrayList<>(),
                lessResult = new LinkedList<>(), greaterResult = new LinkedList<>();

        for (Integer i : arr) {
            if (i <= x) less.add(i);
            else greater.add(i);
        }

        Collections.reverse(less);
        int  i = 0, j = 0, n = less.size(), m = greater.size();
        for (int size=0;size<k;size++) {
            if (i < n && j < m) {
                if (Math.abs(less.get(i) - x) <= Math.abs(greater.get(j) - x)) lessResult.add(less.get(i++));
                else greaterResult.add(greater.get(j++));
            }
            else if (i < n) lessResult.add(less.get(i++));
            else greaterResult.add(greater.get(j++));
        }

        Collections.reverse(lessResult);
        lessResult.addAll(greaterResult);
        return lessResult;
    }
//    Note that above solution can be improved using binary search under the assumption that we have O(1) access to elements in input list.

//------------------------------------------------------------------------------///

//------------------------------------------------------------------------------///
}
/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order.
If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]

Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104

UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers).
Please reload the code definition to get the latest changes.


 */