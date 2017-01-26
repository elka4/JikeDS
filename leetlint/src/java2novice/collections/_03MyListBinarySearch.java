package java2novice.collections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class _03MyListBinarySearch {
 
    public static void main(String a[]){
         
        List<Emp_3> Emp_3List = new ArrayList<Emp_3>();
        Emp_3List.add(new Emp_3(12,"Dinesh",50000));
        Emp_3List.add(new Emp_3(146,"Tom",20000));
        Emp_3List.add(new Emp_3(201,"John",40000));
        Emp_3List.add(new Emp_3(302,"Krish",44500));
        Emp_3List.add(new Emp_3(543,"Abdul",10000));
         
        Emp_3 searchKey = new Emp_3(201,"John",40000);
        int index = Collections.binarySearch(Emp_3List, searchKey, new Emp_3Comp());
        System.out.println("Index of the searched key: "+index);
    }
}
 
class Emp_3Comp implements Comparator<Emp_3>{
 
    public int compare(Emp_3 e1, Emp_3 e2) {
        if(e1.getEmp_3Id() == e2.getEmp_3Id()){
            return 0;
        } else {
            return -1;
        }
    }
}
 
class Emp_3 {
     
    private int Emp_3Id;
    private String Emp_3Name;
    private int Emp_3Sal;
     
    public Emp_3(int id, String name, int sal){
        this.Emp_3Id = id;
        this.Emp_3Name = name;
        this.Emp_3Sal = sal;
    }
     
    public int getEmp_3Id() {
        return Emp_3Id;
    }
     
    public void setEmp_3Id(int Emp_3Id) {
        this.Emp_3Id = Emp_3Id;
    }
     
    public String getEmp_3Name() {
        return Emp_3Name;
    }
     
    public void setEmp_3Name(String Emp_3Name) {
        this.Emp_3Name = Emp_3Name;
    }
     
    public int getEmp_3Sal() {
        return Emp_3Sal;
    }
    public void setEmp_3Sal(int Emp_3Sal) {
        this.Emp_3Sal = Emp_3Sal;
    }
     
    public String toString(){
        return Emp_3Id+" : "+Emp_3Name+" : "+Emp_3Sal;
    }
}
