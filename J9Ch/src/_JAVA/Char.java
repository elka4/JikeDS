package _JAVA;

import org.junit.Test;

import java.io.*;

public class Char {
    //https://stackoverflow.com/questions/13717898/java-character-literals-value-with-getnumericvalue
    //Java Character literals value with getNumericValue()

    @Test
    public void test01(){
        char ch1 = 'A';
        char ch2 = 'a';
        char ch3 = 'Z';
        char ch4 = 'z';

        System.out.println("ch1 -- > " + Integer.toBinaryString(Character.getNumericValue(ch1)));
        System.out.println("ch2 -- > " + Integer.toBinaryString(Character.getNumericValue(ch2)));
        System.out.println("ch3 -- > " + Integer.toBinaryString(Character.getNumericValue(ch3)));
        System.out.println("ch4 -- > " + Integer.toBinaryString(Character.getNumericValue(ch4)));
    }

    /*
    ch1 -- > 1010
    ch2 -- > 1010
    ch3 -- > 100011
    ch4 -- > 100011
     */

    /*
    The letters A-Z in their uppercase ('\u0041' through '\u005A'), lowercase ('\u0061' through '\u007A'), and full width variant ('\uFF21' through '\uFF3A' and '\uFF41' through '\uFF5A') forms have numeric values from 10 through 35.
Basically this means that when parsing hex (say), 0xfa == 0xFA, as you'd expect.
     */
/*
Judging from the commentary, you're actually looking for the codepoints of the characters, rather than their numeric value, so I'll just isolate that into an answer. The getNumericValue() function returns what the character means as a number when interpreting its glyph, it does not return the codepoint of a character. For instance, getNumericValue('5') returns 5 as an int, not the codepoint of 5.
 */
    @Test
    public void test02(){
        char ch1 = 'A';
        char ch2 = 'a';
        char ch3 = 'Z';
        char ch4 = 'z';

        System.out.println("ch1 -- > " + Character.getNumericValue(ch1));
        System.out.println("ch2 -- > " + Character.getNumericValue(ch2));

        System.out.println("ch3 -- > " + Character.getNumericValue(ch3));
        System.out.println("ch4 -- > " + Character.getNumericValue(ch4));

    }
    /*
    ch1 -- > 10
    ch2 -- > 10
    ch3 -- > 35
    ch4 -- > 35
     */

//////////////////////////////////////////////////////////////////

    @Test
    public void test03(){
        System.out.println('\u0041');
        System.out.println('\u0061');
        System.out.println('\uFF21');
        System.out.println('\uFF41');
    }
    /*
    A
    a
    Ａ
    ａ
     */
//////////////////////////////////////////////////////////////////
    @Test
    public void test04(){
        System.out.println('\u0041' == '\u0061');
    }
//////////////////////////////////////////////////////////////////

    /*
    getNumericValue
public static int getNumericValue(char ch)
Returns the int value that the specified Unicode character represents. For example, the character '\u216C' (the roman numeral fifty) will return an int with a value of 50.
The letters A-Z in their uppercase ('\u0041' through '\u005A'), lowercase ('\u0061' through '\u007A'), and full width variant ('\uFF21' through '\uFF3A' and '\uFF41' through '\uFF5A') forms have numeric values from 10 through 35. This is independent of the Unicode specification, which does not assign numeric values to these char values.

If the character does not have a numeric value, then -1 is returned. If the character has a numeric value that cannot be represented as a nonnegative integer (for example, a fractional value), then -2 is returned.

Note: This method cannot handle supplementary characters. To support all Unicode characters, including supplementary characters, use the getNumericValue(int) method.

Parameters:
ch - the character to be converted.
Returns:
the numeric value of the character, as a nonnegative int value; -2 if the character has a numeric value that is not a nonnegative integer; -1 if the character has no numeric value.
Since:
1.1
     */

    /*
    getNumericValue
public static int getNumericValue(int codePoint)
Returns the int value that the specified character (Unicode code point) represents. For example, the character '\u216C' (the Roman numeral fifty) will return an int with a value of 50.
The letters A-Z in their uppercase ('\u0041' through '\u005A'), lowercase ('\u0061' through '\u007A'), and full width variant ('\uFF21' through '\uFF3A' and '\uFF41' through '\uFF5A') forms have numeric values from 10 through 35. This is independent of the Unicode specification, which does not assign numeric values to these char values.

If the character does not have a numeric value, then -1 is returned. If the character has a numeric value that cannot be represented as a nonnegative integer (for example, a fractional value), then -2 is returned.

Parameters:
codePoint - the character (Unicode code point) to be converted.
Returns:
the numeric value of the character, as a nonnegative int value; -2 if the character has a numeric value that is not a nonnegative integer; -1 if the character has no numeric value.
Since:
1.5

     */
//////////////////////////////////////////////////////////////////

