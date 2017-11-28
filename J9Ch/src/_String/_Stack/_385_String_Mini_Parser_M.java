package _String._Stack;
import java.util.*;
import org.junit.Test;

//  385. Mini Parser
//  https://leetcode.com/problems/mini-parser/description/
//
public class _385_String_Mini_Parser_M {
//------------------------------------------------------------------------------
/*An Java Iterative Solution
    This approach will just iterate through every char in the string (no recursion).

    If encounters '[', push current NestedInteger to stack and start a new one.
    If encounters ']', end current NestedInteger and pop a NestedInteger from stack to continue.
    If encounters ',', append a new number to curr NestedInteger, if this comma is not right after a brackets.
    Update index l and r, where l shall point to the start of a integer substring, while r shall points to the end+1 of substring.
    Java Code:*/
    class Solution1{

        public NestedInteger deserialize(String s) {
            if (s.isEmpty())
                return null;
            if (s.charAt(0) != '[') // ERROR: special case
                return new NestedInteger(Integer.valueOf(s));

            Stack<NestedInteger> stack = new Stack<>();
            NestedInteger curr = null;
            int l = 0; // l shall point to the start of a number substring;
            // r shall point to the end+1 of a number substring
            for (int r = 0; r < s.length(); r++) {
                char ch = s.charAt(r);
                if (ch == '[') {
                    if (curr != null) {
                        stack.push(curr);
                    }
                    curr = new NestedInteger();
                    l = r+1;
                } else if (ch == ']') {
                    String num = s.substring(l, r);
                    if (!num.isEmpty())
                        curr.add(new NestedInteger(Integer.valueOf(num)));
                    if (!stack.isEmpty()) {
                        NestedInteger pop = stack.pop();
                        pop.add(curr);
                        curr = pop;
                    }
                    l = r+1;
                } else if (ch == ',') {
                    if (s.charAt(r-1) != ']') {
                        String num = s.substring(l, r);
                        curr.add(new NestedInteger(Integer.valueOf(num)));
                    }
                    l = r+1;
                }
            }

            return curr;
        }
    }

//------------------------------------------------------------------------------
    //Straightforward Java solution with explanation and a simple implementation of NestedInteger for your ease of testing
    class Solution2{
        public NestedInteger deserialize(String s) {
            if(s == null || s.isEmpty() || s.length() == 0) return new NestedInteger();
            Stack<NestedInteger> workStack = new Stack<NestedInteger>();
            NestedInteger result = null;
            StringBuilder sb = new StringBuilder();
            int i = 0;
            //if it's just a single number, then we'll just return a nested integer with one integer
            if(s.charAt(i) != '['){
                sb.setLength(0);
                while(i < s.length() && ((Character.getNumericValue(s.charAt(i)) < 10 && Character.getNumericValue(s.charAt(i)) >= 0) || s.charAt(i) == '-')){
                    sb.append(s.charAt(i));
                    i++;
                }
                int num = Integer.parseInt(sb.toString());
                return new NestedInteger(num);
            }//all other cases, we'll return a nested integer with a list
            else{
                while (i < s.length()) {
                    if (s.charAt(i) == '[') {
                        NestedInteger ni = new NestedInteger();
                        // we'll put this one into its last one if there's one on the workStack
                        if (!workStack.isEmpty()) {
                            NestedInteger lastNi = workStack.pop();
                            lastNi.add(ni);
                            workStack.push(lastNi);// then push it back
                        }
                        workStack.push(ni);
                        i++;
                    } else if (s.charAt(i) == ',') {
                        i++;
                    } else if (s.charAt(i) == ']') {
                        NestedInteger completedNi = workStack.pop();
                        result = completedNi;
                        i++;
                    } else {
                        // then it must be a number
                        sb.setLength(0);
                        while (i < s.length()
                                && ((Character.getNumericValue(s.charAt(i)) < 10 && Character
                                .getNumericValue(s.charAt(i)) >= 0) || s.charAt(i) == '-')) {
                            sb.append(s.charAt(i));
                            i++;
                        }
                        int num = Integer.parseInt(sb.toString());
                        NestedInteger ni = null;
                        if (!workStack.isEmpty())
                            ni = workStack.pop();
                        else
                            ni = new NestedInteger();
                        // case 1: if this one contains one integer
                        if (ni.isInteger()) {
                            // we'll add it to this ni
                            ni.add(new NestedInteger(num));
                        }
                        // case 2: if this one contains a nested integer
                        else if (ni.getList() != null && ni.getList().size() != 0) {
                            // we'll get the last nested integer and add this one to it
                            ni.add(new NestedInteger(num));
                        } else {
                            // case 3: if this is an empty nested integer
                            if(i > 0) ni.add(new NestedInteger(num));
                            else ni.setInteger(num);
                        }
                        workStack.push(ni);
                        if (i == s.length())
                            return ni;// this is for test cases like this: "324", there's no '[' or ']'
                    }
                }
            }
            return result;
        }
    }



//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
}
/*
Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:

String is non-empty.
String does not contain white spaces.
String contains only digits 0-9, [, - ,, ].
Example 1:

Given s = "324",

You should return a NestedInteger object which contains a single integer 324.
Example 2:

Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
Seen this question in a real interview before?   Yes  No
Companies
Airbnb
Related Topics
String Stack
Similar Questions
Flatten Nested List Iterator Ternary Expression Parser Remove Comments
Java
 */