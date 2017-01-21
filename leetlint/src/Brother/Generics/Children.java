package Brother.Generics;

/**
 * Created by tzh on 1/21/17.
 */
public class Children {
    private String name;
    private Object age;

    @Override
    public String toString() {
        return "Children{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Children(String name, Object age) {
        this.name = name;
        this.age = age;
    }

    public Children(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAge() {
        return age;
    }

    public void setAge(Object age) {
        this.age = age;
    }
}
