package _1Linear_Adv;
import org.junit.Test;
//用本题学习找第二大，第三大
//https://codereview.stackexchange.com/questions/109214/maximum-product-of-3-integers-in-an-int-array
public class _2HighestProductOf3 {
    // Assume input array is of at least length 3.

    public int max_prod_three(int[] A){

        int len = A.length;

        // Base case
        if (len == 3) return A[0]*A[1]*A[2];

        int max = A[0], min = A[0], max_index = 0, min_index = 0;

        for (int i = 0; i < len; i++) {

            if (A[i] > max) {

                max = A[i];
                max_index = i;
            }
            else if (A[i] < min) {

                min = A[i];
                min_index = i;
            }
        }

        int max_sec = min, max_third = min , min_sec = max;

        for (int i = 0; i < len; i++) {

            if (i == max_index || i == min_index) continue;

            if (A[i] > max_sec) {

                max_third = max_sec;
                max_sec = A[i];
            }
            else if (A[i] > max_third) {
                max_third = A[i];
            }

            if (A[i] < min_sec) min_sec = A[i];

        }

        int prod_one = max * max_sec * max_third ;
        int prod_two = min * min_sec * max ;

        if (prod_one > prod_two) return prod_one ;
        return prod_two;
    }

    @Test
    public void test01(){
        int[] nums = {1,10,-5,1,-100};//5000
        System.out.println(max_prod_three(nums));
    }

////////////////////////////////////////////////////////////
    public int max_prod_three2(int[] A){
        int[] maxThree = getMaxThree(A);
        int[] minTwo = getMinTwo(A);

        return Math.max(maxThree[0] * maxThree[1] * maxThree[2],
                maxThree[0] * minTwo[0] * minTwo[1]);
    }
    //The helper methods could be something like the following:

    private int[] getMaxThree(int[] arr){
        int[] result = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };
        int len = arr.length;

        for(int i = 0; i < len; i++){
            if(arr[i] >= result[0]){
                result[2] = result[1];
                result[1] = result[0];
                result[0] = arr[i];
            } else if(arr[i] >= result[1]){
                result[2] = result[1];
                result[1] = arr[i];
            } else if(arr[i] >= result[2]){
                result[2] = arr[i];
            }
        }

        return result;
    }

    private int[] getMinTwo(int[] arr){
        int[] result = { Integer.MAX_VALUE, Integer.MAX_VALUE };
        int len = arr.length;

        for(int i = 0; i < len; i++){
            if(arr[i] <= result[0]){
                result[1] = result[0];
                result[0] = arr[i];
            } else if(arr[i] <= result[1]){
                result[1] = arr[i];
            }
        }

        return result;
    }

    @Test
    public void test02(){
        int[] nums = {1,10,-5,1,-100};//5000
        System.out.println(max_prod_three2(nums));
    }

////////////////////////////////////////////////////////////

    public int max_prod_three3(int[] A){
        int maxOne = Integer.MIN_VALUE,
                maxTwo = Integer.MIN_VALUE,
                maxThree = Integer.MIN_VALUE,
                minOne = Integer.MAX_VALUE,
                minTwo = Integer.MAX_VALUE;
        int len = A.length;

        for(int i = 0; i < len; i++){
            if(A[i] >= maxOne){
                maxThree = maxTwo;
                maxTwo = maxOne;
                maxOne = A[i];
            } else if(A[i] >= maxTwo){
                maxThree = maxTwo;
                maxTwo = A[i];
            } else if(A[i] >= maxThree){
                maxThree = A[i];
            }

            if(A[i] <= minOne){
                minTwo = minOne;
                minOne = A[i];
            } else if(A[i] <= minTwo){
                minTwo = A[i];
            }
        }

        return Math.max(maxOne * maxTwo * maxThree,
                maxOne * minOne * minTwo);
    }

    @Test
    public void test03(){
        int[] nums = {1,10,-5,1,-100};//5000
        System.out.println(max_prod_three3(nums));
    }

////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////


}
