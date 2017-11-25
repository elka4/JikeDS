package J_6_List_Array.other;

import org.junit.Test;

public class _leet_287_Find_the_Duplicate_Number {
    /*
    Given an array nums containing n + 1 integers where each integer is between
     1 and n (inclusive), prove that at least one duplicate number must exist.
      Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
     */

    // Binary Search, Array, Two Pointers

/*
Given an array of n elements，已知所有elements都在1 - n里面，
并且有只有一个数字missing，一个数字duplicate。 return那个duplicate。
 Linear time，constant space
Example：［1，3，4，3］return 3
因为每个element和index都是一一对应的关系，所以我们可以通过不断的交换，
让每一个element都回到应该在的位置上。一旦我们发现有两个位置都对应着同一个element的时候，
说明这个元素是重复的。
 */
    public int findDupNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            while (nums[i] - 1 != i) {
                if (nums[nums[i] - 1] == nums[i]) {
                    // find dup
                    return nums[i];
                } else {
                    swap(nums, nums[i] - 1, i);
                }
            }
            i++;
        }
        return -1;
    }

    public void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test01(){
        int[] nums  = {1,3,4,3};
        System.out.println(findDupNumber(nums));

    }
//-----------------------------------------------------------------------------/
//Java O(n) time and O(1) space solution. Similar to find loop in linkedlist.
//https://haolin29.gitbooks.io/algorithm-collection/content/Array/missing_num.html

/*
suppose the array is

index: 0 1 2 3 4 5

value: 2 5 1 1 4 3
first subtract 1 from each element in the array, so it is much easy to understand.
use the value as pointer. the array becomes:

index: 0 1 2 3 4 5

value: 1 4 0 0 3 2
we must choose the last element as the head of the linked list.
If we choose 0, we can not detect the cycle.

Now the problem is the same as find the cycle in linkedlist!
 */
    public int findDuplicate2(int[] nums) {
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

    //One condition is we cannot modify the array. So the solution is


    public int findDuplicate3(int[] nums) {
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
//-----------------------------------------------------------------------------/
//Java O(1)space using Binary-Search
    public int findDuplicate4(int[] nums) {
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


//-----------------------------------------------------------------------------/
//JAVA-------------Easy Version To UnderStand!!!!!!!!!
    public int findDuplicate5(int[] nums) {
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


//-----------------------------------------------------------------------------/
/*
You must not modify the array (assume the array is read only). 所以不能swap，不能排序。
You must use only constant, O(1) extra space. 不能用hashset
Your runtime complexity should be less than O(n2). 不能two for-loop
There is only one duplicate number in the array, but it could be repeated more than once.
鸽巢法，二分法

实际上，我们可以根据抽屉原理简化刚才的暴力法。我们不一定要依次选择数，然后看是否有这个数的重复数，
我们可以用二分法先选取n/2，按照抽屉原理，整个数组中如果小于等于n/2的数的数量大于n/2，
说明1到n/2这个区间是肯定有重复数字的。比如6个抽屉，如果有7个袜子要放到抽屉里，那肯定有一个抽屉至少两个袜子。
这里抽屉就是1到n/2的每一个数，而袜子就是整个数组中小于等于n/2的那些数。这样我们就能知道下次选择的数的范围，
如果1到n/2区间内肯定有重复数字，则下次在1到n/2范围内找，否则在n/2到n范围内找。下次找的时候，还是找一半。
 */


    public int findDuplicate6(int[] nums) {
        // index range from 0 ~ (n - 1)
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            int count = 0;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] <= mid) {
                    count++;
                }
            }
            if(count > mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
//-----------------------------------------------------------------------------/
/*
映射找环法 O(N)

index 范围是 0 ~ n 数值 范围是 1 ~ n
从0开始，把每个index对应的数值作为一个新的index 组成一个链表的形式，
当这个链表开始有环的地方就是那个duplicate的元素。我们需要做的就是找到这个环的入口。
所以这一题的解法就和linked list cycle II 一样了。
 */

    public int findDuplicate7(int[] nums) {
        int walker = 0;
        int runner = 0;

        do {
            walker = nums[walker];
            runner = nums[nums[runner]];
        } while (walker != runner);

        int find = 0;

        while (find != runner) {
            find = nums[find];
            runner = nums[runner];
        }

        return find;
    }



}
