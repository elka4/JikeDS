package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;

//  350. Intersection of Two Arrays II
//  https://leetcode.com/problems/intersection-of-two-arrays-ii/description/

//  http://www.lintcode.com/zh-cn/problem/intersection-of-two-arrays-ii/
public class _350_TwoPointer_Intersection_of_Two_Arrays_II_E {
    //Use ArrayList to dynamic increase size
    class Solution1 {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            List<Integer> res = new ArrayList<>();

            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    res.add(nums1[i]);
                    i++;
                    j++;
                }
            }

            int index = 0;
            int[] ans = new int[res.size()];

            for (int num : res) {
                ans[index++] = num;
            }

            return ans;

        }
    }

//        Given the range of the test case is not that massive, I used counting sort instead of HashMap. Beats 96%
//        https://discuss.leetcode.com/topic/107558/optimal-2ms-java-solution-beats-96
    class Solution2{

        public int[] intersect(int[] nums1, int[] nums2) {
            //skip a stupid corner case:)
            if(nums1.length>0&&nums1[0]==Integer.MIN_VALUE) return new int[]{1,Integer.MIN_VALUE};

            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int i = 0; i < nums1.length; i++) {
                min = Math.min(min, nums1[i]);
                max = Math.max(max, nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                min = Math.min(min, nums2[i]);
                max = Math.max(max, nums2[i]);
            }
            int[] count = new int[max-min+1];
            for (int i = 0; i < nums1.length; i++) {
                count[nums1[i]-min]++;
            }
            List<Integer> intersect = new ArrayList<Integer>();
            for (int i = 0; i < nums2.length; i++) {
                if(count[nums2[i]-min]>0) {
                    count[nums2[i]-min]--;
                    intersect.add(nums2[i]);
                }
            }
            return result(intersect);
        }
        public int[] result(List<Integer> intersect){
            int i = 0;
            int[] result = new int[intersect.size()];
            for (int num : intersect) {
                result[i++] = num;
            }
            return result;
        }
    }


    //AC solution using Java HashMap
    //  https://discuss.leetcode.com/topic/45920/ac-solution-using-java-hashmap
    public class Solution3 {
        public int[] intersect(int[] nums1, int[] nums2) {
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            ArrayList<Integer> result = new ArrayList<Integer>();
            for(int i = 0; i < nums1.length; i++)
            {
                if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
                else map.put(nums1[i], 1);
            }

            for(int i = 0; i < nums2.length; i++)
            {
                if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
                {
                    result.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i])-1);
                }
            }

            int[] r = new int[result.size()];
            for(int i = 0; i < result.size(); i++)
            {
                r[i] = result.get(i);
            }

            return r;
        }
    }

    public class Solution4 {
        public int[] intersect(int[] nums1, int[] nums2) {
            List<Integer> res = new ArrayList<Integer>();
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            for(int i = 0, j = 0; i< nums1.length && j<nums2.length;){
                if(nums1[i]<nums2[j]){
                    i++;
                }
                else if(nums1[i] == nums2[j]){
                    res.add(nums1[i]);
                    i++;
                    j++;
                }
                else{
                    j++;
                }
            }
            int[] result = new int[res.size()];
            for(int i = 0; i<res.size();i++){
                result[i] = res.get(i);
            }
            return result;
        }
    }

//    using stream

    public class Solution5 {
        public int[] intersect(int[] nums1, int[] nums2) {

            Map<Integer, Integer> map = new HashMap();
            List<Integer> list = new ArrayList();
            for (int n : nums1) {
                map.put(n, map.getOrDefault(n, 0) + 1);
            }

            for (int n : nums2) {
                Integer intersection = map.get(n);

                if(intersection != null && intersection > 0) {
                    list.add(n);
                    map.put(n, map.get(n) - 1);
                }
            }

            return list.stream().mapToInt(i->i).toArray();
        }
    }

/*    Using a List.
1.) Add Elements of nums1 array in a List l1.
2.) check if elements of nums2 array are in list l1.
            3.) if l1 contains an element of nums2, add that element in list2(result list) and remove that element from l1.
            4.) Convert this list l2 to array*/

    public class Solution6 {
        public int[] intersect(int[] nums1, int[] nums2) {
            List<Integer> l1 =new ArrayList<Integer>();
            List<Integer> l2 =new ArrayList<Integer>();
            for(int i:nums1){
                l1.add(i);
            }
            for(int j:nums2){
                if(l1.contains(j)){
                    l2.add(j);
                    l1.remove((Integer)j);
                }
            }
            int[] a=new int[l2.size()];
            int count=0;
            for(int k:l2){
                a[count++]=k;
            }
            return a;
        }
    }


//    Use ArrayList and two poiters .

    public class Solution7 {
        public int[] intersect(int[] nums1, int[] nums2) {
            ArrayList<Integer> arraylist = new ArrayList<Integer>();
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int index1 = 0;
            int index2 = 0;
            while (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] == nums2[index2]) {
                    arraylist.add(nums1[index1]);
                    index1++;
                    index2++;
                } else if (nums1[index1] < nums2[index2]) {
                    index1++;
                } else {
                    index2++;
                }
            }
            int answer[] = new int[arraylist.size()];
            for (int i = 0; i < arraylist.size(); i++) {
                answer[i] = arraylist.get(i);
            }
            return answer;
        }
    }
