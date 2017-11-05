package _TwoPointer.TwoPointer_J;

import java.util.ArrayList;
import java.util.Arrays;

/** 58 4Sum
 * Created by tianhuizhu on 6/28/17.
 */
//
//  http://www.lintcode.com/zh-cn/problem/4sum/
public class _58_4Sum {

    //jiuzhang
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);

        for (int i = 0; i < num.length - 3; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < num.length - 2; j++) {
                if (j != i + 1 && num[j] == num[j - 1])
                    continue;

                int left = j + 1;
                int right = num.length - 1;

                while (left < right) {
                    int sum = num[i] + num[j] + num[left] + num[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        ArrayList<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(num[i]);
                        tmp.add(num[j]);
                        tmp.add(num[left]);
                        tmp.add(num[right]);
                        rst.add(tmp);
                        left++;
                        right--;
                        while (left < right && num[left] == num[left - 1]) {
                            left++;
                        }
                        while (left < right && num[right] == num[right + 1]) {
                            right--;
                        }
                    }
                }
            }
        }

        return rst;
    }

/////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
}
/*
给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。

 注意事项

四元组(a, b, c, d)中，需要满足a <= b <= c <= d

答案中不可以包含重复的四元组。

样例
例如，对于给定的整数数组S=[1, 0, -1, 0, -2, 2] 和 target=0. 满足要求的四元组集合为：

(-1, 0, 0, 1)

(-2, -1, 1, 2)

(-2, 0, 0, 2)


 */