package ch9J.chapter4_DP1;
//
public class _1Triangle2016 {

}
//变种，从上到下最大值，从上到下有多少种方案？

//Ch9 2016 summer， recursive， 会超时
//DFS， traverse，就是把所有路径都穷举一遍 O（2 ^ n），n是这个数字三角形的高度
//总共有n层，每层都有两个选择
 
class Triangle2016_1{
	private int minPath;//用了全局变量，不好。
	/**
	 * @param triagle: a list of lists of integers
	 * @return An integer, minimum path sum.
	 */
	public int minimumTotal(int[][] triangle){
		//求最小值时候的一个技巧，把结果先设为最大值
		minPath = Integer.MAX_VALUE;
		//traverse(0, 0, triangle[0][0]);
		traverse(triangle, 0, 0, 0);
		return minPath;
	}
	//1, 递归的定义：递归的定义：我从0，0出发，
	//走到x，y这个点，一路上（不包括x，y）得到的和是sum
	//可以包括，看个人设计。这里老师觉得不包括比较好。
	//比如说接下来的递归出口就很好设计，下面的x就是越界的点。
	private void traverse(int[][] triangle, int x, int y, int sum){
		//2, 递归的出口，当我走过了最后一层的时候
		//如果包括x，y if(x == triangle.length － 1)  
		if(x == triangle.length) {
			minPath = Math.min(minPath, sum);
			return;
		}
		//3, 递归怎样变为更小的问题
		traverse(triangle, x + 1, y, sum + triangle[x][y]);
		traverse(triangle, x + 1, y + 1, sum + triangle[x][y]);
		
		//如果一开始包括x，y
		//traverse(triangle, x + 1, y, sum + triangle[x + 1][y]);
		//traverse(triangle, x + 1, y + 1, sum + triangle[x + 1][y + 1]);
	}
}

//Ch9 2016 summer //会超时
//DFS， Divide and Conquer
//这里n代表二叉树的高度， 时间复杂度O（2 ^ n）
//总共有n层，每层都有两个选择

class Triangle2016_2{
	/**
	 * @param triagle: a list of lists of integers
	 * @return An integer, minimum path sum.
	 */
	public int minimumTotal(int[][] triangle){
		return divideConquer(triangle, 0, 0);
	}
	//1, 递归的定义：从x，y出发，走到最底层（终点），所能得到的最短的路径长度
	//
	private int divideConquer(int[][] triangle, int x, int y){
		//2, 递归的出口，当我走过了最后一层的时候
		//the last level should be triangle.length - 1
		if(x == triangle.length){
			return 0; //有负数也应该return 0
		}
		//3, 递归怎样变为更小的问题
		//x + 1, y
		//x + 1, y + 1
		int left = divideConquer(triangle, x + 1, y);
		int right = divideConquer(triangle, x + 1, y + 1);
		
		return Math.min(left, right) + triangle[x][y]; 
	}
}

//Ch9 2016 summer
//DFS， Divide Conquer + memorization
//这里n代表二叉树的高度， O(n ^ 2)， 多项式级别
//动态规划无法彻底分开左边右边，所以记忆可以避免重复计算，从而提高速度

/*O(n ^ 2)是这么算出来的：
 * 1
 * 2
 * 3
 * 4
 * .....n(n+1)/2 = n^2
 * 总之是要遍历一边每个点
 */

/**
 * @param triagle: a list of lists of integers
 * @return An integer, minimum path sum.
 */
class Triangle2016_3AC{
	int n ;	
	//这个数组记录一个位置的数值
	int[][] minPath;
	//这个数组记录一个位置有没有记录数值，默认都是false
	boolean[][] hasValue;

public int minimumTotal(int[][] triangle){
	 n = triangle.length;
	 minPath = new int[n][n];
	 hasValue = new boolean[n][n];
	return divideConquer(triangle, 0, 0);
}

//1, 递归的定义：从x，y出发，走到最底层，所能得到的最短的路径长度
//Version 2 : Memorize Search 用的是Integer.MAX_VALUE
//这里用的是 boolean[][] hasValue;
private int divideConquer(int[][] triangle, int x, int y){
	//2, 递归的出口，当我走过了最后一层的时候
	if(x == triangle.length){
		return 0; //有负数也应该return 0
	}
	//如果有值，就直接返回数值，不再重复计算。就是在这里节省的时间。
	if(hasValue[x][y]){
		return minPath[x][y];
	}
	//3, 递归怎样变为更小的问题
	int left = divideConquer(triangle, x + 1, y);
	int right = divideConquer(triangle, x + 1, y + 1);
	
	hasValue[x][y] = true;
	minPath[x][y] = Math.min(left, right) + triangle[x][y];
	
	return minPath[x][y];
}
}

/*
//时间复杂度：
class DFS_Traverse{
	void traverse(int x, int y, int sum){
		if (x == n) {
			//found a whole path from top to bottom
			if (sum < best){
				best = sum;
			}
			return;
		}
		traverse (x + 1, y, sum + A[x][y]);
		traverse (x + 1, y + 1, sum + A[x][y]);
	}
	best = MAXINT;
	traverse(0, 0, 0);
	//best is the answer
}

//时间复杂度：
class DFS_Divide_Conquer{
	//return minimum path from (x, y) to bottom
	int divideConquer(int x, int y){
		if(x == n){
			return 0;
		}
		return A[x][y] + Math.min(
				divideConquer(x + 1, y), 
				divideConquer(x + 1, y + 1)
				);
	}
	divideConquer(0, 0);
}

//时间复杂度：O(n ^ 2)
class DS_Divide_Conquer_Memorization{
	//return minimum path from (x, y) to bottom
	int divideConquer(int x, int y){
		//row index from 0 to n-1.
		if(x == n){
			return 0;
		}
		//if we already got the minimum path from (x, y) to bottom.
		//just return it
		if(hash[x][y] != Integer.MAX_VALUE){
			return hash[x][y];
		}
		//任何x，y能计算到这里就只有一次
		//set before return
		hash[x][y] = A[x][y] + Math.min(divideConquer(x + 1, y), 
				divideConquer(x + 1, y + 1));
		return hash[x][y];
	} 
	initialize: hash[*][*] = Integer.MAX_VALUE;
	answer: divideConquer(0, 0);
}
if[0][0]
		
{
	               //多重循环：自底向上
//时间复杂度：
//空间复杂度：
A[][]
//状态定义
if[i][j]表示从，j出发走到最后一层的最小路径长度
//初始化，终点先有值
for(int i = 0; i < n; i++){
	f[n - 1][i] = A[n - 1][i];
}
//循环递推求解
for(int i = n - 2; i >= 0; i--){
	for(int j = 0; j <= i; j++){
		f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + A[i][j];
	}
}
//求结果：起点
f[0][0]
}

{
					//多重循环：自顶向下
//时间复杂度：
//空间复杂度：
//初始化，起点
f[0][0] = A[0][0];

//初始化三角形的左边和右边。有问题的地方，提前放到前面来。
for(int i = 1; i < n; i++){
	f[i][0] = f[i - 1][0] + A[i][0];
	f[i][i] = f[i - 1][i - 1];
}
//top down
for(int i = 1; i < n; i++){
	for(int j = 1; j < i; j++){
		f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + A[i][j];
	}
} 
//最后一层的n个点里选最小的
Math.min(f[n - 1][0], f[n - 1][1], f[n - 1][2]...)

}
	*/	