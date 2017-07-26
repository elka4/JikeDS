package _1Linear_Adv;


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
}
