package _JAVA;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

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
    The letters A-Z in their uppercase ('\\u0041' through '\\u005A'), lowercase ('\\u0061' through '\\u007A'), and full width variant ('\\uFF21' through '\\uFF3A' and '\\uFF41' through '\\uFF5A') forms have numeric values from 10 through 35.
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

//-------------------------------------------------------------------------////

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
//-------------------------------------------------------------------------////
    @Test
    public void test04(){
        System.out.println('\u0041' == '\u0061');
    }
//-------------------------------------------------------------------------////

    /*
    getNumericValue
public static int getNumericValue(char ch)
Returns the int value that the specified Unicode character represents. For example, the character '\\u216C' (the roman numeral fifty) will return an int with a value of 50.
The letters A-Z in their uppercase ('\\u0041' through '\\u005A'), lowercase ('\\u0061' through '\\u007A'), and full width variant ('\\uFF21' through '\\uFF3A' and '\\uFF41' through '\\uFF5A') forms have numeric values from 10 through 35. This is independent of the Unicode specification, which does not assign numeric values to these char values.

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
Returns the int value that the specified character (Unicode code point) represents. For example, the character '\\u216C' (the Roman numeral fifty) will return an int with a value of 50.
The letters A-Z in their uppercase ('\\u0041' through '\\u005A'), lowercase ('\\u0061' through '\\u007A'), and full width variant ('\\uFF21' through '\\uFF3A' and '\\uFF41' through '\\uFF5A') forms have numeric values from 10 through 35. This is independent of the Unicode specification, which does not assign numeric values to these char values.

If the character does not have a numeric value, then -1 is returned. If the character has a numeric value that cannot be represented as a nonnegative integer (for example, a fractional value), then -2 is returned.

Parameters:
codePoint - the character (Unicode code point) to be converted.
Returns:
the numeric value of the character, as a nonnegative int value; -2 if the character has a numeric value that is not a nonnegative integer; -1 if the character has no numeric value.
Since:
1.5

     */
//-------------------------------------------------------------------------////

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
//-------------------------------------------------------------------------////

    @Test
    public void test06() {
        byte b = 65;
        for (byte i = b; i <= b + 25; i++) {
            System.out.print((char) i + ", ");
        }
    }//A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

