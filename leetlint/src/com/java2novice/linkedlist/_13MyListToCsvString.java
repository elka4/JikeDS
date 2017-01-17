package com.java2novice.linkedlist;

import java.util.LinkedList;
import java.util.List;
 /*Program: How to convert list to csv string format?

Description:
This example shows how to convert given list of strings to comma seperated values (csv) format.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/list-to-csv/#sthash.7mO6xcsp.dpuf*/
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
         
        List<String> li1 = new LinkedList<String>(){
            {
                this.add("animal");
                this.add("nuts");
                this.add("java");
            }
        };
        _13MyListToCsvString mtc = new _13MyListToCsvString();
        System.out.println(mtc.getListAsCsvString(li1));
        List<String> li2 = new LinkedList<String>(){
            {
                this.add("java");
                this.add("unix");
                this.add("c++");
            }
        };
        System.out.println(mtc.getListAsCsvString(li2));
    }
}
