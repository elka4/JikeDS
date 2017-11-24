package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _071_Stack_Simplify_Path_M {
/*    Java 10-lines solution with stack
    Hi guys!

    The main idea is to push to the stack every valid file name (not in {"",".",".."}), popping only if there's smth to pop and we met "..". I don't feel like the code below needs any additional comments.*/

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








//-------------------------------------------------------------------------////
    // 9Ch
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


}
/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 */

/*
给定一个文档(Unix-style)的完全路径，请进行路径简化。

您在真实的面试中是否遇到过这个题？ Yes
样例
"/home/", => "/home"

"/a/./b/../../c/", => "/c"

挑战
你是否考虑了 路径 = "/../" 的情况？

在这种情况下，你需返回"/"。

此外，路径中也可能包含双斜杠'/'，如 "/home//foo/"。

在这种情况下，可忽略多余的斜杠，返回 "/home/foo"。


 */