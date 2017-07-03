package java2novice.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Set;
 
public class _08MyDeleteKeyObject {
 
    public static void main(String a[]){
         
        LinkedHashMap<Price_4, String> hm = new LinkedHashMap<Price_4, String>();
        hm.put(new Price_4("Banana", 20), "Banana");
        hm.put(new Price_4("Apple", 40), "Apple");
        hm.put(new Price_4("Orange", 30), "Orange");
        printMap(hm);
        Price_4 key = new Price_4("Banana", 20);
        System.out.println("Deleting key...");
        hm.remove(key);
        System.out.println("After deleting key:");
        printMap(hm);
    }
     
    public static void printMap(LinkedHashMap<Price_4, String> map){
         
        Set<Price_4> keys = map.keySet();
        for(Price_4 p:keys){
            System.out.println(p+"==>"+map.get(p));
        }
    }
}
 
class Price_4{
     
    private String item;
    private int Price_4;
     
    public Price_4(String itm, int pr){
        this.item = itm;
        this.Price_4 = pr;
    }
     
    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = Price_4*20;
        hashcode += item.hashCode();
        return hashcode;
    }
     
    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof Price_4) {
            Price_4 pp = (Price_4) obj;
            return (pp.item.equals(this.item) && pp.Price_4 == this.Price_4);
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
    public int getPrice_4() {
        return Price_4;
    }
    public void setPrice_4(int Price_4) {
        this.Price_4 = Price_4;
    }
     
    public String toString(){
        return "item: "+item+"  Price_4: "+Price_4;
    }
}