//-------------------------------------------------------------------------////
    //  https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
    @Test
    public void test07() {
//        byte b = 24;
        for (byte i = -128; i < 127; i++) {
            System.out.print(i + ": ");
            System.out.print((char) i + ", ");
            System.out.print(Character.getNumericValue((char) i )+ "; ");
            System.out.println();
        }
    }
    /*
-128: ﾀ, -1;
-127: ﾁ, -1;
-126: ﾂ, -1;
-125: ﾃ, -1;
-124: ﾄ, -1;
-123: ﾅ, -1;
-122: ﾆ, -1;
-121: ﾇ, -1;
-120: ﾈ, -1;
-119: ﾉ, -1;
-118: ﾊ, -1;
-117: ﾋ, -1;
-116: ﾌ, -1;
-115: ﾍ, -1;
-114: ﾎ, -1;
-113: ﾏ, -1;
-112: ﾐ, -1;
-111: ﾑ, -1;
-110: ﾒ, -1;
-109: ﾓ, -1;
-108: ﾔ, -1;
-107: ﾕ, -1;
-106: ﾖ, -1;
-105: ﾗ, -1;
-104: ﾘ, -1;
-103: ﾙ, -1;
-102: ﾚ, -1;
-101: ﾛ, -1;
-100: ﾜ, -1;
-99: ﾝ, -1;
-98: ﾞ, -1;
-97: ﾟ, -1;
-96: ﾠ, -1;
-95: ﾡ, -1;
-94: ﾢ, -1;
-93: ﾣ, -1;
-92: ﾤ, -1;
-91: ﾥ, -1;
-90: ﾦ, -1;
-89: ﾧ, -1;
-88: ﾨ, -1;
-87: ﾩ, -1;
-86: ﾪ, -1;
-85: ﾫ, -1;
-84: ﾬ, -1;
-83: ﾭ, -1;
-82: ﾮ, -1;
-81: ﾯ, -1;
-80: ﾰ, -1;
-79: ﾱ, -1;
-78: ﾲ, -1;
-77: ﾳ, -1;
-76: ﾴ, -1;
-75: ﾵ, -1;
-74: ﾶ, -1;
-73: ﾷ, -1;
-72: ﾸ, -1;
-71: ﾹ, -1;
-70: ﾺ, -1;
-69: ﾻ, -1;
-68: ﾼ, -1;
-67: ﾽ, -1;
-66: ﾾ, -1;
-65: ﾿, -1;
-64: ￀, -1;
-63: ￁, -1;
-62: ￂ, -1;
-61: ￃ, -1;
-60: ￄ, -1;
-59: ￅ, -1;
-58: ￆ, -1;
-57: ￇ, -1;
-56: ￈, -1;
-55: ￉, -1;
-54: ￊ, -1;
-53: ￋ, -1;
-52: ￌ, -1;
-51: ￍ, -1;
-50: ￎ, -1;
-49: ￏ, -1;
-48: ￐, -1;
-47: ￑, -1;
-46: ￒ, -1;
-45: ￓ, -1;
-44: ￔ, -1;
-43: ￕ, -1;
-42: ￖ, -1;
-41: ￗ, -1;
-40: ￘, -1;
-39: ￙, -1;
-38: ￚ, -1;
-37: ￛ, -1;
-36: ￜ, -1;
-35: ￝, -1;
-34: ￞, -1;
-33: ￟, -1;
-32: ￠, -1;
-31: ￡, -1;
-30: ￢, -1;
-29: ￣, -1;
-28: ￤, -1;
-27: ￥, -1;
-26: ￦, -1;
-25: ￧, -1;
-24: ￨, -1;
-23: ￩, -1;
-22: ￪, -1;
-21: ￫, -1;
-20: ￬, -1;
-19: ￭, -1;
-18: ￮, -1;
-17: ￯, -1;
-16: ￰, -1;
-15: ￱, -1;
-14: ￲, -1;
-13: ￳, -1;
-12: ￴, -1;
-11: ￵, -1;
-10: ￶, -1;
-9: ￷, -1;
-8: ￸, -1;
-7: ￹, -1;
-6: ￺, -1;
-5: ￻, -1;
-4: ￼, -1;
-3: �, -1;
-2: ￾, -1;
-1: ￿, -1;
0:  , -1;
1: , -1;
2: , -1;
3: , -1;
4: , -1;
5: , -1;
6: , -1;
7: , -1;
8:, -1;
9: 	, -1;
10:
, -1;
11: , -1;
12: , -1;
, -1;
14: , -1;
15: , -1;
16: , -1;
17: , -1;
18: , -1;
19: , -1;
20: , -1;
21: , -1;
22: , -1;
23: , -1;
24: , -1;
25: , -1;
26: , -1;
27: , -1;
28: , -1;
29: , -1;
30: , -1;
31: , -1;
32:  , -1;
33: !, -1;
34: ", -1;
35: #, -1;
36: $, -1;
37: %, -1;
38: &, -1;
39: ', -1;
40: (, -1;
41: ), -1;
42: *, -1;
43: +, -1;
44: ,, -1;
45: -, -1;
46: ., -1;
47: /, -1;
48: 0, 0;
49: 1, 1;
50: 2, 2;
51: 3, 3;
52: 4, 4;
53: 5, 5;
54: 6, 6;
55: 7, 7;
56: 8, 8;
57: 9, 9;
58: :, -1;
59: ;, -1;
60: <, -1;
61: =, -1;
62: >, -1;
63: ?, -1;
64: @, -1;
65: A, 10;
66: B, 11;
67: C, 12;
68: D, 13;
69: E, 14;
70: F, 15;
71: G, 16;
72: H, 17;
73: I, 18;
74: J, 19;
75: K, 20;
76: L, 21;
77: M, 22;
78: N, 23;
79: O, 24;
80: P, 25;
81: Q, 26;
82: R, 27;
83: S, 28;
84: T, 29;
85: U, 30;
86: V, 31;
87: W, 32;
88: X, 33;
89: Y, 34;
90: Z, 35;
91: [, -1;
92: \, -1;
93: ], -1;
94: ^, -1;
95: _, -1;
96: `, -1;
97: a, 10;
98: b, 11;
99: c, 12;
100: d, 13;
101: e, 14;
102: f, 15;
103: g, 16;
104: h, 17;
105: i, 18;
106: j, 19;
107: k, 20;
108: l, 21;
109: m, 22;
110: n, 23;
111: o, 24;
112: p, 25;
113: q, 26;
114: r, 27;
115: s, 28;
116: t, 29;
117: u, 30;
118: v, 31;
119: w, 32;
120: x, 33;
121: y, 34;
122: z, 35;
123: {, -1;
124: |, -1;
125: }, -1;
126: ~, -1;
     */