    @Test
    public void test05(){
        final int RADIX = 10;
        int i = 4;
        char ch = Character.forDigit(i, RADIX);
        System.out.println(ch); // Prints '4'

        //There is also a method that can convert from a char back to an int:

        int i2 = Character.digit(ch, RADIX);
        System.out.println(i2); // Prints '4'

    }
//////////////////////////////////////////////////////////////////

    @Test
    public void test06() {
        byte b = 65;
        for (byte i = b; i <= b + 25; i++) {
            System.out.print((char) i + ", ");
        }
    }//A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

//////////////////////////////////////////////////////////////////
    //  https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
    @Test
    public void test07() {
//        byte b = 24;
        for (byte i = -128; i < 127; i++) {
            System.out.print((char) i + ", ");
        }
    }
    /*
    ﾀ, ﾁ, ﾂ, ﾃ, ﾄ, ﾅ, ﾆ, ﾇ, ﾈ, ﾉ, ﾊ, ﾋ, ﾌ, ﾍ, ﾎ, ﾏ, ﾐ, ﾑ, ﾒ, ﾓ, ﾔ, ﾕ, ﾖ, ﾗ, ﾘ, ﾙ, ﾚ, ﾛ, ﾜ, ﾝ, ﾞ, ﾟ, ﾠ, ﾡ, ﾢ, ﾣ, ﾤ, ﾥ, ﾦ, ﾧ, ﾨ, ﾩ, ﾪ, ﾫ, ﾬ, ﾭ, ﾮ, ﾯ, ﾰ, ﾱ, ﾲ, ﾳ, ﾴ, ﾵ, ﾶ, ﾷ, ﾸ, ﾹ, ﾺ, ﾻ, ﾼ, ﾽ, ﾾ, ﾿, ￀, ￁, ￂ, ￃ, ￄ, ￅ, ￆ, ￇ, ￈, ￉, ￊ, ￋ, ￌ, ￍ, ￎ, ￏ, ￐, ￑, ￒ, ￓ, ￔ, ￕ, ￖ, ￗ, ￘, ￙, ￚ, ￛ, ￜ, ￝, ￞, ￟, ￠, ￡, ￢, ￣, ￤, ￥, ￦, ￧, ￨, ￩, ￪, ￫, ￬, ￭, ￮, ￯, ￰, ￱, ￲, ￳, ￴, ￵, ￶, ￷, ￸, ￹, ￺, ￻, ￼, �, ￾, ￿,  , , , , , , , ,, 	,
, , , , , , , , , , , , , , , , , , ,  , !, ", #, $, %, &, ', (, ), *, +, ,, -, ., /, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, :, ;, <, =, >, ?, @, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \, ], ^, _, `, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, {, |, }, ~,
     */
//////////////////////////////////////////////////////////////////
    //http://www.baeldung.com/java-write-to-file
    //char: The char data type is a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).

    //2. Write with BufferedWriter


    @Test
    public void test08() throws IOException {


//        String str = "test08";
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/_JAVA/test08.txt", true));
//        writer.append(' ');
//        writer.append(str);



        int count = 0;
        for (char i = '\u0000'; i < '\uffff' ; i++) {
//            System.out.print((char) i + ", ");
            writer.append((char) i + ", ");
            count++;
        }
        writer.append("count: " + count);
        System.out.println("count: " + count);
        writer.close();
    }//count: 65535

//////////////////////////////////////////////////////////////////
    //3. Write with PrintWriter


    @Test
    public void test09() throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/_JAVA/test09.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int count = 0;
        for (char i = '\u0000'; i < '\uffff' ; i++) {
//            System.out.print((char) i + ", ");
            printWriter.print((char) i + ", ");
            count++;
        }
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", count);
        printWriter.close();
    }
//////////////////////////////////////////////////////////////////
    //4. Write with FileOutputStream


    @Test
    public void test10() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("/Users/tianhuizhu/Downloads/uber/code/JikeDS/J9Ch/src/_JAVA/test10.txt");

        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (char i = '\u0000'; i < '\uffff' ; i++) {
    //            System.out.print((char) i + ", ");
            sb.append((char) i + ", ");
//            printWriter.print((char) i + ", ");
            count++;
        }
        byte[] strToBytes = sb.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();

    }

//////////////////////////////////////////////////////////////////

}
