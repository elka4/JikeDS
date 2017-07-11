package J_2_Binary_Search.Optional_7.all;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
460
 K Closest Numbers In Sorted Array
 * Created by tianhuizhu on 6/28/17.
 */
public class _460_K_Closest_Numbers_In_Sorted_Array_my {

    class type {
        int val;
        int dif;
        type(int val, int dif){
            this.val = val;
            this.dif = dif;
        }
    }
    class comp implements Comparator<type>{
        @Override
        public int compare(type a, type b){
            if(a == b){
                return 0;
            }
            if(a.dif == b.dif){
                return a.val - b.val;
            }
             return a.dif - b.dif;
        }

    }

    public int[] kClosestNumbers(int[] A, int target, int k) {
        // Write your code here
        PriorityQueue<type> stack = new PriorityQueue<type>(new comp());
        //Stack<type> stack = new Stack<>();
        for(int i = 0; i < A.length; i++){
            int diff = Math.abs(target - A[i]);
            stack.offer(new type(A[i], diff));
        }
        int[] result = new int[k];
        for(int i = 0 ; i < k; i++){
            result[i] = stack.poll().val;
        }
        return result;
    }

    //Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].

    //Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].
    @Test
    public void test01(){

        int[] input = new int[]{1,2,3};
        int[] result = kClosestNumbers(input, 2,3);
        for (int i:result
                ) {
            System.out.println(i);
        }
    }
    @Test
    public void test02(){

        int[] input = new int[]{1, 4, 6, 8};
        int[] result = kClosestNumbers(input, 3,3);
        for (int i:result
                ) {
            System.out.println(i);
        }
    }

}