//-------------------------------------------------------------------------////

    Character c;
//-------------------------------------------------------------------------////
    /*
          If the character does not have a numeric value, then -1 is returned.
      If the character has a numeric value that cannot be represented as a
      nonnegative integer (for example, a fractional value), then -2
      is returned.
     */
@Test
public void test08() {
    int i = 88888;
//    System.out.print((char) i + ", ");
    //如果字符中有一个不是非负整数的数值，则返回-2；
    System.out.print((char)-Math.PI);
    System.out.print(Character.getNumericValue(Integer.MIN_VALUE));
    System.out.print(Character.getNumericValue( '\u216C'));
}
//-------------------------------------------------------------------------////
    //http://blog.csdn.net/lipipifighting/article/details/47321853

    //关于Character的digit，forDigit，getNumericValue方法的一点理解
/*
Character类是一个包装类。
char这种数据类型是基于原始的Unicode编码的，储存一个char用16个bit，因此定义characters也是16位定长的实体集合。
Unicode编码标准发生了变化，数量级从\\uFFFF到了\\u10FFFF
对Unicode标准中的所有字符，16位已经是不够的了，即一部分字符并不能通过char来表示了。
我们称\\u0000 to \\uFFFF的字符集合，也就是用一个char能够表示的字符的集合，为the Basic Multilingual Plane (BMP)（基本字符）。
而称码点（code point）大于\\uFFFF的字符集合为supplementary characters（补充字符）。
为了解决补充字符表示的问题，首先想到的是基本数据类型int，一个int对象占了32bit，肯定可以表达所有的字符。
但是表示基本字符，只需要16位就够了，表示一个补充字符，也只需要21位，高位的11位都为0，很浪费空间呐！
这个时候可以考虑使用到Character这个包装类了
允许一个Character的对象包装一个或者两个基本类型为char的值，补充字符就拥有了两个基本类型为char的值。
其中，高位的char的取值范围是\\uD800--\\uDBFF（4*16*16种），低位的char的取值范围是\\uDC00--\\uDFFF（4*16*16种）。
4*16*16*4*16*16==0x10*16*16*16*16，可以完整表达所有补充字符。
Character有两种构造方法，它接受不同的参数类型。
一种是char，这样自然不能实现补充字符的构造Character
另一种就是int，一个int的范围是0x00000000-0xFFFFFFFF，可以拿任意一个字符的Unicode编码值来作为参数
几个比较难懂，又可能会用到的方法介绍：

1.public static int digit(char ch,int radix)

首先基数radix必须满足在开区间[Character.MIN_RADIX ,Character.MAX_RADIX ]之内，否则直接返回-1；
Character.MIN_RADIX = 2;Character.MAX_RADIX = 36，为什么是2和36呢？
然后ch（ch会被默认转化成Character）也必须是有效的，ch怎样才算有效呢？分以下几种情况：
1.isDigit(ch)==true，也就是new Character(ch).getType(ch)==DECIMAL_DIGIT_NUMBER，也就是可能是一个数字。
数字可以有很多，‘0’--‘9’：
'\\u0030' through '\\u0039', ISO-LATIN-1 digits ('0' through '9')
'\\u0660' through '\\u0669', Arabic-Indic digits
'\\u06F0' through '\\u06F9', Extended Arabic-Indic digits
'\\u0966' through '\\u096F', Devanagari digits
'\\uFF10' through '\\uFF19', Fullwidth digits
2.ch是大写字母'A'('\\u65')--'Z'('\\u90')或者小写字母'a'('\\u97')--'z'('\\u122')中的一个时，A/a对应是10，依次下去Z/z对应的就是35.
但是同时对应的那个数字不能大于或者等于radix，如果大于或者等于了radix还是返回-1，否则将数字返回
3.ch是全宽大写字母'A'('\\uFF21')--'Z'('\\uFF3A')或者全宽小写字母'a'('\\uFF41')--'z'('\\uFF5A')中的一个时，A/a对应是10，依次下去Z/z对应的就是35.
但是同时对应的那个数字不能大于或者等于radix，如果大于或者等于了radix还是返回-1，否则将数字返回
public static int digit(int codePoint,int radix)本质上是一样的，只是char现在已经不能表示所有的字符了，
有些补充字符只用字符的Unicode编码值，用一个int来表示了，所以对应也用用一个int表示一个字符来传参的方法
digit('A',16)和digit(65,16)的结果是一样的。(int)'A'==65
2.public static char forDigit(int digit,int radix)

首先基数radix有效（即在开区间[2,36]内）
然后digit要有效，即0<=digit<radix（好像多少多少进制一样，最高36进制，最低二进制）
0-9分别对应的'0'-'9'，10-35分别对应的小写的'a'-'z'
只有当radix和digit同时有效的时候才返回字符，否则返回'\\u0000'空字符
3.public static int getNumericValue(char ch)

Returns the int value that the specified Unicode character represents.
返回指定的Unicode字符表示的int值。
For example, the character '\\u216C' (the roman numeral fifty) will return an int with a value of 50.
例如，字符'\ u216C'(罗马数字50)将返回一个int 值50。
字符'0'-'9'就返回0-9
字符不管是'A'('\\u65')--'Z'('\\u90')，'a'('\\u97')--'z'('\\u122')，还是'A'('\\uFF21')--'Z'('\\uFF3A')，'a'('\\uFF41')--'z'('\\uFF5A')
都对应返回的是10-35
public static int getNumericValue(int codePoint)就是对应的用一个int表示一个字符来传参的方法
总结

简单讲其实就是，radix确定基数，确定是多少进制，从0到9，再从a到z（大小写不敏感），分别被视为0到9，10到35
像十六进制一样数字0到9表示了0-9，那9之后的10，11，12，13，14，15怎么表示呢，就把26个字母拿出来用啊。
最少是二进制（MIN_RADIX），最多是三十六（10+26=36）进制（MAX_RADIX）。
int digit(char ch,int radix)

是几进制中某一位上的字符（是数字或者字母）所对应的数字，digit('h',20)==17
char forDigit(int digit,int radix)

是几进制上数字应该用哪个字符（是数字或者字母）表示，forDigit(17,20)=='h'

 */
//-------------------------------------------------------------------------////
    //https://stackoverflow.com/questions/37428441/too-many-characters-in-character-literal-trying-to-check-if-my-value-is-not-wi

    @Test
    public void test09(){
        Character c = '\u0030';
        int value = c.charValue();
        System.out.println(value);
    }

//-------------------------------------------------------------------------////
}
