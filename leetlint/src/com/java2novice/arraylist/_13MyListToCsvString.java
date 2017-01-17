package com.java2novice.arraylist;

import java.util.ArrayList;
import java.util.List;
 //This example shows how to convert given list of strings to comma seperated values (csv) format.

public class _13MyListToCsvString {
 
    public String getListAsCsvString(List<String> list){
         
        StringBuilder sb = new StringBuilder();
        for(String str:list){
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }
     
    public static void main(String a[]){
         
        List<String> li1 = new ArrayList<String>(){
            {
                this.add("animal");
                this.add("nuts");
                this.add("java");
            }
        };
        _13MyListToCsvString mtc = new _13MyListToCsvString();
        System.out.println(mtc.getListAsCsvString(li1));
        List<String> li2 = new ArrayList<String>(){
            {
                this.add("java");
                this.add("unix");
                this.add("c++");
            }
        };
        System.out.println(mtc.getListAsCsvString(li2));
    }
}