//-------------------------------------------------------------------------/
/*Great solution without sorting two arrays, thank you! The solution using java hashmap has O(m + n) time complexity and O(min(m, n)) space complexity. In theory, they are faster than sorted arrays in terms of time complexity, at the expense of extra space. However, tests show that the 1st solution(85.83%) > 2nd solution(67.33%) > your solution(48.29%). The difference between theory and practice may lie in the fact that the frequent get/put operations on HashMap and final for-loop to convert a List<Integer> to an int array.

    Here are two other solutions based on sorted arrays:
            (1) linear search : O(n*log n + m*log m) time complexity.*/

    public int[] intersect8(int[] nums1, int[] nums2) {
        // Write your code here
        int[] res = nums1.length < nums2.length ? new int[nums1.length] : new int[nums2.length];
        if (res.length == 0) {
            return res;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int pt1 = 0;
        int pt2 = 0;
        int pt = 0;
        while(pt1 < nums1.length && pt2 < nums2.length) {
            int n1 = nums1[pt1];
            int n2 = nums2[pt2];
            if (n1 == n2) {
                res[pt++] = n1;
                pt1++;
                pt2++;
            } else if (n1 > n2) {
                pt2++;
            } else {
                pt1++;
            }
        }
        return Arrays.copyOfRange(res, 0, pt);
    }

/*    (2) binary search : O((m+n)log n)
    STEP 1: determine the frequency of one number in "nums1", f1
    STEP 2: determine the frequency of the same number in "nums2", f2
    STEP 3: add this number in result array for min(f1, f2) times.*/

    public int[] intersect9(int[] nums1, int[] nums2) {
        // Write your code here
        if (nums1.length > nums2.length) {
            return intersect9(nums2, nums1);
        }
        int[] res = new int[nums1.length];
        if (res.length == 0) {
            return res;
        }

        // binary search version
        int i = 0;
        int j = 0;
        int freq1, freq2, freq, num, lower, upper;
        int[] bound;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (j < nums2.length) {
            num = nums2[j];
            // determine the frequency of the number in "nums1"
            bound = searchRange(num, nums1);
            upper = bound[1];
            lower = bound[0];
            if (upper != -1 && lower != -1) {
                freq1 = upper - lower + 1;
                // calculate the frequency of the number in "nums2"
                for (freq2 = 1; j < nums2.length - 1 && nums2[j] == nums2[j + 1]; j++) freq2++;
                // add the number multiple time to the result
                freq = (freq1 > freq2) ? freq2 : freq1;
                for (int k = 0; k < freq; k++) {
                    res[i++] = num;
                }
            }
            j++;
        }
        return Arrays.copyOfRange(res, 0, i);
    }

    private int[] searchRange(int target, int[] a) {
        int[] bound = new int[]{-1, -1};

        if (a.length == 0) return bound;
        // search lower bound
        int low = 0;
        int high = a.length -1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                high = mid;
            } else if (a[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (a[low] == target) {
            bound[0] = low;
        } else if (a[high] == target) {
            bound[0] = high;
        } else {
            return bound;
        }
        // search upper bound
        low = 0;
        high = a.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                low = mid;
            } else if (a[mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (a[high] == target) {
            bound[1] = high;
        } else if (a[low] == target) {
            bound[1] = low;
        }
        return bound;
    }
//-------------------------------------------------------------------------/
//    @VanillaCoke yes,we are same,as I just use ArrayList:

    public class Solution10 {
        public int[] intersect(int[] nums1, int[] nums2) {
            List<Integer> list=new ArrayList<>();
            List<Integer> relt=new ArrayList<>();
            for(Integer i:nums1) list.add(i);
            for(Integer i:nums2){
                if(list.contains(i)){
                    relt.add(i);
                    list.remove(list.indexOf(i));
                }
            }
            int[] re=new int[relt.size()];
            for(int i=0;i<relt.size();i++){
                re[i]=relt.get(i);
            }
            return re;
        }
    }

    //4ms java solution
    class Solution11 {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int pnt1 = 0;
            int pnt2 = 0;
            ArrayList<Integer> myList = new ArrayList<Integer>();
            while((pnt1 < nums1.length) &&(pnt2< nums2.length)){
                if(nums1[pnt1]<nums2[pnt2]){
                    pnt1++;
                }
                else{
                    if(nums1[pnt1]>nums2[pnt2]){
                        pnt2++;
                    }
                    else{
                        myList.add(nums1[pnt1]);
                        pnt1++;
                        pnt2++;
                    }
                }
            }
            int[] res = new int[myList.size()];
            for(int i = 0; i<res.length; i++){
                res[i] = (Integer)myList.get(i);
            }
            return res;
        }
    }
    //-------------------------------------------------------------------------/
    //jiuzhang
public class Jiuzhang {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums1.length; ++i) {
            if (map.containsKey(nums1[i]))
                map.put(nums1[i], map.get(nums1[i]) + 1);
            else
                map.put(nums1[i], 1);
        }

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 0; i < nums2.length; ++i)
            if (map.containsKey(nums2[i]) &&
                    map.get(nums2[i]) > 0) {
                results.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }

        int result[] = new int[results.size()];
        for(int i = 0; i < results.size(); ++i)
            result[i] = results.get(i);

        return result;
    }
}

}
/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

/*
两数组的交 II

 描述
 笔记
 数据
 评测
计算两个数组的交

 注意事项

每个元素出现次数得和在数组里一样
答案可以以任意顺序给出

样例
nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
 */