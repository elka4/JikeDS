package j_2_BinarySearch; import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;

//  k Closest Number in Sorted arrrray
//   leetcode 658. Find K Closest Elements

public class _19K_Closest_Numbers_In_Sorted_Array {

    //Approach #1 Using Collection.sort( ) [Accepted]
    /*
    Intuitively, we can sort the elements in list arr by their absolute difference values to the target x. Then the sublist of the first k elements is the result after sorting the elements by the natural order.
     */
    class Solution1 {
        public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
            Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
            arr = arr.subList(0, k);
            Collections.sort(arr);
            return arr;
        }

        public List<Integer> findClosestElements2(int[] arr, int k, int x) {
            List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

            Collections.sort(list, (a,b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
            list = list.subList(0, k);
            Collections.sort(list);
            return list;
        }
    }
    /*
    Complexity Analysis

Time complexity : O(n*log(n))O(n∗log(n)). Collections.sort() uses binary sort so it has a O(n*log(n))O(n∗log(n)) complexity.

Space complexity : O(k)O(k). The in-place sorting does not consume any extra space. However, generating a k length sublist will take some space.
     */





    //Approach #2 Using Binary Search and Two Pointers [Accepted]
/*
The original array has been sorted so we can take this advantage by the following steps.
1. If the target x is less or equal than the first element in the sorted array, the first k elements are the result.
2. Similarly, if the target x is more or equal than the last element in the sorted array, the last k elements are the result.
3. Otherwise, we can use binary search to find the index of the element, which is equal (when this list has x) or a little bit larger than x (when this list does not have it).

Then set low to its left k-1 position, and high to the right k-1 position of this index as a start. The desired k numbers must in this rang [index-k-1, index+k-1]. So we can shrink this range to get the result using the following rules. * If low reaches the lowest index 0 or the low element is closer to x than the high element, decrease the high index. * If high reaches to the highest index arr.size()-1 or it is nearer to x than the low element, increase the low index. * The looping ends when there are exactly k elements in [low, high], the subList of which is the result.
 */
    class Solution2 {
        public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
            int n = arr.size();
            if ( x <= arr.get(0)) {
                return arr.subList(0, k);
            } else if (arr.get(n - 1) <= x) {
                return arr.subList(n - k, n);
            } else {
                int index = Collections.binarySearch(arr, x);
                if (index < 0) {
                    index = -index - 1;
                }
                int low = Math.max(0, index - k - 1);
                int high = Math.min(arr.size() - 1, index + k - 1);

                while (high - low > k - 1) {
                    if (low < 0 || (x - arr.get(low)) <= (arr.get(high) - x)) {
                        high--;
                    } else if (high > arr.size() - 1 || (x - arr.get(low)) > (arr.get(high) - x)) {
                        low++;
                    } else {
                        System.out.println("unhandled case: " + low + " " + high);
                    }
                }
                return arr.subList(low, high + 1);
            }
        }
    }
/*
Time complexity : O(log(n)+k)O(log(n)+k). O(log(n))O(log(n)) is for the time of binary search, while O(k)O(k) is for shrinking the index range to k elements.

Space complexity : O(k)O(k). It is to generate the required sublist.
 */

    //  Java 4-Liner and O(n) Time Solution
    class Solution3 {
//        Java 4-Liner and O(n) Time Solution
//        O(nlog(n)) Time Solution:

        public List<Integer> findClosestElements1(List<Integer> arr, int k, int x) {
            Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
            arr = arr.subList(0, k);
            Collections.sort(arr);
            return arr;
        }

//        O(n) Time Solution:

        public List<Integer> findClosestElements2(List<Integer> arr, int k, int x) {
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
//        Note that above solution can be improved using binary search under the assumption that we have O(1) access to elements in input list.
    }


    //easy java
    class Solution4{
        public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
            List<Integer> result = new LinkedList<>();
            TreeMap<Integer, List<Integer>> map = new TreeMap<>();
            for (int a : arr) {
                int abs = Math.abs(a - x);
                if (!map.containsKey(abs)) map.put(abs, new LinkedList<>());
                map.get(abs).add(a);
            }
            for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
                for (int i : e.getValue()) {
                    result.add(i);
                    if (result.size() == k) break;
                }
                if (result.size() == k) break;
            }
            Collections.sort(result);
            return result;
        }
    }

    //[Java/C++] Very simple binary search solution

    /*
    The idea is to find the first number which is equal to or greater than x in arr. Then, we determine the indices of the start and the end of a subarray in arr, where the subarray is our result. The time complexity is O(logn + k).

In the following code, arr[index] is the first number which is euqal to or geater than x (if all numbers are less than x, index is arr.size()), and the result is arr[i+1, i+2, ... j].


     */
    class Solution5{
        public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
            int index = Collections.binarySearch(arr, x);
            if(index < 0) index = -(index + 1);
            int i = index - 1, j = index;
            while(k-- > 0){
                if(i<0 || (j<arr.size() && Math.abs(arr.get(i) - x) > Math.abs(arr.get(j) - x) ))j++;
                else i--;
            }
            return arr.subList(i+1, j);
        }
    }
///////////////////////////////////////////////////////////////////////////////
	 /*    这个是lintcode解法，result 按照差大小排序，然后按照数字大小排序
     * @param arr an integer array
     * @param x an integer
     * @param k a non-negative integer
     * @return an integer array
     */
     public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new LinkedList<>();
        
        if (arr == null || arr.length == 0) {
            return result;
        }
        if (k > arr.length) {
            return result;
        }
        
        int index = firstIndex(arr, x);
        
        int start = index - 1;
        int end = index;
        for (int i = 0; i < k; i++) {
            if (start < 0) {
                result.add(i,  arr[end++]);
            } else if (end >= arr.length) {
                result.add(i,  arr[start--]);
            } else {
                if (x - arr[start] <= arr[end] - x) {
                    result.add(i,  arr[start--]);
                } else {
                    result.add(i,  arr[end++]);
                }
            }
        }
        return result;
    }
    
    private int firstIndex(int[] arr, int x) {
        int start = 0, end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < x) {
                start = mid;
            } else if (arr[mid] > x) {
                end = mid;
            } else {
                end = mid;
            }
        }
        
        if (arr[start] >= x) {
            return start;
        }
        if (arr[end] >= x) {
            return end;
        }
        return arr.length;
    }

    @Test
    public void test(){
        int[] arr = new int[]{1,2,3,4,5};
        int k = 4;
        int x = 3;
        System.out.println(findClosestElements(arr, 4, 3));
    }
}
/*   leetcode
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

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
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
 */



/* old lint
 *Given a x number, a non-negative integer k and an integer array 
 *arr sorted in ascending order, find the k closest numbers to x in arr, 
 *sorted in ascending order by the difference between the number and x. 
 *Otherwise, sorted in ascending order by number if the difference is same.

Have you met this question in a real interview? Yes
Example
Given arr = [1, 2, 3], x = 2 and k = 3, return [2, 1, 3].

Given arr = [1, 4, 6, 8], x = 3 and k = 3, return [4, 1, 6].

Challenge 
O(logn + k) time complexity.

Tags 
Binary Search Two Pointers
Related Problems 
Easy Closest Number in Sorted arrrray 38 %
 * */
