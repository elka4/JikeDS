package com.java2novice.hashset;

import java.util.HashSet;

public class _09MylhsDeleteObject {
 
    public static void main(String a[]){
         
        HashSet<Price_3> lhs = new HashSet<Price_3>();
        lhs.add(new Price_3("Banana", 20));
        lhs.add(new Price_3("Apple", 40));
        lhs.add(new Price_3("Orange", 30));
        for(Price_3 pr:lhs){
            System.out.println(pr);
        }
        Price_3 key = new Price_3("Banana", 20);
        System.out.println("deleting key from set...");
        lhs.remove(key);
        System.out.println("Elements after delete:");
        for(Price_3 pr:lhs){
            System.out.println(pr);
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
