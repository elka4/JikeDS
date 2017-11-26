package _01_Array.Two_Pointers_3;

/**
 *
 * Created by tianhuizhu on 6/21/17.
 */


// Remove Element
public class a_27_Remove_Element {

    public int removeElement(int[] A, int elem) {
        int i = 0;
        int pointer = A.length - 1;
        while(i <= pointer){
            if(A[i] == elem){
                A[i] = A[pointer];
                pointer--;
            } else {
                i++;
            }
        }
        return pointer + 1;
    }



//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------



}

/*
Given an array and a value, remove all occurrences of that value in place and return the new length.

The order of elements can be changed, and the elements after the new length don't matter.

Have you met this question in a real interview? Yes
Example
Given an array [0,4,4,0,0,2,4,4], value=4

return 4 and front four elements of the array is [0,0,0,2]
 */
