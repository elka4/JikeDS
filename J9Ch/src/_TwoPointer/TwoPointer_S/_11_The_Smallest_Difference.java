package _TwoPointer.TwoPointer_S;
import java.util.*;

//  The Smallest Difference
//  http://www.lintcode.com/zh-cn/problem/the-smallest-difference/
//  LintCode Copyright Two Pointers Array Sort
public class _11_The_Smallest_Difference {
	/**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int ai = 0, bi = 0;
        int min = Integer.MAX_VALUE;
        while (ai < A.length && bi < B.length) {
            min = Math.min(min, Math.abs(A[ai] - B[bi]));
            if (A[ai] < B[bi]) {
                ai++;
            } else {
                bi++;
            }
        }
        return min;
    }
//---------------------------------------------------------------------------

}
/*
最小差

 描述
 笔记
 数据
 评测
给定两个整数数组（第一个是数组 A，第二个是数组 B），在数组 A 中取 A[i]，数组 B 中取 B[j]，A[i] 和 B[j]两者的差越小越好(|A[i] - B[j]|)。返回最小差。

您在真实的面试中是否遇到过这个题？ Yes
样例
给定数组 A = [3,4,6,7]， B = [2,3,8,9]，返回 0。

挑战
时间复杂度 O(n log n)

标签
LintCode 版权所有 两根指针 数组 排序
 */
