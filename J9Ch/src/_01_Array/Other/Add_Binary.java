package _01_Array.Other;


/*
LeetCode – Add Binary (Java)

Given two binary strings, return their sum (also a binary string).

For example, a = "11", b = "1", the return is "100".
 */


public class Add_Binary {

    //Very simple, nothing special. Note how to convert a character to an int.
    public String addBinary(String a, String b) {
        if(a==null || a.length()==0)
            return b;
        if(b==null || b.length()==0)
            return a;

        int pa = a.length()-1;
        int pb = b.length()-1;

        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while(pa >= 0 || pb >=0){
            int va = 0;
            int vb = 0;

            if(pa >= 0){
                va = a.charAt(pa)=='0'? 0 : 1;
                pa--;
            }
            if(pb >= 0){
                vb = b.charAt(pb)=='0'? 0: 1;
                pb--;
            }

            int sum = va + vb + flag;
            if(sum >= 2){
                sb.append(String.valueOf(sum-2));
                flag = 1;
            }else{
                flag = 0;
                sb.append(String.valueOf(sum));
            }
        }

        if(flag == 1){
            sb.append("1");
        }

        String reversed = sb.reverse().toString();
        return reversed;
    }


//////////////////////////////////////////////////////////////////////

    //UPDATE: we can simply the method above.
    public String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int i=a.length()-1;
        int j=b.length()-1;

        int carry = 0;

        while(i>=0 || j>=0){
            int sum=0;

            if(i>=0 && a.charAt(i)=='1'){
                sum++;
            }

            if(j>=0 && b.charAt(j)=='1'){
                sum++;
            }

            sum += carry;

            if(sum>=2){
                carry=1;
            }else{
                carry=0;
            }

            sb.insert(0,  (char) ((sum%2) + '0'));

            i--;
            j--;
        }

        if(carry==1)
            sb.insert(0, '1');

        return sb.toString();
    }


////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






}
