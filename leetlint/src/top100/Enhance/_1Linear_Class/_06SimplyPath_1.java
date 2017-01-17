package top100.Enhance._1Linear_Class;

import java.util.*;

public class _06SimplyPath_1 {
	public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return new String();
        }
        path = path.trim();
        Deque <String>  deque = new LinkedList <String> ();
        for (String cur : path.split( "/")) {        
            if (cur.equals( "..")) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (!cur.equals( ".") && !cur.equals( "")) {
                deque.offerLast(cur);
            }
        }       

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append( "/").append(deque.pollFirst());
        }
        return sb.length() == 0 ? new String( "/") : sb.toString();
    }
}
