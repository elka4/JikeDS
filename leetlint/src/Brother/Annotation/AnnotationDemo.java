package Brother.Annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;


/**
 * Created by tzh on 1/21/17.
 */
@SuppressWarnings("all")
public class AnnotationDemo {
    public static void main(String[] args) {

    }

    @SuppressWarnings("deprecation")
    @Test
    public void Test01(){
        User user = new User("XB");
        user.print("sssss");
    }

    @Test
    public void Test02() throws ClassNotFoundException {
        User user = new User("XB");
        user.print("sssss");
        //使用反射解析注解
        //必须是runtime才行
        Class<?> c = Class.forName("Brother.Annotation.User");
        Annotation[] annotations = c.getAnnotations();    //得到全部注解
        System.out.println(annotations.length);
        for (Annotation a : annotations  ) {
            //System.out.println(a.getClass());
            //判断是否是指定的注解  375视频
            if(c.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation ma = (MyAnnotation)a;
                String name = ma.name();
                System.out.println(name);
            }
        }

    }


}
