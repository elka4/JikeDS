package jike;

/**
 *折纸问题
 */
public class Course16_5_FoldPaper {
	public static final String Left="Left";
	public static final String Right="Right";
	public static void print(String flag){
		if(flag.equals(Left)){
			System.out.println("凸");
		}else{
			System.out.println("凹");
		}
	}
	public static void inOrder(int level,String flag,int maxLevel){
		if(level==maxLevel){
			print(flag);
		}else{
			inOrder(level+1, Left, maxLevel);
			print(flag);
			inOrder(level+1, Right, maxLevel);
		}
	}
	public static void main(String[] args) {
		inOrder(1, Right, 4);
	}
}
