package com.java2novice.linkedhashmap;

import java.util.LinkedHashMap;
/*Program: Basic LinkedHashMap Operations.

Description:
Here you can find example code for basic LinkedHashMap operation. 
It shows how to create object for LinkedHashMap, adding elements, 
getting size, checking empty or not, remove, etc.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedhashmap/basic-operations/#sthash.UiYAbtCW.dpuf*/
public class _01BasicLinkedHashMap {
 
    public static void main(String a[]){
         
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("one", "This is first element");
        lhm.put("two", "This is second element");
        lhm.put("four", "this element inserted at 3rd position");
        System.out.println(lhm);
        System.out.println("Getting value for key 'one': "+lhm.get("one"));
        System.out.println("Size of the map: "+lhm.size());
        System.out.println("Is map empty? "+lhm.isEmpty());
        System.out.println("Contains key 'two'? "+lhm.containsKey("two"));
        System.out.println("Contains value 'This is first element'? "
                            +lhm.containsValue("This is first element"));
        System.out.println("delete element 'one': "+lhm.remove("one"));
        System.out.println(lhm);
    }
}
