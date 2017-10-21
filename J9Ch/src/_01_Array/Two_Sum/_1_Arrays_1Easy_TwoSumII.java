package _01_Array.Two_Sum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/two-sum-iii-data-structure-design/

public class _1_Arrays_1Easy_TwoSumII {

    //数据结构设计题最重要就是一开始选好数据结构
    //选用hashmap是为了降低查询时间

	//<number, frequency>
	private final Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	//选用

	//Maintain a list to for iteration in find,
    // to avoid iterate the map, which can be really slow
	private final List<Integer> list = new ArrayList<Integer>();

	public void add(int number) {
		list.add(number);
		Integer frequency = map.get(number);
		if(frequency == null) {
			map.put(number, 1);
		} else {
			map.put(number, frequency + 1);
		}
        System.out.println("add operation:");
        System.out.println("number: " + number);
        System.out.println("list: " + list);
        System.out.println("map: " + map);
        System.out.println("=======================");
    }

    // value = curKey + target
	public boolean find (int value) {
		for (int curKey : list) {
			int target = value - curKey;
			Integer count = map.get(target);
			//count != null就是map里存在target这个数值， target就是差值
			if (count != null) {
			    //
                //
                System.out.println("curKey :" + curKey);
                System.out.println("target :" + target);
                //两种情况，要么curKey 和 target不一样
                //要么curKey 和 target一样，而且这个数值在map里的freq大于等于2
                if (curKey != target || (curKey == target && count >= 2)) {
					return true;
				}
			}
		}
		return false;
	}

	@Test
    public void test01(){
        _1_Arrays_1Easy_TwoSumII twoSumII = new _1_Arrays_1Easy_TwoSumII();
        twoSumII.add(1);
        twoSumII.add(2);
        twoSumII.add(3);
        twoSumII.add(6);
        System.out.println(twoSumII.find(6));

    }
}
//follow up: https://leetcode.com/problems/moving-average-from-data-stream/