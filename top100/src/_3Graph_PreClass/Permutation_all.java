package _3Graph_PreClass;

import org.junit.Test;

import java.util.ArrayList;

public class Permutation_all {

    public static ArrayList<String> getPerms(String str) {
        if (str == null) {
            return null;
        }
        ArrayList<String> permutations = new ArrayList<String>();
        if (str.length() == 0) { // base case
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0); // get the first character
        String remainder = str.substring(1); // remove the first character
        ArrayList<String> words = getPerms(remainder);
        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                String s = insertCharAt(word, first, j);
                permutations.add(s);
            }
        }
        return permutations;
    }

    public static String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }

    /////////////////////////////////////////////////////////////////

    public static ArrayList<String> getPerms2(String remainder) {
        int len = remainder.length();
        ArrayList<String> result = new ArrayList<String>();

		/* Base case. */
        if (len == 0) {
            result.add(""); // Be sure to return empty string!
            return result;
        }

        for (int i = 0; i < len; i++) {
			/* Remove char i and find permutations of remaining characters.*/
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);
            ArrayList<String> partials = getPerms2(before + after);

			/* Prepend char i to each permutation.*/
            for (String s : partials) {
                result.add(remainder.charAt(i) + s);
            }
        }

        return result;
    }


    ////////////////////////////////////////////////////////////////////

    public static void getPerms3(String prefix, String remainder, ArrayList<String> result) {
        if (remainder.length() == 0) {
            result.add(prefix);
        }
        int len = remainder.length();
        for (int i = 0; i < len; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, len);
            char c = remainder.charAt(i);
            getPerms3(prefix + c, before + after, result);
        }
    }

    public static ArrayList<String> getPerms3(String str) {
        ArrayList<String> result = new ArrayList<String>();
        getPerms3("", str, result);
        return result;
    }


    ///////////////////////////////////////////////////////

    @Test
    public void test01(){
        long start = System.currentTimeMillis();
        ArrayList<String> list = getPerms("abcdefgh");
        System.out.println("There are " + list.size() + " permutations.");
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(  ": " + time + "ms");


         start = System.currentTimeMillis();
         list = getPerms2("abcdefgh");
        System.out.println("There are " + list.size() + " permutations.");
         end = System.currentTimeMillis();
         time = end - start;
        System.out.println(  ": " + time + "ms");


         start = System.currentTimeMillis();
        list = getPerms3("abcdefgh");
        System.out.println("There are " + list.size() + " permutations.");
         end = System.currentTimeMillis();
         time = end - start;
        System.out.println(  ": " + time + "ms");



    }
}
