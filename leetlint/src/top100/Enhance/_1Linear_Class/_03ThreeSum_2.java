package top100.Enhance._1Linear_Class;

import java.util.*;

//Three Sum -- Method 2
public class _03ThreeSum_2 {

public List <List <Integer>>   threeSum2(int[] array) {
	List <List <Integer>>   res = new ArrayList <List <Integer>>  ();
        if(array == null || array.length <  3) {
            return res;
        }

        Arrays.sort(array);
        //Use HashSet to remove duplicates
        Set <List <Integer>>   set = new HashSet <List< Integer>>  ();

        for (int i = 0; i  < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;
            int rest = 0 - array[i];
            while (left  < right) {
                if (array[left] + array[right] == rest)  {
                	List<Integer>  list = new ArrayList <Integer> ();
                    list.add(array[i]);
                    list.add(array[left++]);
                    list.add(array[right--]);
                    if (set.add(list)) {
                        res.add(list);
                    }
                } else if (array[left] + array[right]  < rest) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
