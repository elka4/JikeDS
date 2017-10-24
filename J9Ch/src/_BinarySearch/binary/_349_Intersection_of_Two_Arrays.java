package _0BinarySearch.binary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by tzh on 1/15/17.
 */
public class _349_Intersection_of_Two_Arrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        int[]  result = new int[0];
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return result;
        }
        ArrayList arrayList = new ArrayList();
        Arrays.sort(nums1);
        for (int i : nums2) {
            ResultType resultType = helper(nums1, i);
            System.out.println(resultType);
            if (!resultType.exist) {
                continue;
            }
            arrayList.add(i);
            /*if (!arrayList.contains(i)) {
                arrayList.add(i);
            }*/

        }
        Collections.sort(arrayList);
        result = new int[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++) {
            result[i] = (Integer) arrayList.get(i);
            System.out.println(result[i]);
        }

        return result;




    }
    private class ResultType{
        private boolean exist = false;
        private int value;
        private ResultType(boolean exist, int value) {
            this.exist = exist;
            this.value = value;
        }
        public boolean getExist() {
            return exist;
        }
        public void setExist(boolean exist) {
            this.exist = exist;
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "" + exist + " " + value;
        }
    }
    private ResultType helper(int[] nums, int target) {
        int start = 0;
        int end = nums.length  - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end =  mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            return new ResultType(true, nums[start]);
        }
        if (nums[end] == target) {
            return new ResultType(true, nums[end]);
        }
        return new ResultType(false, 0);
    }

    @Test
    public  void test01() {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1,2,3};
        int[] result = intersection(nums1, nums2);
        for (int i :result) {
            System.out.println(i);
        }
    }
}
