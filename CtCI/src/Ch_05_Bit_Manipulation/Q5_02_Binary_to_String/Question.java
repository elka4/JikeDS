package Ch_05_Bit_Manipulation.Q5_02_Binary_to_String;

import org.junit.Test;
//给一个0-1之间的double，转为二进制形式的string
//如果长度超过32，返回error
public class Question {
	public static String printBinary(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
	
		StringBuilder binary = new StringBuilder();
		binary.append(".");

		while (num > 0) {
			/* Setting a limit on length: 32 characters */
			if (binary.length() > 32) {
				return "ERROR";
			}
			double r = num * 2;
			if (r >= 1) {
				binary.append(1);
				num = r - 1;
			} else {
				binary.append(0);
				num = r;
			}
		}

		return binary.toString();
	}
	
	public static String printBinary2(double num) {
		if (num >= 1 || num <= 0) {
			return "ERROR";
		}
	
		StringBuilder binary = new StringBuilder();
		double frac = 0.5;
		binary.append(".");

		while (num > 0) {
			/* Setting a limit on length: 32 characters */
			if (binary.length() >= 32) {
				return "ERROR";
			}
			if (num >= frac) {
				binary.append(1);
				num -= frac;
			} else {
				binary.append(0);
			}
			frac /= 2;
		}

		return binary.toString();
	}	
	
	public static void main(String[] args) {
		String bs = printBinary(.125);
		System.out.println(bs);
		
		for (int i = 0; i < 1000; i++) {
			double num = i / 1000.0;
			String binary = printBinary(num);
			String binary2 = printBinary2(num);
			if (!binary.equals("ERROR") || !binary2.equals("ERROR")) {
				System.out.println(num + " : " + binary + " " + binary2);
			}
		}
	}

	@Test
    public void test01(){
        System.out.println(printBinary(0.5));
        System.out.println(printBinary(0.25));
        System.out.println(printBinary(0.125));
        System.out.println(printBinary(0.7));
    }
}
/*
5.2

Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that
 is passed in as a double, print the binary representation.If the number
 cannot be represented accurately in binary with at most 32 characters, print "ERROR:'

 */