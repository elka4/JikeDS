package J_7_Two_Pointers.all;

import java.util.HashMap;

/** 56 Two Sum
 * Created by tianhuizhu on 6/28/17.
 */
public class _56_Two_Sum {

    public class Solution {
        /*
         * @param numbers : An array of Integer
         * @param target : target = numbers[index1] + numbers[index2]
         * @return : [index1 + 1, index2 + 1] (index1 < index2)
             numbers=[2, 7, 11, 15],  target=9
             return [1, 2]
         */
        public int[] twoSum(int[] numbers, int target) {
            HashMap<Integer,Integer> map = new HashMap<>();

            for (int i = 0; i < numbers.length; i++) {
                if (map.get(numbers[i]) != null) {
                    int[] result = {map.get(numbers[i]) + 1, i + 1};
                    return result;
                }
                map.put(target - numbers[i], i);
            }

            int[] result = {};
            return result;
        }
    }

}
