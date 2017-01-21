package Brother.Reflection;

/**
 * Created by tzh on 1/20/17.
 */
public class Person {
    private  String name;
    private int age;
    private  char sex;

    public Person() {
        System.out.println("--无参构造方法--");
    }

    public Person(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        System.out.println("--有参构造方法--");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
