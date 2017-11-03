package _TwoPointer.TwoPointer_J;

import org.junit.Test;

/** 148 Sort Colors
 *
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _148_Sort_Colors {
    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else if(a[i] == 2) {
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Test
    public void test01(){
        int[] a = {1, 0, 1, 2};
        sortColors(a);
        for (int i:a ) {
            System.out.print(i + " ");
        }
    }

}

/*
给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。

我们可以使用整数 0，1 和 2 分别代表红，白，蓝。

 注意事项

不能使用代码库中的排序函数来解决这个问题。
排序需要在原数组中进行。

您在真实的面试中是否遇到过这个题？ Yes
样例
给你数组 [1, 0, 1, 2], 需要将该数组原地排序为 [0, 1, 1, 2]。
 */