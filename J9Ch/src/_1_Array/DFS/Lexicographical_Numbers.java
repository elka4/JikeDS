package _1_Array.DFS;
import java.util.*;

/*
LeetCode – Lexicographical Numbers (Java)

Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.


 */


// The basic idea is to find the next number to add.
// 这个方法太长了，看后面的leetcode的比较好
public class Lexicographical_Numbers {
    public List<Integer> lexicalOrder(int n) {
        int c=0;
        int t=n;
        while(t>0){
            c++;
            t=t/10;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        char[] num = new char[c];

        helper(num, 0, n, result);

        return result;
    }

    public void helper(char[] num, int i, int max, ArrayList<Integer> result){
        if(i==num.length){
            int val = convert(num);
            if(val <=max)
                result.add(val);
            return;
        }

        if(i==0){
            for(char c='1'; c<='9'; c++){
                num[i]=c;
                helper(num, i+1, max, result);
            }
        }else{
            num[i]='a';
            helper(num, num.length, max, result);

            for(char c='0'; c<='9'; c++){
                num[i]=c;
                helper(num, i+1, max, result);
            }
        }

    }

    private int convert(char[] arr){
        int result=0;
        for(int i=0; i<arr.length; i++){
            if(arr[i]>='0'&&arr[i]<='9')
                result = result*10+arr[i]-'0';
            else
                break;
        }
        return result;
    }



//////////////////////////////////////////////////////////////////////////////

    //Java O(n) time, O(1) space iterative solution 130ms
    //这个方法是不是greedy？存粹的按照各种数学情况做做计算
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;

        for (int i = 1; i <= n; i++) {
            list.add(curr);

            //以下code就是for循环中每次改变curr，好在下一轮加入list
            /*
            三个条件
            第一，*10
            第二，非9结尾的情况
            第三，9结尾的情况
             */

            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {

                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }

        }
        return list;
    }


/*
The basic idea is to find the next number to add.
Take 45 for example: if the current number is 45, the next one will be 450 (450 == 45 * 10)(if 450 <= n), or 46 (46 == 45 + 1) (if 46 <= n) or 5 (5 == 45 / 10 + 1)(5 is less than 45 so it is for sure less than n).
We should also consider n = 600, and the current number = 499, the next number is 5 because there are all "9"s after "4" in "499" so we should divide 499 by 10 until the last digit is not "9".
It is like a tree, and we are easy to get a sibling, a left most child and the parent of any node.
 */



//////////////////////////////////////////////////////////////////////////////

//    Simple Java DFS Solution

/*
The idea is pretty simple. If we look at the order we can find out we just keep adding digit from 0 to 9 to every digit and make it a tree.
Then we visit every node in pre-order.
       1        2        3    ...
      /\        /\       /\
   10 ...19  20...29  30...39   ....

 */

/*
这个做法也不错，简洁



 */
    public class Solution {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            for(int i=1;i<10;++i){
                dfs(i, n, res);
            }
            return res;
        }

        public void dfs(int cur, int n, List<Integer> res){
            if(cur>n)return;

            res.add(cur);

            for(int i=0;i<10;++i){
                //if(10*cur+i>n)  return;
                dfs(10*cur+i, n, res);
            }
        }
    }



//////////////////////////////////////////////////////////////////////////////

    //Recursive Java Solution using pre-order traversal

/*    The big idea is Tree's pre-order traversal, which means we first output the root, and then its left and right child, and we cannot output right child until we finishing outputting all nodes on the left branch.
    For this problem, a dummy node is the top root, and its children are 1 through 9; for node 1, its children are 10, 100, 1000 and so on; for node 10, its children are 11 through 19.
    Notes that we use (i + 1 <= (i / 10) * 10 + 9) to restrict the range of children.*/


    /*
    这个方法挺好，容易理解，dfs过程中其实是分两种处理
    一种是i*10
    一种是i+1

    注意i+1的条件

    因为是
     */

    public class Solution5 {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<>();
            lexicalOrderHelper(res, 1, n);
            return res;
        }

        private void lexicalOrderHelper(List<Integer> res, int i, int n) {
            if(i > n) return;

            res.add(i);

            lexicalOrderHelper(res, i * 10, n);

            if(i + 1 <= (i / 10) * 10 + 9) lexicalOrderHelper(res, i + 1, n);

            else return;
        }
    }






//////////////////////////////////////////////////////////////////////////////








//////////////////////////////////////////////////////////////////////////////








//////////////////////////////////////////////////////////////////////////////









}
