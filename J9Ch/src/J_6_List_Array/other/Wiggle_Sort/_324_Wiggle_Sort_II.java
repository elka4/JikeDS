package J_6_List_Array.other.Wiggle_Sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 */


public class _324_Wiggle_Sort_II {

    //https://leetcode.com/problems/wiggle-sort-ii/discuss/

    //Step by step explanation of index mapping in Java
    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {

            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            else {
                i++;
            }
        }


    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[i] = temp;
    }

    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }

    public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }


//------------------------------------------------------------------------------/////
    /*
    https://discuss.leetcode.com/topic/71990/summary-of-the-various-solutions-to-wiggle-sort-for-your-reference
     */

    //AC java solution(7ms)


    public void wiggleSort2(int[] nums) {
        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int mid = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        int index = 0;
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < nums.length)
                temp[index + 1] = nums[nums.length - i - 1];
            index = index + 2;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }





//------------------------------------------------------------------------------/////

    //Summary of the various solutions to Wiggle Sort for your reference
    //II -- O(nlogn) time and O(n) space solution by sorting

    public void wiggleSort3(int[] nums) {
        int n = nums.length, m = (n + 1) >> 1;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) nums[j] = copy[i];
    }

//------------------------------------------------------------------------------/////

    //III -- O(n) time and O(n) space solution by median partition

    public void wiggleSort4(int[] nums) {
        int n = nums.length, m = (n + 1) >> 1;
        int[] copy = Arrays.copyOf(nums, n);
        int median = kthSmallestNumber(nums, m);

        for (int i = 0, j = 0, k = n - 1; j <= k;) {
            if (copy[j] < median) {
                swap(copy, i++, j++);
            } else if (copy[j] > median) {
                swap(copy, j, k--);
            } else {
                j++;
            }
        }

        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) nums[j] = copy[i];
    }

    private int kthSmallestNumber(int[] nums, int k) {
        Random random = new Random();

        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, i, random.nextInt(i + 1));
        }

        int l = 0, r = nums.length - 1;
        k--;

        while (l < r) {
            int m = getMiddle(nums, l, r);

            if (m < k) {
                l = m + 1;
            } else if (m > k) {
                r = m - 1;
            } else {
                break;
            }
        }

        return nums[k];
    }

    private int getMiddle(int[] nums, int l, int r) {
        int i = l;

        for (int j = l + 1; j <= r; j++) {
            if (nums[j] < nums[l]) swap2(nums, ++i, j);
        }

        swap2(nums, l, i);
        return i;
    }

    private void swap2(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

//------------------------------------------------------------------------------/////

    //IV -- O(n) time and O(1) space solution by combining partition and placement


    public void wiggleSort5(int[] nums) {
        int n = nums.length, m = (n + 1) >> 1;
        int median = kthSmallestNumber(nums, m);

        for (int i = 0, j = 0, k = n - 1; j <= k;) {
            if (nums[A(j, n)] > median) {
                swap(nums, A(i++, n), A(j++, n));
            } else if (nums[A(j, n)] < median) {
                swap(nums, A(j, n), A(k--, n));
            } else {
                j++;
            }
        }
    }

    private int A(int i, int n) {
        return (2 * i + 1) % (n | 1);
    }



//------------------------------------------------------------------------------/////

    //https://discuss.leetcode.com/topic/52116/o-n-o-1-after-median-without-virtual-indexing/2

    //O(n)+O(1) after median --- without Virtual Indexing :-)

    public void wiggleSort6(int[] nums) {
        int n = nums.length;
        int med = findKthLargest6(nums, (n + 1) / 2);
        //(2) elements larger than the 'median' are put into the first odd slots
        int i = 1;
        //(1) elements smaller than the 'median' are put into the last even slots
        int j = (n - 1) / 2 * 2;
        int x = j;

        for(int k = 0; k < n;k++){
            if(nums[x] > med){
                swap6(nums, x, i);
                i += 2;
            } else if(nums[x] < med){
                swap6(nums, x, j);
                j = j - 2;
                x = x - 2;
                if(x < 0) x = n / 2 * 2 - 1;
            } else {
                x = x - 2;
                if(x < 0) x = n / 2 * 2 - 1;
            }
        }
    }

    void swap6(int[] nums, int i, int j){
        if(i < 0 || i >= nums.length || j < 0 || j >= nums.length) return;
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int findKthLargest6(int[] nums, int k) {

        Queue<Integer> q = new PriorityQueue<>(k);
        for(int n: nums){
            if(q.size() == k){
                if(q.peek() == null){
                    q.offer(n);
                }else if(n > q.peek()){
                    q.poll();
                    q.offer(n);
                }
            } else {
                q.offer(n);
            }
        }

        return q.peek();
    }




//------------------------------------------------------------------------------/////

    //Java 18 ms true O(1) space / cheated O(n) time using binary search

    public void wiggleSort7(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int p = bsSelect(nums, (nums.length - 1) / 2 + 1);
        // Reverse Dutch National Flag with Wiggle Indexing (StefanPochmann's Virtual Indexing).
        // Thanks to apolloydy for reversing this thing.
        final int n = nums.length;
        int m = 0, r = nums.length - 1;
        int lw = 1, mw = 1, rw = (1 + 2 * (nums.length - 1)) % (n | 1);
        while (m <= r) {
            if (nums[mw] > p) {
                int tmp = nums[mw];
                nums[mw] = nums[lw];
                nums[lw] = tmp;
                mw = (mw + 2) % (n | 1);
                ++m;
                lw = (lw + 2) % (n | 1);
            } else if (nums[mw] < p) {
                int tmp = nums[mw];
                nums[mw] = nums[rw];
                nums[rw] = tmp;
                rw = (rw - 2 + (n | 1)) % (n | 1);
                --r;
            } else {
                mw = (mw + 2) % (n | 1);
                ++m;
            }
        }
    }

    private int bsSelect(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("length=" + nums.length + " k=" + k);
        }
        int left = Integer.MIN_VALUE, right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left < 0 && right > 0) ? (left + right) / 2 : left + (right - left) / 2;
            int cl = 0, cg = 0, d = 0;
            for (int n : nums) {
                if (n < mid) {
                    if (++cl > k - 1) {
                        d = +1; // mid larger than kth
                        break;
                    }
                } else if (n > mid) {
                    if (++cg > (nums.length - k)) {
                        d = -1; // mid smaller than kth
                        break;
                    }
                }
            }
            if (d == 0) {
                return mid;
            } else if (d < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        throw new AssertionError();
    }



//------------------------------------------------------------------------------/////
    /*
    https://discuss.leetcode.com/topic/38189/clear-java-o-n-avg-time-o-n-space-solution-using-3-way-partition
     */
    //Clear Java O(n) avg time & O(n) space solution using 3-way-partition

    private int selectKth8(int[] nums, int start, int end, int k) {
        int[] res = partition(nums,start,end);
        int lb = res[0]; int hb = res[1];
        if(k-1<lb)
            return selectKth8(nums,start,lb-1,k);
        else if (k-1>hb)
            return selectKth8(nums,hb+1,end,k);
        else
            return k-1;
    }

    private int[] partition(int[] nums, int lb, int hb) {
        int pVal = nums[lb]; // use random genarater is better in performance
        int i = lb;
        while(i<=hb) {
            if(nums[i]==pVal)
                i++;
            else if(nums[i]<pVal)
                swap8(nums,i++,lb++);
            else
                swap8(nums,i,hb--);
        }
        int[] res = new int[2];
        res[0] = lb; res[1] = hb;
        return res;
    }

    private void swap8(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }




//------------------------------------------------------------------------------/////





//------------------------------------------------------------------------------/////



}
