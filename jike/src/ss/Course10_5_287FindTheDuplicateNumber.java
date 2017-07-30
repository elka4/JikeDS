package ss;

import org.junit.Test;

import java.util.HashSet;

/**
 * 寻找重复元素
 */
public class Course10_5_287FindTheDuplicateNumber {
    /**
     * 暴力求解法
     */
    public int bruteForce(int[] nums){
        if(nums==null||nums.length==0){
            return 0;
        }else{
            int n=nums.length;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    if(nums[i]==nums[j]){
                        return nums[i];
                    }
                }
            }
            return 0;
        }
    }

///////////////////////////////////////////////////////

    /**
     * 利用哈希表
     */
    public int hashFunction(int[] nums){
        if(nums==null||nums.length==0){
            return 0;
        }else{
            HashSet<Integer> hashSet=new HashSet<Integer>();
            for(int i:nums){
                if(!hashSet.contains(i)){
                    hashSet.add(i);
                }else{
                    return i;
                }
            }
            return 0;
        }
    }

///////////////////////////////////////////////////////

    /**
     * 线性算法+常数空间
     * 借用“链表环”的思想解决“数组环”
     */
    public int findDuplicate(int[] nums){
        if(nums==null||nums.length==0){
            return 0;
        }else{
            int n=nums.length;
            int fast=n-1;
            int slow=n-1;
            while(true){
                slow=nums[slow]-1;
                fast=nums[fast]-1;
                fast=nums[fast]-1;
                if(fast==slow){
                    break;
                }
            }
            slow=n-1;
            while(slow!=fast){
                slow=nums[slow]-1;
                fast=nums[fast]-1;
            }
            return slow+1;
        }
    }


///////////////////////////////////////////////////////

    @Test
    public void test(){
        int[] nums1={2,7,1,3,5,3,4,6};
        System.out.println(findDuplicate(nums1));
        int[] nums2={4,1,2,3,7,5,5,6};
        System.out.println(findDuplicate(nums2));
    }
}
