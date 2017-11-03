package _TwoPointer.All_TwoPointer;

import lib.ListNode;

//  287. Find the Duplicate Number
//  https://leetcode.com/problems/find-the-duplicate-number/description/

//http://www.lintcode.com/zh-cn/problem/find-the-duplicate-number/
public class _287_TwoPointer_Find_the_Duplicate_Number_H {

    //  Java O(n) time and O(1) space solution. Similar to find loop in linkedlist.
    //  https://discuss.leetcode.com/topic/25685/java-o-n-time-and-o-1-space-solution-similar-to-find-loop-in-linkedlist
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for(int i=0;i<nums.length;i++) nums[i]--;
        int slow = n-1;
        int fast = n-1;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        slow = n-1;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow+1;
    }
//    One condition is we cannot modify the array. So the solution is

    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        int slow = n;
        int fast = n;
        do{
            slow = nums[slow-1];
            fast = nums[nums[fast-1]-1];
        }while(slow != fast);
        slow = n;
        while(slow != fast){
            slow = nums[slow-1];
            fast = nums[fast-1];
        }
        return slow;
    }

//    Nice idea! Code could be simpler: start with 0 and no need to deduct 1.
//
//    Accepted code:

    public int findDuplicate3(int[] nums) {
        int slow = 0, fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

///////////////////////////////////////////////////////////////////
/*
Similar idea. Also attached the code of Linked List Cycle II. The code of cycle problem is referred to the popular post in the Leetcode discuss.
    Find duplicate num
*/

    public class Solution4 {
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            int len = nums.length;
            while (fast < len && nums[fast] < len) {
                slow = nums[slow];
                fast = nums[nums[fast]];
                if (slow == fast) {
                    slow = 0;
                    while (slow != fast) {
                        slow = nums[slow];
                        fast = nums[fast];
                    }
                    return slow;
                }
            }
            return -1;
        }
    }
//    Linked List Cycle II

    public class Solution44 {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null)  return null;
            ListNode fast = head, slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    slow = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow;
                }
            }
            return null;
        }
    }
///////////////////////////////////////////////////////////////////
    //Java O(1)space using Binary-Search
    public int findDuplicate5(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
/*
good method, the wise use of number density and binary search, so smart!

For "int mid = (int) (low + (high - low) * 0.5);", you would better to use int instead of double, which can be much faster.
 */





///////////////////////////////////////////////////////////////////
//    JAVA-------------Easy Version To UnderStand!!!!!!!!!
    public int findDuplicate6(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        int low = 1, high = nums.length - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    /*
    @HelloWorld123456 It's a very good solution and I've learned from you :) The idea is calculate a middle number between low and high, then in for loop to check total numbers less or equal than middle. If numbers less than middle, then the duplicate must fall in the [low, middle] range; else in [middle+1, high]. For this solution, time is O(nlgn) and space is O(1) for my understanding.

Except declare "int count = 0;" out side of while loop. You can also consider the following two suggestions:

Check nums == null before nums.length == 0, else you can get a NullPointException as well.
You can also add data validation code below in the for loop to make it more robust.
if(x >= nums.length || x < 1) return x;
Here is my practice after I learnt your idea and thanks again for your sharing:
     */

    public int findDuplicate7(int[] nums) {
        if( nums == null || nums.length <2){ //No duplicate if length is 1
            return 0;
        }

        int low=1, mid, high = nums.length - 1;  //values range from [1..n-1]
        int countLessThanMid = 0;
        while (low < high) {
            //Recalculate the mid based on half range reduced [lower', high']
            mid = low + (high - low)/2;

            //Count how many numbers less than mid
            for(int x: nums) {
                //Valid data checking
                if(x >= nums.length || x < 1) {
                    return x;
                }

                if(x <=mid) countLessThanMid++;
            }

            if(countLessThanMid > mid) {
                //duplicate occurs in [lower, mid]
                high=mid;
            } else {
                //dupicate occurs in [mid+1, high]
                low=mid+1;
            }
            countLessThanMid = 0;
        }

        return low;
    }
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
    //jiuzhang
    // 二分法
    public class Jiuzhang1 {
        /**
         * @param nums an array containing n + 1 integers which is between 1 and n
         * @return the duplicate one
         */
        public int findDuplicate(int[] nums) {
            // Write your code here
            int start = 1;
            int end = nums.length - 1;
            while(start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (check_smaller_num(mid, nums) <= mid) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (check_smaller_num(start, nums) <= start) {
                return end;
            }
            return start;
        }

        public int check_smaller_num(int mid, int[] nums) {
            int cnt = 0;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] <= mid){
                    cnt++;
                }
            }
            return cnt;
        }
    }

    // 映射法
    public class Jiuzhang2 {
        /**
         * @param nums an array containing n + 1 integers which is between 1 and n
         * @return the duplicate one
         */
        public int findDuplicate(int[] nums) {
            // Write your code here
            if (nums.length <= 1)
                return -1;

            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
    }

}
/*
给出一个数组 nums 包含 n + 1 个整数，每个整数是从 1 到 n (包括边界)，保证至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

 注意事项

1.不能修改数组(假设数组只能读)
2.只能用额外的O(1)的空间
3.时间复杂度小于O(n^2)
4.数组中只有一个重复的数，但可能重复超过一次

样例
给出 nums = [5,5,4,3,2,1]，返回 5.
给出 nums = [5,4,4,3,2,1]，返回 4.


 */

/*

 */