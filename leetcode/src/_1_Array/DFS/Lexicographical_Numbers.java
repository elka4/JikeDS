package _1_Array.DFS;
import java.util.*;

/*
LeetCode – Lexicographical Numbers (Java)

Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.


 */
public class Lexicographical_Numbers {
    public List<Integer> lexicalOrder(int n) {
        int c=0;
        int t=n;
        while(t>0){
            c++;
            t=t/10;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        char[] num = new char[c];

        helper(num, 0, n, result);

        return result;
    }

    public void helper(char[] num, int i, int max, ArrayList<Integer> result){
        if(i==num.length){
            int val = convert(num);
            if(val <=max)
                result.add(val);
            return;
        }

        if(i==0){
            for(char c='1'; c<='9'; c++){
                num[i]=c;
                helper(num, i+1, max, result);
            }
        }else{
            num[i]='a';
            helper(num, num.length, max, result);

            for(char c='0'; c<='9'; c++){
                num[i]=c;
                helper(num, i+1, max, result);
            }
        }

    }

    private int convert(char[] arr){
        int result=0;
        for(int i=0; i<arr.length; i++){
            if(arr[i]>='0'&&arr[i]<='9')
                result = result*10+arr[i]-'0';
            else
                break;
        }
        return result;
    }
}
