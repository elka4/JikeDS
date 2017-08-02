package _6_Math.power;

/*
LeetCode – Power of Four (Java)

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 */
public class Power_of_Four {
    //Java Solution 1 - Naive Iteration

    public boolean isPowerOfFour(int num) {
        while(num>0){
            if(num==1){
                return true;
            }

            if(num%4!=0){
                return false;
            }else{
                num=num/4;
            }
        }

        return false;
    }
    //Java Solution 2 - Bit Manipulation

    public boolean isPowerOfFour2(int num) {
        int count0=0;
        int count1=0;

        while(num>0){
            if((num&1)==1){
                count1++;
            }else{
                count0++;
            }

            num>>=1;
        }

        return count1==1 && (count0%2==0);
    }
    //Java Solution 3 - Math Equation

    //We can use the following formula to solve this problem without using recursion/iteration.

    //Power of Four

    public boolean isPowerOfFour3(int num) {
        if(num==0) return false;

        int pow = (int) (Math.log(num) / Math.log(4));
        if(num==Math.pow(4, pow)){
            return true;
        }else{
            return false;
        }
    }
}
