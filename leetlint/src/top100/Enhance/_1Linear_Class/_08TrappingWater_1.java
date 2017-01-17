package top100.Enhance._1Linear_Class;

import java.util.*;

public class _08TrappingWater_1 {
public int trap(int[] height) {
    if(height.length == 0)
        return 0;
    Deque <Integer>  stack = new LinkedList <Integer> ();  
    int sum = 0;  

    for(int i = 0; i <  height.length; i++) {
        int cur = height[i];

        while(!stack.isEmpty() && height[stack.peekLast()]  < cur) {
            int index = stack.pollLast();
            if(!stack.isEmpty()) {
                int rightBound = stack.peekLast();
                int leftBound = i;
                sum += (leftBound - rightBound - 1) * 
                		(Math.min(cur, height[rightBound]) - height[index]);
            }
        }
            stack.offerLast(i);
    }
    return sum;
}
}
