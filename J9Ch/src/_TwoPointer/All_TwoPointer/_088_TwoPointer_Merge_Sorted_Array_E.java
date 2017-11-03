package _TwoPointer.All_TwoPointer;

import java.util.Arrays;

//  88. Merge Sorted Array
//  https://leetcode.com/problems/merge-sorted-array/description/

//http://www.lintcode.com/zh-cn/problem/merge-sorted-array/
public class _088_TwoPointer_Merge_Sorted_Array_E {
//3 line Java Solution

    public void merge(int A[], int m, int B[], int n) {
        int i=m-1, j=n-1, k=m+n-1;
        while (i>-1 && j>-1) A[k--]= (A[i]>B[j]) ? A[i--] : B[j--];
        while (j>-1)         A[k--]=B[j--];
    }


    public void merge2(int A[], int m, int B[], int n) {
        int length = m+n;
        while(n > 0) A[--length] = (m == 0 || A[m-1] < B[n-1]) ? B[--n] : A[--m];
    }

    //while(n>0)A[m+n-1]=(m==0||B[n-1]>A[m-1])?B[--n]:A[--m];

//0ms Java intuitive solution, insert from the back, keep 3 pointers

//    it is ok to use two pointers, just tracking the end of nums1 and nums2; But i feel that the three pointer solution is easier to visualize.

    public class Solution3 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // insert from the m+n-1 position at the bigger array
            // keep 3 pointers: one at the insertion point
            // one at the end of nums1; one at the end of nums2
            int insertP = m + n - 1;
            int nums1P = m - 1;
            int nums2P = n - 1;

            while (nums1P >= 0 && nums2P >= 0) {
                if (nums1[nums1P] > nums2[nums2P]) {
                    nums1[insertP--] = nums1[nums1P--];
                } else {
                    nums1[insertP--] = nums2[nums2P--];
                }
            }
            while (nums2P >= 0) {
                nums1[insertP--] = nums2[nums2P--];
            }
        }
    }
/*
Awesome job! I had the exact same thing, but I had an extra useless operation at the end. I need to pay attention to the small details. >_<

    if(mIndex < 0){
        while(nIndex >= 0){
            nums1[last--] = nums2[nIndex--];
        }
    }
 */



//Share my accepted Java solution!

    public class Solution4 {
        public void merge(int A[], int m, int B[], int n) {
            int i = m - 1, j = n - 1, k = m + n - 1;
            while(i >= 0 && j >= 0) {
                A[k--] = A[i] > B[j] ? A[i--] : B[j--];
            }
            while(j >= 0) {
                A[k--] = B[j--];
            }
        }
    }

    public class Solution5 {
        public void merge(int A[], int m, int B[], int n) {
            System.arraycopy(B, 0, A, m, n);
            Arrays.sort(A);
        }
    }


    public void merge6(int A[], int m, int B[], int n) {

        for(int i = m + n - 1; i>=0; i--)
        {
            if( m>0 && n>0)
            {
                if(B[n-1] > A[m-1])
                {
                    A[i] = B[n-1];
                    n--;
                }
                else
                {
                    A[i] = A[m-1];
                    m--;
                }
            }
            else if(m>0)
            {
                A[i] = A[m-1];
                m--;
            }
            else if(n>0)
            {
                A[i] = B[n-1];
                n--;
            }
        }

    }
    //*Java* one-pass, 4 lines, 0ms


    public void merge7(int[] nums1, int m, int[] nums2, int n) {
        int p = m+n, p1 = m-1, p2 = n-1;
        while(--p>=0) {
            if(p1<0 || (p2>=0 && nums1[p1]<nums2[p2])) nums1[p] = nums2[p2--];
            else nums1[p] = nums1[p1--];
        }
    }

    //Java easy to understand solution.

    public void merge8(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m+n-1;
        while (i>=0 && j>=0) {
            nums1[k--] = nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while (i==-1 && j>=0)
            nums1[j] = nums2[j--];
    }

//////////////////////////////////////////////////
    //jiuzhang
class Jiuzhang {
    /**
     * @param A: sorted integer array A which has m elements,
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int i = m-1, j = n-1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }
}
}
/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */

/*
合并两个排序的整数数组A和B变成一个新的数组。

 注意事项

你可以假设A具有足够的空间（A数组的大小大于或等于m+n）去添加B中的元素。

样例
给出 A = [1, 2, 3, empty, empty], B = [4, 5]

合并之后 A 将变成 [1,2,3,4,5]
 */