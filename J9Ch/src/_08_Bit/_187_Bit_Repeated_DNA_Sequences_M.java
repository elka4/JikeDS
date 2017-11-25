package _08_Bit;
import java.util.*;
import org.junit.Test;

//  187. Repeated DNA Sequences
//  https://leetcode.com/problems/repeated-dna-sequences/description/
//  9:
//
public class _187_Bit_Repeated_DNA_Sequences_M {
//------------------------------------------------------------------------------
    //1
    //  7 lines simple Java, O(n)
    public List<String> findRepeatedDnaSequences1(String s) {
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }
//------------------------------------------------------------------------------
    //2
    //Clean Java solution (hashmap + bits manipulation)
    public List<String> findRepeatedDnaSequences2(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
//------------------------------------------------------------------------------
    //3
    //Short Java "rolling-hash" solution
/*    The idea is to use rolling hash technique or in case of string search also known as Rabin-Karp algorithm. As our alphabet A consists of only 4 letters we can be not afraid of collisions. The hash for a current window slice could be found in a constant time by subtracting the former first character times size of the A in the power of 9 and updating remaining hash by the standard rule: hash = hash*A.size() + curr_char.

    Check out the Java code below.

    Hope it helps!*/

    public class Solution3 {
        private final Map<Character, Integer> A = new HashMap<>();
         { A.put('A',0); A.put('C',1); A.put('G',2); A.put('T',3); }
        private final int A_SIZE_POW_9 = (int) Math.pow(A.size(), 9);

        public List<String> findRepeatedDnaSequences(String s) {
            Set<String> res = new HashSet<>();
            Set<Integer> hashes = new HashSet<>();
            for (int i = 0, rhash = 0; i < s.length(); i++) {
                if (i > 9) rhash -= A_SIZE_POW_9 * A.get(s.charAt(i-10));
                rhash = A.size() * rhash + A.get(s.charAt(i));
                if (i > 8 && !hashes.add(rhash)) res.add(s.substring(i-9,i+1));
            }
            return new ArrayList<>(res);
        }
    }
//------------------------------------------------------------------------------
    //4
    //  Easy understand and straightforward java solution
    public class Solution4 {
        public List<String> findRepeatedDnaSequences(String s) {
            List<String> res = new ArrayList<String>();
            Set<String> resset = new HashSet<String>();
            if(s == null || s.length() <= 10){
                return res;
            }
            Set<String> set = new HashSet<String>();
            int len = s.length();
            for(int i = 0; i <= len - 10; i++){
                String sub = s.substring(i, i + 10);
                if(!set.add(sub)){
                    resset.add(sub);
                }
            }
            res.addAll(resset);
            return res;
        }
    }
//------------------------------------------------------------------------------
    //5
//    my idea is to get all the possible 10 letter long sequences and put them into set, it the operation failed, it means there are duplicates. so put the sequence into another set("AAAAAAAAAAAA" could have three "AAAAAAAAAA" sequences, so this set will remove the duplicates) then add all the set to the final list.


    //  Simple java solution with HashSet and Bit Manipulation Optimized O(n)
/*    Use bit manipulation to avoid String.substring from creating object repeatedly.
    Mask with bitwise and to avoid loop each 10-char substring.
    Let me know if anywhere unclear. Thx

    Hope it helps. :)*/

    public class Solution5 {
        public List<String> findRepeatedDnaSequences(String s) {
            int len = s.length();
            if (len < 11) return new ArrayList<>();

            // 00, 01, 10, 11 respectively for A, C, G, T
            Set<Integer> seen = new HashSet<>();
            Set<String> repeated = new HashSet<>();
            int[] table = new int[26];
            table['C' - 'A'] = 1;
            table['G' - 'A'] = 2;
            table['T' - 'A'] = 3;

            int cur = 0;
            int mask = 0x000fffff;
            for (int i = 0; i < len; ++i) {
                cur <<= 2;
                cur |= table[s.charAt(i) - 'A'];
                if (i < 9) continue; //initialize
                cur &= mask;
                if (!seen.add(cur)) { //return true if not existed
                    repeated.add(s.substring(i-9, i+1));
                }
            }

            return new ArrayList<>(repeated);

        }
    }

//------------------------------------------------------------------------------
    //6
// jiuzhang
public class Jiuzhang {
    public int encode(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                sum = sum * 4;
            } else if (s.charAt(i) == 'C') {
                sum = sum * 4 + 1;
            } else if (s.charAt(i) == 'G') {
                sum = sum * 4 + 2;
            } else {
                sum = sum * 4 + 3;
            }
        }
        return sum;
    }
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<Integer> hash = new HashSet<Integer>();
        HashSet<String> dna = new HashSet<String>();
        for (int i = 9; i < s.length(); i++) {
            String subString = s.substring(i - 9, i + 1);
            int encoded = encode(subString);
            if (hash.contains(encoded)) {
                dna.add(subString);
            } else {
                hash.add(encoded);
            }
        }
        List<String> result = new ArrayList<String>();
        for (String d: dna) {
            result.add(d);
        }
        return result;
    }
}

//------------------------------------------------------------------------------
    //7
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();

        int len = s.length();
        if (len < 10) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> temp = new HashSet<Integer>();
        Set<Integer> added = new HashSet<Integer>();

        int hash = 0;
        for (int i = 0; i < len; i++) {
            if (i < 9) {
                //each ACGT fit 2 bits, so left shift 2
                hash = (hash << 2) + map.get(s.charAt(i));
            } else {
                hash = (hash << 2) + map.get(s.charAt(i));
                //make length of hash to be 20
                hash = hash &  (1 << 20) - 1;

                if (temp.contains(hash) && !added.contains(hash)) {
                    result.add(s.substring(i - 9, i + 1));
                    added.add(hash); //track added
                } else {
                    temp.add(hash);
                }
            }

        }

        return result;
    }


//------------------------------------------------------------------------------
    //8
    //Clean Java solution (hashmap + bits manipulation)

    public List<String> findRepeatedDnaSequences02(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
//------------------------------------------------------------------------------
    //9
    //7 lines simple Java, O(n)
    public List<String> findRepeatedDnaSequences3(String s) {
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }
//------------------------------------------------------------------------------

}
/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 */

/*
LeetCode – Repeated DNA Sequences (Java)

Problem

All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example, given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
return: ["AAAAACCCCC", "CCCCCAAAAA"].

Java Solution

The key to solve this problem is that each of the 4 nucleotides can be stored in 2 bits. So the 10-letter-long sequence can be converted to 20-bits-long integer. The following is a Java solution. You may use an example to manually execute the program and see how it works.
 */

//all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
