package _04_Tree._Count;

import lib.TreeNode;


//
//
//
public class _270_Tree_Closest_Binary_Search_Tree_Value_E {


    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right; if (kid == null) return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }



    public int closestValue2(TreeNode root, double target) {
        int ret = root.val;
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - ret)){
                ret = root.val;
            }
            root = root.val > target? root.left: root.right; }
        return ret;
    }

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
}
/*

 */
/*

 */
