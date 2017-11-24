package _01_Array.Random;

import java.util.Arrays;
import java.util.Random;

//Shuffle a set of numbers without duplicates.


public class Shuffle_an_Array {
    int[] original;
    int[] shuffled;
    Random r;

    public Shuffle_an_Array(int[] nums) {
        original = nums;
        shuffled = Arrays.copyOf(nums,nums.length);
        r = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        shuffled=Arrays.copyOf(original, original.length);
        return shuffled;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int len = shuffled.length;

        for(int i=0; i<len; i++){
            int si = r.nextInt(len-i);
            int temp = shuffled[i];
            shuffled[i]=shuffled[si+i];
            shuffled[si+i]=temp;
        }

        return shuffled;
    }


//------------------------------------------------------------------------------//



//------------------------------------------------------------------------------//



//------------------------------------------------------------------------------//



//------------------------------------------------------------------------------//



//------------------------------------------------------------------------------//



//------------------------------------------------------------------------------//



}
