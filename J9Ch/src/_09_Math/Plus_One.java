package _09_Math;

/*
LeetCode – Plus One (Java)

Given a non-negative number represented as an array of digits, plus one to the number. The digits are stored such that the most significant digit is at the head of the list.
 */
public class Plus_One {
   // To solve this problem, we can use a flag to mark if the current digit needs to be changed.

    public int[] plusOne(int[] digits) {
        if(digits==null||digits.length==0)
            return new int[0];

        int carry = 1;
        for(int i=digits.length-1; i>=0; i--){
            int sum = digits[i]+carry;
            if(sum>=10){
                carry=1;
            }else{
                carry=0;
            }
            digits[i]=sum%10;
        }

        if(carry==1){
            int[] result = new int[digits.length+1];
            System.arraycopy(digits, 0, result, 1, digits.length);
            result[0]=1;
            return result;
        }else{
            //int[] result = new int[digits.length];
            return digits;
        }
    }
}
