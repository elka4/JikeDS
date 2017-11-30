package _05_DFS._Back_String;
import org.junit.Test;

import java.util.*;

//  22. Generate Parentheses
//  https://leetcode.com/problems/generate-parentheses/description/
//  http://www.lintcode.com/zh-cn/problem/generate-parentheses/
// 3 == 9， 10
public class _022_Generate_Parentheses_M {

    // 1
    /*  An iterative method.
    My method is DP. First consider how to get the result f(n) from previous result f(0)...f(n-1).
    Actually, the result f(n) will be put an extra () pair to f(n-1). Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

    Let us consider an example to get clear view:

    f(0): ""

    f(1): "("f(0)")"

    f(2): "("f(0)")"f(1), "("f(1)")"

    f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"

    So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"

    Below is my code:
    */
    public List<String> generateParenthesis01(int n) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; ++i) {
            final List<String> list = new ArrayList<>();

            for (int j = 0; j < i; ++j) {
                for (final String first : lists.get(j)) {
                    for (final String second : lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }

        return lists.get(lists.size() - 1);
    }
    @Test
    public void test01(){
        System.out.println(generateParenthesis01(3));
    }
    //[()()(), ()(()), (())(), (()()), ((()))]

//-------------------------------------------------------------------------------
    // 2
    //Easy to understand Java backtracking solution
    public List<String> generateParenthesis02(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max){

        if(str.length() == max*2){
            list.add(str);
            return;
        }

        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
    @Test
    public void test02(){
        System.out.println(generateParenthesis02(3));
    }
    //[((())), (()()), (())(), ()(()), ()()()]

//--------------------------------------------------------------------------------
    // 3
    //Java DFS way solution
    public List<String> generateParenthesis03(int n) {
        List<String> list = new ArrayList<String>();
        generateOneByOne("", list, n, n);
        return list;
    }
    public void generateOneByOne(String sublist, List<String> list, int left, int right){
        if(left > right){
            return;
        }
        if(left == 0 && right == 0){ //这个用preorder或者postorder或者inorder都可以
            list.add(sublist);
            return;
        }

        if(left > 0){
            generateOneByOne( sublist + "(" , list, left-1, right);
        }
        if(right > 0){
            generateOneByOne( sublist + ")" , list, left, right-1);
        }

    }
    @Test
    public void test03(){
        System.out.println(generateParenthesis03(3));
    }
    //[((())), (()()), (())(), ()(()), ()()()]

//--------------------------------------------------------------------------------
    // 4
//My accepted JAVA solution
//
//31
//    P peterdyf
//    Reputation:  67
//    For 2, it should place one "()" and add another one insert it but none tail it,
//
//'(' f(1) ')' f(0)
//
//    or add none insert it but tail it by another one,
//
//            '(' f(0) ')' f(1)
//
//    Thus for n, we can insert f(i) and tail f(j) and i+j=n-1,
//
//            '(' f(i) ')' f(j)

    public List<String> generateParenthesis04(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) {
            result.add("");
        } else {
            for (int i = n - 1; i >= 0; i--) {
                List<String> insertSub = generateParenthesis04(i);
                List<String> tailSub = generateParenthesis04(n - 1 - i);
                for (String insert : insertSub) {
                    for (String tail : tailSub) {
                        result.add("(" + insert + ")" + tail);
                    }
                }

            }
        }
        return result;
    }
    @Test
    public void test04(){
        System.out.println(generateParenthesis04(3));
    }

//--------------------------------------------------------------------------------
    // 5
    //2ms AC JAVA Solution using recursive call
    public List<String> generateParenthesis05(int n) {
        ArrayList<String> m=new ArrayList<>();
        generate(m, "", n, n);
        return m;
    }
    public void generate(ArrayList m, String s, int l, int r){
        if(l==0 && r==0){
            m.add(s);
            return;
        }
        if(l>0) generate(m, s+"(",  l-1,  r);
        if(r>l) generate(m, s+")",  l,  r-1);
    }
    @Test
    public void test05(){
        System.out.println(generateParenthesis05(3));
    }


//--------------------------------------------------------------------------------
    //  6
    //Easy java solution
    private void helper(List<String> res, String present, int left, int right) {
        if (right == 0) {
            res.add(present);
        }
        if (left > 0) {
            helper(res, present + "(", left - 1, right);
        }
        if (right > left) {
            helper(res, present + ")", left, right - 1);
        }
    }
    public List<String> generateParenthesis06(int n) {
        List<String> res = new ArrayList<String>();
        if (n == 0) {
            return res;
        }
        helper(res, "", n, n);
        return res;
    }
    @Test
    public void test06(){
        System.out.println(generateParenthesis06(3));
    }


//--------------------------------------------------------------------------------
    // 7
/*
@yin10 The reason why there are no duplicates is because it is based on an unambiguous context-free grammar (if you know what those are):
S -> S(S) | empty
Here it is rewritten as in a single function:
 */
    public List<String> ans (int n) {
        List<String> parens = new ArrayList<String>();
        if (n == 0) parens.add(""); //base case
        else { // recursive case
            for (int i = 0; i < n; i++){
                for ( String front:ans(i))
                    for (String back:ans(n-i-1))
                        parens.add( front +
                                "(" +
                                back +
                                ")");
            }
        }
        return parens;
    }
    @Test
    public void test07(){
        System.out.println(ans(3));
    }
    //[((())), (()()), ()(()), (())(), ()()()]

//--------------------------------------------------------------------------------
    // 8
    // 9Ch
    public ArrayList<String> generateParenthesisJ_1(int n) {
        ArrayList<String> result = new ArrayList<String>();
        if (n <= 0) {
            return result;
        }
        helper(result, "", n, n);
        return result;
    }

    public void helper(ArrayList<String> result,
                       String paren, // current paren
                       int left,     // how many left paren we need to add
                       int right) {  // how many right paren we need to add
        if (left == 0 && right == 0) {
            result.add(paren);
            return;
        }

        if (left > 0) {
            helper(result, paren + "(", left - 1, right);
        }

        if (right > 0 && left < right) {
            helper(result, paren + ")", left, right - 1);
        }
    }
    @Test
    public void test08(){
        System.out.println(generateParenthesisJ_1(3));
    }
    //[((())), (()()), (())(), ()(()), ()()()]

//--------------------------------------------------------------------------------
    // 9
    //Zhu
    /*
    这个方法是DFS，但是并不是backtracking，因为每次传递到下层函数的是通过 + 重新创建的一个String

     */
    public List<String> generateParenthesis_Zhu(int n) {
        List<String> result = new ArrayList<>();

        if(n <= 0){
            return result;
        }
        dfs("", n, n, result);
        return result;
    }

    void dfs(String paren, int left, int right, List<String> result){
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(paren);
        }
        if (left > 0) {
            dfs(paren + "(", left - 1, right, result);
        }
        if(right > 0 ){
            dfs(paren + ")", left, right - 1, result);
        }
    }
    @Test
    public void test09(){
        System.out.println(generateParenthesis_Zhu(3));
    }
    //[((())), (()()), (())(), ()(()), ()()()]

    @Test
    public void test09_1(){
//        System.out.println(generateParenthesis_Zhu(3));
        System.gc();
        long t0 = System.currentTimeMillis();
/*        for (int i = 1; i < 15; i++) {
            generateParenthesis_Zhu(i);
        }*/
        generateParenthesis_Zhu(15);
        long t = System.currentTimeMillis() - t0;
        System.out.println(" String time: " + t + " ms");
    }//String time: 7658 ms

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
    // 10
    //Zhu
    /*
    现在这方法就是正宗的backtracking，使用了StringBuilder，每次传到下层的都是同一个Object
    因此每次在返回上一层之后，需要删除上一层的操作

     */
    public List<String> generateParenthesis_Zhu10(int n) {
        List<String> result = new ArrayList<>();

        if(n <= 0){
            return result;
        }
        dfs10(new StringBuilder(), n, n, result);
        return result;
    }

    void dfs10(StringBuilder paren, int left, int right, List<String> result){
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(paren.toString());
        }
        if (left > 0) {
            dfs10(paren.append("("), left - 1, right, result);
            paren.deleteCharAt(paren.length() - 1);
        }
        if(right > 0 ){
            dfs10(paren.append(")"), left, right - 1, result);
            paren.deleteCharAt(paren.length() - 1);
        }
    }
    @Test
    public void test10(){
//        System.out.println(generateParenthesis_Zhu(3));
        System.gc();
        long t0 = System.currentTimeMillis();
//        for (int i = 1; i < 15; i++) {
//            generateParenthesis_Zhu10(i);
//        }
        generateParenthesis_Zhu10(15);
        long t = System.currentTimeMillis() - t0;
        System.out.println(" StringBuilder time: " + t + " ms");
    }// StringBuilder time: 6110 ms
    // StringBuilder time: 5928 ms
    //[((())), (()()), (())(), ()(()), ()()()]

//--------------------------------------------------------------------------------
}

/*
生成括号

 描述
 笔记
 数据
 评测
给定 n 对括号，请写一个函数以将其生成新的括号组合，并返回所有组合结果。

样例
给定 n = 3, 可生成的组合如下:

"((()))", "(()())", "(())()", "()(())", "()()()"

标签
字符串处理 回溯法 递归 谷歌 Zenefits
相关题目
容易 有效的括号序列 28 %
中等 不同的二叉查找树 II 33 %
中等 不同的二叉查找树
 */


/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */

/*

 */