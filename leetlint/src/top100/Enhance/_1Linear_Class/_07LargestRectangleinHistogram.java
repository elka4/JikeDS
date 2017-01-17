package top100.Enhance._1Linear_Class;

import java.util.*;

public class _07LargestRectangleinHistogram {

public int largestRectangleArea(int[] array) {         
    if (array == null || array.length == 0)
      return 0;
    Deque <Integer>  stack = new LinkedList <Integer> ();//store the index
    int max = 0;
    for(int i = 0; i  <= array.length; i++) {//Each elem will be push once and poll once
      //1. Check whether this elem can be pushed into the stack
      int curVal = i == array.length ? 0 : array[i];
      while(!stack.isEmpty() && array[stack.peekLast()]  >= curVal) {
          int height = array[stack.pollLast()];
          int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1;
          int rightBound = i;
          max = Math.max(max, height * (rightBound - leftBound));
      }
      //2. Push the elem into the stack
      stack.addLast(i);
    }
    return max;
    }
}
