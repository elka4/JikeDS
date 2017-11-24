package _TwoPointer.TwoPointer_S;
import java.util.*;

//
//
//  http://www.lintcode.com/zh-cn/problem/nuts-bolts-problem/
public class _06_Nuts_Bolts_Problem {
//-------------------------------------------------------------------------///
	/**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null) return;
        if (nuts.length != bolts.length) return;

        qsort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare,
                       int l, int u) {
        if (l >= u) return;
        // find the partition index for nuts with bolts[l]
        int part_inx = partition(nuts, bolts[l], compare, l, u);
        // partition bolts with nuts[part_inx]
        partition(bolts, nuts[part_inx], compare, l, u);
        // qsort recursively
        qsort(nuts, bolts, compare, l, part_inx - 1);
        qsort(nuts, bolts, compare, part_inx + 1, u);
    }
    
    private int partition(String[] str, String pivot, NBComparator compare,
                          int l, int u) {
        for (int i = l; i <= u; i++) {
            if (compare.cmp(str[i], pivot) == 0 || 
                compare.cmp(pivot, str[i]) == 0) {
                swap(str, i, l);
                break;
            }
        }
        String now = str[l];
        int left = l; 
        int right = u;
        while (left < right) {
            while (left < right && 
            (compare.cmp(str[right], pivot) == -1 || 
            compare.cmp(pivot, str[right]) == 1)) {
                right--;
            }
            str[left] = str[right];
            
            while (left < right && 
            (compare.cmp(str[left], pivot) == 1 || 
            compare.cmp(pivot, str[left]) == -1)) {
                left++;
            }
            str[right] = str[left];
        }
        str[left] = now;

        return left;
    }

    private void swap(String[] str, int l, int r) {
        String temp = str[l];
        str[l] = str[r];
        str[r] = temp;
    }

    /*https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/73103
    题解
首先结合例子读懂题意，本题为 nuts 和 bolts 的配对问题，但是需要根据题目所提供的比较函数，且 nuts 与 nuts 之间的元素无法直接比较，compare 仅能在 nuts 与 bolts 之间进行。首先我们考虑若没有比较函数的限制，那么我们可以分别对 nuts 和 bolts 进行排序，由于是一一配对，故排完序后即完成配对。那么在只能通过比较对方元素得知相对大小时怎么完成排序呢？

我们容易通过以一组元素作为参考进行遍历获得两两相等的元素，这样一来在最坏情况下时间复杂度为 O(n2)O(n^2)O(n2), 相当于冒泡排序。根据排序算法理论可知基于比较的排序算法最好的时间复杂度为 O(nlogn)O(n \log n)O(nlogn), 也就是说这道题应该是可以进一步优化。回忆一些基于比较的排序算法，能达到 O(nlogn)O(n \log n)O(nlogn) 时间复杂度的有堆排、归并排序和快速排序，由于这里只能通过比较得到相对大小的关系，故可以联想到快速排序。

快速排序的核心即为定基准，划分区间。由于这里只能以对方的元素作为基准，故一趟划分区间后仅能得到某一方基准元素排序后的位置，那通过引入 O(n)O(n)O(n) 的额外空间来对已处理的基准元素进行标记如何呢？这种方法实现起来较为困难，因为只能对一方的元素划分区间，而对方的元素无法划分区间进而导致递归无法正常进行。

山穷水尽疑无路，柳暗花明又一村。由于只能通过对方进行比较，故需要相互配合进行 partition 操作(这个点确实难以想到)。核心在于：首先使用 nuts 中的某一个元素作为基准对 bolts 进行 partition 操作，随后将 bolts 中得到的基准元素作为基准对 nuts 进行 partition 操作。


源码分析
难以理解的可能在partition部分，不仅需要使用compare.cmp(alist[i], pivot), 同时也需要使用compare.cmp(pivot, alist[i]), 否则答案有误。第二个在于alist[i] == pivot时，需要首先将其和alist[l]交换，因为i是从l+1开始处理的，将alist[l]换过来后可继续和 pivot 进行比较。在 while 循环退出后在将当前遍历到的小于 pivot 的元素 alist[m] 和 alist[l] 交换，此时基准元素正确归位。对这一块不是很清楚的举个例子就明白了。
复杂度分析
快排的思路，时间复杂度为 O(2nlogn)O(2n \log n)O(2nlogn), 使用了一些临时变量，空间复杂度 O(1)O(1)O(1).


     */
//------------------------------------------------------------------------------



