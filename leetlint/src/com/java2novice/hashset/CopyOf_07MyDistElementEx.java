package com.java2novice.hashset;

import java.util.HashSet;
/*Program: How to eliminate duplicate user defined objects from HashSet?

Description:
Below example shows how to avoid duplicate user defined objects from HashSet. 
You can achieve this by implementing equals and hashcode methods at the user 
defined objects.

- See more at: http://www.java2novice.com/java-collections-and-util/hashset/duplicate/#sthash.GvINI7t7.dpuf*/
public class CopyOf_07MyDistElementEx {
 
    public static void main(String a[]){
         
        HashSet<Price_2_Copy> lhm = new HashSet<Price_2_Copy>();
        
        Price_2_Copy p1 = new Price_2_Copy("Banana", 20);
        Price_2_Copy p2 = new Price_2_Copy("Apple", 40);
        Price_2_Copy p3 = new Price_2_Copy("Orange", 30);
        Price_2_Copy p4 = new Price_2_Copy("Hehe", 50);
        
        lhm.add(p1);
        lhm.add(p4);
        lhm.add(p3);
        lhm.add(p2);
        System.out.println(lhm);
        
        /*Price_2_Copy duplicate = new Price_2_Copy("Banana", 20);
        System.out.println("inserting duplicate object...");
        lhm.add(duplicate);
        System.out.println("After insertion:");
        for(Price_2_Copy pr:lhm){
            System.out.println(pr);
        }*/
        
        System.out.println(p2.hashCode());
        System.out.println(p1.hashCode());
        System.out.println(p4.hashCode());
        System.out.println(p3.hashCode());
        //System.out.println(lhm.get("mango").hashCode()%16);
    }
}
 
class Price_2_Copy{
     
    private String item;
    private int Price_2_Copy;
     
    public Price_2_Copy(String itm, int pr){
        this.item = itm;
        this.Price_2_Copy = pr;
    }
     
    public int hashCode(){
       // System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = Price_2_Copy*20;
        hashcode += item.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Price_2_Copy) {
            Price_2_Copy pp = (Price_2_Copy) obj;
            return (pp.item.equals(this.item) && pp.Price_2_Copy == this.Price_2_Copy);
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
    public int getPrice_2_Copy() {
        return Price_2_Copy;
    }
    public void setPrice_2_Copy(int Price_2_Copy) {
        this.Price_2_Copy = Price_2_Copy;
    }
     
    public String toString(){
        return "item: "+item+"  Price_2_Copy: "+Price_2_Copy;
    }
}
