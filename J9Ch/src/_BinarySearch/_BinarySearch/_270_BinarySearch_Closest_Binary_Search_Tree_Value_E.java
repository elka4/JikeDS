package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
import lib.*;


public class _270_BinarySearch_Closest_Binary_Search_Tree_Value_E {
    //4-7 lines recursive/iterative Ruby/C++/Java/Python
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }


//    Clean and concise java solution
    public int closestValue2(TreeNode root, double target) {
        int ret = root.val;
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            }
            root = root.val > target? root.left: root.right;
        }
        return ret;
    }

//    Simple iterative Java solution with explaination
    public int closestValue3(TreeNode root, double target) {
        int closestVal = root.val;
        while(root != null){
            //update closestVal if the current value is closer to target
            closestVal = (Math.abs(target - root.val) < Math.abs(target - closestVal))? root.val : closestVal;
            if(closestVal == target){   //already find the best result
                return closestVal;
            }
            root = (root.val > target)? root.left: root.right;   //binary search
        }
        return closestVal;
    }

//    Super clean recursive Java solution
    public class Solution {
        public int closestValue(TreeNode root, double target) {
            return closest(root, target, root.val);
        }

        private int closest(TreeNode node, double target, int val) {
            if (node == null) return val;
            if (Math.abs(node.val - target) < Math.abs(val - target)) val = node.val;
            if (node.val < target) val = closest(node.right, target, val);
            else if (node.val > target) val = closest(node.left, target, val);
            return val;
        }
    }


}
/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
 */