    /*
    Analysis

螺帽螺母问题脱胎于排序问题，这里的特别之处在于需要通过两个array进行对应的排序。这就需要利用一个array中的元素对另一个array进行partition，并反过来重复这一个过程，最终让两个array都满足comparator所定义的相同顺序。
这里要注意的是partition的变式，因为pivot并非来源当前array，只能通过一方元素作为基准，对另一个array进行partition。+

核心在于：首先使用 nuts 中的某一个元素作为基准对 bolts 进行 partition 操作，随后将 bolts 中得到的基准元素作为基准对 nuts 进行 partition 操作。
     */
    /**
     * public class NBCompare {
     *     public int cmp(String a, String b);
     * }
     * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
     * if "a" is bigger than "b", it will return 1, else if they are equal,
     * it will return 0, else if "a" is smaller than "b", it will return -1.
     * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
     */
    public class Solution {
        /**
         * @param nuts: an array of integers
         * @param bolts: an array of integers
         * @param compare: a instance of Comparator
         * @return: nothing
         */
        public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
            if (nuts == null || bolts == null) {
                return;
            }
            if (nuts.length != bolts.length) {
                return;
            }

            int totalLength = nuts.length;
            qsort(nuts, bolts, 0, totalLength - 1, compare);
        }

        public void qsort(String[] nuts, String[] bolts, int l, int r, NBComparator compare) {
            if (l >= r) {
                return;
            }
            // Find partition index for nuts, with bolts[l]
            int partIndex = partition(nuts, bolts[l], l, r, compare);
            // Partition bolts, with nuts[partIndex]
            partition(bolts, nuts[partIndex], l, r, compare);

            // quick sort recursively
            qsort(nuts, bolts, l, partIndex - 1, compare);
            qsort(nuts, bolts, partIndex + 1, r, compare);
        }

        public int partition(String[] arr, String pivot, int l, int r, NBComparator compare) {
            // pivot is from another array
            int m = l;
            for (int i = l + 1; i <= r; i++) {
                if (compare.cmp(arr[i], pivot) == -1 || compare.cmp(pivot, arr[i]) == 1) {
                    m++;
                    swap(arr, i, m);
                } else if (compare.cmp(arr[i], pivot) == 0 || compare.cmp(pivot, arr[i]) == 0) {
                    swap(arr, i, l);
                    i--;
                }
            }
            swap(arr, m, l);

            return m;
        }

        public void swap(String[] arr, int l, int r) {
            String temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
    }
//------------------------------------------------------------------------------

}
/*
Nuts 和 Bolts 的问题

 描述
 笔记
 数据
 评测
给定一组 n 个不同大小的 nuts 和 n 个不同大小的 bolts。nuts 和 bolts 一一匹配。 不允许将 nut 之间互相比较，也不允许将 bolt 之间互相比较。也就是说，只许将 nut 与 bolt 进行比较， 或将 bolt 与 nut 进行比较。请比较 nut 与 bolt 的大小。

样例
给出 nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC']
你的程序应该找出bolts和nuts的匹配。
一组可能的返回结果是：
nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG']
我们将给你一个匹配的比较函数，如果我们给你另外的比较函数，可能返回的结果是：
nuts = ['ab','bc','dd','gg'], bolts = ['BC','AB','DD','GG']

因此的结果完全取决于比较函数，而不是字符串本身。
因为你必须使用比较函数来进行排序。
各自的排序当中nuts和bolts的顺序是无关紧要的，只要他们一一匹配就可以。

标签
排序 快速排序
 */

/*
Nuts & Bolts Problem

 Description
 Notes
 Testcase
 Judge
Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts. Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller.

We will give you a compare function to compare nut with bolt.

Example
Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].

Your code should find the matching bolts and nuts.

one of the possible return:

nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].

we will tell you the match compare function. If we give you another compare function.

the possible return is the following:

nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].

So you must use the compare function that we give to do the sorting.

The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.

Tags
Related Problems

 */