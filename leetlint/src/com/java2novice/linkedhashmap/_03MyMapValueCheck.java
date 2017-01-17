package com.java2novice.linkedhashmap;

import java.util.LinkedHashMap;

public class _03MyMapValueCheck {
 
    public static void main(String a[]){
         
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("one", "This is first element");
        lhm.put("two", "This is second element");
        lhm.put("four", "Element inserted at 3rd position");
        System.out.println("Map contains value 'This is first element'? "
                +lhm.containsValue("This is first element"));
    }
}

/*Program: How to check whether the value exists or not in a LinkedHashMap?

Description:
Below example helps us to find whether a LinkedHashMap object contains
 the given value or not.
 The method containsValue() returns boolean value true if the value exists.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedhashmap/value-exist/#sthash.P6wT8ANz.dpuf*/