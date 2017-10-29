package _BinarySearch;
import java.util.*;
import org.junit.Test;
public class _454_BinarySearch_4Sum_II_M {

//    Clean java solution O(n^2)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<C.length; i++) {
            for(int j=0; j<D.length; j++) {
                int sum = C[i] + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res=0;
        for(int i=0; i<A.length; i++) {
            for(int j=0; j<B.length; j++) {
                res += map.getOrDefault(-1 * (A[i]+B[j]), 0);
            }
        }

        return res;
    }

//    Time complexity:  O(n^2)
//    Space complexity: O(n^2)


//    Simple Java Solution with Explanation
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> sums = new HashMap<>();
        int count = 0;
        for(int i=0; i<A.length;i++) {
            for(int j=0;j<B.length;j++){
                int sum = A[i]+B[j];
                if(sums.containsKey(sum)) {
                    sums.put(sum, sums.get(sum)+1);
                } else {
                    sums.put(sum, 1);
                }
            }
        }
        for(int k=0; k<A.length;k++) {
            for(int z=0;z<C.length;z++){
                int sum = -(C[k]+D[z]);
                if(sums.containsKey(sum)) {
                    count+=sums.get(sum);
                }
            }
        }
        return count;
    }
//    Take the arrays A and B, and compute all the possible sums of two elements. Put the sum in the Hash map, and increase the hash map value if more than 1 pair sums to the same value.
//
//    Compute all the possible sums of the arrays C and D. If the hash map contains the opposite value of the current sum, increase the count of four elements sum to 0 by the counter in the map.


    public int fourSumCount3(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                hashMap.put(a + b, hashMap.getOrDefault(a + b, 0) + 1);
            }
        }
        int result = 0;
        for (int c : C) {
            for (int d : D) {
                result += hashMap.getOrDefault(-c - d, 0);
            }
        }
        return result;
    }

    //Java, hashmap solution
    public int fourSumCount4(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> second = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                int val = A[i]+B[j];
                first.put(val, first.getOrDefault(val,0)+1);
            }
        }
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                int val = C[i]+D[j];
                second.put(val, second.getOrDefault(val,0)+1);
            }
        }
        int count = 0;
        for(int key : first.keySet()){
            if(second.containsKey(0-key)){
                count += first.get(key)*second.get(-key);
            }
        }
        return count;
    }

    //Java HashMap O(n^2) solution
    public int fourSumCount5(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        int i,j, N =A.length, sum;
        Integer count;

        for (i=0;i<N;i++) {
            for (j=0;j<N;j++) {
                sum = A[i] + B[j];
                count = m1.get(sum);
                if (count == null)
                    m1.put(sum, 1);
                else
                    m1.put(sum, count + 1);
                sum = C[i] + D[j];
                count = m2.get(sum);
                if (count == null)
                    m2.put(sum,1);
                else
                    m2.put(sum , count + 1);
            }
        }

        int result = 0;
        for (Map.Entry<Integer , Integer> entry : m1.entrySet()) {
            int c1 = entry.getValue();
            Integer c2 = m2.get(-entry.getKey());
            if (c2 != null)
                result += c1 * c2;
        }
        return result;
    }
}

/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */