package Brother.Annotation;

/**
 * Created by tzh on 1/21/17.
 */
//使用自定义注解
//@MyAnnotation(name="XB", info = "WSDR")

@MyAnnotation(name = "XB", like = {"hehe", "泡妞","睡觉"}, sex = EnumSex.GG)
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
//@MyAnnotation(like = {""}, sex = new EnumSex("M")) String name
    public void setName( @MyAnnotation(like = {"hehe", "泡妞","睡觉"}, sex = EnumSex.GG) String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Deprecated
    public void print(String str){
        System.out.println(str);
    }


}
