package top100.Enhance._1Linear_Class;
import java.util.*;
//Length of Longest substring -- Method 1
public class _04LengthofLongestSubstring_1 {

public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) { 
            return 0;
        }
        //Queue: Maintain the substring
        Deque <Character>  queue = new LinkedList <Character> ();
        //Set: Remove duplicates
        Set <Character>  set = new HashSet <Character> ();
        int max = Integer.MIN_VALUE;
        for (char ch : s.toCharArray()) {
            //Keep pollFirst when duplicates found
            while (!set.add(ch)) {
                set.remove(queue.pollFirst());
            }  
            //offerLast when no duplicates
            queue.offerLast(ch);
            set.add(ch);
            max = Math.max(max, queue.size());
        }
        return max;
    }
}

