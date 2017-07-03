package Brother.RegularExpression;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tzh on 1/21/17.
 */
public class RegexDemo {


    public static void main(String[] args){


    }
    @Test
    public void test01(){
        String s = "0487561";
        //
        char[] cs = s.toCharArray();
        boolean flag = true;
        for (int i = 0; i < cs.length; i++) {
            if(!(cs[i] >= '0' && cs[i] <= '9')) {
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.println("全是数字");
        } else{
            System.out.println("不全是数字");
        }

        //使用正则表达式进行验证
        if (s.matches("\\d+")){
            System.out.println("是数字");
        } else {
            System.out.println("不是数字");

        }
    }

    @Test
    public void test02(){
        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaaab");
        boolean b = m.matches();
        System.out.println(b);

    }

    @Test
    public void test03(){
        String info = "小白是个:高端大气上档次,低调奢华有内涵的淫";
        Pattern p = Pattern.compile("\\p{Punct}");
        String[] str = p.split(info);
        for (String s : str ) {
            System.out.println(s);
        }
        String email = "xiaobai@qq.com";
        Pattern p2 = Pattern.compile("\\w+@\\w+.[a-zA-Z]+");
        Matcher m = p2.matcher(email);
        if(m.matches()){
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");
        }

    }
    @Test
    public void test04(){
        String date = "2013/11/05";
        Pattern p3 = Pattern.compile("/");
        //
        Matcher m = p3.matcher(date);
        String s = m.replaceAll("-");
        System.out.println(s);

    }
    @Test
    public void test05(){
        //phone
        String phoneNumber = "010-1234567";
        boolean b = phoneNumber.matches("\\d{3,4}-\\d{7,8}");
        System.out.println(b);
    }
    @Test
    public void test06(){
        //cell phone
        String phoneNumber = "13602407316";
        boolean b = phoneNumber.matches("[1][3-9]\\d{9}");
        System.out.println(b);
    }
    @Test
    public void test07(){
        //user name, start with letter...
        String userName = "xb1314";
        boolean b = userName.matches("[a-zA-Z]+[\\w+_]*");
        System.out.println(b);
    }

    @Test
    public void test08(){
        //IP
        String userName = "20.10.20.123";
        boolean b = userName.matches("\\d{1,3}.\\{1,3}.\\d{1,3}");
        System.out.println(b);
    }

    @Test
    public void test09(){
        //IP
        String address = "http://java.lamp.net";
        boolean b = address.matches("http://\\w+.\\w+.\\S*");
        System.out.println(b);
    }

    @Test
    public void test10(){
        //IP
        String age = "18";
        boolean b = age.matches("\\d{1,2}");
        System.out.println(b);

        String price = "19.8";
        boolean b2 = price.matches("\\d+.\\d+");
        System.out.println(b2);
    }

}
