package com.java2novice.hashmap;

import java.util.HashMap;
import java.util.Set;
 
public class _11MyDeleteKeyObject {
 
    public static void main(String a[]){
         
        HashMap<Price_3, String> hm = new HashMap<Price_3, String>();
        hm.put(new Price_3("Banana", 20), "Banana");
        hm.put(new Price_3("Apple", 40), "Apple");
        hm.put(new Price_3("Orange", 30), "Orange");
        printMap(hm);
        Price_3 key = new Price_3("Banana", 20);
        System.out.println("Deleting key...");
        hm.remove(key);
        System.out.println("After deleting key:");
        printMap(hm);
    }
     
    public static void printMap(HashMap<Price_3, String> map){
         
        Set<Price_3> keys = map.keySet();
        for(Price_3 p:keys){
            System.out.println(p+"==>"+map.get(p));
        }
    }
}
 
class Price_3{
     
    private String item;
    private int Price_3;
     
    public Price_3(String itm, int pr){
        this.item = itm;
        this.Price_3 = pr;
    }
     
    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = Price_3*20;
        hashcode += item.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Price_3) {
            Price_3 pp = (Price_3) obj;
            return (pp.item.equals(this.item) && pp.Price_3 == this.Price_3);
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
    public int getPrice_3() {
        return Price_3;
    }
    public void setPrice_3(int Price_3) {
        this.Price_3 = Price_3;
    }
     
    public String toString(){
        return "item: "+item+"  Price_3: "+Price_3;
    }
}
