package _01_Array.Ch_01_Arrays_and_Strings.Q1_06_String_Compression;
import org.junit.Test;

/*
String Compression: Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 */

// 三个不同的方法就只是String， StringBuffer, StringBuilding的区别

public class QuestionA {

	public static String compressBad(String str) {
		String compressedString = "";
		int countConsecutive = 0;

		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;
			
			/* If next character is different than current, append this char to result.*/
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressedString += "" + str.charAt(i) + countConsecutive;
				countConsecutive = 0;
			}
		}
		return compressedString.length() < str.length() ? compressedString : str;
	}


//////////////////////////////////////////////////////////////////////

    public static String compress2(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

			/* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }



    @Test
    public void test01(){
        //System.out.println(compress2("aabcccccaaa"));//a2b1c5a3
    }
//////////////////////////////////////////////////////////////////////

    public static String compress3(String str) {
        int finalLength = countCompression(str);
        if (finalLength >= str.length()) return str;
        // initialize capacity
        StringBuffer compressed = new StringBuffer(finalLength);
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

			/* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.toString();
    }

    public static int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

			/* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }

    @Test
    public void testCount(){
        System.out.println(countCompression("aabcccccaaa"));//11 -> 8 8
        System.out.println(countCompression("aaabbccccccaaaad"));//15 -> 8
        System.out.println(compress3("aaabbccccccaaaad"));//16 -> 10  //a3b2c6a4d1 10
    }
//////////////////////////////////////////////////////////////////////


	public static void main(String[] args) {
		String str = "aa";
		System.out.println(str);
		System.out.println(compressBad(str));
		System.out.println(compressBad("aabcccccaaa"));//a2b1c5a3

	}
}