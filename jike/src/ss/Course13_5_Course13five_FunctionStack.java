package ss;

public class Course13_5_Course13five_FunctionStack {
	public static int fac(int n){
		if(n==1){
			return 1;
		}else{
			return n*fac(n-1);
		}
	}
	public static void main(String[] args) {
		System.out.println(fac(10));
	}
}
