package Classification.Bit_Manipulation_26;
import java.util.*;

public class a_169_Majority_Element {

///////////////////////////////////////////////////
//Java solutions (sorting, hashmap, moore voting, bit manipulation).

    // Bit manipulation
    //想法和下个方法类似，不过是两个pass，并且使用额外space bit[]， O（n）
    //bit[] 统计所有num在某个bit位上出现的次数
    public int majorityElement04(int[] nums) {
        int[] bit = new int[32];

        for (int num: nums){
            for (int i=0; i<32; i++) {
                if ((num >> (31 - i) & 1) == 1)
                    bit[i]++;
            }
        }

        int ret=0;

        for (int i=0; i<32; i++) {
            bit[i] = bit[i] > nums.length/2 ? 1 : 0;
            ret += bit[i]*(1<<(31-i));
        }

        return ret;
    }


    //这个比较好理解，要重点理解，对于32个bit的位置，外层循环对每个bit进行检查从0到31
    //内层循环针对每个bit位置，设置一个count，然后对与nums里每个num检查这个bit位上的数是否为1
    //如果count超过nums长度一半，result的这个bit位设为1，
    // 也就是设为majority num在这个位置上的数值。（默认为0）
    //
    //也就是说，本算法就是逐个对nums每个num检查从小到大某个bit位置出现最多的bit（1 or 0）

    //O(32n) ?

    //
    public int majorityElement5(int[] nums) {

        //Bit manipulation
        int result = 0;
        for (int i=0; i<32; i++) {
            int count = 0;
            for (int j=0; j<nums.length; j++) {
                //num[j]的第i位是1，count++
                if((nums[j]>>i&1) ==1) {
                    count++;
                }
            }
            if (count > nums.length/2) {
                result |= (1<<i);
            }
        }
        return result;
    }


///////////////////////////////////////////////////////////////////////////////

    // Sorting
    public int majorityElement01(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // Hashtable
    public int majorityElement02(int[] nums) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        //Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
        int ret=0;
        for (int num: nums) {
            if (!myMap.containsKey(num))
                myMap.put(num, 1);
            else
                myMap.put(num, myMap.get(num)+1);
            if (myMap.get(num)>nums.length/2) {
                ret = num;
                break;
            }
        }
        return ret;
    }

    // Moore voting algorithm
    //这个算法很棒
    //count == 0 就说明已经遍历过偶数次数。已经遍历过的数字，和ret相同的与不同的数量相等。
    //这时把当前num设为ret，相当于重新开始循环
    //到达循环结束的时候，最后一个被设为ret的数就是结果
    public int majorityElement03(int[] nums) {
        int count=0, ret = 0;
        for (int num: nums) {
            if (count==0)
                ret = num;

            if (num!=ret)
                count--;
            else
                count++;
        }
        return ret;
    }

    public int majorityElement(int[] num) {

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;

        }
        return major;
    }




////////////////////////////////////////////////////////
public int majorityElement2(int[] nums) {
    Arrays.sort(nums);
    int len = nums.length;
    return nums[len/2];
}
////////////////////////////////////////////////////////
public int majorityElement3(int[] nums) {
    Arrays.sort(nums);
    int maj=0;
    for(int i=0;i<nums.length/2+1;i++){
        if(nums[i]==nums[i+nums.length/2]){
            maj=nums[i];
            break;
        }
    }
    return maj;
}
////////////////////////////////////////////////////////
public int majorityElement4(int[] nums) {
    int L = nums.length;
    HashMap<Integer, Integer> map = new HashMap<>();
    for(int i=0; i<L; i++) {
        if(map.containsKey(nums[i])) {
            if(map.get(nums[i]) >= L/2)
                return nums[i];
            map.replace(nums[i], map.get(nums[i])+1);
        }
        else
            map.put(nums[i], 1);
    }
    return nums[L-1];
}
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////

////////////////////////////////////////////////////////
////////////////////////////////////////////////////////

////////////////////////////////////////////////////////
////////////////////////////////////////////////////////

////////////////////////////////////////////////////////




}

/*
Given an array of size n, find the majority element.
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */
