package com.java2novice.ds.stack;
/*Write a program to find out delimiter matching using stack.

One of the common use of the stacks is to parse certain kinds of
 expressions or string text. Write a program that verifies the delimiters
  in a line of text or expression typed by the user. In this case we will
   take an arthimetic expression like a*(b+c)+[c/(a-b)]. 
   We need to validate the expression such that the opening and closing
    brackets are proper or not.

a*(b+c) //This expression is right

b/[a*(b+c)] //This expression is right

{a*(b+c]} //This expression is wrong
*/
public class _5MyDelimiterMatching {
	 
    public static void main(String a[]){
        _5MyDelimiterMatching mdm = new _5MyDelimiterMatching();
        String expression = "{(a+b)*(c+d)}";
        boolean result = mdm.isDelimiterMatching(expression);
        System.out.println(expression +" == "+result);
         
        expression = "{(a+b)+[x*(c+d)]}";
        result = mdm.isDelimiterMatching(expression);
        System.out.println(expression +" == "+result);
         
        expression = "{(a+b)+[x*(c+d)}}";
        result = mdm.isDelimiterMatching(expression);
        System.out.println(expression +" == "+result);
    }
 
    public boolean isDelimiterMatching(String inputExpr) {
         
        int stackSize = inputExpr.length();
        StackImpl_2 theStack = new StackImpl_2(stackSize);
        for (int j = 0; j < inputExpr.length(); j++) {
            char ch = inputExpr.charAt(j);
            switch (ch) {
            case '{':
            case '[':
            case '(':
                    theStack.push(ch);
                    break;
            case '}':
            case ']':
            case ')':
                    if (!theStack.isStackEmpty()) {
                        char stackContent = theStack.pop();
                        if ((ch == '}' && stackContent != '{')
                                || (ch == ']' && stackContent != '[')
                                || (ch == ')' && stackContent != '(')){
                            System.out.println("Mismatch found: " + ch + " at " + j);
                            return false;
                        }
                    } else {
                        System.out.println("Mismatch found: " + ch + " at " + j);
                        return false;
                    }
                    break;
            default: break;
            }
        }
        if (!theStack.isStackEmpty()){
            System.out.println("Error: missing right delimiter");
            return false;
        }
        return true;
    }
}
 
 
class StackImpl_2 {
 
    private int stackSize;
    private char[] stackArr;
    private int top;
 
    /**
     * constructor to create stack with size
     * @param size
     */
    public StackImpl_2(int size) {
        this.stackSize = size;
        this.stackArr = new char[stackSize];
        this.top = -1;
    }
 
    /**
     * This method adds new entry to the top
     * of the stack
     * @param entry
     * @throws Exception
     */
    public void push(char entry) {
        this.stackArr[++top] = entry;
    }
 
    /**
     * This method removes an entry from the
     * top of the stack.
     * @return
     * @throws Exception
     */
    public char pop() {
        char entry = this.stackArr[top--];
        return entry;
    }
     
    /**
     * This method returns top of the stack
     * without removing it.
     * @return
     */
    public char peek() {
        return stackArr[top];
    }
 
    /**
     * This method returns true if the stack is
     * empty
     * @return
     */
    public boolean isStackEmpty() {
        return (top == -1);
    }
 
    /**
     * This method returns true if the stack is full
     * @return
     */
    public boolean isStackFull() {
        return (top == stackSize - 1);
    }
}
