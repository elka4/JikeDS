package java2novice.treemap;

import java.util.TreeMap;
/*Program: How to search a key in TreeMap?

Description:
Below example shows how to find out whether a specific key exists in the TreeMap or not. 
By using containsKey() method you can find out keys presence in the TreeMap.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/key-search/#sthash.KLoW77bX.dpuf*/
public class _04MyTreeMapKeySearch {
     
    public static void main(String a[]){
        TreeMap<String, String> hm = new TreeMap<String, String>();
         
        //add key-value pair to TreeMap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        if(hm.containsKey("first")){
            System.out.println("The TreeMap contains key first");
        } else {
            System.out.println("The TreeMap does not contains key first");
        }
        if(hm.containsKey("fifth")){
            System.out.println("The TreeMap contains key fifth");
        } else {
            System.out.println("The TreeMap does not contains key fifth");
        }
    }
}
