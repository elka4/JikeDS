package _TwoPointer.TwoPointer_J;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 607 Two Sum - Data structure design
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */
//  170. Two Sum III - Data structure design
//  https://leetcode.com/problems/two-sum-iii-data-structure-design/description/
//  http://www.lintcode.com/zh-cn/problem/two-sum/
public class _607_Two_Sum_Data_structure_design {

//------------------------------------------------------------------------------///////////
    /*
    关键就是要处理重复数字，用Hashmap的value存储
     */
    public class TwoSum {

        private List<Integer> list = null;
        private Map<Integer, Integer> map = null;
        public TwoSum() {
            list = new ArrayList<Integer>();
            map = new HashMap<Integer, Integer>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            // Write your code here
            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
                list.add(number);
            }
//            map.putIfAbsent(number, map.getOrDefault(number, 0) + 1);
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            // Write your code here
            for (int i = 0; i < list.size(); i++) {
                int num1 = list.get(i);
                int num2 = value - num1;
                if ((num1 == num2 && map.get(num1) > 1) ||
                        (num1 != num2 && map.containsKey(num2)))
                    return true;
            }
            return false;
        }
    }


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
}
/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
 */