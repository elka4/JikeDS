package top100.HighFreq._1Linear;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.util.*;

/**
 * Created by tianhuizhu on 6/10/17.
 */

class Calculator2{
    HashMap<Character, Integer> priority;
    Calculator2() {
        priority = new HashMap<Character, Integer>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('^', 3);
        priority.put('(', 0);
    }

    int eval(String equation){
        int num = 0;
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();

        for(int i = 0; i < equation.length(); ++i){
            char next = equation.charAt(i);
            switch (next) {
                case '(':
                    ops.add(next);
                    break;
                case ')':
                    while(ops.peek() != '(') {
                        nums.add(cal(nums.pop(), nums.pop(),  ops.pop()));
                    }
                    ops.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    if(ops.isEmpty() || priority.get(next) > priority.get(ops.peek())){
                        ops.add(next);
                    } else {
                        nums.add(cal(nums.pop(), nums.pop(), ops.pop()));
                        ops.add(next);
                    }
                    //deal with it
                    break;
                default:
                    num = num*10 + (int)(next - '0');
                    if(Character.isDigit(equation.charAt(i+1)) != false){
                        nums.add(num);
                        num = 0;
                    }
            }
        }
        return nums.pop();

    }

    int cal(int second, int first, char op){
        switch (op) {
            case '+':
                return first + second;
            case '-':
                return first - second;
        }
        return -1;
    }
}

public class _38Calculater2 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(new File("input.txt"));
        //ScriptEngine cal = (new ScriptEngineManager()).getEngineByName("JavaScript");
        Calculator2 cal = new Calculator2();
        String equation = in.nextLine();
        while(equation.equals("END") == false){
            System.out.println(cal.eval("(" + equation + ")")   );
            equation = in.nextLine();
        }
        in.close();
    }
}
