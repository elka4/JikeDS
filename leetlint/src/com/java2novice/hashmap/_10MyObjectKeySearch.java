package com.java2novice.hashmap;

import java.util.HashMap;
import java.util.Set;
 /*Program: How to find user defined objects as a key from HashMap?

Description:
Below example shows how to search user defined objects as a key from HashMap. 
You can achieve this by implementing equals and hashcode methods at the user defined objects.

- See more at: http://www.java2novice.com/java-collections-and-util/hashmap/find-object/#sthash.r8x3AG3C.dpuf*/
public class _10MyObjectKeySearch {
 
public static void main(String a[]){
         
        HashMap<Price_2, String> hm = new HashMap<Price_2, String>();
        hm.put(new Price_2("Banana", 20), "Banana");
        hm.put(new Price_2("Apple", 40), "Apple");
        hm.put(new Price_2("Orange", 30), "Orange");
        printMap(hm);
        Price_2 key = new Price_2("Banana", 20);
        System.out.println("Does key available? "+hm.containsKey(key));
    }
     
    public static void printMap(HashMap<Price_2, String> map){
         
        Set<Price_2> keys = map.keySet();
        for(Price_2 p:keys){
            System.out.println(p+"==>"+map.get(p));
        }
    }
}
 
class Price_2{
     
    private String item;
    private int Price_2;
     
    public Price_2(String itm, int pr){
        this.item = itm;
        this.Price_2 = pr;
    }
     
    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = Price_2*20;
        hashcode += item.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Price_2) {
            Price_2 pp = (Price_2) obj;
            return (pp.item.equals(this.item) && pp.Price_2 == this.Price_2);
        } else {
            return false;
        }
    }
     
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public int getPrice_2() {
        return Price_2;
    }
    public void setPrice_2(int Price_2) {
        this.Price_2 = Price_2;
    }
     
    public String toString(){
        return "item: "+item+"  Price_2: "+Price_2;
    }
}
