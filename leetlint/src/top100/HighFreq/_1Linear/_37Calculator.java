package top100.HighFreq._1Linear;

import java.io.*;
import java.util.*;

class Calculator{

    int eval(String equation){
        int num = 0;
        int result = -1;
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < equation.length(); ++i){
            char next = equation.charAt(i);
            switch (next){
                case '+':
                case '-':
                    nums.add(num);
                    num = 0;
                    if (nums.size() > 1){
                        nums.add(cal(nums.pop(), nums.pop(), ops.pop()));
                    }
                    ops.add(next);
                    //deal with it
                    break;
                default: //deal with number
                    num = num*10 + (int)(next - '0');
            }
        }
        nums.add(num);
        while(nums.size() > 1) {
            nums.add(cal(nums.pop(), nums.pop(), ops.pop()));
        }
        return nums.pop();
    }

    int cal(int second, int first, char op){
        switch (op) {
            case '+':
                return first + second;
            case '_':
                return first - second;
        }
        return -1;
    }
}

public class _37Calculator {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new File("input.txt"));
        Calculator cal = new Calculator();
        String equation = in.nextLine();

        //read input process
        while(equation.equals("END") == false){
            System.out.println(cal.eval(equation));
            equation = in.nextLine();
        }
        in.close();
    }
}
/*
input
1+2+3-4
11+12
12
END
 */
