package _String._Stack;
import java.util.*;
import org.junit.Test;

//  71. Simplify Path
//  https://leetcode.com/problems/simplify-path/description/
//  http://www.lintcode.com/problem/simplify-path/
//
//  给定一个文档(Unix-style)的完全路径，请进行路径简化。
//  String Stack
//  6:
//
//
public class _071_String_Simplify_Path_M {
//------------------------------------------------------------------------------
    //1
    //Java 10-lines solution with stack
/*    Hi guys!

    The main idea is to push to the stack every valid file name (not in {"",".",".."}), popping only if there's smth to pop and we met "..". I don't feel like the code below needs any additional comments.*/
    class Solution1{
        public String simplifyPath(String path) {
            Deque<String> stack = new LinkedList<>();
            Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
            for (String dir : path.split("/")) {
                if (dir.equals("..") && !stack.isEmpty()) stack.pop();
                else if (!skip.contains(dir)) stack.push(dir);
            }
            String res = "";
            for (String dir : stack) res = "/" + dir + res;
            return res.isEmpty() ? "/" : res;
        }
    }

//------------------------------------------------------------------------------
    //2
    //Share my 8ms Java solution
    public String simplifyPath(String path) {
        String[] dir = path.split("/");
        String[] stack = new String[dir.length];
        int ptr = 0;
        for(int i = 0; i < dir.length; i++){
            if(dir[i].equals(".") || dir[i].equals("")){
                continue;
            }else if(dir[i].equals("..")){
                if(ptr > 0) ptr--;
            }else{
                stack[ptr] = dir[i];
                ptr++;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < ptr; i++){
            result.append("/");
            result.append(stack[i]);
        }
        return result.length() == 0 ? "/" : result.toString();
    }

//------------------------------------------------------------------------------
    //3
    //Accepted solution using Deque
    /**
     * Deque of strings (directories).
     * iterate path:
     *  if "/", continue,
     *  if ".", conitnue,
     *  if "..", poll last,
     *  else, add a new directory
     * in the end, build result from deque.
     */
    public String simplifyPath3(String path) {
        Deque<String> deque = new LinkedList<String>();
        String[] splits = path.split("/");
        for (String split : splits) {
            // CATCH: must use "equals()" instead of "==",
            // because 'split' is a variable!
            // Also, 'split' could be empty string.
            if (split.equals(""))
                continue;
            else if (split.equals("."))
                continue;
            else if (split.equals(".."))
                deque.pollLast();
            else
                deque.addLast(split);
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            String s = deque.pollFirst();
            builder.append("/").append(s);
        }
        if (builder.length() == 0)
            return "/";
        return builder.toString();
    }

//------------------------------------------------------------------------------
    //4
    //Java easy to understand Stack solution.
    public String simplifyPath4(String path) {
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (int i = 0; i < p.length; i++) {
            if (!stack.empty() && p[i].equals(".."))
                stack.pop();
            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
                stack.push(p[i]);
        }
        List<String> list = new ArrayList(stack);
        return "/"+String.join("/", list);
    }



//------------------------------------------------------------------------------
    //5
    //AC Solution in Java
    public class Solution5 {
        public String simplifyPath(String path) {
            Set<String> isSkip = new HashSet<>(Arrays.asList("", ".", ".."));
            Deque<String> stack = new ArrayDeque<>();
            for (String token : path.split("/")) {
                if (token.equals("..") && !stack.isEmpty()) stack.pop();
                if (isSkip.contains(token)) continue;
                stack.push(token);
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append("/" + stack.pollLast());
            }
            return sb.length() == 0 ? "/" : sb.toString();
        }
    }

//------------------------------------------------------------------------------
    //6
    //9Ch
    public class Jiuzhang {
        public String simplifyPath(String path) {
            String result = "/";
            String[] stubs = path.split("/+");
            ArrayList<String> paths = new ArrayList<String>();
            for (String s : stubs){
                if(s.equals("..")){
                    if(paths.size() > 0){
                        paths.remove(paths.size() - 1);
                    }
                }
                else if (!s.equals(".") && !s.equals("")){
                    paths.add(s);
                }
            }
            for (String s : paths){
                result += s + "/";
            }
            if (result.length() > 1)
                result = result.substring(0, result.length() - 1);
            return result;
        }
    }


//------------------------------------------------------------------------------
}
/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Seen this question in a real interview before?   Yes  No
Companies
Facebook Microsoft

Related Topics
String Stack
 */