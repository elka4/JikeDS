package Brother.Introspector;

/**
 * Created by tzh on 1/21/17.
 */
public class Introspector {
    public static void main (String[] Args) {
        //Dog dog = new Dog();//使用者与被使用者耦合

        //加大code难度来降低耦合

        //工厂模式

        Dog dog = DogFactory.getDog("dog");
        System.out.println(dog);



    }


}
