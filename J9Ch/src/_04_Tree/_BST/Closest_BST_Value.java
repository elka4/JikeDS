package _04_Tree._BST;

import lib.*;

/*  LeetCode 270. Closest Binary Search Tree Value

LeetCode – Closest Binary Search Tree Value (Java)

Given a non-empty binary search tree and a target value,
find the value in the BST that is closest to the target
 */

public class Closest_BST_Value {
    /*Java Solution 1 - Recursion

    Recursively traverse down the root.
    When target is less than root, go left; when target is greater than root, go right.*/
    int goal;
    double min = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        helper(root, target);
        return goal;
    }

    public void helper(TreeNode root, double target){
        if(root==null)
            return;

        if(Math.abs(root.val - target) < min){
            min = Math.abs(root.val - target);
            goal = root.val;
        }

        if(target < root.val){
            helper(root.left, target);
        }else{
            helper(root.right, target);
        }
    }



/////////////////////////////////////////////////////////////////

    //Java Solution 2 - Iteration

    public int closestValue2(TreeNode root, double target) {
        double min=Double.MAX_VALUE;
        int result = root.val;

        while(root!=null){
            if(target>root.val){

                double diff = Math.abs(root.val-target);
                if(diff<min){
                    min = Math.min(min, diff);
                    result = root.val;
                }
                root = root.right;
            }else if(target<root.val){

                double diff = Math.abs(root.val-target);
                if(diff<min){
                    min = Math.min(min, diff);
                    result = root.val;
                }
                root = root.left;
            }else{
                return root.val;
            }
        }

        return result;
    }


/////////////////////////////////////////////////////////////////

    //4-7 lines recursive/iterative Ruby/C++/Java/Python

    //Recursive
    //Closest is either the root's value (a) or the closest in the appropriate subtree (b).

    public int closestValue3(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue3(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    //Iterative
    //Walk the path down the tree close to the target, return the closest value on the path.
    // Inspired by yd, I wrote these after reading "while loop".



/////////////////////////////////////////////////////////////////

    //Clean and concise java solution
    public int closestValue4(TreeNode root, double target) {
        int ret = root.val;
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            }
            root = root.val > target? root.left: root.right;
        }
        return ret;
    }


/////////////////////////////////////////////////////////////////

    //Simple iterative Java solution with explaination
    public int closestValue5(TreeNode root, double target) {
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


/////////////////////////////////////////////////////////////////

    /*Java iterative solution
    Basic idea: In a while loop, calculate min for the current root and update the closest value when necessary. Depending on whether root node is smaller or larger than the target, go to the right or the left branch.
*/
    public int closestValue6(TreeNode root, double target) {
        double closest = Integer.MAX_VALUE;
        int value = 0;
        TreeNode current = root;
        while (current != null) {
            if (closest > Math.abs(current.val-target)) {
                closest = Math.abs(current.val-target);
                value = current.val;
            }

            if (current.val < target) {
                current = current.right;
            } else if (current.val > target) {
                current = current.left;
            } else {
                break;
            }
        }
        return value;
    }


/////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////




}
/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

 */