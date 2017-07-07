package chapter1;

import java.util.ArrayList;

//Non-Recursion
class _15Permutations_NonRecursion {
  /**
   * @param nums: A list of integers.
   * @return: A list of permutations.
   */
  public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
      ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();
      if (nums == null || nums.size() == 0) {
          return permutations;
      }
      
      int n = nums.size();
      ArrayList<Integer> stack = new ArrayList<>();
      
      stack.add(-1);
      while (stack.size() != 0) {
          Integer last = stack.get(stack.size() - 1);
          stack.remove(stack.size() - 1);
          
          // increase the last number
          int next = -1;
          for (int i = last + 1; i < n; i++) {
              if (!stack.contains(i)) {
                  next = i;
                  break;
              }
          }
          if (next == -1) {
              continue;
          }
          
          // generate the next permutation
          stack.add(next);
          for (int i = 0; i < n; i++) {
              if (!stack.contains(i)) {
                  stack.add(i);
              }
          }
          
          // copy to permutations set
          ArrayList<Integer> permutation = new ArrayList<>();
          for (int i = 0; i < n; i++) {
              permutation.add(nums.get(stack.get(i)));
          }
          permutations.add(permutation);
      }
      
      return permutations;
  }
}