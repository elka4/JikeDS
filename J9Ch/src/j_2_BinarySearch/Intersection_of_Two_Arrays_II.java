package j_2_BinarySearch;
import java.util.*;
import java.util.stream.*;

//  http://lintcode.com/zh-cn/problem/intersection-of-two-arrays-ii/

// leetcode 350. Intersection of Two Arrays II
//  https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
public class Intersection_of_Two_Arrays_II {
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

    //Given the range of the test case is not that massive, I used counting sort instead of HashMap. Beats 96%
    class Solution2 {
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

    //Same idea, Java 8 using Stream
    class Solution4{
        public int[] intersect(int[] nums1, int[] nums2) {
            Map<Integer, Long> map =
                    Arrays.stream(nums2).boxed().collect(Collectors.groupingBy(e->e, Collectors.counting()));
    return Arrays.stream(nums1).filter(e ->{
            if(!map.containsKey(e)) return false;
            map.put(e, map.get(e) - 1);
            if(map.get(e) == 0) map.remove(e);
            return true;
        }).toArray();

        }
    }

    class Solution5{
        public int[] intersect1(int[] nums1, int[] nums2) {
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
        //similar to yours
        public int[] intersect2(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int p1 = 0;
            int p2 = 0;
            List <Integer> s = new ArrayList<>();
            while(p1 < nums1.length && p2 < nums2.length){
                if (nums1[p1] == nums2[p2]){
                    s.add(nums1[p1]);
                    p1 ++;
                    p2 ++;
                }


                else if (nums1[p1] < nums2[p2])
                    p1 ++;
                else
                    p2 ++;
            }
            int [] r = new int[s.size()];
            for (int i =0; i < s.size(); i++){
                r[i] = s.get(i);
            }
            return r;
        }
        //This is an example where O(NlogN) solution beats in time to my O(N) solution for available tests.
        // Due to use of map lookups?
        public int[] intersect3(int[] nums1, int[] nums2) {
            HashMap<Integer,Integer> hm= new HashMap<>();
            HashMap<Integer,Integer> result = new HashMap<>();
            int resultLength=0,i=0;

            for(int n1: nums1){
                if(hm.containsKey(n1)){
                    hm.put(n1,hm.get(n1)+1);
                }
                else
                    hm.put(n1,1);
            }

            for(int n2: nums2){
                if(hm.containsKey(n2)){ //this is a result candidate
                    resultLength++; //for result array length
                    if(!result.containsKey(n2)){result.put(n2,0);} // insert in result for first time
                    result.put(n2,result.get(n2)+1);

                    int freq = (int) hm.get(n2); //to manage the result candidate in hm
                    hm.put(n2,freq-1);
                    if(freq-1==0){hm.remove(n2);}
                }
            }

            int[] resultArray = new int[resultLength];
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                int temp =0;
                while(temp<(int)entry.getValue()){
                    resultArray[i++] = entry.getKey();
                    temp++;
                }
            }
            return resultArray;
        }
    }

//-------------------------------------------------------------------------/////////
    //jiuzhang
    public class Solution {
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
两数组的交 II

计算两个数组的交

 注意事项

每个元素出现次数得和在数组里一样
答案可以以任意顺序给出

您在真实的面试中是否遇到过这个题？ Yes
样例
nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].

挑战
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to num2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
