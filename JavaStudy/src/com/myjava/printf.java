package com.myjava;

import org.junit.Test;

/**
 * Created by tzh on 3/2/17.
 */
public class printf {
    /**
     * 格式化输出 字符串
     * [*]左对齐,右补空格

     *
     * @param str
     * @param min_length : 最小输出长度
     * @return
     */
    public String formatLeftS(String str, int min_length) {
        String format = "%-" + (min_length < 1 ? 1 : min_length) + "s";
        return String.format(format, str);
    }

    /**
     * 格式化输出 整数
     * [*]右对齐,左补0

     *
     * @param num
     * @param min_length : 最小输出长度
     * @return
     */
    public String format0Right(long num, int min_length) {
        String format = "%0" + (min_length < 1 ? 1 : min_length) + "d";
        return String.format(format, num);
    }

    /**
     * 格式化输出 浮点数
     * [*]右对齐,左补0

     *
     * @param d
     * @param min_length : 最小输出长度
     * @param precision : 小数点后保留位数
     * @return
     */
    public String format0Right(double d, int min_length, int precision) {
        String format = "%0" + (min_length < 1 ? 1 : min_length) + "."
                + (precision < 0 ? 0 : precision) + "f";
        return String.format(format, d);
    }

    public void test(){
        //定义一些变量，用来格式化输出。
        double d = 345.678;
        String s = "你好！";
        int i = 1234;
        //"%"表示进行格式化输出，"%"之后的内容为格式的定义。
        System.out.printf("%f",d);//"f"表示格式化输出浮点数。
        System.out.println();
        System.out.printf("%9.2f",d);//"9.2"中的9表示输出的长度，2表示小数点后的位数。
        System.out.println();
        System.out.printf("%+9.2f",d);//"+"表示输出的数带正负号。
        System.out.println();
        System.out.printf("%-9.4f",d);//"-"表示输出的数左对齐（默认为右对齐）。
        System.out.println();
        System.out.printf("%+-9.3f",d);//"+-"表示输出的数带正负号且左对齐。
        System.out.println();
        System.out.printf("%d",i);//"d"表示输出十进制整数。
        System.out.println();
        System.out.printf("%o",i);//"o"表示输出八进制整数。
        System.out.println();
        System.out.printf("%x",i);//"d"表示输出十六进制整数。
        System.out.println();
        System.out.printf("%#x",i);//"d"表示输出带有十六进制标志的整数。
        System.out.println();
        System.out.printf("%s",s);//"d"表示输出字符串。
        System.out.println();
        System.out.printf("输出一个浮点数：%f，一个整数：%d，一个字符串：%s",d,i,s);
        //可以输出多个变量，注意顺序。
        System.out.println();
        System.out.printf("字符串：%2$s，%1$d的十六进制数：%1$#x",i,s);
        System.out.println();
        //"X$"表示第几个变量。
        System.out.printf("a=%d, b=%d,c =%d", 1, 2, 3);
    }

    public void col(){
        String s1 = "java";
        String s2 = "cpp";
        String s3 = "python";
        int x1 = 100;
        int x2 = 65;
        int x3 = 50;
        System.out.printf( "%-15s%03d%n", s1, x1);
        System.out.printf( "%-15s%03d%n", s2, x2);
        System.out.printf( "%-15s%03d%n", s3, x3);
        System.out.printf( "%-15s%04d%n", s1, x1);
        System.out.printf( "%-15s%04d%n", s2, x2);
        System.out.printf( "%-15s%04d%n", s3, x3);
    }

    @Test
    public void test01(){
        col();
    }
}