package J_7_Two_Pointers.all;

/** 415 Valid Palindrome
 * Created by tianhuizhu on 6/28/17.
 */
public class _415_Valid_Palindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int front = 0;
        int end = s.length() - 1;
        while (front < end) {
            // nead to check range of a/b
            while (front < s.length() && !isvalid(s.charAt(front))){
                front++;
            }

            if (front == s.length()) { // for emtpy string “.,,,”
                return true;
            }

            // same here, need to check border of a,b
            while (end >= 0 && ! isvalid(s.charAt(end))) {
                end--;
            }

            if (Character.toLowerCase(s.charAt(front))
                    != Character.toLowerCase(s.charAt(end))) {
                break;
            } else {
                front++;
                end--;
            }
        }

        return end <= front;
    }

    private boolean isvalid (char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }



}
