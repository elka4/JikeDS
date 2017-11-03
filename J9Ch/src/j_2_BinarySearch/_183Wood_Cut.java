package j_2_BinarySearch; import org.junit.Test;

//Wood Cut
//  http://lintcode.com/zh-cn/problem/wood-cut/
public class _183Wood_Cut {
	/** 
	 *@param L: Given n pieces of wood with length L[i]
	 *@param k: An integer
	 *return: The maximum length of the small pieces.
	 */
	public int woodCut(int[] L, int k) {
	    int max = 0;
	    for (int i = 0; i < L.length; i++) {
	        max = Math.max(max, L[i]);
	    }
	    
// find the largest length that can cut more than k pieces of wood.
	    //Start should be 1!!!!!! NOT 0!!!!
	    int start = 1, end = max;
	    while (start + 1 < end) {
	        int mid = start + (end - start) / 2;
	        if (count(L, mid) >= k) {
	            start = mid;
	        } else {
	            end = mid;
	        }
	    }
	    
	    if (count(L, end) >= k) {
	        return end;
	    }
	    if (count(L, start) >= k) {
	        return start;
	    }
	    return 0;
	}
	
	private int count(int[] L, int length) {
	    int sum = 0;
	    for (int i = 0; i < L.length; i++) {
	        sum += L[i] / length;
	    }
	    return sum;
	}

////////////////////////////////////////////////////
public int woodCut2(int[] L, int k) {
    int max = 0;
    for (int i = 0; i < L.length; i++) {
        max = Math.max(max, L[i]);
    }

// find the largest length that can cut more than k pieces of wood.
    //Start should be 1!!!!!! NOT 0!!!!
    int start = 1, end = max;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (count(L, mid) > k) {        //count(L, mid)还挺大，说明每块木头还可再大一些
            start = mid;
        } else if (count(L, mid) < k){   //count(L, mid)太小了，说明每块木头必须再小一些
            end = mid;
        } else {                        //目标是取最大的木头，相等的时候收缩左区间
            start = mid;
        }
    }

    if (count(L, end) >= k) {
        return end;
    }
    if (count(L, start) >= k) {
        return start;
    }
    return 0;
}
}
/*
有一些原木，现在想把这些木头切割成一些长度相同的小段木头，需要得到的小段的数目至少为 k。

当然，我们希望得到的小段越长越好，你需要计算能够得到的小段木头的最大长度。

 注意事项

木头长度的单位是厘米。原木的长度都是正整数，我们要求切割得到的小段木头的长度也要求是整数。无法切出要求至少 k 段的,则返回 0 即可。

您在真实的面试中是否遇到过这个题？ Yes
样例
有3根木头[232, 124, 456], k=7, 最大长度为114.

挑战
O(n log Len), Len为 n 段原木中最大的长度
 */

/*
 * Given n pieces of wood with length L[i] (integer array). 
 * Cut them into small pieces to guarantee you could have equal or more than 
 * k pieces with the same length. What is the longest length you can get from
  the n pieces of wood? Given L & k, return the maximum length of the small pieces.

 Notice

You couldn't cut wood into float length.

Have you met this question in a real interview? Yes
Example
For L=[232, 124, 456], k=7, return 114.

Challenge 
O(n log Len), where Len is the longest length of the wood.

Tags 
Binary Search
 * */
