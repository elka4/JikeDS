package _01_Array.Two_Pointers_12;

import java.util.ArrayList;
import java.util.List;

/*
LeetCode – Binary Watch (Java)

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */
public class Binary_Watch {
    //Accepted Java Solution
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<String>();

        for(int i=0; i<12; i++){
            for(int j=0; j<60; j++){
                int total = countDigits(i)+countDigits(j);
                if(total==num){
                    String s="";
                    s+=i+":";

                    if(j<10){
                        s+="0"+j;
                    }else{
                        s+=j;
                    }

                    result.add(s);
                }
            }
        }

        return result;
    }

    public int countDigits(int num){
        int result=0;

        while(num>0){
            if((num&1)==1){
                result++;
            }

            num>>=1;
        }

        return result;
    }

//-------------------------------------------------------------------------/////////

    //Naive Solution

    public List<String> readBinaryWatch2(int num) {

        List<String> result = new ArrayList<String>();

        for(int i=0; i<=4; i++){
            int h=i;
            int m=num-i;

            ArrayList<ArrayList<Integer>> hSet = new ArrayList<ArrayList<Integer>>();
            subSet(h, 4, 1, new ArrayList<Integer>(), hSet);

            ArrayList<ArrayList<Integer>> mSet = new ArrayList<ArrayList<Integer>>();
            subSet(m, 6, 1, new ArrayList<Integer>(), mSet);

            ArrayList<String> hoursList = new ArrayList<String>();
            ArrayList<String> minsList = new ArrayList<String>();

            if(hSet.size()==0){
                hoursList.add("0");
            }else{
                hoursList.addAll(getTime(hSet, true));
            }


            if(mSet.size()==0){
                minsList.add("00");
            }else{
                minsList.addAll(getTime(mSet, false));
            }

            for(int x=0; x<hoursList.size(); x++){
                for(int y=0; y<minsList.size(); y++){
                    result.add(hoursList.get(x)+":"+minsList.get(y));
                }
            }

        }


        return result;
    }

    public ArrayList<String> getTime(ArrayList<ArrayList<Integer>> lists, boolean isHour){
        ArrayList<String> result = new ArrayList<String>();

        for(ArrayList<Integer> l : lists){
            int sum=0;
            for(int i: l){
                sum+= (1<<(i-1));
            }
            if(isHour && sum>=12)
                continue;

            if(!isHour&&sum>=60)
                continue;

            if(sum<10 && !isHour){
                result.add("0"+sum);
            }else{
                result.add(""+sum);
            }
        }

        return result;
    }

    public void subSet(int k, int m, int start, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> result){
        if(k==0){
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for(int i=start; i<=m; i++){
            temp.add(i);
            subSet(k-1, m, i+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }

//-------------------------------------------------------------------------/////////




//-------------------------------------------------------------------------/////////




//-------------------------------------------------------------------------/////////




//-------------------------------------------------------------------------/////////





}
