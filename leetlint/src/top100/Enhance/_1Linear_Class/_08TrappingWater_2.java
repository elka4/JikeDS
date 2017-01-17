package top100.Enhance._1Linear_Class;

public class _08TrappingWater_2 {
	public int trap2(int[] arr) {
	    if(arr == null || arr.length  <= 2)
	    return 0;
	    //Two Scanners
	    int left = 0;
	    int right = arr.length - 1;
	    int sum = 0;
	    //Two Walls
	    int leftMax = 0;
	    int rightMax = 0;
	    while(left  <= right) {
	      //Move lower wall first: Guarantee middle region can trap water
	      if(leftMax  <= rightMax) {
	      leftMax = Math.max(leftMax, arr[left]);
	      if(arr[left]  < leftMax)
	        sum += leftMax - arr[left];
	        left++;
	      }else {
	        rightMax = Math.max(rightMax, arr[right]);
	        if(arr[right]  < rightMax)
	        sum += rightMax - arr[right];
	        right--;
	      }
	    }
	    return sum;
	  }

}
