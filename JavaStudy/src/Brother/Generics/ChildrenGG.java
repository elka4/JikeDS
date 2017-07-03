package Brother.Generics;

/**使用泛型定义, 定义泛型上限
 * T表示类型，泛指所有类型
 * Created by tzh on 1/21/17.
 */
public class ChildrenGG<T extends Number> {
    private String name;
    private T age;

    @Override
    public String toString() {
        return "Children{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public ChildrenGG(String name, T age) {
        this.name = name;
        this.age = age;
    }

    public ChildrenGG() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getAge() {
        return age;
    }

    public void setAge(T age) {
        this.age = age;
    }
}
