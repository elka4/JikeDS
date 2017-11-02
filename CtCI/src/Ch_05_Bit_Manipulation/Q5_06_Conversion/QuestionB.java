package Ch_05_Bit_Manipulation.Q5_06_Conversion;
import CtCILibrary.AssortedMethods;
//将a翻转bit为b需要翻转多少个bit？

public class QuestionB {
    //觉得这个更容易理解一些，反正就是每轮检查for的满足条件，满足条件count就++
    //c是a和b的异或，只要c不是0，a和b就不同，就可以进入循环，count++
    //每次循环结束，c = c & (c-1). c-1，将c中最后一个1和1之后的位置全部反转.
    // c & (c-1) 就去掉最后一个a和b不同的bit。这样下一轮就少一个不同，对应本轮count++。
	public static int bitSwapRequired(int a, int b){
		int count = 0;
		for (int c = a ^ b; c != 0; c = c & (c-1)) {
			count++;
		}
		return count;
	}
    public static int bitSwapRequired1(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >>> 1) {
            count += c & 1;
        }
        return count;
    }
	
	public static void main(String[] args) {
		int a = -23432;
		int b = 512132;
		System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
		System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
		System.out.println("Required number of bits: " + bitSwapRequired(a, b));
	}

	/*
                    -23432: 11111111111111111010010001111000
                    512132: 00000000000001111101000010000100
                    Required number of bits: 23

	 */
}
/*
Conversion: Write a function to determine the number of bits you would need to
flip to convert integer A to integer B.
EXAMPLE
Input: 29 (or: 111131), 15 (or: 131111) Output: 2
 */