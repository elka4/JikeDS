package _09_Math;

/*
LeetCode – Add Digits (Java)

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 */
public class Add_Digits {
    //Java Solution 1 - Recusion

    public int addDigits(int num) {
        int sum=0;

        String s = String.valueOf(num);
        for(int i=0; i<s.length(); i++){
            sum = sum + (s.charAt(i)-'0');
        }

        if(sum < 10){
            return sum;
        }else{
            return addDigits(sum);
        }
    }
    //Java Solution 2 - Math

    public int addDigits2(int num) {
        return num - 9*((num-1)/9);
    }
}
